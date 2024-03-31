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
        Integer price = getBuyPrice();
        List<List<Integer>> purchasedLottos = getPurchasedLottos(price);
        List<Integer> winningNumber = getWinningNumber();
        Integer bonusNumber = getBonusNumber(winningNumber);

        Result result = new Result(price, purchasedLottos, winningNumber, bonusNumber);
        ioController.showLottoResult(result.getWinPrice(), result.getWinResult(), result.getProfitRate());
    }

    private Integer getBuyPrice() {
        Integer price = ioController.purchasePriceInput();
        return price;
    }

    private List<List<Integer>> getPurchasedLottos(Integer price) {
        Purchase purchase = new Purchase(price);
        List<List<Integer>> purchasedLottos = purchase.getPurchasedLottos();
        ioController.showPurchasedLotto(purchasedLottos);
        return purchasedLottos;
    }

    private List<Integer> getWinningNumber() {
        List<Integer> winningNumbers = ioController.winningNumberInput();
        Lotto lotto = new Lotto(winningNumbers);
        return lotto.getNumbers();
    }

    private Integer getBonusNumber(List<Integer> winningNumber) {
        Integer bonusNumber = ioController.bonusNumberInput(winningNumber);
        return bonusNumber;
    }
}
