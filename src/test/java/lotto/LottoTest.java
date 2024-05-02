package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
  @Nested
  class create {
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void byOverSize() {
      assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
          .hasMessage("로또 번호는 6개의 숫자로 이뤄져야 합니다.")
          .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void byDuplicatedNumber() {
      assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
          .hasMessage("로또 번호는 중복이 없어야 합니다.")
          .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 1보다 작거나 45보다 크면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("generateOutOfRangeNumbers")
    void byOutOfRangedNumber(List<Integer> numbers) {
      assertThatThrownBy(() -> new Lotto(numbers))
          .hasMessage("로또 번호는 1부터 45 사이의 숫자여야 합니다.")
          .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> generateOutOfRangeNumbers() {
      return Stream.of(
          Arguments.of(List.of(1, 2, 3, 4, 5, 50)),
          Arguments.of(List.of(1, 2, -3, 4, 5, 8)),
          Arguments.of(List.of(1, 2, 13, 34, 45, 46)),
          Arguments.of(List.of(1, 2, 3, 14, 15, -6)),
          Arguments.of(List.of(11, 21, 31, 41, -15, 46)));
    }

    @DisplayName("로또를 랜덤하게 생성할 수 있다.")
    @RepeatedTest(10)
    void byRandom() {
      assertThatCode(Lotto::rand).doesNotThrowAnyException();
    }
  }
}
