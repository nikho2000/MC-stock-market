package de.nikho2000.mcstockmarket;

import de.nikho2000.mcstockmarket.stocks.Stock;
import de.nikho2000.mcstockmarket.stocks.StockParameters;
import org.bukkit.Bukkit;

import java.math.BigDecimal;
import java.util.*;

public class StockManager {

    private final List<Stock> stocks = new ArrayList<>();
    private final Map<Stock, FictionalCompany> fictionalCompanies = new HashMap<>();
    private final Map<Stock, StockParameters> stockPrices = new HashMap<>();
    private int stockChange;
    private int stockPrice;

    public StockManager() {
        loadStocks();
        loadFictionalCompanies();
        calucateStockPrices();
        calculateStockChange();
    }

    private void loadStocks() {
        stockPrices.put(Stock.CREEPERIUM, new StockParameters(50, 65, 60, 1));
    }

    private void loadFictionalCompanies() {
        fictionalCompanies.put(Stock.CREEPERIUM, new FictionalCompany(Stock.CREEPERIUM, 10000, 1000000, 20, 0.2, 30));
    }

    private void calucateStockPrices() {
        stockPrice = Bukkit.getScheduler().scheduleAsyncRepeatingTask(MC_stock_market.getInstance(), () -> {
            for (Stock stock : stockPrices.keySet()) {
                StockParameters stockParameters = stockPrices.get(stock);
                stockParameters.calculateNewPrice();
            }
        }, 0L, 40L);
    }

    private void calculateStockChange() {
        stockChange = Bukkit.getScheduler().scheduleAsyncRepeatingTask(MC_stock_market.getInstance(), () -> {
            for (Stock stock : stockPrices.keySet()) {
                StockParameters stockParameters = stockPrices.get(stock);
                FictionalCompany fictionalCompany = fictionalCompanies.get(stock);
                Random random = new Random();
                double chance = random.nextDouble(0, 5);
                double noice = random.nextDouble();
                stockParameters.reset(stockParameters.getCurrentPrice().doubleValue(),
                        stockParameters.getCurrentPrice().doubleValue() * fictionalCompany.buySellRandom(), noice*chance);
            }
        }, 2*20*60L, 2*20*60L);
    }

    public BigDecimal getStockPrice(Stock stock) {
        return stockPrices.get(stock).getCurrentPrice();
    }

    public void closeStockMarket() {
        Bukkit.getScheduler().cancelTask(stockChange);
        Bukkit.getScheduler().cancelTask(stockPrice);
    }

}
