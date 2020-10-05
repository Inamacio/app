package inter.app.util;

import inter.app.model.SingleDigit;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.*;

@Component
@ApplicationScope
public class Cache {

    private List<SingleDigit> list = new ArrayList<>();

    public SingleDigit findByInputAndBase(int input, int base) {
        return list.stream()
                .filter((a) -> Objects.equals(a.getInput(), input) && Objects.equals(a.getBase(), base))
                .findFirst().orElse(null);

    }

    public void add(SingleDigit singleDigit) {
        if (list.size() >= 10) list.remove(0);
        list.add(singleDigit);
    }
}
