package lotto.domain;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;

public class Draw {
  private static final int BONUS_LEN = 1;
  private static final int WINNING_LEN = 6;

  private final List<Integer> winningNumbers;
  private final List<Integer> bonusNumbers;

  // TODO
  // 보너스 번호랑 당첨 번호가 겹치면 안 됨....ㅋㅋ

  public static Draw fromUser() {
    System.out.println("당첨 번호를 입력해 주세요.");
    String winningNumbersInput = Console.readLine();
    List<Integer> winningNumbers = Draw.parseWinningNumbers(winningNumbersInput);

    System.out.println("보너스 번호를 입력해 주세요.");
    String bonusNumbersInput = Console.readLine();
    List<Integer> bonusNumbers = Draw.parseBonusNumbers(bonusNumbersInput);

    return new Draw(winningNumbers, bonusNumbers);
  }

  public static List<Integer> parseWinningNumbers(String input) {
    try {
      List<Integer> winningNumbers =
          Arrays.stream(input.split(",")).map(Integer::parseInt).toList();
      // TODO
      new Draw(winningNumbers, List.of(1));
      return winningNumbers;
    } catch (NumberFormatException ex) {
      throw new IllegalArgumentException("[ERROR] 당첨 번호는 숫자 배열이어야 합니다. ex) 1,2,3,4");
    }
  }

  public static List<Integer> parseBonusNumbers(String input) {
    try {
      List<Integer> bonusNumbers = Arrays.stream(input.split(",")).map(Integer::parseInt).toList();
      // TODO
      new Draw(List.of(1, 2, 3, 4, 5, 6), bonusNumbers);
      return bonusNumbers;
    } catch (NumberFormatException ex) {
      throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자 배열이어야 합니다. ex) 1,2,3,4");
    }
  }

  private Draw(List<Integer> winningNumbers, List<Integer> bonusNumbers) {
    validateWinningNumbers(winningNumbers);
    validateBonusNumbers(bonusNumbers);

    this.winningNumbers = winningNumbers;
    this.bonusNumbers = bonusNumbers;
  }

  private void validateWinningNumbers(List<Integer> numbers) {
    if (numbers.size() != Draw.WINNING_LEN) {
      throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개의 숫자로 이뤄져야 합니다.");
    }

    for (Integer num : numbers) {
      if (num < Lotto.NUM_LOWER || num > Lotto.NUM_UPPER) {
        throw new IllegalArgumentException("[ERROR] 당첨 번호는 1부터 45 사이의 숫자여야 합니다.");
      }
    }
  }

  private void validateBonusNumbers(List<Integer> numbers) {
    if (numbers.size() != Draw.BONUS_LEN) {
      throw new IllegalArgumentException("[ERROR] 보너스 번호는 1개의 숫자로 이뤄져야 합니다.");
    }

    for (Integer num : numbers) {
      if (num < Lotto.NUM_LOWER || num > Lotto.NUM_UPPER) {
        throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
      }
    }
  }
}
