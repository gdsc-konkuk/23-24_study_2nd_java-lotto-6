package lotto.src.view;

import java.util.List;

import static lotto.common.responses.ResponseContext.PURCHASED_LOTTO_MESSAGE;

public class OutputView {
    public void purchasedLottosOutput(List<List<Integer>> lottos) {
        System.out.println(lottos.size() + PURCHASED_LOTTO_MESSAGE.getMessage());
        printPurchasedLottos(lottos);
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
}
