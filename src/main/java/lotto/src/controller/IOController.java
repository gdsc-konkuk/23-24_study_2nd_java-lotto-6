package lotto.src.controller;

import lotto.common.exceptions.InputException;
import lotto.src.model.lotto.Lotto;
import lotto.src.view.InputView;
import lotto.src.view.OutputView;
import lotto.utils.Conversion;

import java.util.List;
import java.util.Map;


public class IOController {
    private final InputView inputView;
    private final OutputView outputView;


    public IOController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public Integer purchasePriceInput() {
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

    public void showPurchasedLotto(List<Lotto> purchasedLottos) {
        outputView.purchasedLottosOutput(purchasedLottos);
    }

    public List<Integer> winningNumberInput() {
        List<Integer> winningNumbers;
        while (true) {
            try {
                String winningNumber = inputView.winningNumberInput();
                InputException.validateWinningNumbers(winningNumber);
                winningNumbers = Conversion.stringWithCommaToArrayList(winningNumber);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return winningNumbers;
    }

    public Integer bonusNumberInput(List<Integer> winningNumber) {
        Integer bonusNumber;
        while (true) {
            try {
                String bonusNumberStr = inputView.bonusNumberInput();
                InputException.validateBonusNumberInput(winningNumber, bonusNumberStr);
                bonusNumber = Integer.parseInt(bonusNumberStr);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return bonusNumber;
    }

    public void showLottoResult(String lottoResult) {
        outputView.lottoResultOutput(lottoResult);
    }
}
