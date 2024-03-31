package lotto.src.controller;

import lotto.src.model.Lotto;
import lotto.src.model.Purchase;
import lotto.src.model.Result;

import java.util.List;

public class LottoController {
    private final IOController ioController;

    public LottoController() {
        this.ioController = new IOController();
    }

    public void lottoStart() {
        Integer price = ioController.lottoPriceInput();
        List<List<Integer>> purchasedLottos = purchaseLotto(price);
        List<Integer> winningNumber = ioController.getWinningNumber();
        Integer bonusNumber = ioController.getBonusNumber(winningNumber);

        Result result = new Result(price, purchasedLottos, winningNumber, bonusNumber);
        ioController.showLottoResult(result.getWinPrice(), result.getWinResult(), result.getProfitRate());
    }

    private List<List<Integer>> purchaseLotto(Integer price) {
        Purchase purchase = new Purchase(price);
        List<List<Integer>> purchasedLottos = purchase.getPurchasedLottos();
        ioController.showPurchasedLotto(purchasedLottos);
        return purchasedLottos;
    }
}
