package lotto.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static lotto.common.exceptions.ExceptionContext.NUMBER_WRONG_TYPE;

public class Conversion {
    public static ArrayList<Integer> stringWithCommaToArrayList(String input) {
        try {
            return Arrays.stream(input.split(","))
                    .map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NUMBER_WRONG_TYPE.getMessage());
        }
    }
}
