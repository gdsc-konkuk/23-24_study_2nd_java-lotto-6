package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
  public static final int NUM_LOWER = 1;
  public static final int NUM_UPPER = 45;
  public static final int LEN = 6;
  public static final Money PRICE = new Money(1_000);

  private final List<Integer> numbers;

  public Lotto(List<Integer> numbers) {
    // make sure the numbers can be sorted.
    List<Integer> mutable = new ArrayList<>(numbers);

    Collections.sort(mutable);
    validate(mutable);
    this.numbers = mutable;
  }

  public Boolean has(int target) {
    for (Integer number : this.numbers) {
      if (number == target) {
        return true;
      }
    }

    return false;
  }

  @Override
  public String toString() {
    return this.numbers.toString();
  }

  private void validate(List<Integer> numbers) {
    if (numbers.size() != Lotto.LEN) {
      throw new IllegalArgumentException(
          String.format("[ERROR] 로또 번호는 %d개의 숫자로 이뤄져야 합니다.", Lotto.LEN));
    }

    for (Integer num : numbers) {
      if (num < Lotto.NUM_LOWER || num > Lotto.NUM_UPPER) {
        throw new IllegalArgumentException(
            String.format(
                "[ERROR] 로또 번호는 %d부터 %d 사이의 숫자여야 합니다.", Lotto.NUM_LOWER, Lotto.NUM_UPPER));
      }
    }

    // numbers are already sorted in constructor
    for (int i = 0; i < numbers.size() - 1; i++) {
      if (numbers.get(i).equals(numbers.get(i + 1))) {
        throw new IllegalArgumentException("[ERROR] 로또 번호는 중복이 없어야 합니다.");
      }
    }
  }
}
