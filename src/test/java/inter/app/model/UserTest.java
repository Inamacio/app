package inter.app.model;

import inter.app.util.DigitRoot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private DigitRoot digitRoot;

    @Test
    public void testSetName() {
        User user = new User();

        String name = "Banco Inter";

        user.setName(name);

        Assertions.assertEquals(name, user.getName());
    }

    @Test
    public void testSetEmail() {
        User user = new User();

        String email = "test@inter.com.br";

        user.setEmail(email);

        Assertions.assertEquals(email, user.getEmail());
    }

    @Test
    public void testSetId() {
        User user = new User();

        UUID id = UUID.randomUUID();;

        user.setId(id);

        Assertions.assertEquals(id, user.getId());
    }

    @Test
    public void testSetCalculations() {
        User user = new User();

        SingleDigit single = this.digitRoot.calculate(22,1);

        List<SingleDigit> calculations = new ArrayList<>();

        calculations.add(single);

        user.setCalculations(calculations);

        Assertions.assertEquals(calculations, user.getCalculations());
    }
}
