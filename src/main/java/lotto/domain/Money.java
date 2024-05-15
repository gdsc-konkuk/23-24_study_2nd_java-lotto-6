package lotto.domain;

import camp.nextstep.edu.missionutils.Console;

public class Money {
  private static final int MIN_VALUE = 1_000;
  private static final int UNIT = 1_000;
  private final int value;

  public static Money fromUser() {
    String input = Console.readLine();
    int value = Money.parse(input);
    return new Money(value);
  }

  public static int parse(String input) {
    try {
      int parsed = Integer.parseInt(input);
      new Money(parsed);
      return parsed;
    } catch (NumberFormatException ex) {
      throw new IllegalArgumentException("[ERROR] 구입금액은 숫자여야 합니다.");
    }
  }

  public Money(int value) {
    validate(value);
    this.value = value;
  }

  public int divide(Money other) {
    return this.value / other.value;
  }

  private void validate(int value) {
    if (value < Money.MIN_VALUE) {
      throw new IllegalArgumentException("[ERROR] 구입금액은 1,000 이상의 숫자여야 합니다.");
    }

    if (value % Money.UNIT != 0) {
      throw new IllegalArgumentException("[ERROR] 구입금액의 기본 단위는 1,000원 입니다.");
    }
  }
}
