package lotto.handler;

import lotto.Lotto;
import lotto.WinningNumbers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class WinningNumbersHandler {

    public WinningNumbers getValidWinningNumbers() {
        Lotto lotto = getValidLottoNumbers();
        System.out.println();
        int bonusNumber = getValidBonusNumber(lotto);
        return new WinningNumbers(lotto, bonusNumber);
    }

    private Lotto getValidLottoNumbers() {
        while (true) {
            try {
                System.out.println("당첨 번호를 입력해 주세요.");
                String input = readLine();
                List<Integer> numbers = parseNumbers(input);
                return new Lotto(numbers);
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 입력 값은 숫자여야 합니다.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private List<Integer> parseNumbers(String input) {
        if (!input.matches("^\\d+(,\\d+)*$")) {
            throw new IllegalArgumentException("[ERROR] 입력 값은 쉼표(,)로 구분된 숫자여야 합니다.");
        }
        return Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public int getValidBonusNumber(Lotto lotto) {
        while (true) {
            try {
                System.out.println("보너스 번호를 입력해 주세요.");
                String input = readLine();
                validateBonusNumber(input, lotto);
                return Integer.parseInt(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    void validateBonusNumber(String input, Lotto lotto) {
        if (!isNumeric(input)) {
            throw new IllegalArgumentException("[ERROR] 입력 값은 숫자여야 합니다.");
        }
        int number = Integer.parseInt(input);
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("로또 번호는 1에서 45 사이어야 합니다.");
        }
        Set<Integer> uniqueNumbers = new HashSet<Integer>(lotto.getNumbers());
        uniqueNumbers.add(number);
        if (uniqueNumbers.size() != 7) {
            throw new IllegalArgumentException("로또 번호는 중복되지 않아야 합니다.");
        }
    }
    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

}
