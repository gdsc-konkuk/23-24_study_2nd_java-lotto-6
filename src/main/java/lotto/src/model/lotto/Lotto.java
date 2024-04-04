package lotto.src.model.lotto;

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

    public void numberSort() {
        Collections.sort(numbers);
    }

    @Override
    public String toString() {
        String output = "";
        output += "[";
        for (int i = 0; i < numbers.size(); i++) {
            output += numbers.get(i);
            if (i < numbers.size() - 1) {
                output += ", ";
            }
        }
        output += "]";
        return output;
    }

}
