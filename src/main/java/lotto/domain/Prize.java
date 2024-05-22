package lotto.domain;

public enum Prize {
  NOT_WIN(0),
  FIFTH(5_000),
  FOURTH(50_000),
  THIRD(1_500_000),
  SECOND(30_000_000),
  FIRST(2_000_000_000);

  private final int reward;

  public static Prize from(int winningCount, boolean hitBonus) {
    return switch (winningCount) {
      case 6 -> Prize.FIRST;
      case 5 -> (hitBonus) ? Prize.SECOND : Prize.THIRD;
      case 4 -> Prize.FOURTH;
      case 3 -> Prize.FIFTH;
      default -> Prize.NOT_WIN;
    };
  }

  Prize(int reward) {
    this.reward = reward;
  }

  public Money getReward() {
    return new Money(this.reward);
  }

  @Override
  public String toString() {
    return switch (this) {
      case FIRST -> "6개 일치 (2,000,000,000원)";
      case SECOND -> "5개 일치, 보너스 볼 일치 (30,000,000원)";
      case THIRD -> "5개 일치 (1,500,000원)";
      case FOURTH -> "4개 일치 (50,000원)";
      case FIFTH -> "3개 일치 (5,000원)";
      case NOT_WIN -> "당첨 안 됨";
    };
  }
}
