package projectpolaris.CBA.Handshake;

import lombok.extern.log4j.Log4j2;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import projectpolaris.CBA.SecurityServices.HandshakePostman;
import projectpolaris.Messaging.KafkaConfigs;

import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


// #Todo 1. Test all IFs, all the data validation, everything
// #Todo 1.1 Tests must be written as tests not, manual testing
// #Todo 1.2 Data, Request and Response validation logic must be in dedicated classes as designed
// #Todo 2. Add some way for handshakePostman to know where to contact, not using hardcoded urls

// #Todo 3. Add receivers logic
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
            // Using default method compare locally created hash with one that is received
            boolean checkpw_result = BCrypt.checkpw(result_chest.get("PK: IV") + result_chest.get("PK: SecretKeySpec") + result_chest.get("Certificate"), result_chest.get("HASH"));

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

    //    @PostMapping("/proceed-handshake/send_encrypted_message")
    private ResponseEntity<Map<String, String>> proceedHandshake_SendEncryptedMessage(@RequestBody Map<String, String> chest) {
        kafkaTemplate.send(kafkaConfigs.getErrorsSecurity(), "[HANDSHAKE CONTROLLER] Sending encrypted message encountered errors due to poor coding xdd");

        camellia.generateSymmetricKeys(Base64.getDecoder().decode(chest.get("PK: IV")),
                Base64.getDecoder().decode(chest.get("PK: SecretKeySpec")));

        log.info("[Camellia] IV: " + Base64.getEncoder().encodeToString(camellia.getIvParameterSpec().getIV()));
        log.info("[Camellia] SecretKeySpec: " + Base64.getEncoder().encodeToString(camellia.getSecretKeySpec().getEncoded()));

        String plainMessage = "The Dying Message";
        String encryptedMessage = camellia.enchant(plainMessage);

        String encryptedMessage_hash = BCrypt.hashpw(encryptedMessage, BCrypt.gensalt());

        Map<String, String> encryptedMessagePayload = new HashMap<>();

        encryptedMessagePayload.put("EncryptedMessage", encryptedMessage);
        encryptedMessagePayload.put("EncryptedMessageHash", encryptedMessage_hash);

        // #todo 1. add a way to know whom to call
        Map<String, String> result = handshakePostman.contact("http://localhost:8090/handshake/proceed-handshake/get_encrypted_message", encryptedMessagePayload);

        Map<String, String> response = new HashMap<>();

        if (!result.isEmpty() && result.get("ACK") != null && !result.get("ACK").isEmpty() && result.get("ACK").equals("acknowledgment")) {
            finishHandshake_sendAck();
        } else {
            response.put("Message", "Something wrong with ACK. Handshake refused");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        response.put("Message", "Something wrong with server while doing last step of handshake");

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //    @PostMapping("/finish-handshake/send_ACK")
    private ResponseEntity<Map<String, String>> finishHandshake_sendAck() {
        Map<String, String> ackPayload = new HashMap<>();

        ackPayload.put("ACK", "acknowledgment");

        Map<String, String> result = handshakePostman.contact("http://localhost:8090/handshake/proceed-handshake/get_ACK", ackPayload);

        log.info("result of getAck: " + result);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
