package lotto.util;

import java.text.DecimalFormat;

public class Calculator {
    public static Double getRevenue(Integer purchaseAmount, Integer winningAmount) {
        double profit = calculateProfit(purchaseAmount, winningAmount);
        return roundToTwoDecimalPlaces(profit);
    }

    private static Double calculateProfit(Integer initialInvestment, Integer finalValue) {
        return ((double) (finalValue) / initialInvestment) * 100.0;
    }

    public static Double roundToTwoDecimalPlaces(Double number) {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(number));
    }
}
