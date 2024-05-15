package lotto.domain;

public class Payment {
  private final Money amount;
  private final Lottery lottery;

  public Payment(Money amount) {
    this.amount = amount;
    this.lottery = new Lottery(amount.divide(Lotto.PRICE));
  }

  public Lottery getLottery() {
    return this.lottery;
  }

  @Override
  public String toString() {
    int numBought = this.amount.divide(Lotto.PRICE);
    String header = String.format("%d개를 구매했습니다.", numBought);
    return header + "\n" + this.lottery.toString();
  }
}
