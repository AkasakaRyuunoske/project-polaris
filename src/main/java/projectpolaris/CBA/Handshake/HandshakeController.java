package projectpolaris.CBA.Handshake;

import jakarta.annotation.Nullable;
import lombok.extern.log4j.Log4j2;
import org.bouncycastle.operator.OperatorCreationException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import projectpolaris.CBA.SecurityServices.HandshakePostman;
import projectpolaris.Messaging.KafkaConfigs;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/handshake")
public class HandshakeController {

    @Autowired
    CertificationGenerator certificationGenerator;

    @Autowired
    Camellia camellia;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    KafkaConfigs kafkaConfigs;

    @Autowired
    HandshakePostman handshakePostman;

    // Handshake process as INITIATOR
    @GetMapping("/start-handshake") //#todo for testing purposes is GET, must be changed to POST later
    public ResponseEntity<String> initiateHandshake() {
        kafkaTemplate.send(kafkaConfigs.getSecurity(), "[HANDSHAKE CONTROLLER] Handshake Requested....");
        log.info("Handshake started...");

        // #todo 1. add a way to know whom to call
        Map<String, String> result = handshakePostman.contact("http://localhost:8090/handshake/start-handshake", "Nee");

        if (result.get("Response Message") != null && result.get("Response Message").equals("Nee Nee")) {
            kafkaTemplate.send(kafkaConfigs.getSecurity(), "[HANDSHAKE CONTROLLER] Nee Nee received, handshake proceeds");
            log.info("Nee Nee");

            proceedHandshake_SendChest();
        }

        return new ResponseEntity<>(result.get("Body"), HttpStatus.ACCEPTED);
    }

