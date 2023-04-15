package de.nikho2000.mcstockmarket.depots;

import de.nikho2000.mcstockmarket.stocks.StockEnum;

import java.math.BigDecimal;

public class Transaction {
    private final String receiver;
    private final BigDecimal amount;
    private final BigDecimal price;
    private final StockEnum stock;

    public Transaction(String receiver, BigDecimal amount, BigDecimal price, StockEnum stock) {
        this.receiver = receiver;
        this.amount = amount;
        this.price = price;
        this.stock = stock;
    }


}
