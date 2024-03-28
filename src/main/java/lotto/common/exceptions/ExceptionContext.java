package lotto.common.exceptions;

import lotto.common.MessageContext;

public enum ExceptionContext implements MessageContext {
    PRICE_NOT_POSITIVE_MESSAGE("[ERROR] 구입 금액은 양수로 입력해주세요."),
    PRICE_NOT_DIVIDED_BY_THOUSAND("[ERROR] 구입 금액은 1,000원 으로 나누어 떨어져야 합니다.");

    private final String message;

    ExceptionContext(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
