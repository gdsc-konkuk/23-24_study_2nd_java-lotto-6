package lotto.src.controller;

import lotto.common.exceptions.InputException;
import lotto.src.view.InputView;
import lotto.src.view.OutputView;

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

}
