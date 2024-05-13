package lotto.domain;

public class Payment {
  private final Lottery lottery;

  public Payment(Money amount) {
    // TODO
    // need validation
    this.lottery = new Lottery(amount.divide(Lotto.PRICE));
  }

  public Lottery getLottery() {
    return this.lottery;
  }

  @Override
  public String toString() {
    // TODO
    return "payment";
  }
}
