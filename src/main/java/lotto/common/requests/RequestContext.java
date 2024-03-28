package lotto.common.requests;

import lotto.common.MessageContext;

public enum RequestContext implements MessageContext {
    PRICE_REQUEST_MESSAGE("구입 금액을 입력해주세요.");

    private final String message;

    RequestContext(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
