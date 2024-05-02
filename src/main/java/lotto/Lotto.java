package lotto;

import java.util.List;

public class Lotto {
  private final List<Integer> numbers;

  public Lotto(List<Integer> numbers) {
    validate(numbers);
    this.numbers = numbers;
  }

  private void validate(List<Integer> numbers) {
    if (numbers.size() != 6) {
      throw new IllegalArgumentException();
    }
  }

  public static Lotto rand() {
    return new Lotto(List.of(1, 2, 3, 4, 5, 6));
  }
}
