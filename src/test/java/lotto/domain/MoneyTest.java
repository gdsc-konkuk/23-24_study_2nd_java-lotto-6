package lotto.domain;

import camp.nextstep.edu.missionutils.Console;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
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

class MoneyTest {
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
    @ValueSource(strings = {"3000", "12000", "5000"})
    void byStdin(String input) {
      // given
      mockedConsole.when(Console::readLine).thenReturn(input);

      // when
      Money money = Money.fromUser();

      // then
      assertThat(money).isInstanceOf(Money.class);
      assertThat(outputStreamCaptor.toString()).isEqualToIgnoringWhitespace("금액을 입력해 주세요.");
      mockedConsole.verify(Console::readLine, times(1));
    }

    @DisplayName("입력이 숫자가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"test", "12.0", "555$"})
    void byNonNumberInput(String input) {
      // given
      mockedConsole.when(Console::readLine).thenReturn(input);

      // when, then
      assertThatThrownBy(Money::fromUser)
          .hasMessage("[ERROR] 금액은 숫자여야 합니다.")
          .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("1000원 단위가 아닌 입력에는 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1200", "2300", "3050"})
    void byNonUnitInput(String input) {
      // given
      mockedConsole.when(Console::readLine).thenReturn(input);

      // when, then
      assertThatThrownBy(Money::fromUser)
          .hasMessage("[ERROR] 금액 단위는 1,000원입니다.")
          .isInstanceOf(IllegalArgumentException.class);
    }
  }

  @Nested
  class total {
    @DisplayName("총액을 알 수 있다.")
    @ParameterizedTest()
    @MethodSource({"genTotalTestInput"})
    void byGenerator(List<Money> left, int right) {
      // when
      Money result = Money.total(left);

      // then
      assertThat(result).hasFieldOrPropertyWithValue("value", right);
    }

    private static Stream<Arguments> genTotalTestInput() {
      Money money5000 = new Money(5_000);
      Money money3000 = new Money(3_000);
      Money money1000 = new Money(1_000);

      return Stream.of(
          Arguments.of(List.of(money3000, money1000, money1000), 3_000 + 1_000 + 1_000),
          Arguments.of(List.of(money3000, money5000, money1000), 3_000 + 5_000 + 1_000),
          Arguments.of(List.of(money3000, money5000, money5000), 3_000 + 5_000 + 5_000));
    }
  }

  @Nested
  class constructor {
    @DisplayName("정수로 생성할 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {3_000, 12_000, 5_000})
    void byStdin(int value) {
      // when
      Money money = new Money(value);

      // then
      assertThat(money).isInstanceOf(Money.class);
    }

    @DisplayName("1000원 단위가 아닌 입력에는 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {1_200, 2_300, 50})
    void byNonUnit(int nonUnitValue) {
      // when, then
      assertThatThrownBy(() -> new Money(nonUnitValue))
          .hasMessage("[ERROR] 금액 단위는 1,000원입니다.")
          .isInstanceOf(IllegalArgumentException.class);
    }
  }

  @Nested
  class divide {
    @DisplayName("값을 나눌 수 있다.")
    @ParameterizedTest()
    @CsvSource({"2000,1000", "3000,2000", "12000,4000"})
    void byValue(int left, int right) {
      // given
      Money money1 = new Money(left);
      Money money2 = new Money(right);

      // when
      int result = money1.divide(money2);

      // then
      assertThat(result).isEqualTo(left / right);
    }
  }

  @Nested
  class gte {
    @DisplayName("더 작은 돈과 비교하면 `false`를 반환한다.")
    @Test
    void byLess() {
      // given
      Money greater = new Money(3_000);
      Money less = new Money(2_000);

      // when
      boolean result = greater.gte(less);

      // then
      assertThat(result).isFalse();
    }

    @DisplayName("더 큰 돈과 비교하면 `true`를 반환한다.")
    @Test
    void byGreater() {
      // given
      Money greater = new Money(3_000);
      Money less = new Money(2_000);

      // when
      boolean result = less.gte(greater);

      // then
      assertThat(result).isTrue();
    }

    @DisplayName("같은 돈과 비교하면 `true`를 반환한다.")
    @Test
    void bySame() {
      // given
      Money same1 = new Money(3_000);
      Money same2 = new Money(3_000);

      // when
      boolean result = same1.gte(same2);

      // then
      assertThat(result).isTrue();
    }
  }
}
