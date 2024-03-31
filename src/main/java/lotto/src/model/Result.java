package lotto.src.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lotto.common.Constant.*;

public class Result {
    private final Integer buyPrice;
    private final List<List<Integer>> lottos;
    private final List<Integer> winningNumber;
    private final Integer bonusNumber;
    private Map<Integer, Integer> winResult;
    private Map<Integer, Integer> winPrice;

    private Double profitRate;

    public Result(Integer buyPrice, List<List<Integer>> lottos, List<Integer> winningNumber, Integer bonusNumber) {
        this.buyPrice = buyPrice;
        this.lottos = lottos;
        this.winningNumber = winningNumber;
        this.bonusNumber = bonusNumber;
        initializeWinResult();
        initializeWinPrice();
        calculateLottoResult();
    }

    public void calculateLottoResult() {
        for (List<Integer> lotto : lottos) {
            Integer equalCount = getEqualCount(lotto);
            insertResult(equalCount);
        }
        calculateProfitRate();
    }

    private void initializeWinResult() {
        this.winResult = new HashMap<>();
        winResult.put(3, 0);
        winResult.put(4, 0);
        winResult.put(5, 0);
        winResult.put(6, 0);
        winResult.put(7, 0);
    }

    private void initializeWinPrice() {
        this.winPrice = new HashMap<>();
        winPrice.put(3, WIN_LOTTO_THREE_NUMBER);
        winPrice.put(4, WIN_LOTTO_FOUR_NUMBER);
        winPrice.put(5, WIN_LOTTO_FIVE_NUMBER);
        winPrice.put(6, WIN_LOTTO_FIVE_NUMBER_WITH_BONUS);
        winPrice.put(7, WIN_LOTTO_SIX_NUMBER);
    }

    private Integer getEqualCount(List<Integer> lotto) {
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
            Integer count = winResult.get(equalCount);
            count += 1;
            winResult.put(equalCount, count);
        }
    }

    private void calculateProfitRate() {
        int totalWinPrice = 0;
        for (int i = 3; i <= 7; i++) {
            totalWinPrice += winResult.get(i) * winPrice.get(i);
        }
        double profitRateWithoutRound = ((double) totalWinPrice / (double) buyPrice) * 100;
        profitRate = Math.round(profitRateWithoutRound * 10.0) / 10.0;
    }

    public Map<Integer, Integer> getWinResult() {
        return winResult;
    }

    public Map<Integer, Integer> getWinPrice() {
        return winPrice;
    }

    public Double getProfitRate() {
        return profitRate;
    }
}
