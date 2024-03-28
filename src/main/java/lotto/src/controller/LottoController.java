package lotto.src.controller;

public class LottoController {
    private final IOController ioController;

    public LottoController() {
        this.ioController = new IOController();
    }

    public void lottoStart() {
        Integer price = ioController.lottoPriceInput();
        ioController.purchaseLotto(price);
    }
}
