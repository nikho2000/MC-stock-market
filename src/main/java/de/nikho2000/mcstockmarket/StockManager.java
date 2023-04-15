package de.nikho2000.mcstockmarket;

import de.nikho2000.mcstockmarket.events.CompanyEvents;
import de.nikho2000.mcstockmarket.events.GlobalEvents;
import de.nikho2000.mcstockmarket.events.NationalEvents;
import de.nikho2000.mcstockmarket.stocks.Stock;
import org.bukkit.Bukkit;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

public class StockManager {

    private final List<Stock> stocks;
    private final List<CompanyEvents> companyEvents = Arrays.asList(CompanyEvents.values());
    private final List<NationalEvents> nationalEvents = Arrays.asList(NationalEvents.values());
    private final List<GlobalEvents> globalEvents = Arrays.asList(GlobalEvents.values());
    private int stockChange;
    private int stockPrice;
    private int tick = 0;

    public StockManager(List<Stock> stocks) {
        this.stocks = stocks;
        loadStocks();
        calcuateStockPrices();
        calculateStockChange();
    }

    private void loadStocks() {

    }

    private void loadEvents() {
        // TODO
    }

    public BigDecimal getStockPrice(Stock stock) {
        return stock.getParameters().getCurrentPrice();
    }

    public void closeStockMarket() {
        Bukkit.getScheduler().cancelTask(stockChange);
        Bukkit.getScheduler().cancelTask(stockPrice);
    }

    /**
     * Calculates every two seconds a new Price for every Stock
     */
    private void calcuateStockPrices() {
        stockPrice = Bukkit.getScheduler().scheduleAsyncRepeatingTask(MC_stock_market.getInstance(), () -> {
            for (Stock stock : stocks) {
                stock.getParameters().calculateNewPrice();
            }
        }, 0L, 4L);
    }

    /**
     * Calculates every minute a new Percentage goal for every Stock, that the Stock reaches within the next minute
     */
    private void calculateStockChange() {
        Random random = new Random();
        stockChange = Bukkit.getScheduler().scheduleAsyncRepeatingTask(MC_stock_market.getInstance(), () -> {

            tick++;

            for (Stock stock : stocks) {
                switch (stock.getType()) {
                    case SHARE:
                        stock.getParameters().changeParameters(
                                stock.getParameters().getCurrentPrice().doubleValue() *
                                        stock.getCompany().buySellRandom(),
                                random.nextDouble()*random.nextDouble(0, 5));
                        break;
                    case CRYPTO:

                        break;
                    case ETF:

                        break;
                    case RESOURCE:

                        break;
                }
            }

            if ((tick%60) == 0) {

            }
            if ((tick%3601) == 0) {

            }
            if ((tick%14401) == 0) {

            }
            if ((tick%86401) == 0) {
                tick = 0;
            }
        }, 20*6L, 20*6L);

    }

    private void triggerCompanyEvent() {

    }

}
