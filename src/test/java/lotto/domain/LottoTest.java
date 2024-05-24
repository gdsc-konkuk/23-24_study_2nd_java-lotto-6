package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
  @Nested
  class constructor {
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
    @MethodSource("generateOutRange")
    void byOutRanged(List<Integer> outRanged) {
      assertThatThrownBy(() -> new Lotto(outRanged))
          .hasMessage("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.")
          .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> generateOutRange() {
      return Stream.of(
          Arguments.of(List.of(1, 2, 3, 4, 5, 0)),
          Arguments.of(List.of(1, 2, -3, 4, 5, 8)),
          Arguments.of(List.of(1, 2, 13, 34, 45, 46)));
    }
  }

  @Nested
  class has {
    @DisplayName("숫자를 포함하고 있다면 `true`를 반환한다.")
    @Test
    void truePositive() {
      // given
      Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

      // when
      Boolean result = lotto.has(3);

      // then
      assertThat(result).isTrue();
    }

    @DisplayName("숫자를 포함하고 있지 않다면 `false`를 반환한다.")
    @Test
    void trueNegative() {
      // given
      Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

      // when
      Boolean result = lotto.has(7);

      // then
      assertThat(result).isFalse();
    }
  }

  @Nested
  class toString {
    @DisplayName("문자열로 바꿀 수 있다.")
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
