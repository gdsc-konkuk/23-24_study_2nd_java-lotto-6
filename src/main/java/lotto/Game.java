package lotto;

import java.util.*;

public class Game {
    private final PurchaseHandler purchaseHandler;
    private final LottoTicketsHandler ticketsHandler;
    private final WinningNumbersHandler winningNumbersHandler;

    private int price;
    private List<Lotto> tickets;
    private WinningNumbers winningNumbers;
    private HashMap<Score, Integer> result;
    private  int earnedPrice;

    public Game() {
        this.purchaseHandler = new PurchaseHandler();
        this.ticketsHandler = new LottoTicketsHandler();
        this.winningNumbersHandler = new WinningNumbersHandler();
        this.result = new HashMap<>();
        this.earnedPrice = 0;
    }

    public void start() {
        getInput();
        calculateResult();
        printResult();
    }

    private void getInput() {
        price = purchaseHandler.getValidPurchaseAmount();
        System.out.println();
        tickets = ticketsHandler.generateLottoTicket(price);
        ticketsHandler.print(tickets);
        System.out.println();
        winningNumbers = winningNumbersHandler.getValidWinningNumbers();
        System.out.println();
    }

    private void calculateResult() {
        for (Lotto lotto : tickets) {
            Score score = lotto.countMatchingNumbers(winningNumbers);
            result.put(score, result.getOrDefault(score, 0) + 1);
        }
        for (Map.Entry<Score, Integer> entry : result.entrySet()) {
            Score score = entry.getKey();
            int count = entry.getValue();
            earnedPrice += score.getPrize() * count;
        }
    }

    private void printResult() {
        System.out.println("당첨 통계");
        System.out.println("---");
        printScore(Score.THREE, "3개 일치 (5,000원)");
        printScore(Score.FOUR, "4개 일치 (50,000원)");
        printScore(Score.FIVE, "5개 일치 (1,500,000원)");
        printScore(Score.FIVE_WITH_BONUS, "5개 일치, 보너스 볼 일치 (30,000,000원)");
        printScore(Score.SIX, "6개 일치 (2,000,000,000원)");
        System.out.println("총 수익률은 " + earnedPrice + "원");
    }

    private void printScore(Score score, String description) {
        System.out.println(description + " - " + result.getOrDefault(score, 0) + "개");
    }

}
