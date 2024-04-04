package lotto.src.model;

import lotto.src.model.lotto.Lotto;
import lotto.src.model.lotto.LottoMachine;
import lotto.utils.Log;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private final Integer purchaseNumber;
    private static final Log log = new Log();

    public Shop(Integer purchaseAmount) {
        isPurchaseAmountValid(purchaseAmount);
        this.purchaseNumber = purchaseAmount / 1000;
    }

    private void isPurchaseAmountValid(Integer purchaseAmount) {
        if (purchaseAmount % 1000 != 0) {
            log.error("1000 단위의 숫자여야 합니다.");
            throw new IllegalArgumentException();
        }
    }

    public static Shop of(Integer purchaseAmount) {
        return new Shop(purchaseAmount);
    }

    public List<Lotto> givePurchasedLotto(){
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < purchaseNumber; i++) {
            Lotto lotto = LottoMachine.generateLotto();
            lottos.add(lotto);
        }
        return lottos;
    }
}
