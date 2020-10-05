package inter.app.util;

import inter.app.model.SingleDigit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CacheTest {

    @Autowired
    private Cache cache;

    @Autowired
    private DigitRoot digitRoot;

    @Test
    public void testAdd() {
        SingleDigit singleDigit = this.digitRoot.calculate(11, 2);

        this.cache.add(singleDigit);

        SingleDigit expect = this.cache.findByInputAndBase(11, 2);

        Assertions.assertEquals(singleDigit, expect);
    }

    @Test
    public void testFindByInputAndBase() {
        SingleDigit singleDigit = this.cache.findByInputAndBase(2,1);

        Assertions.assertNull(singleDigit);
    }
}
