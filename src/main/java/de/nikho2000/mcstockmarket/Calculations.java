package de.nikho2000.mcstockmarket;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculations {


    /**
     * This method is used to calculate a value between -0.5 and 0.5, based on the current amount of products in Percent.
     * It is calculated by following function: <p>
     * 3 - e^(-intensity * x) / 3 + e^(-intensity * x) - 3 - e^(-intensity * (100-x)) / 3 + e^(-intensity * (100-x))
     * @return 0.5 with 100% Product left and -0.5 0% Products left.
     * Depending on the intensity the return value will be higher or lower for the Product amounts between 100% and 0%
     */
    public static synchronized double increaseDecreaseChance(double intensity, int getProductsInPercent) {
        BigDecimal term1 = BigDecimal.valueOf(Math.pow(Math.E, -intensity * getProductsInPercent));
        BigDecimal term2 = BigDecimal.valueOf(3).subtract(term1);
        BigDecimal term3 = BigDecimal.valueOf(3).add(term1);
        BigDecimal term4 = BigDecimal.valueOf(Math.pow(Math.E, -intensity* (100-getProductsInPercent)));
        BigDecimal term5 = BigDecimal.valueOf(3).subtract(term4);
        BigDecimal term6 = BigDecimal.valueOf(3).add(term4);
        // term2 / term3 - term5 / term6
        BigDecimal x = term2.divide(term3, 5, RoundingMode.HALF_UP).subtract(term5.divide(term6, 5, RoundingMode.HALF_UP));
        return x.doubleValue();
    }

    public static double calculatePriceWithNoise(double price, double noise) {
        return price + (price * noise * (Math.random() - 0.5));
    }

}
