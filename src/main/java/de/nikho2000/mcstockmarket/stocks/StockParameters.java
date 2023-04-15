package de.nikho2000.mcstockmarket.stocks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StockParameters {
    private double targetPrice;
    private double currentPrice;
    private final int Steps;
    private int currentStep = 1;
    private final double noise;
    private double yNoise = 1;
    private List<Double> priceHistory = new ArrayList<Double>();
    private final transient Random random = new Random();

    /**
     * The StockParameters class is used to calculate a stock price.
     * It uses a linear function and plays with the noise to make the stock price more realistic.
     * @param price is the starting price of the stock
     */
    public StockParameters(double price) {
        this(price, price+5, 30, 1);
    }

    /**
     * The StockParameters class is used to calculate a stock price.
     * It uses a linear function and plays with the noise to make the stock price more realistic.
     * @param price is the starting price of the stock
     * @param targetPrice is the price the stock should reach
     * @param steps is the amount of steps the stock should take to reach the target price
     * @param noise is a value between 0 and 1, which is multiplied with the random value
     *              1 means that the random value of the noice doesn't get changed.
     *              0 means that the random value of the noice is 0.
     */
    public StockParameters(double price, double targetPrice, int steps, double noise) {
        this.targetPrice = targetPrice;
        Steps = steps+1; // +1 because in the last iteration it would be divided by 0
        this.noise = noise;
        priceHistory.add(price);
    }

    public void changeParameters(double targetPrice, double noise) {
        this.targetPrice = targetPrice;
        this.yNoise = noise;
        currentStep = 1;
        priceHistory.clear();
        priceHistory.add(currentPrice);
    }

    public void calculateNewPrice() {
        if (currentStep < 1) {
            return;
        }

        double yDiff = targetPrice - priceHistory.get(currentStep-1);
        double yStep = yDiff / (Steps - currentStep);
        double yRand = random.nextDouble() * 2 * yNoise - yNoise;
        if (this.noise == 0) {
            yRand = 0;
        }
        priceHistory.add(currentPrice + yStep + yRand);
        currentPrice = priceHistory.get(currentStep);
        currentStep++;
        for (Player all : Bukkit.getOnlinePlayers()) {
            all.sendMessage("New price: " + getCurrentPrice() + " Iteration: " + (currentStep-1));
        }
    }

    public BigDecimal getCurrentPrice() {
        return BigDecimal.valueOf(currentPrice).setScale(5, RoundingMode.HALF_UP);
    }

}
