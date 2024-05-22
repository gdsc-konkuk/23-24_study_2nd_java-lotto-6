package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Payment {
  private static final Money MIN_AMOUNT = new Money(1_000);

  private final Money amount;
  private final int numBought;
  private final List<Lotto> lottery;

  public Payment(Money amount) {
    validate(amount);
    this.amount = amount;
    this.numBought = (int) amount.divide(Lotto.PRICE);

    this.lottery = new ArrayList<>();
    issueLottery(this.numBought);
  }

  public Money getAmount() {
    return this.amount;
  }

  public List<Prize> getPrizes(Draw draw) {
    return this.lottery.stream().map(draw::compare).toList();
  }

  @Override
  public String toString() {
    String header = String.format("%d개를 구매했습니다.", this.numBought);
    return this.lottery.stream()
        .map(Lotto::toString)
        .reduce(header, (result, lotto) -> result + "\n" + lotto);
  }

  private void validate(Money amount) {
    if (!amount.gte(Payment.MIN_AMOUNT)) {
      throw new IllegalArgumentException("[ERROR] 최소 주문 금액은 1000원입니다.");
    }
  }

  private void issueLottery(int numBought) {
    for (int i = 0; i < numBought; i++) {
      this.lottery.add(
          new Lotto(Randoms.pickUniqueNumbersInRange(Lotto.NUM_LOWER, Lotto.NUM_UPPER, Lotto.LEN)));
    }
  }
}
