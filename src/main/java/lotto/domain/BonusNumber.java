package lotto.domain;

import camp.nextstep.edu.missionutils.Console;

public class BonusNumber {
  private final int number;

  public static BonusNumber fromUser() {
    try {
      System.out.println("보너스 번호를 입력해 주세요.");
      String input = Console.readLine();
      return new BonusNumber(Integer.parseInt(input));
    } catch (NumberFormatException ex) {
      throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자여야 합니다.");
    }
  }

  public BonusNumber(int number) {
    validate(number);
    this.number = number;
  }

  public boolean belonged(Lotto lotto) {
    return lotto.has(this.number);
  }

  public boolean equals(Integer number) {
    return this.number == number;
  }

  private void validate(int number) {
    if (number < Lotto.NUM_LOWER || number > Lotto.NUM_UPPER) {
      throw new IllegalArgumentException(
          String.format("[ERROR] 보너스 번호는 %d부터 %d 사이의 숫자여야 합니다.", Lotto.NUM_LOWER, Lotto.NUM_UPPER));
    }
  }
}
