package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final List<Lotto> lottoList = new ArrayList<>();
    private int price;
    public void start() {
        purchase();
    }

    private void purchase() {
        PurchaseHandler purchase = new PurchaseHandler();
        price = purchase.getValidPurchaseAmount();
    }

}
