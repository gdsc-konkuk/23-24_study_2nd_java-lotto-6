package lotto.src.model;

import lotto.common.Constant;
import lotto.common.exceptions.InputException;

import java.util.List;

import static lotto.common.Constant.*;
import static lotto.common.exceptions.ExceptionContext.*;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        InputException.validateWinningNumbers(numbers);
        this.numbers = numbers;
    }
    public List<Integer> getNumbers() {
        return numbers;
    }
}
