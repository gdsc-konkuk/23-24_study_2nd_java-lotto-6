package lotto.domain;

import java.text.DecimalFormat;
import java.util.List;

public class Result {
  private final List<Prize> prizes;
  private final float returnRate;

  public Result(Draw draw, Payment payment) {
    this.prizes = payment.getPrizes(draw);

    List<Money> rewards = prizes.stream().map(Prize::getReward).toList();
    Money principalAmount = payment.getAmount();
    this.returnRate = calcReturnRate(rewards, principalAmount);
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder("당첨 통계\n---\n");

    for (Prize type : Prize.values()) {
      if (type.equals(Prize.NOT_WIN)) {
        continue;
      }

      result.append(
          String.format(
              "%s - %d개\n",
              type, this.prizes.stream().filter(prize -> prize.equals(type)).count()));
    }

    result.append(
        String.format("총 수익률은 %s%%입니다.\n", new DecimalFormat("#.##").format(this.returnRate)));

    return result.toString();
  }

  private float calcReturnRate(List<Money> rewards, Money principalAmount) {
    return Money.total(rewards).divide(principalAmount) * 100;
  }
}
