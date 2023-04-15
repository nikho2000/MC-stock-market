package de.nikho2000.mcstockmarket.stocks;

import org.bukkit.Bukkit;

public class Stock {

    private final String name;
    private final String shortName;
    private final StockType type;
    private final StockParameters parameters;
    private final FictionalCompany company;

    public Stock(String name, String shortName, StockType type, StockParameters parameters) {
        this(name, shortName, type, parameters, null);
    }

    public Stock(String name, String shortName, StockType type, StockParameters parameters, FictionalCompany company) {
        this.name = name;
        this.shortName = shortName;
        this.type = type;
        this.parameters = parameters;
        if (company != null) {
            if (type == StockType.SHARE) {
                this.company = company;
            } else {
                Bukkit.getLogger().warning("Company was set for " +
                        name + " but type is not SHARE. Company will be ignored.");
                this.company = null;
            }
        } else {
            if (type == StockType.SHARE) {
                Bukkit.getLogger().warning("Company was not set for " +
                        name + " but type is SHARE.");
                Bukkit.getLogger().warning(
                        "If this is the first start of the Plugin, you can ignore this message.\n" +
                        "A new company will be created ...\n" +
                        "The parameters of the company will be set to the default values" +
                        "and can be changed in the plugins/MC_Stock_Manager/stocks.json file.");
                this.company = new FictionalCompany(20000, 2000000, 50, 0.2, 50);
            } else {
                this.company = null;
            }
        }

    }

    public StockType getType() {
        return type;
    }

    public StockParameters getParameters() {
        return parameters;
    }

    public FictionalCompany getCompany() {
        return company;
    }

}
