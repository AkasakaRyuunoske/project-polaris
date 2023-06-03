package projectpolaris.CBA.Handshake;

import jakarta.annotation.Nullable;
import lombok.extern.log4j.Log4j2;
import org.mindrot.jbcrypt.BCrypt;
import org.bouncycastle.operator.OperatorCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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

    // Handshake process as INITIATOR
    @GetMapping("/start-handshake") //#todo for testing purposes is GET, must be changed to POST later
    public ResponseEntity<String> initiateHandshake(@RequestBody @Nullable String message_in) {
        log.info("Handshake started...");

        if (message_in == null) {
            // If message from receiver is null than controller is called for INITIATING the handshake

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            String message_out = "Nee";

            HttpEntity<String> httpEntity = new HttpEntity<>(message_out, headers);

            // #todo 1. add a way to know whom to call
            // #todo 2. put all of this logic into a dedicated service
            String uri = "http://localhost:8090/handshake/start-handshake";

            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.postForObject(uri, httpEntity, String.class);

            log.info("result: " + result);

            if (result != null && result.equals("Nee Nee")) {
                log.info("Nee Nee");
                proceedHandshake_SendChest(null);
            }

        } else {
//            respondToHandshake(message_in);
        }

        return new ResponseEntity<>("Nee", HttpStatus.ACCEPTED);
    }

    @PostMapping("/proceed-handshake/send_chest")
    private ResponseEntity<Map<String, String>> proceedHandshake_SendChest(@RequestBody @Nullable Map<String, String> chest_in) {
        log.info("Nee Nee Neeee");

        if (chest_in == null) {
            X509Certificate certificate;

            try {
                certificate = certificationGenerator.generateCertification();
            } catch (NoSuchAlgorithmException | CertificateException | IOException | OperatorCreationException e) {
                throw new RuntimeException(e);
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            String salt = BCrypt.gensalt();
            String hashedMessage = BCrypt.hashpw(certificate.getPublicKey().toString() + certificate.getPublicKey().toString(), salt);

            Map<String, String> chest = new HashMap<>();

            chest.put("PK", certificate.getPublicKey().toString());
            chest.put("Certificate", certificate.toString());
            chest.put("HASH", hashedMessage);
            chest.put("salt", salt);

            HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(chest, headers);

            // #todo 1. add a way to know whom to call
            // #todo 2. put all of this logic into a dedicated service
            String uri = "http://localhost:8090/handshake/proceed-handshake/get_chest";

            RestTemplate restTemplate = new RestTemplate();
            Map<String, String> result = restTemplate.postForObject(uri, httpEntity, Map.class);

            log.info("result[PK: SecretKeySpec]: " + Arrays.toString(Base64.getDecoder().decode(result.get("PK: SecretKeySpec"))));
            log.info("result[PK: IV]: " + Arrays.toString(Base64.getDecoder().decode(result.get("PK: IV"))));

            return new ResponseEntity<>(chest, HttpStatus.OK); //#todo might need to change
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            if (camellia != null) {
                try {
                    log.info("trying to generate symmetric key...");
                    camellia.generateSymmetricKeys();

                } catch (IllegalBlockSizeException e) {
                    log.info("IllegalBlockSizeException was thrown.");
                    throw new RuntimeException(e);

                } catch (BadPaddingException e) {
                    log.info("BadPaddingException was thrown.");
                    throw new RuntimeException(e);

                } catch (InvalidAlgorithmParameterException e) {
                    log.info("InvalidAlgorithmParameterException was thrown.");
                    throw new RuntimeException(e);

                } catch (InvalidKeyException e) {
                    log.info("InvalidKeyException was thrown.");
                    throw new RuntimeException(e);

                } catch (NoSuchPaddingException e) {
                    log.info("NoSuchPaddingException was thrown.");
                    throw new RuntimeException(e);

                } catch (NoSuchAlgorithmException e) {
                    log.info("NoSuchAlgorithmException was thrown.");
                    throw new RuntimeException(e);

                } catch (NoSuchProviderException e) {
                    log.info("NoSuchProviderException was thrown.");
                    throw new RuntimeException(e);
                }
            }

            Map<String, String> chest = new HashMap<>();

            chest.put("PK: IV", Base64.getEncoder().encodeToString(camellia.getIvParameterSpec().getIV()));
            chest.put("PK: SecretKeySpec", Base64.getEncoder().encodeToString(camellia.getSecretKeySpec().getEncoded()));

            log.info("Data to be transferred [Camellia] IV: " + Base64.getEncoder().encodeToString(camellia.getIvParameterSpec().getIV()));
            log.info("Data to be transferred [Camellia] SecretKeySpec: " + Base64.getEncoder().encodeToString(camellia.getSecretKeySpec().getEncoded()));

            HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(chest, headers);

            // #todo 1. add a way to know whom to call
            // #todo 2. put all of this logic into a dedicated service
            String uri = "http://localhost:8090/handshake/proceed-handshake/send_chest";

            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.postForObject(uri, httpEntity, String.class);

            chest.clear();

            chest.put("Message", "Yay we are talking");

            log.info("result is: " + result);

            return new ResponseEntity<>(chest, HttpStatus.OK); //#todo might need to change
        }
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
