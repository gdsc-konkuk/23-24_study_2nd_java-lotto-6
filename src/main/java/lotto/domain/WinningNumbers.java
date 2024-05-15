package lotto.domain;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class WinningNumbers {
  private static final int LEN = 6;

  private final List<Integer> numbers;

  public static WinningNumbers fromUser() {
    try {
      System.out.println("당첨 번호를 입력해 주세요.");
      String[] input = Console.readLine().split(",");
      List<Integer> numbers = Arrays.stream(input).map(Integer::parseInt).toList();
      return new WinningNumbers(numbers);
    } catch (NumberFormatException ex) {
      throw new IllegalArgumentException("[ERROR] 당첨 번호는 숫자 배열이어야 합니다. ex) 1,2,3,4");
    }
  }

  public WinningNumbers(List<Integer> numbers) {
    // make sure the numbers can be sorted.
    List<Integer> mutable = new ArrayList<>(numbers);

    Collections.sort(mutable);
    validate(mutable);
    this.numbers = mutable;
  }

  public boolean overlap(BonusNumber bonusNumber) {
    for (Integer number : this.numbers) {
      if (bonusNumber.equals(number)) {
        return true;
      }
    }

    return false;
  }

  public int compare(Lotto lotto) {
    int result = 0;
    for (Integer number : this.numbers) {
      if (lotto.has(number)) {
        result++;
      }
    }
    return result;
  }

  private void validate(List<Integer> numbers) {
    if (numbers.size() != WinningNumbers.LEN) {
      throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개의 숫자로 이뤄져야 합니다.");
    }

    for (Integer number : numbers) {
      if (number < Lotto.NUM_LOWER || number > Lotto.NUM_UPPER) {
        throw new IllegalArgumentException(
            String.format(
                "[ERROR] 당첨 번호는 %d부터 %d 사이의 숫자여야 합니다.", Lotto.NUM_LOWER, Lotto.NUM_UPPER));
      }
    }

    // numbers are already sorted in constructor
    for (int i = 0; i < numbers.size() - 1; i++) {
      if (Objects.equals(numbers.get(i), numbers.get(i + 1))) {
        throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복이 없어야 합니다.");
      }
    }
  }
}
