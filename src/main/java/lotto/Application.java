package lotto;

import lotto.domain.Draw;
import lotto.domain.Money;
import lotto.domain.Payment;

public class Application {
  public static void main(String[] args) {
    try {
      Payment payment = new Payment(Money.fromUser());
      System.out.println(payment);

      Draw draw = Draw.fromUser();
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }
}
