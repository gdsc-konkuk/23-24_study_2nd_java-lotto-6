package lotto.model.lotto;

import lotto.util.Log;

import java.util.List;

public class LottoWinRequirements {
    private final List<Integer> winningNumber;
    private final Integer bonusNumber;
    private static final Log log = new Log();

    private LottoWinRequirements(List<Integer> winningNumber, Integer bonusNumber) {
        checkDuplicateNumbers(winningNumber, bonusNumber);
        this.winningNumber = winningNumber;
        this.bonusNumber = bonusNumber;
    }

    public static LottoWinRequirements of(List<Integer> winningNumber, Integer bonusNumber) {
        return new LottoWinRequirements(winningNumber, bonusNumber);
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

    public int getBonusNumber() {
        return bonusNumber;
    }
}
