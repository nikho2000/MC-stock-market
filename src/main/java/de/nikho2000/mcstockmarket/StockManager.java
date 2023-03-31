package de.nikho2000.mcstockmarket;

import de.nikho2000.mcstockmarket.stocks.Stock;
import de.nikho2000.mcstockmarket.stocks.StockParameters;
import org.bukkit.Bukkit;

import java.math.BigDecimal;
import java.util.Map;

public class StockManager {

    private final Map<Stock, StockParameters> stockPrices = new java.util.HashMap<>();

    public StockManager() {
        stockPrices.put(Stock.CREEPERIUM, new StockParameters(new BigDecimal("50"), new BigDecimal("65"), 3600, 0.5));
        calucateStockPrices();
    }

    public void calucateStockPrices() {
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(MC_stock_market.getInstance(), () -> {
            for (Stock stock : stockPrices.keySet()) {
                StockParameters stockParameters = stockPrices.get(stock);
                stockParameters.calculateNewPrice();
            }
        }, 0L, 20L);
    }

    public BigDecimal getStockPrice(Stock stock) {
        return stockPrices.get(stock).getCurrentPrice();
    }

}
