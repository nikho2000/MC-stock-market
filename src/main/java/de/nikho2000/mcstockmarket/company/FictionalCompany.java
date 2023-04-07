package de.nikho2000.mcstockmarket.company;

import de.nikho2000.mcstockmarket.Calculations;
import de.nikho2000.mcstockmarket.stocks.Stock;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class FictionalCompany {

    private final Stock stock;
    private final int maxProducts;
    private int products;
    private final double intensity;
    private BigDecimal netWorthChanged = BigDecimal.ONE;
    private BigDecimal netWorth;
    private final BigDecimal percentageWorthOfProducts;
    private BigDecimal worthOfProducts;
    private final int maxProductChange;

    /**
        * @param intensity: min 0.1, max double.MAX_VALUE, but higher values will simply result in more random behavior
        * values between 0.1 and 1 are recommended
     */
    public FictionalCompany(Stock stock, int Products, double netWorth, double productStartPrice, double intensity, int maxProductChange) {
        this.stock = stock;
        this.maxProducts = Products;
        this.products = Products;
        this.netWorth = BigDecimal.valueOf(netWorth);
        this.worthOfProducts = BigDecimal.valueOf(productStartPrice);
        this.percentageWorthOfProducts = BigDecimal.valueOf(productStartPrice).divide(this.netWorth, 10, RoundingMode.HALF_DOWN).add(BigDecimal.ONE);
        this.intensity = intensity;
        this.maxProductChange = maxProductChange;
    }

    public synchronized Stock getStock() {
        return stock;
    }

    /**
     * This method is used to randomly buy or sell products of the fictional company.
     * This works by calculating a random value between -0.5 and 0.5, if the value is positive the company will buy products,
     * if the value is negative the company will sell products. Using the increaseDecreaseChance() method the chance of buying or selling
     * gets increased or decreased to prevent an overflow of the products.
     * @return the change in net worth of the fictional company
     */
    public synchronized double buySellRandom() {
        Random random = new Random();
        int amount = random.nextInt(1,maxProductChange);
        double x = Calculations.increaseDecreaseChance(intensity, getProductsInPercent());
        double buySell = random.nextDouble(-0.5 + x,0.5 + x);
        if (buySell > 0) {
            buyProducts(amount);
        } else if (buySell < 0 ) {
            sellProducts(amount);
        }
        double change = netWorthChanged.doubleValue();
        netWorthChanged = BigDecimal.ONE;
        System.out.println("change: " + change);
        return change;
    }

    /**
     * This method is used to buy products of the fictional company.
     * It will increase the net value of the fictional company and the price of the remaining products and related shares.
     * @param amount the amount of products to buy
     */
    public synchronized void buyProducts(int amount) {
        if (amount > products) {
            return;
        }
        products -= amount;
        BigDecimal x = percentageWorthOfProducts.pow(amount).setScale(200, RoundingMode.HALF_DOWN);
        netWorth = netWorth.multiply(x).setScale(2, RoundingMode.HALF_DOWN);
        worthOfProducts = worthOfProducts.multiply(x).setScale(2, RoundingMode.HALF_DOWN);
        netWorthChanged = netWorthChanged.multiply(x).setScale(200, RoundingMode.HALF_DOWN);
    }

    public synchronized void sellProducts(int amount) {
        if (amount+products > maxProducts) {
            return;
        }
        products += amount;
        BigDecimal x = percentageWorthOfProducts.pow(amount).setScale(200, RoundingMode.HALF_DOWN);
        netWorth = netWorth.divide(x, 200, RoundingMode.HALF_DOWN).setScale(2, RoundingMode.HALF_DOWN);
        worthOfProducts = worthOfProducts.divide(x, 200, RoundingMode.HALF_DOWN).setScale(2, RoundingMode.HALF_DOWN);
        netWorthChanged = netWorthChanged.divide(x, 200, RoundingMode.HALF_DOWN).setScale(2, RoundingMode.HALF_DOWN);
    }

    public synchronized BigDecimal getWorthOfProducts() {
        return worthOfProducts;
    }

    public synchronized int getProductsInPercent() {
        return (products * 100) / maxProducts;
    }

    public synchronized int getProducts() {
        return products;
    }

    public synchronized BigDecimal getNetWorth() {
        return netWorth;
    }

    public synchronized BigDecimal getPercentageWorthOfProducts() {
        return percentageWorthOfProducts;
    }

}
