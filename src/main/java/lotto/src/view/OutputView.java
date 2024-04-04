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

    public void lottoResultOutput(String lottoResult) {
        System.out.println(LOTTO_RESULT);
        System.out.println("---");
        System.out.println(lottoResult);
    }

}
