package lotto.common.exceptions;

import static lotto.common.exceptions.ExceptionContext.*;

public class InputException {
    public static void validatePriceInput(Integer input) {
        checkInputPriceRange(input);
        checkInputPriceDivision(input);
    }

    public static void validateWinningNumberInput(String input) {
        checkInputCommaDivided(input);
    }

    public static void validateBonusNumberInput(Integer input) {
        checkInputRange(input);
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

    private static void checkInputCommaDivided(String input) {
        String[] parts = input.split(",");

        if (parts.length == 0) {
            throw new IllegalArgumentException(NUMBER_NOT_SPLIT_BY_COMMA.getMessage());
        }
    }

    private static void checkInputRange(Integer input) {
        if (input < 1 || input > 45) {
            throw new IllegalArgumentException(NUMBER_RANGE_ERROR.getMessage());
        }
    }

}