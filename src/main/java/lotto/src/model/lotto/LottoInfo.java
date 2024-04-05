package lotto.src.model.lotto;

import static lotto.common.Constant.*;

public enum LottoInfo {
    FIRST_WINNER(4,"6개 일치 (2,000,000,000원)", WIN_LOTTO_SIX_NUMBER),
    SECOND_WINNER(3, "5개 일치, 보너스 볼 일치 (30,000,000원)", WIN_LOTTO_FIVE_NUMBER_WITH_BONUS),
    THIRD_WINNER(2, "5개 일치 (1,500,000원)", WIN_LOTTO_FIVE_NUMBER),
    FOURTH_WINNER(1, "4개 일치 (50,000원)", WIN_LOTTO_FOUR_NUMBER),
    FIFTH_WINNER(0,"3개 일치 (5,000원)", WIN_LOTTO_THREE_NUMBER);

    private final Integer index;
    private final String info;
    private final Integer winningAmount;
    LottoInfo(Integer index, String info, Integer winningAmount) {
        this.index = index;
        this.info = info;
        this.winningAmount = winningAmount;
    }

    public String getInfo() {
        return info;
    }

    public Integer getWinningAmount() {
        return winningAmount;
    }

    public Integer getIndex() {
        return index;
    }
}
