package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {
  @Nested
  class constructor {
    @DisplayName("상금은 구매한 로또 수량만큼 있어야 한다.")
    @Test
    void byPaymentAndDraw() {
      // given
      Draw draw = new Draw(new WinningNumbers(List.of(1, 2, 3, 4, 5, 6)), new BonusNumber(7));
      Payment payment = new Payment(new Money(6_000));

      // when
      Result result = new Result(draw, payment);

      // then
      assertThat(result).extracting("prizes").asList().hasSize(payment.getPrizes(draw).size());
    }

    @DisplayName("`mock` 정보가 포함되어야 한다.")
    @Test
    void byMock() {
      // given
      Draw mockedDraw = Mockito.mock(Draw.class);
      Payment mockedPayment = Mockito.mock(Payment.class);
      Money mockedPrincipalAmount = new Money(5_000);
      Mockito.when(mockedPayment.getAmount()).thenReturn(mockedPrincipalAmount);
      List<Prize> mockedPrizes = List.of(Prize.FIFTH, Prize.THIRD, Prize.FOURTH, Prize.THIRD);
      Mockito.when(mockedPayment.getPrizes(mockedDraw)).thenReturn(mockedPrizes);

      // when
      Result result = new Result(mockedDraw, mockedPayment);

      // then
      assertThat(result)
          .extracting("prizes")
          .asList()
          .hasSize(mockedPrizes.size())
          .containsSequence(mockedPrizes);
      assertThat(result)
          .extracting("returnRate")
          .isEqualTo(
              Money.total(mockedPrizes.stream().map(Prize::getReward).toList())
                  .divide(mockedPrincipalAmount));
    }
  }

  @Nested
  class toString {
    @DisplayName("`mock` 정보가 포함되어야 한다.")
    @Test
    void byMock() {
      // given
      Draw mockedDraw = Mockito.mock(Draw.class);
      Payment mockedPayment = Mockito.mock(Payment.class);
      Money mockedPrincipalAmount = new Money(15_000);
      Mockito.when(mockedPayment.getAmount()).thenReturn(mockedPrincipalAmount);
      List<Prize> mockedPrizes =
          List.of(Prize.FIFTH, Prize.FOURTH, Prize.FOURTH, Prize.NOT_WIN, Prize.FIRST);
      Mockito.when(mockedPayment.getPrizes(mockedDraw)).thenReturn(mockedPrizes);

      // when
      String result = new Result(mockedDraw, mockedPayment).toString();

      // then
      assertThat(result)
          .isEqualToIgnoringWhitespace(
              """
                        당첨 통계
                        ---
                        3개 일치 (5,000원) - 1개
                        4개 일치 (50,000원) - 2개
                        5개 일치 (1,500,000원) - 0개
                        5개 일치, 보너스 볼 일치 (30,000,000원) - 0개
                        6개 일치 (2,000,000,000원) - 1개
                        총 수익률은 133340.33%입니다.
                        """);
    }
  }
}
