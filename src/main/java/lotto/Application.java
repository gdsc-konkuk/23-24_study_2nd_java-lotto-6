package lotto;

import lotto.domain.BonusNumber;
import lotto.domain.Draw;
import lotto.domain.Money;
import lotto.domain.Payment;
import lotto.domain.Result;
import lotto.domain.WinningNumbers;

import java.util.function.Supplier;

public class Application {
  public static void main(String[] args) {
    Money principalAmount = tillSuccess(Money::fromUser);
    Payment payment = tillSuccess(() -> new Payment(principalAmount));
    System.out.println(payment);

    WinningNumbers winningNumbers = tillSuccess(WinningNumbers::fromUser);
    Draw draw = tillSuccess(() -> new Draw(winningNumbers, BonusNumber.fromUser()));
    Result result = new Result(draw, payment);
    System.out.println(result);
  }

  public static <T> T tillSuccess(Supplier<T> func) {
    while (true) {
      try {
        return func.get();
      } catch (Exception ex) {
        System.out.println(ex.getMessage());
      }
    }
  }
}
