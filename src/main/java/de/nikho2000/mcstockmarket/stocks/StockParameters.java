package de.nikho2000.mcstockmarket.stocks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class StockParameters {

    /*
        * This class is used to calculate the price of a stock.
        * It uses a linear Regression -> f(y) = mx + b
        * m = yDiff / Steps and b is ignored
        * yDiff is the difference between the current price and the target price
        * Steps is the number of steps the price has to reach the target price
        * The current Price of the Stock gets calculated by adding the points randomly within the noise range.
        * Reverting this function and calculating the Linear function with a linear Regression and the points in the
        * array would give us the same function as above.
     */

    private double targetPrice;
    private int Steps;
    private int currentStep = 1;
    private double yNoise;
    private double[] priceHistory;
    private Random random = new Random();

    public StockParameters(double price, double targetPrice, int steps, double noise) {
        this.targetPrice = targetPrice;
        Steps = steps+1;
        this.yNoise = noise; // + ((double) Steps / 1000.0);
        priceHistory = new double[steps];
        priceHistory[0] = price;
    }

    public void reset(double price, double targetPrice, double noise) {
        this.targetPrice = targetPrice;
        this.yNoise = noise;
        currentStep = 1;
        priceHistory = new double[Steps];
        priceHistory[0] = price;
    }

    public void calculateNewPrice() {
        if (Steps < 1) {
            return;
        }

        double yDiff = targetPrice - priceHistory[currentStep-1];
        double yStep = yDiff / (Steps-1 - currentStep);
        double yRand = random.nextDouble() * 2 * yNoise - yNoise;
        priceHistory[currentStep] = priceHistory[currentStep-1] + yStep + yRand;
        for (Player all : Bukkit.getOnlinePlayers()) {
            all.sendMessage("New price: " + getCurrentPrice() + " Iteration: " + currentStep);
        }
        currentStep++;
    }

    public BigDecimal getCurrentPrice() {
        return BigDecimal.valueOf(priceHistory[currentStep-1]).setScale(5, RoundingMode.HALF_UP);
    }

}
