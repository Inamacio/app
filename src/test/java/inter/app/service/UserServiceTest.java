package inter.app.service;

import inter.app.model.SingleDigit;
import inter.app.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService service;

    @Test
    public void testCreate() throws Exception {
        User user = new User();
        user.setName("Test inter");
        user.setEmail("demo@inter.com.br");

        HashMap<String, Object> result = this.service.create(user);

        Assertions.assertTrue(result.containsKey("publicKey") && result.get("publicKey") != null);
        Assertions.assertTrue(result.containsKey("privateKey") && result.get("privateKey") != null);
        Assertions.assertTrue(result.containsKey("id") && result.get("id") != null);
    }

    @Test
    public void testFind() throws Exception {
        User user = new User();
        String name = "Test inter";
        String email = "demo@inter.com.br";
        user.setName(name);
        user.setEmail(email);

        HashMap<String, Object> createObj = this.service.create(user);

        User result = this.service.find((UUID) createObj.get("id"), "");

        Assertions.assertNotNull(result.getId());
        Assertions.assertNotNull(result.getEmail());
        Assertions.assertNotNull(result.getName());

        try {
            UUID id = UUID.randomUUID();
            this.service.find(id, "");
            Assertions.fail();
        }catch (Exception e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void testCalculate() {
        SingleDigit singleDigit = new SingleDigit();
        singleDigit.setInput(66);
        singleDigit.setBase(1);

        SingleDigit result = this.service.calculate(singleDigit);

        Assertions.assertEquals(3, result.getDigit());
    }

    @Test
    public void testCalculateByUser() throws Exception {
        User user = new User();
        user.setName("Test inter");
        user.setEmail("demo@inter.com.br");

        HashMap<String, Object> creatObj = this.service.create(user);

        SingleDigit singleDigit = new SingleDigit();
        singleDigit.setInput(66);
        singleDigit.setBase(1);

        SingleDigit result = this.service.calculateByUser((UUID) creatObj.get("id"), singleDigit);

        Assertions.assertEquals(3, result.getDigit());
    }

    @Test
    public void testDelete() throws Exception {
        User user = new User();
        user.setName("Test inter");
        user.setEmail("demo@inter.com.br");

        HashMap<String, Object> creatObj = this.service.create(user);

        Assertions.assertNull(this.service.delete((UUID) creatObj.get("id")));
    }

    @Test
    public void testUpdate() throws Exception {
        User user = new User();
        user.setEmail("test@inter.com.br");
        user.setName("Test");

        try {
            UUID id = UUID.randomUUID();
            this.service.update(id, user, "");
            Assertions.fail();
        }catch (Exception e) {
            Assertions.assertTrue(true);
        }

        HashMap<String, Object> result = this.service.create(user);

        try {
            this.service.update((UUID) result.get("id"), user, "");
            Assertions.fail();
        }catch (Exception e) {
            Assertions.assertTrue(true);
        }
    }
}
