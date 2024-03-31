package lotto.src.view;

import lotto.utils.Conversion;

import java.util.List;
import java.util.Map;

import static lotto.common.responses.ResponseContext.*;
public class OutputView {
    public void purchasedLottosOutput(List<List<Integer>> lottos) {
        System.out.println(lottos.size() + PURCHASED_LOTTO.getMessage());
        printPurchasedLottos(lottos);
        System.out.println();
    }

    private void printPurchasedLottos(List<List<Integer>> lottos) {
        for (List<Integer> lotto : lottos) {
            System.out.print("[");
            for (int i = 0; i < lotto.size(); i++) {
                System.out.print(lotto.get(i));
                if (i < lotto.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
    }

    public void getLottoResultOutput(Map<Integer, Integer> price, Map<Integer, Integer> result, Double profitRate) {
        System.out.println(LOTTO_RESULT.getMessage());
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
