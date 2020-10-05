package inter.app.util;

import inter.app.model.SingleDigit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DigitRoot {

    @Autowired
    private Cache cache;

    public int calculateDigit(int n) {
        int digit = 0;

        while (digit > 9 || n > 0) {
            if (n == 0) {
                n = digit;
                digit = 0;
            }

            digit += n % 10;
            n /= 10;
        }
        return digit;
    }

    public int calc(String sum) {
        String[] nums = sum.split("");
        int[] nxtList = new int[nums.length];
        for (int i = 0; i < nums.length; i++) nxtList[i] = Integer.parseInt(nums[i]);

        int result = 0;
        for (Integer x : nxtList) result += x;

        return this.calculateDigit(result);
    }

    public String duplicateInput(int number, int base) {
        if (base <= 1) return Integer.toString(number);

        return String.valueOf(Integer.toString(number) + String.valueOf(number)
                .repeat(Math.max(0, base - 1)));
    }

    public SingleDigit calculate(int input, int base) {
        SingleDigit singleDigit = this.cache.findByInputAndBase(input, base);

        if (singleDigit != null) return singleDigit;

        var single = new SingleDigit();
        single.setInput(input);
        single.setBase(base);

        String number = this.duplicateInput(input, base);

        single.setDigit(this.calc(number));

        this.cache.add(single);

        return single;
    }
}
