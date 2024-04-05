package lotto.common.exceptions;

import lotto.common.Constant;
import lotto.utils.Conversion;

import java.util.*;
import java.util.stream.Collectors;

import static lotto.common.Constant.*;
import static lotto.common.message.Message.*;

public class InputException {

    public static void validatePriceInput(String input) {
        checkIsNumeric(input);
        Integer inputInteger = Integer.parseInt(input);
        checkInputPriceRange(inputInteger);
        checkInputPriceDivision(inputInteger);
    }

    public static void validateWinningNumbers(String input) {
        checkInputHasWrongType(input);
        ArrayList<Integer> inputList = Conversion.stringWithCommaToArrayList(input);
        checkInputCommaDivided(inputList);
        checkInputSize(inputList);
        checkInputNumberRange(inputList);
        checkInputNumberDuplicate(inputList);
    }

    public static void validateWinningNumbers(List<Integer> input) {
        checkInputCommaDivided(input);
        checkInputSize(input);
        checkInputNumberRange(input);
        checkInputNumberDuplicate(input);
    }

    public static void validateBonusNumberInput(List<Integer> winningNumber, String input) {
        checkIsNumeric(input);
        Integer inputNum = Integer.parseInt(input);
        checkInputRange(inputNum);
        checkInputDuplicateNumber(winningNumber, inputNum);
    }

    private static void checkIsNumeric(String input) {
        try {
            int number = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NUMBER_WRONG_TYPE);
        }
    }

    private static void checkInputHasWrongType(String input) {
        ArrayList<String> inputList = Arrays.stream(input.split(","))
                .collect(Collectors.toCollection(ArrayList::new));
        try {
            for (String num : inputList) {
                int number = Integer.parseInt(num);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NUMBER_WRONG_TYPE);
        }
    }

    private static void checkInputPriceRange(Integer input) {
        if (input <= 0) {
            throw new IllegalArgumentException(PRICE_NOT_POSITIVE);
        }
    }

    private static void checkInputPriceDivision(Integer input) {
        if (input % 1000 != 0) {
            throw new IllegalArgumentException(PRICE_NOT_DIVIDED_BY_THOUSAND);
        }
    }

    private static void checkInputCommaDivided(List<Integer> input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(NUMBER_NOT_SPLIT_BY_COMMA);
        }
    }

    private static void checkInputRange(Integer input) {
        if (input < MIN_LOTTO_NUMBER || input > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(NUMBER_RANGE_ERROR);
        }
    }

    private static void checkInputSize(List<Integer> input) {
        if (input.size() != LOTTO_WINNING_NUMBER_SIZE) {
            throw new IllegalArgumentException(NUMBER_LIST_SIZE_ERROR);
        }
    }

    private static void checkInputNumberRange(List<Integer> input) {
        for (Integer number : input) {
            checkInputRange(number);
        }
    }

    private static void checkInputNumberDuplicate(List<Integer> input) {
        if (hasDuplicates(input)) {
            throw new IllegalArgumentException(NUMBER_DUPLICATE_ERROR);
        }
    }

    private static void checkInputDuplicateNumber(List<Integer> winningNumber, Integer input) {
        if (winningNumber.contains(input)) {
            throw new IllegalArgumentException(BONUS_NUMBER_DUPLICATE_ERROR);
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