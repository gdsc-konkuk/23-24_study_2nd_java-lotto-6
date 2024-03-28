package lotto.src.model;

import java.util.List;

public class Bonus {
    private final Integer bonusNumber;

    public Bonus(Integer bonusNumber) {
        validate(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validate(Integer numbers) {
//        if (numbers.size() != 6) {
//            throw new IllegalArgumentException();
//        }
    }
}
