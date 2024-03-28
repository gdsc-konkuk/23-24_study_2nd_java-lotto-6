package lotto.src.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.List;

import static lotto.common.requests.RequestContext.*;

public class InputView {

    public String priceInput() {
        System.out.println(PRICE_REQUEST.getMessage());
        String priceStr = Console.readLine();
        return priceStr;
    }

    public String winningNumberInput() {
        System.out.println(WINNING_NUMBER_REQUEST.getMessage());
        String winningNumberStr = Console.readLine();
        return winningNumberStr;
    }

}
