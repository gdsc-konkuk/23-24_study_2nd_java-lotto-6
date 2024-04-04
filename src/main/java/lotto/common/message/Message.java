package lotto.common.message;

import static lotto.common.Constant.ERROR_LOG;

public class Message {

    // request
    public static final String PRICE_REQUEST = "구입 금액을 입력해주세요.";
    public static final String WINNING_NUMBER_REQUEST = "당첨 번호를 입력해 주세요.";
    public static final String BONUS_NUMBER_REQUEST = "보너스 번호를 입력해 주세요.";

    // response
    public static final String PURCHASED_LOTTO = "개를 구매했습니다.";
    public static final String LOTTO_RESULT = "당첨 통계";

    // exception
    public static final String PRICE_NOT_POSITIVE = ERROR_LOG + " 구입 금액은 양수로 입력해주세요.";
    public static final String PRICE_NOT_DIVIDED_BY_THOUSAND = ERROR_LOG + " 구입 금액은 1,000원 으로 나누어 떨어져야 합니다.";
    public static final String NUMBER_NOT_SPLIT_BY_COMMA = ERROR_LOG + " 로또 당첨 번호는 쉼표로 구분되어야 합니다.";
    public static final String NUMBER_RANGE_ERROR = ERROR_LOG + " 로또 번호는 1부터 45 사이의 숫자여야 합니다.";
    public static final String NUMBER_LIST_SIZE_ERROR = ERROR_LOG + " 로또 번호는 6개여야 합니다.";
    public static final String NUMBER_DUPLICATE_ERROR = ERROR_LOG + " 로또 번호는 중복되지 않아야 합니다.";
    public static final String NUMBER_WRONG_TYPE = ERROR_LOG + " 잘못된 타입의 입력입니다.";
    public static final String BONUS_NUMBER_DUPLICATE_ERROR = ERROR_LOG + " 보너스 번호는 당첨 번호와 중복되면 안됩니다.";
}
