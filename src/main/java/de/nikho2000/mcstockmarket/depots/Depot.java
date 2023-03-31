package de.nikho2000.mcstockmarket.depots;

import de.nikho2000.mcstockmarket.stocks.Stock;
import de.nikho2000.mcstockmarket.stocks.StockType;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Depot {

    private final UUID id;
    private final Player owner;
    private Map<Stock, BigDecimal> stocks;
    private List<Transaction> transactions;

    public Depot(Player owner, UUID id , Map<Stock, BigDecimal> stocks) {
        this.owner = owner;
        this.id = id;
        this.stocks = stocks;
    }

    public Player getOwner() {
        return owner;
    }

    public UUID getID() {
        return id;
    }

    public BigDecimal getDepotValue() {

        // Calculate the value of all Stocks in the Depot

        return BigDecimal.ZERO;
    }

    public double getPercentageOfStock() {

        // Calculate the percentage of the Depot value that is made up of Stocks for every transaction in the Depot
        // Use the geometrical arithmetic middle

        return 0;
    }

    public double getPercentageOfStock(Stock type) {

        // Calculate the percentage of the Depot value that is made up of Money for every transaction in the Depot

        return 0;
    }

    public Map<Stock, BigDecimal> getStocks() {
        return stocks;
    }

    public void setStocks(Map<Stock, BigDecimal> stocks) {
        this.stocks = stocks;
    }

    public void setStock(Stock stock, BigDecimal amount) {
        stocks.put(stock, amount);
    }

    public void addStock(Stock stock, BigDecimal amount) {
        if (stocks.containsKey(stock)) {
            stocks.put(stock, stocks.get(stock).add(amount));
        } else {
            stocks.put(stock, amount);
        }
    }

    public void removeStock(Stock stock, BigDecimal amount) {
        if (stocks.containsKey(stock)) {
            stocks.put(stock, stocks.get(stock).subtract(amount));
        } else {
            stocks.put(stock, BigDecimal.ZERO);
        }
    }

    public BigDecimal getStockAmount(Stock stock) {
        return stocks.getOrDefault(stock, BigDecimal.ZERO);
    }

}
