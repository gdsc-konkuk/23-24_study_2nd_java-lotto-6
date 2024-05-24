package lotto.domain;

import camp.nextstep.edu.missionutils.Console;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;

class WinningNumbersTest {
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
    @ValueSource(strings = {"1,2,3,4,5,6", "13,42,33,22,11,6", "1,3,2,4,5,9"})
    void byStdin(String input) {
      // given
      mockedConsole.when(Console::readLine).thenReturn(input);

      // when
      WinningNumbers winningNumbers = WinningNumbers.fromUser();

      // then
      assertThat(winningNumbers).isInstanceOf(WinningNumbers.class);
      assertThat(outputStreamCaptor.toString()).isEqualToIgnoringWhitespace("당첨 번호를 입력해 주세요.");
      mockedConsole.verify(Console::readLine, times(1));
    }

    @DisplayName("입력이 숫자 배열이 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"test", "12.0", "5$"})
    void byNonNumberListInput(String input) {
      // given
      mockedConsole.when(Console::readLine).thenReturn(input);

      // when, then
      assertThatThrownBy(WinningNumbers::fromUser)
          .hasMessage("[ERROR] 당첨 번호는 숫자 배열이어야 합니다. ex) 1,2,3,4")
          .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("숫자가 6개가 아니라면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1,2,3", "34", "43,23,5,9,17,2,33"})
    void byWrongSizedInput(String input) {
      // given
      mockedConsole.when(Console::readLine).thenReturn(input);

      // when, then
      assertThatThrownBy(WinningNumbers::fromUser)
          .hasMessage("[ERROR] 당첨 번호는 6개의 숫자로 이뤄져야 합니다.")
          .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("범위를 벗어난 숫자를 포함하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1,3,46,2,9,10", "-1,2,3,4,5,6", "0,2,5,3,8,7"})
    void byOutRangedInput(String outRanged) {
      // given
      mockedConsole.when(Console::readLine).thenReturn(outRanged);

      // when, then
      assertThatThrownBy(WinningNumbers::fromUser)
          .hasMessage("[ERROR] 당첨 번호는 1부터 45 사이의 숫자여야 합니다.")
          .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력에 중복이 있으면 예외가 발생한다.")
    @Test
    void byDuplicatedInput() {
      // given
      mockedConsole.when(Console::readLine).thenReturn("1,2,3,3,4,5");

      // when, then
      assertThatThrownBy(WinningNumbers::fromUser)
          .hasMessage("[ERROR] 당첨 번호는 중복이 없어야 합니다.")
          .isInstanceOf(IllegalArgumentException.class);
    }
  }

  @Nested
  class constructor {
    @DisplayName("숫자 배열로 생성할 수 있다.")
    @Test
    void creation() {
      // given
      List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

      // when
      WinningNumbers winningNumbers = new WinningNumbers(numbers);

      // then
      assertThat(winningNumbers).isInstanceOf(WinningNumbers.class);
    }

    @DisplayName("길이가 6이 아닌 배열로 생성하면 예외가 발생한다.")
    @Test
    void byWrongSized() {
      // given
      List<Integer> wrongSized = List.of(1, 2, 3, 4, 5);

      // when, then
      assertThatThrownBy(() -> new WinningNumbers(wrongSized))
          .hasMessage("[ERROR] 당첨 번호는 6개의 숫자로 이뤄져야 합니다.")
          .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("범위를 벗어난 숫자를 포함한 배열로 생성하면 예외가 발생한다.")
    @Test
    void byOutRangedInput() {
      // given
      List<Integer> outRanged = List.of(1, 2, 3, 4, 5, -55);

      // when, then
      assertThatThrownBy(() -> new WinningNumbers(outRanged))
          .hasMessage("[ERROR] 당첨 번호는 1부터 45 사이의 숫자여야 합니다.")
          .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("중복이 있는 숫자 배열로 생성하면 예외가 발생한다.")
    @Test
    void byDuplicatedInput() {
      // given
      List<Integer> duplicated = List.of(1, 2, 3, 4, 5, 5);

      // when, then
      assertThatThrownBy(() -> new WinningNumbers(duplicated))
          .hasMessage("[ERROR] 당첨 번호는 중복이 없어야 합니다.")
          .isInstanceOf(IllegalArgumentException.class);
    }
  }

  @Nested
  class overlap {
    @DisplayName("보너스 번호와 중복이 있으면 `true`를 반환한다.")
    @Test
    void truePositive() {
      // given
      WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
      BonusNumber bonusNumber = new BonusNumber(3);

      // when
      boolean result = winningNumbers.overlap(bonusNumber);

      // then
      assertThat(result).isTrue();
    }

    @DisplayName("보너스 번호와 중복이 없으면 `false`를 반환한다.")
    @Test
    void trueNegative() {
      // given
      WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 13, 4, 5, 6));
      BonusNumber bonusNumber = new BonusNumber(3);

      // when
      boolean result = winningNumbers.overlap(bonusNumber);

      // then
      assertThat(result).isFalse();
    }
  }

  @Nested
  class compare {
    @DisplayName("로또와 겹치는 번호의 개수를 반환한다.")
    @ParameterizedTest
    @MethodSource({"genCompareTestInput"})
    void countOverlap(WinningNumbers winningNumbers, Lotto lotto, int count) {
      // when
      int result = winningNumbers.compare(lotto);

      // then
      assertThat(result).isEqualTo(count);
    }

    private static Stream<Arguments> genCompareTestInput() {
      Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

      WinningNumbers winningNumbers1 = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
      WinningNumbers winningNumbers2 = new WinningNumbers(List.of(1, 12, 33, 44, 5, 6));
      WinningNumbers winningNumbers3 = new WinningNumbers(List.of(1, 2, 23, 4, 15, 6));

      return Stream.of(
          Arguments.of(winningNumbers1, lotto, 6),
          Arguments.of(winningNumbers2, lotto, 3),
          Arguments.of(winningNumbers3, lotto, 4));
    }
  }
}
