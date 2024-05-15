package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;

class LotteryTest {
  @Nested
  class create {
    @DisplayName("지정한 만큼 무작위 로또를 생성한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 10})
    void byAmount(int givenAmount) {
      // given

      // when
      Lottery lottery = new Lottery(givenAmount);

      // then
      assertThat(lottery.getAmount()).isEqualTo(givenAmount);
    }

    @DisplayName("camp.nextstep.edu.missionutils.Randoms를 활용해서 지정한 만큼 무작위 로또를 생성한다.")
    @Test
    void byAmountAndPickUniqueNumbersInRange() {
      try (MockedStatic<Randoms> mockedRandoms = Mockito.mockStatic(Randoms.class)) {
        // given
        mockedRandoms
            .when(() -> Randoms.pickUniqueNumbersInRange(1, 45, 6))
            .thenReturn(List.of(1, 2, 3, 4, 5, 6));

        // when
        Lottery lottery = new Lottery(2);

        // then
        mockedRandoms.verify(() -> Randoms.pickUniqueNumbersInRange(1, 45, 6), times(2));
        Assertions.assertThat(lottery.toString())
            .isEqualToIgnoringWhitespace(
                """
                        [1, 2, 3, 4, 5, 6]
                        [1, 2, 3, 4, 5, 6]
                        """);
      }
    }
  }

  @Nested
  class add {
    @DisplayName("로또를 하나씩 추가할 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 3, 9})
    void addOne(int initialAmount) {
      // given
      Lottery lottery = new Lottery(initialAmount);

      // when
      lottery.add(new Lotto(List.of(1, 2, 3, 4, 5, 6)));

      // then
      assertThat(lottery.getAmount()).isEqualTo(initialAmount + 1);
      assertThat(lottery.toString()).contains("[1, 2, 3, 4, 5, 6]");
    }
  }

  @Nested
  class toString {
    @DisplayName("모든 로또 정보를 출력할 수 있다.")
    @Test
    void withFixture() {
      // given
      Lottery lottery = new Lottery(0);
      lottery.add(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
      lottery.add(new Lotto(List.of(2, 32, 40, 1, 5, 6)));
      lottery.add(new Lotto(List.of(33, 22, 11, 44, 5, 6)));

      // when
      String result = lottery.toString();

      // then
      assertThat(result)
          .isEqualToIgnoringWhitespace(
              """
                      [1, 2, 3, 4, 5, 6]
                      [1, 2, 5, 6, 32, 40]
                      [5, 6, 11, 22, 33, 44]
                      """);
    }
  }
}
