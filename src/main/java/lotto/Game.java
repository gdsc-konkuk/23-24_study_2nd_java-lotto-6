package lotto;

import java.util.List;

public class Game {
    private final PurchaseHandler purchaseHandler;
    private final LottoTicketsHandler ticketsHandler;

    private List<Lotto> tickets;
    private int price;

    public Game() {
        this.purchaseHandler = new PurchaseHandler();
        this.ticketsHandler = new LottoTicketsHandler();
    }

    public void start() {
        price = purchaseHandler.getValidPurchaseAmount();
        tickets = ticketsHandler.generateLottoTicket(price);
        ticketsHandler.print(tickets);
    }

}
