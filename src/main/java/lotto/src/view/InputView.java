package lotto.src.view;

import camp.nextstep.edu.missionutils.Console;

import static lotto.common.requests.RequestContext.PRICE_REQUEST_MESSAGE;

public class InputView {

    public Integer priceInput() {
        System.out.println(PRICE_REQUEST_MESSAGE.getMessage());
        String price = Console.readLine();
        return Integer.parseInt(price);
    }

}
