package lotto.common.exceptions;

import static lotto.common.exceptions.ExceptionContext.*;

public class InputException {
    public static void validatePriceInput(Integer price) {
        checkInputPriceRange(price);
        checkInputPriceDivision(price);
    }

    private static void checkInputPriceRange(Integer price) {
        if (price <= 0) {
            throw new IllegalArgumentException(PRICE_NOT_POSITIVE_MESSAGE.getMessage());
        }
    }

    private static void checkInputPriceDivision(Integer price) {
        if (price % 1000 != 0) {
            throw new IllegalArgumentException(PRICE_NOT_DIVIDED_BY_THOUSAND.getMessage());
        }
    }

}