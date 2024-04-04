package lotto;

import lotto.src.model.Shop;
import lotto.src.model.lotto.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("로또 테스트")
class LottoTest {

    @DisplayName("구입 금액 입력")
    @Nested
    class priceInput {

        @DisplayName("정수형 숫자를 입력하지 않으면 예외가 발생한다.")
        @Test
        void createPriceByWrongType() {
            assertThatThrownBy(() -> new Shop(Integer.parseInt("a")))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("0 이하의 정수를 입력하는 경우 예외가 발생한다.")
        @Test
        void createPriceByWrongRange() {
            assertThatThrownBy(() -> new Shop(-1))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("1000원 단위로 나누어 떨어지지 않는 경우 예외가 발생한다.")
        @Test
        void createPriceByWrongDivision() {
            assertThatThrownBy(() -> new Shop(1200))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @DisplayName("로또 당첨 번호 입력")
    @Nested
    class LottoInput {
        @DisplayName("로또 번호가 정수형 숫자가 아니면 예외가 발생한다.")
        @Test
        void createLottoByWrongType() {
            assertThatThrownBy(() -> new Lotto(List.of(Integer.parseInt("a"), 2, 3, 4, 5, 6, 7)))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("로또 번호가 쉼표로 구분되어 있지 않은 경우 예외가 발생한다.")
        @Test
        void createLottoByWrongFormat() {
            assertThatThrownBy(() -> new Lotto(List.of(1234567)))
                    .isInstanceOf(IllegalArgumentException.class);
        }
        @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
        @Test
        void createLottoByOverSize() {
            assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("로또 번호가 1부터 45 사이의 숫자가 아니면 예외가 발생한다.")
        @Test
        void createLottoByWrongRange() {
            assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 47, -1)))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
        @Test
        void createLottoByDuplicatedNumber() {
            assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

}