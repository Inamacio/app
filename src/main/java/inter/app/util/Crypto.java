package inter.app.util;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
public class Crypto {

    public KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048, new SecureRandom());
        return generator.generateKeyPair();
    }

    public RSAPublicKey getPublicKey(String token) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] publicKey = Base64.getDecoder().decode(token);

        X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) kf.generatePublic(spec);
    }

    public String decrypt(String cipherText, byte[] privateKey) throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
        RSAPrivateKey privKey = (RSAPrivateKey) kf.generatePrivate(keySpec);

        byte[] bytes = Base64.getDecoder().decode(cipherText);

        Cipher decriptCipher = Cipher.getInstance("RSA");
        decriptCipher.init(Cipher.DECRYPT_MODE, privKey);

        return new String(decriptCipher.doFinal(bytes), StandardCharsets.UTF_8);
    }

    public String encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] cipherText = encryptCipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(cipherText);
    }
}
