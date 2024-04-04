package lotto.src.model.lotto;

import lotto.utils.Conversion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lotto.src.model.lotto.LottoInfo.*;

public class LottoResult {
    private final LottoWinNumbers lottoWinNumbers;
    private Map<Integer, Integer> winResult;
    private Double profitRate;

    private LottoResult(LottoWinNumbers lottoWinNumbers) {
        this.lottoWinNumbers = lottoWinNumbers;
        initializeWinResult();
    }

    public static LottoResult of(LottoWinNumbers lottoWinNumbers) {
        return new LottoResult(lottoWinNumbers);
    }

    private void initializeWinResult() {
        this.winResult = new HashMap<>();
        winResult.put(0, 0); // 5등
        winResult.put(1, 0); // 4등
        winResult.put(2, 0); // 3등
        winResult.put(3, 0); // 2등
        winResult.put(4, 0); // 1등
    }

    public void calculateResult(List<Lotto> lottos, Integer purchaseAmount) {
        List<Integer> winningNumber = lottoWinNumbers.getWinningNumber();
        Integer bonusNumber = lottoWinNumbers.getBonusNumber();
        for (Lotto lotto : lottos) {
            Integer equalCount = getEqualCount(lotto.getNumbers(), winningNumber, bonusNumber);
            insertResult(equalCount);
        }
        calculateProfitRate(purchaseAmount);
    }

    private Integer getEqualCount(List<Integer> lotto, List<Integer> winningNumber, Integer bonusNumber) {
        Integer equalCount = 0;
        for (Integer number : lotto) {
            if (winningNumber.contains(number)) {
                equalCount += 1;
            }
        }

        if (equalCount.equals(6)) { // 6개 일치
            equalCount += 1;
        }

        if (equalCount.equals(5) && lotto.contains(bonusNumber)) { // 5개 일치 + 보너스 볼 일치
            equalCount += 1;
        }

        return equalCount;
    }

    private void insertResult(Integer equalCount) {
        if (equalCount >= 3 && equalCount <= 7) {
            int index = equalCount - 3;
            Integer count = winResult.get(index);
            count += 1;
            winResult.put(index, count);
        }
    }

    private void calculateProfitRate(Integer purchaseAmount) {
        int totalWinPrice = calculateTotalWinPrice();
        double profitRateWithoutRound = ((double) totalWinPrice / (double) purchaseAmount) * 100;
        profitRate = Math.round(profitRateWithoutRound * 10.0) / 10.0;
    }

    private int calculateTotalWinPrice() {
        int totalWinPrice = 0;
        totalWinPrice += winResult.get(FIFTH_WINNER.getIndex()) * FIFTH_WINNER.getWinningAmount();
        totalWinPrice += winResult.get(FOURTH_WINNER.getIndex()) * FOURTH_WINNER.getWinningAmount();
        totalWinPrice += winResult.get(THIRD_WINNER.getIndex()) * THIRD_WINNER.getWinningAmount();
        totalWinPrice += winResult.get(SECOND_WINNER.getIndex()) * SECOND_WINNER.getWinningAmount();
        totalWinPrice += winResult.get(FIRST_WINNER.getIndex()) * FIRST_WINNER.getWinningAmount();
        return totalWinPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(LottoInfo.FIFTH_WINNER.getInfo()).append(" - ").append(winResult.get(0)).append("개").append("\n");
        sb.append(LottoInfo.FOURTH_WINNER.getInfo()).append(" - ").append(winResult.get(1)).append("개").append("\n");
        sb.append(LottoInfo.THIRD_WINNER.getInfo()).append(" - ").append(winResult.get(2)).append("개").append("\n");
        sb.append(LottoInfo.SECOND_WINNER.getInfo()).append(" - ").append(winResult.get(3)).append("개").append("\n");
        sb.append(LottoInfo.FIRST_WINNER.getInfo()).append(" - ").append(winResult.get(4)).append("개").append("\n");
        sb.append("총 수익률은 ").append(profitRate).append("%입니다.");
        return sb.toString();
    }
}
