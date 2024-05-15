package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentTest {
  @Nested
  class constructor {
    @DisplayName("돈으로 구매할 수 있다.")
    @ParameterizedTest
    @CsvSource({"3000,3", "5000,5", "12000,12"})
    void byMoney(int paidMoney, int numBought) {
      // given
      Money amount = new Money(paidMoney);

      // when
      Payment payment = new Payment(amount);

      // then
      assertThat(payment).hasFieldOrPropertyWithValue("numBought", numBought);
      assertThat(payment).extracting("lottery").asList().hasSize(numBought);
    }
  }

  @Nested
  class toString {
    @DisplayName("결제 정보에는 구매 수량이 포함되어야 한다.")
    @ParameterizedTest
    @CsvSource({"3000,3", "5000,5", "12000,12"})
    void containsNumBought(int paidMoney, int numBought) {
      // given
      Money amount = new Money(paidMoney);
      Payment payment = new Payment(amount);

      // when
      String result = payment.toString();

      // then
      assertThat(result).contains(String.format("%d개를 구매했습니다.\n", numBought));
    }

    @DisplayName("`mock` 정보가 포함되어야 한다.")
    @Test
    void byMock() {
      try (MockedStatic<Randoms> mockedRandom = Mockito.mockStatic(Randoms.class)) {
        // given
        mockedRandom
            .when(
                () -> Randoms.pickUniqueNumbersInRange(Lotto.NUM_LOWER, Lotto.NUM_UPPER, Lotto.LEN))
            .thenReturn(List.of(1, 2, 3, 4, 5, 6))
            .thenReturn(List.of(11, 12, 43, 9, 15, 36))
            .thenReturn(List.of(1, 2, 33, 44, 5, 7))
            .thenReturn(List.of(13, 7, 8, 4, 5, 6))
            .thenReturn(List.of(1, 2, 32, 4, 5, 6));
        Payment payment = new Payment(new Money(5_000));

        // when
        String result = payment.toString();

        // then
        assertThat(result)
            .isEqualToIgnoringWhitespace(
                """
                        5개를 구매했습니다.
                        [1, 2, 3, 4, 5, 6]
                        [9, 11, 12, 15, 36, 43]
                        [1, 2, 5, 7, 33, 44]
                        [4, 5, 6, 7, 8, 13]
                        [1, 2, 4, 5, 6, 32]
                        """);
      }
    }
  }
}
