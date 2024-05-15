package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;

class LottoTest {
  @Nested
  class create {
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void byOverSize() {
      assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
          .hasMessage("[ERROR] 로또 번호는 6개의 숫자로 이뤄져야 합니다.")
          .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void byDuplicatedNumber() {
      assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
          .hasMessage("[ERROR] 로또 번호는 중복이 없어야 합니다.")
          .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 1보다 작거나 45보다 크면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("generateOutOfRangeNumbers")
    void byOutOfRangedNumbers(List<Integer> numbers) {
      assertThatThrownBy(() -> new Lotto(numbers))
          .hasMessage("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.")
          .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> generateOutOfRangeNumbers() {
      return Stream.of(
          Arguments.of(List.of(1, 2, 3, 4, 5, 0)),
          Arguments.of(List.of(1, 2, -3, 4, 5, 8)),
          Arguments.of(List.of(1, 2, 13, 34, 45, 46)));
    }
  }

  @Nested
  class rand {
    @DisplayName("로또를 랜덤하게 생성할 수 있다.")
    @RepeatedTest(5)
    void byRandom() {
      // when
      Lotto lotto = Lotto.rand();

      // then
      assertThat(lotto).isInstanceOf(Lotto.class);
    }

    @DisplayName("로또를 camp.nextstep.edu.missionutils.Randoms를 활용해서 랜덤하게 생성한다.")
    @Test
    void byPickUniqueNumbersInRange() {
      try (MockedStatic<Randoms> mockedRandoms = Mockito.mockStatic(Randoms.class)) {
        // given
        mockedRandoms
            .when(() -> Randoms.pickUniqueNumbersInRange(1, 45, 6))
            .thenReturn(List.of(1, 2, 3, 4, 5, 6));

        // when
        Lotto lotto = Lotto.rand();

        // then
        mockedRandoms.verify(() -> Randoms.pickUniqueNumbersInRange(1, 45, 6), times(1));
        assertThat(lotto.toString()).isEqualTo("[1, 2, 3, 4, 5, 6]");
      }
    }
  }

  @Nested
  class toString {
    @DisplayName("정보를 문자열로 바꿀 수 있다.")
    @Test
    void convertToString() {
      // given
      Lotto lotto = new Lotto(List.of(1, 2, 3, 5, 21, 12));

      // when
      String str = lotto.toString();

      // then
      assertThat(str).isEqualTo("[1, 2, 3, 5, 12, 21]");
    }
  }
}
