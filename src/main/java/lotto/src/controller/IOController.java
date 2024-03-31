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
import java.util.Map;

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
                InputException.validatePriceInput(priceStr);
                price = Integer.parseInt(priceStr);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return price;
    }

    public List<List<Integer>> showPurchasedLotto(List<List<Integer>> purchasedLottos) {
        outputView.purchasedLottosOutput(purchasedLottos);
        return purchasedLottos;
    }

    public List<Integer> getWinningNumber() {
        List<Integer> numbers = winningNumberInput();
        return numbers;
    }

    public Integer getBonusNumber(List<Integer> winningNumber) {
        Integer number = bonusNumberInput(winningNumber);
        return number;
    }

    private List<Integer> winningNumberInput() {
        Lotto lotto;
        while (true) {
            try {
                String winningNumber = inputView.winningNumberInput();
                ArrayList<Integer> winningNumbers = Conversion.stringWithCommaToArrayList(winningNumber);
                InputException.validateWinningNumbers(Conversion.stringWithCommaToArrayList(winningNumber));
                lotto = new Lotto(winningNumbers);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return lotto.getNumbers();
    }

    public Integer bonusNumberInput(List<Integer> winningNumber) {
        Bonus bonus;
        while (true) {
            try {
                String bonusNumberStr = inputView.bonusNumberInput();
                InputException.validateBonusNumberInput(bonusNumberStr);
                InputException.validateBonusNumberInput(winningNumber, Integer.parseInt(bonusNumberStr));
                bonus = new Bonus(Integer.parseInt(bonusNumberStr));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return bonus.getBonusNumber();
    }

    public void showLottoResult(Map<Integer, Integer> price, Map<Integer, Integer> result, Double profitRate) {
        outputView.getLottoResultOutput(price, result, profitRate);
    }
}
