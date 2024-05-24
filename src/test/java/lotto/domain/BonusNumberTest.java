package lotto.domain;

import camp.nextstep.edu.missionutils.Console;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;

class BonusNumberTest {
  @Nested
  class fromUser {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    MockedStatic<Console> mockedConsole;

    @BeforeEach
    public void setup() {
      System.setOut(new PrintStream(outputStreamCaptor));
      mockedConsole = Mockito.mockStatic(Console.class);
    }

    @AfterEach
    public void teardown() {
      System.setOut(System.out);
      mockedConsole.close();
    }

    @DisplayName("`stdin`으로 입력받을 수 있다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "42", "9"})
    void byStdin(String input) {
      // given
      mockedConsole.when(Console::readLine).thenReturn(input);

      // when
      BonusNumber bonusNumber = BonusNumber.fromUser();

      // then
      assertThat(bonusNumber).isInstanceOf(BonusNumber.class);
      assertThat(outputStreamCaptor.toString()).isEqualToIgnoringWhitespace("보너스 번호를 입력해 주세요.");
      mockedConsole.verify(Console::readLine, times(1));
    }

    @DisplayName("입력이 숫자가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"test", "12.0", "5$"})
    void byNonNumberListInput(String input) {
      // given
      mockedConsole.when(Console::readLine).thenReturn(input);

      // when, then
      assertThatThrownBy(BonusNumber::fromUser)
          .hasMessage("[ERROR] 보너스 번호는 숫자여야 합니다.")
          .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("범위를 벗어난 숫자를 포함하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"46", "-1", "0"})
    void byOutRangedInput(String outRanged) {
      // given
      mockedConsole.when(Console::readLine).thenReturn(outRanged);

      // when, then
      assertThatThrownBy(BonusNumber::fromUser)
          .hasMessage("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.")
          .isInstanceOf(IllegalArgumentException.class);
    }
  }

  @Nested
  class constructor {
    @DisplayName("숫자로 생성할 수 있다.")
    @Test
    void creation() {
      // given
      int number = 42;

      // when
      BonusNumber bonusNumber = new BonusNumber(number);

      // then
      assertThat(bonusNumber).isInstanceOf(BonusNumber.class);
    }

    @DisplayName("범위를 벗어난 숫자로 생성하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-5, 78, 0})
    void byOutRanged(int outRanged) {
      // when, then
      assertThatThrownBy(() -> new BonusNumber(outRanged))
          .hasMessage("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.")
          .isInstanceOf(IllegalArgumentException.class);
    }
  }

  @Nested
  class belonged {
    @DisplayName("로또에 포함될 경우 `true`를 반환한다.")
    @Test
    void truePositive() {
      // given
      BonusNumber bonusNumber = new BonusNumber(42);
      Lotto lotto = new Lotto(List.of(1, 3, 5, 42, 7, 19));

      // when
      boolean result = bonusNumber.belonged(lotto);

      // then
      assertThat(result).isTrue();
    }

    @DisplayName("로또에 포함되지 않을 경우 `false`를 반환한다.")
    @Test
    void trueNegative() {
      // given
      BonusNumber bonusNumber = new BonusNumber(42);
      Lotto lotto = new Lotto(List.of(1, 3, 5, 43, 7, 19));

      // when
      boolean result = bonusNumber.belonged(lotto);

      // then
      assertThat(result).isFalse();
    }
  }

  @Nested
  class equals {
    @DisplayName("숫자가 `number`와 같을 경우 `true`를 반환한다.")
    @Test
    void truePositive() {
      // given
      BonusNumber bonusNumber = new BonusNumber(42);
      int target = 42;

      // when
      boolean result = bonusNumber.equals(target);

      // then
      assertThat(result).isTrue();
    }

    @DisplayName("숫자가 `number`와 같지 않을 경우 `false`를 반환한다.")
    @Test
    void trueNegative() {
      // given
      BonusNumber bonusNumber = new BonusNumber(42);
      int target = 7;

      // when
      boolean result = bonusNumber.equals(target);

      // then
      assertThat(result).isFalse();
    }
  }
}
