package lotto.domain;

import java.util.List;

public class Draw {
  private static final int BONUS_LEN = 1;
  private static final int WINNING_LEN = 6;

  private final List<Integer> winningNumbers;
  private final List<Integer> bonusNumbers;

  public static Draw fromUser() {
    return new Draw(List.of(0), List.of(0));
  }

  public static List<Integer> parseWinningNumbers(String input) {
    return List.of(0);
  }

  public static List<Integer> parseBonusNumbers(String input) {
    return List.of(0);
  }

  private Draw(List<Integer> winningNumbers, List<Integer> bonusNumbers) {
    // TODO
    this.winningNumbers = winningNumbers;
    this.bonusNumbers = bonusNumbers;
  }
}
