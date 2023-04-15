package de.nikho2000.mcstockmarket.depots;

import de.nikho2000.mcstockmarket.stocks.StockEnum;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Depot {

    private final UUID id;
    private final Player owner;
    private Map<StockEnum, BigDecimal> stocks;
    private List<Transaction> transactions;

    public Depot(Player owner, UUID id , Map<StockEnum, BigDecimal> stocks) {
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

    public double getPercentageOfStock(StockEnum type) {

        // Calculate the percentage of the Depot value that is made up of Money for every transaction in the Depot

        return 0;
    }

    public Map<StockEnum, BigDecimal> getStocks() {
        return stocks;
    }

    public void setStocks(Map<StockEnum, BigDecimal> stocks) {
        this.stocks = stocks;
    }

    public void setStock(StockEnum stock, BigDecimal amount) {
        stocks.put(stock, amount);
    }

    public void addStock(StockEnum stock, BigDecimal amount) {
        if (stocks.containsKey(stock)) {
            stocks.put(stock, stocks.get(stock).add(amount));
        } else {
            stocks.put(stock, amount);
        }
    }

    public void removeStock(StockEnum stock, BigDecimal amount) {
        if (stocks.containsKey(stock)) {
            stocks.put(stock, stocks.get(stock).subtract(amount));
        } else {
            stocks.put(stock, BigDecimal.ZERO);
        }
    }

    public BigDecimal getStockAmount(StockEnum stock) {
        return stocks.getOrDefault(stock, BigDecimal.ZERO);
    }

}
