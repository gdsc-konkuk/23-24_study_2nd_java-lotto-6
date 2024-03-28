package lotto.common.exceptions;

import lotto.common.MessageContext;

public enum ExceptionContext implements MessageContext {
    PRICE_NOT_POSITIVE("[ERROR] 구입 금액은 양수로 입력해주세요."),
    PRICE_NOT_DIVIDED_BY_THOUSAND("[ERROR] 구입 금액은 1,000원 으로 나누어 떨어져야 합니다."),
    NUMBER_NOT_SPLIT_BY_COMMA("[ERROR] 로또 당첨 번호는 쉼표로 구분되어야 합니다."),
    NUMBER_RANGE_ERROR("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    NUMBER_LIST_SIZE_ERROR("[ERROR] 로또 번호는 6개여야 합니다.");

    private final String message;

    ExceptionContext(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
