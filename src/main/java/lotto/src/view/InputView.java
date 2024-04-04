package lotto.src.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.utils.Log;

import static lotto.common.message.Message.*;


public class InputView {
    private static final Log log = new Log();

    public String priceInput() {
        log.info(PRICE_REQUEST);
        String priceStr = Console.readLine();
        System.out.println();
        return priceStr;
    }

    public String winningNumberInput() {
        log.info(WINNING_NUMBER_REQUEST);
        String winningNumberStr = Console.readLine();
        System.out.println();
        return winningNumberStr;
    }

    public String bonusNumberInput() {
        log.info(BONUS_NUMBER_REQUEST);
        String bonusNumberStr = Console.readLine();
        System.out.println();
        return bonusNumberStr;
    }

}
