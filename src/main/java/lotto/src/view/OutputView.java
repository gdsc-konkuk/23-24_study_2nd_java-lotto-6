package lotto.src.view;

import lotto.src.model.lotto.Lotto;
import lotto.utils.Conversion;
import lotto.utils.Log;

import java.util.List;
import java.util.Map;

import static lotto.common.message.Message.PURCHASED_LOTTO;
import static lotto.common.message.Message.LOTTO_RESULT;

public class OutputView {
    private static final Log log = new Log();
    public void purchasedLottosOutput(List<Lotto> lottos) {
        log.info(lottos.size() + PURCHASED_LOTTO);
        printPurchasedLottos(lottos);
        System.out.println();
    }

    private void printPurchasedLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            lotto.numberSort();
            log.info(lotto.toString());
        }
    }

    public void getLottoResultOutput(Map<Integer, Integer> price, Map<Integer, Integer> result, Double profitRate) {
        System.out.println(LOTTO_RESULT);
        System.out.println("---");
        printLottoWinResult(price, result);
        printLottoWinRate(profitRate);
    }

    private void printLottoWinResult(Map<Integer, Integer> price, Map<Integer, Integer> result) {
        for (int i = 3; i <= 7; i++) {
            if (i == 6) {
                System.out.println((i - 1) + "개 일치, 보너스 볼 일치 (" + Conversion.integerToStringPriceWithComma(price.get(i)) + "원) - " + result.get(i) + "개");
                continue;
            }
            if (i == 7) {
                System.out.println((i - 1) + "개 일치 (" + Conversion.integerToStringPriceWithComma(price.get(i)) + "원) - " + result.get(i) + "개");
                continue;
            }
            System.out.println(i + "개 일치 (" + Conversion.integerToStringPriceWithComma(price.get(i)) + "원) - " + result.get(i) + "개");
        }
    }

    private void printLottoWinRate(Double profitRate) {
        System.out.println("총 수익률은 " + profitRate + "%입니다.");
    }

}
