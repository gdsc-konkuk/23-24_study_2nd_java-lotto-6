package lotto.handler;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class PurchaseHandler {
    public int getValidPurchaseAmount() {
        while (true) {
            try {
                System.out.println("구입 금액을 입력해 주세요.");
                String input = readLine();
                validateInput(input);
                return Integer.parseInt(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void validateInput(String input) {
        if (!isNumeric(input)) {
            throw new IllegalArgumentException("[ERROR] 입력 값은 숫자여야 합니다.");
        }

        int amount = Integer.parseInt(input);
        if (amount % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 입력 값은 1000 단위여야 합니다.");
        }
    }

    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
}
