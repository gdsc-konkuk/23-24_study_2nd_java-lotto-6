package lotto.src.model;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Purchase {
    private final List<List<Integer>> purchasedLottos;

    public Purchase(Integer price) {
        purchasedLottos = new ArrayList<>();
        buyLotto(price);
    }

    private void buyLotto(Integer price) {
        int buyCount = getBuyCount(price);
        for (int i = 0; i < buyCount; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            numbers.sort(Comparator.naturalOrder());
            this.purchasedLottos.add(numbers);
        }
    }

    private Integer getBuyCount(Integer price) {
        return price / 1000;
    }

    public List<List<Integer>> getPurchasedLottos() {
        return purchasedLottos;
    }
}
