package lotto.handler;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.Lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketsHandler {
    public List<Lotto> generateLottoTicket(int price) {
        List<Lotto> tickets = new ArrayList<>();
        for (int i = 0; i < price; i += 1000) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            tickets.add(new Lotto(numbers));
        }
        return tickets;
    }

    public void print(List<Lotto> tickets) {
        System.out.println(tickets.size() +"개를 구매했습니다.");
        for (Lotto lotto: tickets) {
            lotto.print();
        }
    }
}
