package lotto.common.responses;

import lotto.common.MessageContext;

public enum ResponseContext implements MessageContext {
    PURCHASED_LOTTO("개를 구매했습니다."),
    LOTTO_RESULT("당첨 통계");

    private final String message;

    ResponseContext(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
