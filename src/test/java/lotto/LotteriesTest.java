package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class LotteriesTest {
  @Nested
  class create {
    @DisplayName("지정한 만큼 무작위 로또를 생성한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 10})
    void byAmount(int amount) {
      assertThatCode(() -> new Lotteries(amount)).extracting("lotteries").asList().hasSize(amount);
    }
  }

  @Nested
  class add {
    @DisplayName("로또를 하나씩 추가할 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 3, 9})
    void addOne(int amount) {
      // given
      Lotteries lotteries = new Lotteries(amount);

      // when
      lotteries.add(Lotto.rand());

      // then
      assertThat(lotteries).extracting("lotteries").asList().hasSize(amount + 1);
    }
  }

  @Nested
  class showAll {
    @DisplayName("모든 로또 번호를 출력할 수 있다.")
    @Test
    void doesNotThrow() {
      // given
      Lotteries lotteries = new Lotteries(5);

      // when, then
      assertThatCode(lotteries::showAll).doesNotThrowAnyException();
    }
  }
}
