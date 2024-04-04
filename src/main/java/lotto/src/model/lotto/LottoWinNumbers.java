package lotto.src.model.lotto;

import lotto.utils.Log;

import java.util.List;

public class LottoWinNumbers {
    private final List<Integer> winningNumber;
    private final Integer bonusNumber;
    private static final Log log = new Log();

    private LottoWinNumbers(List<Integer> winningNumber, Integer bonusNumber) {
        checkDuplicateNumbers(winningNumber, bonusNumber);
        this.winningNumber = winningNumber;
        this.bonusNumber = bonusNumber;
    }

    public static LottoWinNumbers of(List<Integer> winningNumber, Integer bonusNumber) {
        return new LottoWinNumbers(winningNumber, bonusNumber);
    }

    private static void checkDuplicateNumbers(List<Integer> winningNumber, Integer bonusNumber) {
        if (winningNumber.contains(bonusNumber)) {
            log.error("당첨번호와 보너스 번호가 중복됩니다.");
            throw new IllegalArgumentException();
        }
    }

    public List<Integer> getWinningNumber() {
        return winningNumber;
    }

    public Integer getBonusNumber() {
        return bonusNumber;
    }
}
