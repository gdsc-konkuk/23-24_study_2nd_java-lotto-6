package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DrawTest {
  @Nested
  class parseWinningNumbers {
    @DisplayName("당첨 번호가 숫자 배열이 아니라면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"test", "12.0", "555$"})
    void byNonIntegerList(String nonIntegerListValue) {
      assertThatThrownBy(() -> Draw.parseWinningNumbers(nonIntegerListValue))
          .hasMessage("[ERROR] 당첨 번호는 숫자 배열이어야 합니다. ex) 1,2,3,4")
          .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호가 유효 범위(1 ~ 45)를 벗어나면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"0,1,2,3,4,5", "10,13,15,24,52,60", "4,42,5,33,-5,6"})
    void byOutOfRangedList(String outOfRangedList) {
      // when, then
      assertThatThrownBy(() -> Draw.parseWinningNumbers(outOfRangedList))
          .hasMessage("[ERROR] 당첨 번호는 1부터 45 사이의 숫자여야 합니다.")
          .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호가 6개가 아니라면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1,3,4,5", "1,2,3,4,5,6,7", "1,32,44,5,7"})
    void byNonUnit(String wrongLenList) {
      assertThatThrownBy(() -> Draw.parseWinningNumbers(wrongLenList))
          .hasMessage("[ERROR] 당첨 번호는 6개의 숫자로 이뤄져야 합니다.")
          .isInstanceOf(IllegalArgumentException.class);
    }
  }

  @Nested
  class parseBonusNumbers {
    @DisplayName("보너스 번호가 숫자 배열이 아니라면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"test", "12.0", "555$"})
    void byNonIntegerList(String nonIntegerListValue) {
      assertThatThrownBy(() -> Draw.parseBonusNumbers(nonIntegerListValue))
          .hasMessage("[ERROR] 보너스 번호는 숫자 배열이어야 합니다. ex) 1,2,3,4")
          .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 유효 범위(1 ~ 45)를 벗어나면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "100", "-4"})
    void byOutOfRangedList(String outOfRangedList) {
      // when, then
      assertThatThrownBy(() -> Draw.parseBonusNumbers(outOfRangedList))
          .hasMessage("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.")
          .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 1개가 아니라면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1,3,4,5", "1,2,3,4,5,6,7", "1,32,44,5,7"})
    void byNonUnit(String wrongLenList) {
      assertThatThrownBy(() -> Draw.parseBonusNumbers(wrongLenList))
          .hasMessage("[ERROR] 보너스 번호는 1개의 숫자로 이뤄져야 합니다.")
          .isInstanceOf(IllegalArgumentException.class);
    }
  }
}
