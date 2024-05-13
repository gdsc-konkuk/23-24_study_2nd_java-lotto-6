package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentTest {
  @Nested
  class create {
    @DisplayName("돈으로 구매할 수 있다.")
    @ParameterizedTest
    @CsvSource({"3000,3", "5000,5", "12000,12"})
    void byMoney(int paidMoney, int numLotto) {
      // given
      Money paid = new Money(paidMoney);

      // when
      Payment payment = new Payment(paid);

      // then
      assertThat(payment.getLottery().getAmount()).isEqualTo(numLotto);
    }
  }

  @Nested
  class toString {
    @DisplayName("결제 정보에는 구매 수량이 포함되어야 한다.")
    @Test
    void msgHaveAmount() {
      // given
      Money money = new Money(5_000);
      Payment payment = new Payment(money);

      // when
      String paymentInformation = payment.toString();

      // then
      assertThat(paymentInformation).contains("5개를 구매했습니다.");
    }
  }
}
