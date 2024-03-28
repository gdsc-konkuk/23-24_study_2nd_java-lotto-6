package lotto.src.controller;

import lotto.common.exceptions.InputException;
import lotto.src.model.Bonus;
import lotto.src.model.Lotto;
import lotto.src.model.Purchase;
import lotto.src.view.InputView;
import lotto.src.view.OutputView;
import lotto.utils.Conversion;

import java.util.ArrayList;
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
                String priceStr = inputView.priceInput();
                price = Integer.parseInt(priceStr);
                InputException.validatePriceInput(price);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return price;
    }

    public List<List<Integer>> purchaseLotto(Integer price) {
        Purchase purchase = new Purchase(price);
        List<List<Integer>> purchasedLottos = purchase.getPurchasedLottos();
        outputView.purchasedLottosOutput(purchasedLottos);
        return purchasedLottos;
    }

    public List<Integer> getWinningNumber() {
        List<Integer> numbers = winningNumberInput();
        return numbers;
    }

    private List<Integer> winningNumberInput() {
        Lotto lotto;
        while (true) {
            try {
                String winningNumber = inputView.winningNumberInput();
                InputException.validateWinningNumberInput(winningNumber);
                ArrayList<Integer> winningNumbers = Conversion.stringWithCommaToArrayList(winningNumber);
                lotto = new Lotto(winningNumbers);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return lotto.getNumbers();
    }

    public Integer getBonusNumber() {
        Bonus bonus;
        while (true) {
            try {
                String bonusNumberStr = inputView.bonusNumberInput();
                Integer bonusNumber = Integer.parseInt(bonusNumberStr);
                InputException.validateBonusNumberInput(bonusNumber);
                bonus = new Bonus(bonusNumber);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return bonus.getBonusNumber();
    }
}
