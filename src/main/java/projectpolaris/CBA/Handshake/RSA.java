package projectpolaris.CBA.Handshake;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;

@Component
@Log4j2
public class RSA {
    @Value("${security.asymmetric-key.key-size}")
    int key_size;

    @Value("${security.asymmetric-key.algorithm-name}")
    String algorithm_name;

    @Value("${security.asymmetric-key.algorithm-transformation}")
    String algorithm_transformation;

    PrivateKey privateKey;
    PublicKey publicKey;

    Cipher cipher;

    public RSA() throws NoSuchPaddingException, NoSuchAlgorithmException {
    }

    public void generateKeys() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {

// Step 1: Come up with a message we want to encrypt
//        byte[] message = "NEE ANATA WA ITSUMO YUME WO MITE MASU KA?".getBytes();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm_name);

        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        keyPairGenerator.initialize(key_size);

        privateKey = keyPair.getPrivate();
        publicKey = keyPair.getPublic();

        log.info("Private key is : " + privateKey);
        log.info("public key is : " + publicKey);

        Cipher.getInstance(algorithm_transformation);

//        Cipher cipher = Cipher.getInstance(algorithm_transformation);
//
//// Step 7: Initialize the Cipher object
//        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

// Step 8: Give the Cipher our message
//        cipher.update(message);
//
//// Step 9: Encrypt the message
//        byte[] ciphertext = cipher.doFinal();
//// Step 10: Print the ciphertext
//        System.out.println("message: " + new String(message, "UTF8"));
//        System.out.println("ciphertext: " + new String(ciphertext, "UTF8"));
//
//// Step 11: Change the Cipher object's mode
//        cipher.init(Cipher.DECRYPT_MODE, privateKey);
//
//// Step 12: Give the Cipher objectour ciphertext
//        cipher.update(ciphertext);
//
//// Step 13: Decrypt the ciphertext
//        byte[] decrypted = cipher.doFinal();
//        System.out.println("decrypted: " + new String(decrypted, "UTF8"));
    }

    public String enchant(String plainMessage) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        cipher.update(plainMessage.getBytes());

        byte[] enchantedMessage = cipher.doFinal();

        return new String(enchantedMessage, "UTF8");
    }

    public String disenchant(String enchantedMessage){
        String disenchantedMessage = null;

        return disenchantedMessage;
    }
}