    @PostMapping("/proceed-handshake/send_chest")
    private ResponseEntity<String> proceedHandshake_SendChest() {
        // Log about
        log.info("Nee Nee Neeee");
        kafkaTemplate.send(kafkaConfigs.getSecurity(), "[HANDSHAKE CONTROLLER] Sending Chest...");

        // Certification Generation
        X509Certificate certificate;

        certificate = certificationGenerator.generateCertification();

        String salt = BCrypt.gensalt();
        String hashedMessage = BCrypt.hashpw(certificate.getPublicKey().toString() + certificate, salt);

        // Creating and populating request's body
        Map<String, String> chest = new HashMap<>();

        chest.put("PK", certificate.getPublicKey().toString());
        chest.put("Certificate", certificate.toString());
        chest.put("HASH", hashedMessage);
        chest.put("salt", salt);

        // #todo 1. add a way to know whom to call
        Map<String, String> result_chest = handshakePostman.contact("http://localhost:8090/handshake/proceed-handshake/get_chest", chest);

        // Longing result's contents both in logs and in Kafka
        log.info("result[PK: IV]: " + Arrays.toString(Base64.getDecoder().decode(result_chest.get("PK: IV"))));
        log.info("result[PK: SecretKeySpec]: " + Arrays.toString(Base64.getDecoder().decode(result_chest.get("PK: SecretKeySpec"))));

        log.info("result[Certificate]: " + result_chest.get("Certificate"));
        log.info("result[HASH]: " + result_chest.get("HASH"));
        log.info("result[salt]: " + result_chest.get("salt"));

        kafkaTemplate.send(kafkaConfigs.getSecurity(), "[HANDSHAKE CONTROLLER] Chest Received:");
        kafkaTemplate.send(kafkaConfigs.getSecurity(), "[HANDSHAKE CONTROLLER] Certificate" + result_chest.get("Certificate"));
        kafkaTemplate.send(kafkaConfigs.getSecurity(), "[HANDSHAKE CONTROLLER] HASH" + result_chest.get("HASH"));
        kafkaTemplate.send(kafkaConfigs.getSecurity(), "[HANDSHAKE CONTROLLER] salt" + result_chest.get("salt"));

        if (!result_chest.isEmpty() && !result_chest.get("HASH").isEmpty()) {
            boolean checkpw_result = BCrypt.checkpw(result_chest.get("PK") + result_chest.get("Certificate"), result_chest.get("HASH"));
            log.info("checkpw result: " + checkpw_result);
            kafkaTemplate.send(kafkaConfigs.getSecurity(), "[HANDSHAKE CONTROLLER] checkpw result: " + checkpw_result);

            if (checkpw_result) {
                kafkaTemplate.send(kafkaConfigs.getSecurity(), "[HANDSHAKE CONTROLLER] Hashes do match indeed ");
                proceedHandshake_SendEncryptedMessage(result_chest);
            } else {
                kafkaTemplate.send(kafkaConfigs.getSecurity(), "[HANDSHAKE CONTROLLER] Hashes do not match. Handshake stopped.");
                return new ResponseEntity<>("Hashes do not match.", HttpStatus.BAD_REQUEST);
            }

        }

        return new ResponseEntity<>("Unexpected Server side error.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Map<String, String>> proceedHandshake_SendEncryptedMessage(@RequestBody Map<String, String> chest) {
        kafkaTemplate.send(kafkaConfigs.getErrorsSecurity(), "[HANDSHAKE CONTROLLER] Sending encrypted message encountered errors due to poor coding xdd");

        camellia.generateSymmetricKeys(Base64.getDecoder().decode(chest.get("PK: IV")),
                Base64.getDecoder().decode(chest.get("PK: SecretKeySpec")));

        log.info("[Camellia] IV: " + Base64.getEncoder().encodeToString(camellia.getIvParameterSpec().getIV()));
        log.info("[Camellia] SecretKeySpec: " + Base64.getEncoder().encodeToString(camellia.getSecretKeySpec().getEncoded()));

        String plainMessage = "The Dying Message";
        String encryptedMessage = camellia.enchant(plainMessage);

        Map<String, String> encryptedMessagePayload = new HashMap<>();
        encryptedMessagePayload.put("EncryptedMessage", encryptedMessage);

        // #todo 1. add a way to know whom to call
        Map<String, String> result = handshakePostman.contact("http://localhost:8090/handshake/proceed-handshake/get_encrypted_message", encryptedMessagePayload);

        log.info("result: " + result.get("ACK"));

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    // Handshake process as RECEIVER
    private ResponseEntity<String> respondToHandshake_GetChest(@RequestBody String message_in) {
        if (message_in.equals("Nee")) {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            String message_out = "Nee Nee";

            HttpEntity<String> httpEntity = new HttpEntity<>(message_out, headers);

            // #todo 1. add a way to know whom to call
            // #todo 2. put all of this logic into a dedicated service
            String uri = "http://localhost:8090/handshake/start-handshake";

            RestTemplate restTemplate = new RestTemplate();
            Map<String, String> result = restTemplate.postForObject(uri, httpEntity, Map.class);

            if (result != null) {
                if (!result.isEmpty()) {
                    log.info("Public key:  " + result.get("Public_Key"));
                    log.info("Certificate: " + result.get("Certificate"));
                    log.info("Hash:        " + result.get("Hash"));
                }
            }
        } else {
            return new ResponseEntity<>("Wrong greeting. Handshake Refused", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Nee Nee", HttpStatus.OK);
    }

    @PostMapping("/proceed-handshake/get_chest")
    private ResponseEntity<String> proceedHandshake(@RequestBody @Nullable Map<String, String> chest_in) {

        if (chest_in != null) {
            log.info("Chest Received");

            log.info("Chest contents[PK]: " + chest_in.get("PK"));
            log.info("Chest contents[Certificate]: " + chest_in.get("Certificate"));
            log.info("Chest contents[HASH]: " + chest_in.get("HASH"));
            log.info("Chest contents[salt]: " + chest_in.get("salt"));

            // #Todo Add validation logic
            // #Todo Add hash checking logic

            // if ok send chest logic
        }

//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        if (camellia != null) {
//            try {
//                log.info("trying to generate symmetric key...");
//                camellia.generateSymmetricKeys();
//
//            } catch (IllegalBlockSizeException e) {
//                log.info("IllegalBlockSizeException was thrown.");
//                throw new RuntimeException(e);
//
//            } catch (BadPaddingException e) {
//                log.info("BadPaddingException was thrown.");
//                throw new RuntimeException(e);
//
//            } catch (InvalidAlgorithmParameterException e) {
//                log.info("InvalidAlgorithmParameterException was thrown.");
//                throw new RuntimeException(e);
//
//            } catch (InvalidKeyException e) {
//                log.info("InvalidKeyException was thrown.");
//                throw new RuntimeException(e);
//
//            } catch (NoSuchPaddingException e) {
//                log.info("NoSuchPaddingException was thrown.");
//                throw new RuntimeException(e);
//
//            } catch (NoSuchAlgorithmException e) {
//                log.info("NoSuchAlgorithmException was thrown.");
//                throw new RuntimeException(e);
//
//            } catch (NoSuchProviderException e) {
//                log.info("NoSuchProviderException was thrown.");
//                throw new RuntimeException(e);
//            }
//        }
//
//        Map<String, String> chest = new HashMap<>();
//
//        chest.put("PK: IV", camellia.getIvParameterSpec().toString());
//        chest.put("PK: SecretKeySpec", camellia.getSecretKeySpec().toString());
//
//        HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(chest, headers);
//
//        // #todo 1. add a way to know whom to call
//        // #todo 2. put all of this logic into a dedicated service
//        String uri = "http://localhost:8090/handshake/proceed-handshake/send_chest";
//
//        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.postForObject(uri, httpEntity, String.class);
//        log.info("result is: " + result);

        return new ResponseEntity<>("Chest received successfully, expect one in return, baka ><", HttpStatus.ACCEPTED);
    }
}
