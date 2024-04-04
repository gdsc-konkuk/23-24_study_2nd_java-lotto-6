package lotto;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
        Set<Integer> uniqueNumbers = new HashSet<Integer>(numbers);
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호는 중복되지 않아야 합니다.");
        }
        for (int number : numbers) {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException("로또 번호는 1에서 45 사이어야 합니다.");
            }
        }
    }
    public void print() {
        Collections.sort(numbers);
        System.out.print("[");
        for (int i = 0; i < numbers.size(); i++) {
            System.out.print(numbers.get(i));
            if (i < numbers.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
