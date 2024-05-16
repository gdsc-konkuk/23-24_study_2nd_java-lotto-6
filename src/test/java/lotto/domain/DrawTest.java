package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DrawTest {
  @Nested
  class constructor {
    @DisplayName("`WinningNumbers`와 `BonusNumber`로 생성할 수 있다.")
    @Test
    void creation() {
      // given
      WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
      BonusNumber bonusNumber = new BonusNumber(42);

      // when
      Draw draw = new Draw(winningNumbers, bonusNumber);

      // then
      assertThat(draw).isInstanceOf(Draw.class);
    }

    @DisplayName("당첨 번호와 보너스 번호가 겹치면 예외가 발생한다.")
    @Test
    void byOverlapped() {
      WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
      BonusNumber overlappedBonusNumber = new BonusNumber(2);

      // when, then
      assertThatThrownBy(() -> new Draw(winningNumbers, overlappedBonusNumber))
          .hasMessage("[ERROR] 보너스 번호는 당첨 번호와 겹치지 않아야 합니다.")
          .isInstanceOf(IllegalArgumentException.class);
    }
  }
}
