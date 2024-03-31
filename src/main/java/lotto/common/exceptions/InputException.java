package lotto.common.exceptions;

import lotto.common.Constant;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static lotto.common.Constant.*;
import static lotto.common.exceptions.ExceptionContext.*;

public class InputException {

    public static void validatePriceInput(String input) {
        checkIsNumeric(input);
    }

    public static void validatePriceInput(Integer input) {
        checkInputPriceRange(input);
        checkInputPriceDivision(input);
    }

    public static void validateWinningNumbers(List<Integer> input) {
        checkInputCommaDivided(input);
        checkInputSize(input);
        checkInputNumberRange(input);
        checkInputNumberDuplicate(input);
    }

    public static void validateBonusNumberInput(String input) {
        checkIsNumeric(input);
    }

    public static void validateBonusNumberInput(List<Integer> winningNumber, Integer input) {
        checkInputRange(input);
        checkInputDuplicateNumber(winningNumber, input);
    }

    private static void checkIsNumeric(String input) {
        try {
            int number = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NUMBER_WRONG_TYPE.getMessage());
        }
    }

    private static void checkInputPriceRange(Integer input) {
        if (input <= 0) {
            throw new IllegalArgumentException(PRICE_NOT_POSITIVE.getMessage());
        }
    }

    private static void checkInputPriceDivision(Integer input) {
        if (input % 1000 != 0) {
            throw new IllegalArgumentException(PRICE_NOT_DIVIDED_BY_THOUSAND.getMessage());
        }
    }

    private static void checkInputCommaDivided(List<Integer> input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(NUMBER_NOT_SPLIT_BY_COMMA.getMessage());
        }
    }

    private static void checkInputRange(Integer input) {
        if (input < MIN_LOTTO_NUMBER || input > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(NUMBER_RANGE_ERROR.getMessage());
        }
    }

    private static void checkInputSize(List<Integer> input) {
        if (input.size() != LOTTO_WINNING_NUMBER_SIZE) {
            throw new IllegalArgumentException(NUMBER_LIST_SIZE_ERROR.getMessage());
        }
    }

    private static void checkInputNumberRange(List<Integer> input) {
        for (Integer number : input) {
            checkInputRange(number);
        }
    }

    private static void checkInputNumberDuplicate(List<Integer> input) {
        if (hasDuplicates(input)) {
            throw new IllegalArgumentException(NUMBER_DUPLICATE_ERROR.getMessage());
        }
    }

    private static void checkInputDuplicateNumber(List<Integer> winningNumber, Integer input) {
        if (winningNumber.contains(input)) {
            throw new IllegalArgumentException(BONUS_NUMBER_DUPLICATE_ERROR.getMessage());
        }
    }

    public static boolean hasDuplicates(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>();
        for (int number : numbers) {
            if (!uniqueNumbers.add(number)) {
                return true;
            }
        }
        return false;
    }
}