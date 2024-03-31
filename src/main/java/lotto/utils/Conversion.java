package lotto.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static lotto.common.exceptions.ExceptionContext.NUMBER_WRONG_TYPE;

public class Conversion {
    public static ArrayList<Integer> stringWithCommaToArrayList(String input) {
        return Arrays.stream(input.split(","))
                .map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new));
    }

    public static String integerToStringPriceWithComma(Integer price) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(price);
    }
}
