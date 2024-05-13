package lotto.domain;

public class Money {
  private static final int MIN_VALUE = 1_000;
  private static final int UNIT = 1_000;
  private final int value;

  public static Money fromUser() {
    // TODO
    return new Money(5);
  }

  public static int parse(String input) {
    // TODO
    return 5;
  }

  public Money(int value) {
    // TODO
    // need validation
    this.value = value;
  }

  public int divide(Money other) {
    return this.value / other.value;
  }
}
