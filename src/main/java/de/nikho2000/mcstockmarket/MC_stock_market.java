package de.nikho2000.mcstockmarket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.nikho2000.mcstockmarket.stocks.Stock;
import de.nikho2000.mcstockmarket.stocks.StockParameters;
import de.nikho2000.mcstockmarket.stocks.StockType;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MC_stock_market extends JavaPlugin {

    private static MC_stock_market instance;
    private StockManager stockManager;
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final List<Stock> stocks = new ArrayList<>();

    @Override
    public void onEnable() {

        instance = this;
        if (!loadConfig()) {
            Bukkit.getLogger().severe("Failed to load config! Shutting down MC_Stock_Market ...");
            Bukkit.getLogger().severe("Possible causes: No Permissions to create directory and files, " +
                    "no free space on disk");
            Bukkit.getPluginManager().disablePlugin(this);
        } else {
            stockManager = new StockManager(stocks);
        }

    }

    @Override
    public void onDisable() {
        if (stockManager != null) {
            stockManager.closeStockMarket();
        }
    }

    public static MC_stock_market getInstance() {
        return instance;
    }

    private static boolean loadConfig() {
        File dir = new File("plugins/MC_stock_market");
        File FileStocks = new File("plugins/MC_stock_market/stocks.json");
        File FileEvents = new File("plugins/MC_stock_market/events.json");
        if (!dir.exists()) {
            if (dir.mkdir()) {
                Bukkit.getLogger().info("Created directory: " + dir.getName());
            } else {
                Bukkit.getLogger().severe("Failed to create directory: " + dir.getName());
                return false;
            };
        }
        if (!FileStocks.exists()) {
            try {
                if (FileStocks.createNewFile()) {
                    Map<String, Stock> map = new HashMap<>();
                    map.put("Craftsoft Inc.", new Stock("Craftsoft Inc.", "CSI", StockType.SHARE, new StockParameters(50.0)));
                    map.put("Diamond Diggers Inc.", new Stock("Diamond Diggers Inc.", "DDI", StockType.SHARE, new StockParameters(50.0)));
                    map.put("Redstone Robotics Ltd.", new Stock("Redstone Robotics Ltd.", "RRL", StockType.SHARE, new StockParameters(50.0)));
                    //map.put("Creeperium", new Stock("Creeperium", "CRC", StockType.CRYPTO, new StockParameters(50.0)));
                    //map.put("Minecoin", new Stock("Minecoin", "MNC", StockType.CRYPTO, new StockParameters(50.0)));
                    if (!writeToStockJson(FileStocks, map)) {
                        return false;
                    }
                    stocks.addAll(map.values());
                    Bukkit.getLogger().info("Created file: " + FileStocks.getName());
                }
            } catch (IOException e) {
                Bukkit.getLogger().severe("Failed to create file: " + FileStocks.getName());
                return false;
            }
        } else {
            Map<String, Stock> map = readFromStockJson(FileStocks.toPath());
            if (map != null) {
                for (Map.Entry<String, Stock> entry : map.entrySet()) {
                    stocks.add((Stock) entry.getValue());
                }
            }
        }
        if (!FileEvents.exists()) {
            try {
                if (FileEvents.createNewFile()) {
                    // TODO: Add events
                }
            } catch (IOException e) {
                Bukkit.getLogger().severe("Failed to create file: " + FileStocks.getName());
                return false;
            }
        }
        return true;
    }

    public static boolean writeToStockJson(File file, Map<String, Stock> map) {
        try {
            Writer writer = Files.newBufferedWriter(Paths.get("plugins/MC_stock_market/stocks.json"));
            gson.toJson(map, writer);
            writer.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Bukkit.getLogger().severe("Failed to write to file: " + file.getName());
            return false;
        }
    }

    public static Map<String, Stock> readFromStockJson(Path path) {
        try {
            Reader reader = Files.newBufferedReader(path);
            Map<String, Stock> map = gson.fromJson(reader, Map.class);
            reader.close();
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            Bukkit.getLogger().severe("Failed to read from file: " + path.getFileName());
            Bukkit.getLogger().severe("Possible causes: No Permissions to read from file, " +
                    "file is empty");
            return null;
        }
    }

}
