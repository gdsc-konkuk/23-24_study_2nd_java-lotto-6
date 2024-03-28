package lotto.src.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.List;

import static lotto.common.requests.RequestContext.*;

public class InputView {

    public String priceInput() {
        System.out.println(PRICE_REQUEST.getMessage());
        String priceStr = Console.readLine();
        System.out.println();
        return priceStr;
    }

    public String winningNumberInput() {
        System.out.println(WINNING_NUMBER_REQUEST.getMessage());
        String winningNumberStr = Console.readLine();
        System.out.println();
        return winningNumberStr;
    }

    public String bonusNumberInput() {
        System.out.println(BONUS_NUMBER_REQUEST.getMessage());
        String bonusNumberStr = Console.readLine();
        System.out.println();
        return bonusNumberStr;
    }

}
