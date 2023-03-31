package de.nikho2000.mcstockmarket;

import de.nikho2000.mcstockmarket.stocks.Stock;
import de.nikho2000.mcstockmarket.stocks.StockParameters;
import org.bukkit.Bukkit;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Random;

public class StockManager {

    private final Map<Stock, StockParameters> stockPrices = new java.util.HashMap<>();
    private int stockChange;
    private int stockPrice;

    public StockManager() {
        stockPrices.put(Stock.CREEPERIUM, new StockParameters(50, 205, 3600, 1));
        calucateStockPrices();
        calculateStockChange();
    }

    private void calucateStockPrices() {
        stockPrice = Bukkit.getScheduler().scheduleAsyncRepeatingTask(MC_stock_market.getInstance(), () -> {
            for (Stock stock : stockPrices.keySet()) {
                StockParameters stockParameters = stockPrices.get(stock);
                stockParameters.calculateNewPrice();
            }
        }, 0L, 20L);
    }

    private void calculateStockChange() {
        stockChange = Bukkit.getScheduler().scheduleAsyncRepeatingTask(MC_stock_market.getInstance(), () -> {
            for (Stock stock : stockPrices.keySet()) {
                StockParameters stockParameters = stockPrices.get(stock);
                Random random = new Random();
                int chance = random.nextInt(-5, 5);
                int noice = random.nextInt(0, 5);
                stockParameters.reset(stockParameters.getCurrentPrice().doubleValue(), stockParameters.getCurrentPrice().doubleValue() + chance, 1+noice);
            }
        }, 20*60L, 20*60L);
    }

    public BigDecimal getStockPrice(Stock stock) {
        return stockPrices.get(stock).getCurrentPrice();
    }

    public void closeStockMarket() {
        Bukkit.getScheduler().cancelTask(stockChange);
        Bukkit.getScheduler().cancelTask(stockPrice);
    }

}
