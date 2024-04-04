package lotto.src.model;

import lotto.src.model.lotto.Lotto;
import lotto.utils.Log;

import java.util.List;

import static lotto.common.Constant.PRICE_DIVIDER_NUMBER;

public class Person {
    private List<Lotto> lottos;
    private final Integer purchaseAmount;
    private static final Log log = new Log();

    private Person(Integer purchaseAmount) {
        isDivisionOk(purchaseAmount);
        this.purchaseAmount = purchaseAmount;
    }

    public static Person of(Integer purchaseAmount) {
        return new Person(purchaseAmount);
    }

    public void buyLotto(List<Lotto> purchasedLotto) {
        this.lottos = purchasedLotto;
    }
    public Integer getPurchaseAmount() {
        return purchaseAmount;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    private void isDivisionOk(Integer input) {
        if (input % PRICE_DIVIDER_NUMBER != 0) {
            log.error("1000 단위의 숫자여야 합니다.");
            throw new NumberFormatException();
        }
    }

}
