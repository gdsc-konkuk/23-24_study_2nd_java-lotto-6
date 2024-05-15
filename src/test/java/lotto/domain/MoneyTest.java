package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MoneyTest {
  @Nested
  class parse {
    @DisplayName("돈이 숫자가 아니라면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"test", "12.0", "555$"})
    void byNonInteger(String nonIntegerValue) {
      assertThatThrownBy(() -> Money.parse(nonIntegerValue))
          .hasMessage("[ERROR] 구입금액은 숫자여야 합니다.")
          .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("최소구매금액(1,000원) 미만일 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "-5", "500"})
    void byNonPositiveIntStr(String nonIntGiven) {
      // when, then
      assertThatThrownBy(() -> Money.parse(nonIntGiven))
          .hasMessage("[ERROR] 구입금액은 1,000 이상의 숫자여야 합니다.")
          .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("돈의 단위가 1,000원이 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1200", "2300", "3050"})
    void byNonUnit(String nonUnitValue) {
      assertThatThrownBy(() -> Money.parse(nonUnitValue))
          .hasMessage("[ERROR] 구입금액의 기본 단위는 1,000원 입니다.")
          .isInstanceOf(IllegalArgumentException.class);
    }
  }

  @Nested
  class divide {
    @DisplayName("값을 나눌 수 있다.")
    @ParameterizedTest()
    @CsvSource({"2000,1000", "3000,2000", "12000,4000"})
    void byValue(int left, int right) {
      // given
      Money money1 = new Money(left);
      Money money2 = new Money(right);

      // when
      int result = money1.divide(money2);

      // then
      assertThat(result).isEqualTo(left / right);
    }
  }
}
