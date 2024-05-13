package lotto.domain;

import java.util.List;

public class Lotto {
  public static final Money PRICE = new Money(1_000);
  public static final int NUM_LOWER = 1;
  public static final int NUM_UPPER = 45;
  public static final int LEN = 6;

  private final List<Integer> numbers;

  public static Lotto rand() {
    // TODO
    return new Lotto(List.of(1, 2, 3, 4, 5, 6));
  }

  public Lotto(List<Integer> numbers) {
    validate(numbers);
    this.numbers = numbers;
  }

  private void validate(List<Integer> numbers) {
    // TODO
    if (numbers.size() != Lotto.LEN) {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public String toString() {
    // TODO
    return "lotto";
  }
}
