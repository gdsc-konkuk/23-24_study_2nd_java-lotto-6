package lotto.src.model;

import lotto.common.exceptions.InputException;

import java.util.List;

public class Bonus {
    private final Integer bonusNumber;

    public Bonus(List<Integer> winningNumber, String bonusNumber) {
        InputException.validateBonusNumberInput(winningNumber, bonusNumber);
        this.bonusNumber = Integer.parseInt(bonusNumber);
    }

    public Integer getBonusNumber() {
        return bonusNumber;
    }
}
