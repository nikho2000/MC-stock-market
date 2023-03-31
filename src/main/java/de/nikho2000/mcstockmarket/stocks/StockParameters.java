package de.nikho2000.mcstockmarket.stocks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
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

    private BigDecimal targetPrice;
    private int Steps;
    private int currentStep = 1;
    private double noise;
    private BigDecimal[] priceHistory;
    private Random random = new Random();

    public StockParameters(BigDecimal price, BigDecimal targetPrice, int steps, double noise) {
        this.targetPrice = targetPrice.setScale(5, BigDecimal.ROUND_HALF_UP);
        Steps = steps+1;
        this.noise = noise;
        priceHistory = new BigDecimal[steps];
        priceHistory[0] = price;
    }

    public void reset(BigDecimal price, BigDecimal targetPrice, double noise) {
        this.targetPrice = targetPrice.setScale(5, BigDecimal.ROUND_HALF_UP);
        this.noise = noise;
        priceHistory = new BigDecimal[Steps];
        priceHistory[0] = price;
    }

    public void calculateNewPrice() {
        if (Steps < 1) {
            return;
        }

        BigDecimal yDiff = targetPrice.subtract(priceHistory[Steps - 1]);
        BigDecimal yStep = yDiff.divide(BigDecimal.valueOf(Steps - currentStep), 5, BigDecimal.ROUND_HALF_UP);
        BigDecimal yRand = BigDecimal.valueOf(random.nextDouble()).multiply(BigDecimal.valueOf(2*noise-noise));
        priceHistory[currentStep] = priceHistory[currentStep - 1].add(yStep).add(yRand);
        currentStep++;
        for (Player all : Bukkit.getOnlinePlayers()) {
            all.sendMessage("New price: " + priceHistory[currentStep - 1]);
        }

    }

    public BigDecimal getCurrentPrice() {
        return priceHistory[currentStep - 1];
    }

}
