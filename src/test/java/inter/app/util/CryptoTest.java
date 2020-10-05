package inter.app.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.security.KeyPair;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CryptoTest {

    @Autowired
    private Crypto crypto;

    @Test
    public void testEncrypt() throws Exception {
        KeyPair pair = this.crypto.generateKeyPair();

        String name = "Test encrypt";

        String testEncrypt = this.crypto.encrypt(name, pair.getPublic());

        Assertions.assertNotNull(testEncrypt);
    }

    @Test
    public void testDecrypt() throws Exception {
        KeyPair pair = this.crypto.generateKeyPair();

        String name = "Test encrypt";

        String testEncrypt = this.crypto.encrypt(name, pair.getPublic());

        String testDecrypt = this.crypto.decrypt(testEncrypt, pair.getPrivate().getEncoded());

        Assertions.assertEquals(testDecrypt, name);
    }
}
