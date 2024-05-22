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

  @Nested
  class compare {
    @DisplayName("1등상을 구별할 수 있다.")
    @Test
    void first() {
      // given
      Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
      Draw draw = new Draw(new WinningNumbers(List.of(1, 2, 3, 4, 5, 6)), new BonusNumber(7));

      // when
      Prize prize = draw.compare(lotto);

      // then
      assertThat(prize).isEqualTo(Prize.FIRST);
    }

    @DisplayName("2등상을 구별할 수 있다.")
    @Test
    void second() {
      // given
      Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));
      Draw draw = new Draw(new WinningNumbers(List.of(1, 2, 3, 4, 5, 6)), new BonusNumber(7));

      // when
      Prize prize = draw.compare(lotto);

      // then
      assertThat(prize).isEqualTo(Prize.SECOND);
    }

    @DisplayName("3등상을 구별할 수 있다.")
    @Test
    void third() {
      // given
      Lotto lotto = new Lotto(List.of(1, 2, 33, 4, 5, 6));
      Draw draw = new Draw(new WinningNumbers(List.of(1, 2, 3, 4, 5, 6)), new BonusNumber(7));

      // when
      Prize prize = draw.compare(lotto);

      // then
      assertThat(prize).isEqualTo(Prize.THIRD);
    }

    @DisplayName("4등상을 구별할 수 있다.")
    @Test
    void fourth() {
      // given
      Lotto lotto = new Lotto(List.of(1, 22, 23, 4, 5, 6));
      Draw draw = new Draw(new WinningNumbers(List.of(1, 2, 3, 4, 5, 6)), new BonusNumber(7));

      // when
      Prize prize = draw.compare(lotto);

      // then
      assertThat(prize).isEqualTo(Prize.FOURTH);
    }

    @DisplayName("5등상을 구별할 수 있다.")
    @Test
    void fifth() {
      // given
      Lotto lotto = new Lotto(List.of(1, 22, 3, 34, 35, 6));
      Draw draw = new Draw(new WinningNumbers(List.of(1, 2, 3, 4, 5, 6)), new BonusNumber(7));

      // when
      Prize prize = draw.compare(lotto);

      // then
      assertThat(prize).isEqualTo(Prize.FIFTH);
    }

    @DisplayName("당첨되지 않은 경우를 구별할 수 있다.")
    @Test
    void byLotto() {
      // given
      Lotto lotto = new Lotto(List.of(11, 12, 13, 14, 5, 6));
      Draw draw = new Draw(new WinningNumbers(List.of(1, 2, 3, 4, 5, 6)), new BonusNumber(7));

      // when
      Prize prize = draw.compare(lotto);

      // then
      assertThat(prize).isEqualTo(Prize.NOT_WIN);
    }
  }
}
