package inter.app.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SingleDigitTest {

    @Test
    public void testAll() {
        SingleDigit singleDigit = new SingleDigit();
        singleDigit.setInput(66);
        singleDigit.setBase(1);
        singleDigit.setDigit(3);

        Assertions.assertEquals(66, singleDigit.getInput());
        Assertions.assertEquals(1, singleDigit.getBase());
        Assertions.assertEquals(3, singleDigit.getDigit());
    }
}
