package lotto.src.model;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.common.exceptions.InputException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static lotto.common.Constant.PRICE_DIVIDER_NUMBER;

public class Purchase {
    private final Integer buyPrice;
    private final List<List<Integer>> purchasedLottos;

    public Purchase(Integer price) {
        InputException.validatePriceInput(String.valueOf(price));
        buyPrice = price;
        purchasedLottos = new ArrayList<>();
        buyLotto(price);
    }

    private void buyLotto(Integer price) {
        int buyCount = getBuyCount(price);
        for (int i = 0; i < buyCount; i++) {
            List<Integer> numbers = new ArrayList<>(Randoms.pickUniqueNumbersInRange(1, 45, 6));
            Collections.sort(numbers);
            this.purchasedLottos.add(numbers);
        }
    }

    private Integer getBuyCount(Integer price) {
        return price / PRICE_DIVIDER_NUMBER;
    }


    public Integer getBuyPrice() {
        return buyPrice;
    }

    public List<List<Integer>> getPurchasedLottos() {
        return purchasedLottos;
    }
}
