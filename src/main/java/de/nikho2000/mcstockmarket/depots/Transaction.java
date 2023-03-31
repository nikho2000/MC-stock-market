package de.nikho2000.mcstockmarket.depots;

import de.nikho2000.mcstockmarket.stocks.Stock;

import java.math.BigDecimal;

public class Transaction {

    private final String sender;
    private final String receiver;
    private final BigDecimal amount;
    private final BigDecimal price;
    private final Stock stock;

    public Transaction(String sender, String receiver, BigDecimal amount, BigDecimal price, Stock stock) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.price = price;
        this.stock = stock;
    }


}
