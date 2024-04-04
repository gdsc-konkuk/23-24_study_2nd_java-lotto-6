package lotto;

import java.util.*;

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
        if (uniqueNumbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호는 중복되지 않아야 합니다.");
        }
        for (int number : numbers) {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException("로또 번호는 1에서 45 사이어야 합니다.");
            }
        }
    }
    public void print() {
        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers);
        System.out.print("[");
        for (int i = 0; i < sortedNumbers.size(); i++) {
            System.out.print(sortedNumbers.get(i));
            if (i < sortedNumbers.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public Score countMatchingNumbers(WinningNumbers winningNumber) {
        List<Integer> winningLottoNumbers = winningNumber.getLotto().getNumbers();
        int bonusNumber = winningNumber.getBonusNumber();
        int matchingCount = 0;
        boolean bonusMatch = false;
        for (int number : numbers) {
            if (winningLottoNumbers.contains(number)) {
                matchingCount++;
            }
            if (number == bonusNumber) bonusMatch = true;
        }
        for (Score score : Score.values()) {
            if (score.getMatch() == matchingCount && score.isBonusMatch() == bonusMatch) {
                return score;
            }
        }
        return Score.NONE;
    }
}
