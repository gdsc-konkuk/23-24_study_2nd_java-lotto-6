package lotto;

import lotto.common.exceptions.InputException;
import lotto.src.controller.IOController;
import lotto.src.model.Lotto;
import lotto.src.model.Purchase;
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
            assertThatThrownBy(() -> new Purchase(Integer.parseInt("a")))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("0 이하의 정수를 입력하는 경우 예외가 발생한다.")
        @Test
        void createPriceByWrongRange() {
            assertThatThrownBy(() -> new Purchase(-1))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("1000원 단위로 나누어 떨어지지 않는 경우 예외가 발생한다.")
        @Test
        void createPriceByWrongDivision() {
            assertThatThrownBy(() -> new Purchase(1200))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @DisplayName("로또 번호 입력")
    @Nested
    class LottoInput {
        @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
        @Test
        void createLottoByOverSize() {
            assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
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