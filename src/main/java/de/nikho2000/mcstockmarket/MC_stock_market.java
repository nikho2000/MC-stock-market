package de.nikho2000.mcstockmarket;

import org.bukkit.plugin.java.JavaPlugin;

public final class MC_stock_market extends JavaPlugin {

    private static MC_stock_market instance;

    @Override
    public void onEnable() {

        instance = this;
        StockManager stockManager = new StockManager();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static MC_stock_market getInstance() {
        return instance;
    }

}
