package projectpolaris.CBA.Handshake;

import jakarta.annotation.Nullable;
import lombok.extern.log4j.Log4j2;
import org.bouncycastle.operator.OperatorCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/handshake")
public class HandshakeController {

    @Autowired
    CertificationGenerator certificationGenerator;

    // Handshake process as INITIATOR
    @GetMapping("/start-handshake")
    private ResponseEntity<String> startHandshake(@RequestBody @Nullable String message_in){
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

            if (result != null && result.equals("Nee Nee")){
                log.info("Nee Nee");
                proceedHandshake();
            }

        } else if (message_in.equals("Nee")){

        }

        return new ResponseEntity<>("Nee", HttpStatus.ACCEPTED);
    }

    @PostMapping("/proceed-handshake/")
    private ResponseEntity<String> proceedHandshake(){
        log.info("Nee Nee Neeee");
        X509Certificate certificate;
        try {
            certificate = certificationGenerator.generateCertification();
        } catch (NoSuchAlgorithmException | CertificateException | IOException | OperatorCreationException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(certificate.toString(), HttpStatus.CONTINUE);
    }

    // Handshake process as RECEIVER
}
