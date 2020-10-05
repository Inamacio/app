package inter.app.util;

import inter.app.model.SingleDigit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Assertions;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DigitRootTest {

    @Autowired
    private DigitRoot digitRoot;

    @Autowired
    private Cache cache;

    @Test
    public void testCalculateDigit() {
        int digit = this.digitRoot.calculateDigit(11);
        Assertions.assertEquals(2, digit);

        digit = this.digitRoot.calculateDigit(919);
        Assertions.assertEquals(1, digit);
    }

    @Test
    public void testcalc() {
        int number = this.digitRoot.calc("9875987598759875");
        Assertions.assertEquals(8, number);
    }

    @Test
    public void testDuplicateInput() {
        String input = this.digitRoot.duplicateInput(44, 2);
        Assertions.assertEquals("4444", input);

        input = this.digitRoot.duplicateInput(44, 1);
        Assertions.assertEquals("44", input);
    }

    @Test
    public void testCalculate() {
        SingleDigit singleDigit = this.digitRoot.calculate(67, 2);

        Assertions.assertEquals(2, singleDigit.getBase());
        Assertions.assertEquals(67, singleDigit.getInput());
        Assertions.assertEquals(8, singleDigit.getDigit());

        singleDigit = this.digitRoot.calculate(67, 1);

        Assertions.assertEquals(1, singleDigit.getBase());
        Assertions.assertEquals(67, singleDigit.getInput());
        Assertions.assertEquals(4, singleDigit.getDigit());

        this.cache.add(singleDigit);

        SingleDigit expect = this.digitRoot.calculate(67, 1);

        Assertions.assertEquals(expect, singleDigit);
    }
}
