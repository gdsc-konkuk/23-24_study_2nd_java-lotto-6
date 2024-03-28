package lotto.src.controller;

import lotto.src.model.Lotto;
import lotto.src.model.Result;

import java.util.List;

public class LottoController {
    private final IOController ioController;

    public LottoController() {
        this.ioController = new IOController();
    }

    public void lottoStart() {
        Integer price = ioController.lottoPriceInput();
        List<List<Integer>> purchasedLottos = ioController.purchaseLotto(price);
        List<Integer> winningNumber = ioController.getWinningNumber();
        Integer bonusNumber = ioController.getBonusNumber();

        Result result = new Result(purchasedLottos, winningNumber, bonusNumber);
    }
}
