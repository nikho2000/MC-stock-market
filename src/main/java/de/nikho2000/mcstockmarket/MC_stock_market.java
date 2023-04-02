package de.nikho2000.mcstockmarket;

import com.google.gson.Gson;
import org.bukkit.plugin.java.JavaPlugin;

public final class MC_stock_market extends JavaPlugin {

    private static MC_stock_market instance;
    private StockManager stockManager;
    public Gson gson;

    @Override
    public void onEnable() {

        instance = this;
        gson = new Gson();
        stockManager = new StockManager();

    }

    @Override
    public void onDisable() {
        stockManager.closeStockMarket();
    }

    public static MC_stock_market getInstance() {
        return instance;
    }

}
