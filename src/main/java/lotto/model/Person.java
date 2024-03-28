package lotto.model;

import lotto.util.Log;

public class Person {
    private PaperBag paperBag;
    private final Integer purchaseAmout;
    private static final Log log = new Log();

    private Person(Integer purchaseAmout) {
        isDivisionOk(purchaseAmout);
        this.purchaseAmout = purchaseAmout;
    }

    public static Person of(Integer purchaceAmout) {
        return new Person(purchaceAmout);
    }

    public void buyLotto(PaperBag paperBag) {
        this.paperBag = paperBag;
    }

    public PaperBag getPaperBag() {
        return paperBag;
    }

    public String getLottoesToString() {
        return paperBag.lottoesToString();
    }

    public int getPurchaseAmout() {
        return purchaseAmout;
    }

    private void isDivisionOk(Integer input) {
        if (input % 1000 != 0) {
            log.error("1000 단위의 숫자여야 합니다.");
            throw new NumberFormatException();
        }
    }
}