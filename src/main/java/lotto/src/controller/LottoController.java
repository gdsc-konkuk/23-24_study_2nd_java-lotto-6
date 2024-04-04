package lotto.src.controller;

import lotto.src.model.Person;
import lotto.src.model.Shop;
import lotto.src.model.lotto.Lotto;
import lotto.src.model.Purchase;
import lotto.src.model.Result;
import lotto.src.view.InputView;
import lotto.src.view.OutputView;
import lotto.utils.Log;

import java.util.List;

public class LottoController {
    private final IOController ioController;
    private final Person person;
    private final Shop shop;
    private static final Log log = new Log();

    public LottoController() {
        this.ioController = new IOController(new InputView(), new OutputView());
        this.person = Person.of(ioController.purchasePriceInput());
        this.shop = Shop.of(person.getPurchaseAmount());
    }

    public void lottoStart() {
        buyLotto();
//        List<List<Integer>> purchasedLottos = getPurchasedLottos(price);
//        List<Integer> winningNumber = getWinningNumber();
//        Integer bonusNumber = getBonusNumber(winningNumber);
//
//        Result result = new Result(price, purchasedLottos, winningNumber, bonusNumber);
//        ioController.showLottoResult(result.getWinPrice(), result.getWinResult(), result.getProfitRate());
    }
    private void buyLotto() {
        person.buyLotto(shop.givePurchasedLotto());
        ioController.showPurchasedLotto(person.getLottos());
    }

//    private List<List<Integer>> getPurchasedLottos(Integer price) {
//        Purchase purchase = new Purchase(price);
//        List<List<Integer>> purchasedLottos = purchase.getPurchasedLottos();
//        ioController.showPurchasedLotto(purchasedLottos);
//        return purchasedLottos;
//    }
//
//    private List<Integer> getWinningNumber() {
//        List<Integer> winningNumbers = ioController.winningNumberInput();
//        Lotto lotto = new Lotto(winningNumbers);
//        return lotto.getNumbers();
//    }
//
//    private Integer getBonusNumber(List<Integer> winningNumber) {
//        Integer bonusNumber = ioController.bonusNumberInput(winningNumber);
//        return bonusNumber;
//    }
}
