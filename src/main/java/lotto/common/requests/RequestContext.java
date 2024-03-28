package lotto.common.requests;

import lotto.common.MessageContext;

public enum RequestContext implements MessageContext {
    PRICE_REQUEST("구입 금액을 입력해주세요."),
    WINNING_NUMBER_REQUEST("당첨 번호를 입력해 주세요.");

    private final String message;

    RequestContext(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
