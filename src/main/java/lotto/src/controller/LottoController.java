package lotto.src.controller;

import lotto.src.model.Person;
import lotto.src.model.Shop;
import lotto.src.model.lotto.LottoResult;
import lotto.src.model.lotto.LottoWinNumbers;
import lotto.src.view.InputView;
import lotto.src.view.OutputView;

import java.util.List;

public class LottoController {
    private final IOController ioController;
    private final Person person;
    private final Shop shop;
    private LottoResult lottoResult;

    public LottoController() {
        this.ioController = new IOController(new InputView(), new OutputView());
        this.person = Person.of(ioController.purchasePriceInput());
        this.shop = Shop.of(person.getPurchaseAmount());
    }

    public void lottoStart() {
        buyLotto();
        decideWinningNumber();
        showLottoResult();
    }
    private void buyLotto() {
        person.buyLotto(shop.givePurchasedLotto());
        ioController.showPurchasedLotto(person.getLottos());
    }

    private void decideWinningNumber() {
        List<Integer> numbers = ioController.winningNumberInput();
        Integer bonusNumber = ioController.bonusNumberInput(numbers);
        this.lottoResult = LottoResult.of(LottoWinNumbers.of(numbers, bonusNumber));
    }

    private void showLottoResult() {
        lottoResult.calculateResult(person.getLottos(), person.getPurchaseAmount());
        ioController.showLottoResult(lottoResult.toString());
    }
}
