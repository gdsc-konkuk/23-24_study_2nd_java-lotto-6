package lotto.src.model;

import java.util.List;

import static lotto.common.exceptions.ExceptionContext.*;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(NUMBER_LIST_SIZE_ERROR.getMessage());
        }
        for (Integer number : numbers) {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException(NUMBER_RANGE_ERROR.getMessage());
            }
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
