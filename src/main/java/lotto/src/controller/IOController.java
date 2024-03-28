package lotto.src.controller;

import lotto.common.exceptions.InputException;
import lotto.src.model.Purchase;
import lotto.src.view.InputView;
import lotto.src.view.OutputView;

import java.util.List;

public class IOController {
    private final InputView inputView;
    private final OutputView outputView;


    public IOController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public Integer lottoPriceInput() {
        Integer price;
        while(true) {
            try {
                price = inputView.priceInput();
                InputException.validatePriceInput(price);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return price;
    }

    public void purchaseLotto(Integer price) {
        Purchase purchase = new Purchase(price);
        List<List<Integer>> purchasedLottos = purchase.getPurchasedLottos();
        outputView.purchasedLottosOutput(purchasedLottos);
    }

}
