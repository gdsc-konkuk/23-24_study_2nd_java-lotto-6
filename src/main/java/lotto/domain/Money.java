package lotto.domain;

import camp.nextstep.edu.missionutils.Console;

import java.util.List;

public class Money {
  private static final int UNIT = 1_000;
  private final int value;

  public static Money fromUser() {
    try {
      System.out.println("금액을 입력해 주세요.");
      String input = Console.readLine();
      return new Money(Integer.parseInt(input));
    } catch (NumberFormatException ex) {
      throw new IllegalArgumentException("[ERROR] 금액은 숫자여야 합니다.");
    }
  }

  public static Money total(List<Money> sources) {
    return new Money(sources.stream().map((other) -> other.value).reduce(0, Integer::sum));
  }

  public Money(int value) {
    validate(value);
    this.value = value;
  }

  public int divide(Money other) {
    return this.value / other.value;
  }

  public boolean gte(Money other) {
    return this.value >= other.value;
  }

  private void validate(int value) {
    if (value % Money.UNIT != 0) {
      throw new IllegalArgumentException("[ERROR] 금액 단위는 1,000원입니다.");
    }
  }
}
