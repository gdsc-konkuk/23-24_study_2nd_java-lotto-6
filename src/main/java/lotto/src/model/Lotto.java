package lotto.src.model;

import lotto.common.exceptions.InputException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
