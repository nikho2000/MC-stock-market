package de.nikho2000.mcstockmarket.stocks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class StockParameters {

    private double targetPrice;
    private final int Steps;
    private int currentStep = 1;
    private final double noise;
    private double yNoise = 1;
    private double[] priceHistory;
    private final Random random = new Random();

    /**
        * @param noise is a value between 0 and 1, which is multiplied with the random value
     *              1 means that the random value of the noice doesn't get changed.
     *              0 means that the random value of the noice is 0.
     */
    public StockParameters(double price, double targetPrice, int steps, double noise) {
        this.targetPrice = targetPrice;
        Steps = steps+1; // +1 because in the last iteration it would be divided by 0
        this.noise = noise;
        priceHistory = new double[this.Steps];
        priceHistory[0] = price;
        System.out.println("Array length: " + priceHistory.length);
    }

    public void reset(double price, double targetPrice, double noise) {
        this.targetPrice = targetPrice;
        this.yNoise = noise;
        currentStep = 1;
        priceHistory = new double[Steps];
        priceHistory[0] = price;
    }

    public void calculateNewPrice() {
        if (currentStep < 1) {
            return;
        }

        double yDiff = targetPrice - priceHistory[currentStep-1];
        double yStep = yDiff / (Steps - currentStep);
        double yRand = random.nextDouble() * 2 * yNoise*noise - yNoise*noise;
        if (this.noise == 0) {
            yRand = 0;
        }
        priceHistory[currentStep] = priceHistory[currentStep-1] + yStep + yRand;
        currentStep++;
        for (Player all : Bukkit.getOnlinePlayers()) {
            all.sendMessage("New price: " + getCurrentPrice() + " Iteration: " + (currentStep-1));
        }
    }

    public BigDecimal getCurrentPrice() {
        return BigDecimal.valueOf(priceHistory[currentStep-1]).setScale(5, RoundingMode.HALF_UP);
    }

}
