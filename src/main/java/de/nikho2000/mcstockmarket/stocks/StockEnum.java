package de.nikho2000.mcstockmarket.stocks;

public enum StockEnum {
    /**
     * A lot of Stocks that are currently hardcoded. This will maybe be changed in the future.
     */
    CRAFTSOTF("Craftsotf Inc.", "CSI", StockType.SHARE),
    DIAMONDDIGGERS("Diamond Diggers Inc.", "DDI", StockType.SHARE),
    REDSTONEROBOTICS("Redstone Robotics Ltd.", "RRL", StockType.SHARE),
    ENDERPEARLEXPRESS("Enderpearl Express", "EPE", StockType.SHARE),
    CROPCORP("Crop Corp Ltd.", "CCL", StockType.SHARE),
    BLOCKSTREAM("Blockstream Inc.", "BSI", StockType.SHARE),
    MINEBOOK("Minebook Holdings", "MBH", StockType.SHARE),
    ENDERTECH("Endertech Corp.", "ETC", StockType.SHARE),
    ENCHANTMENTENTERPRISES("Enchantment Enterprises", "EEE", StockType.SHARE),
    BEACONBANK("Beacon Bank", "BEB", StockType.SHARE),
    MSCIVILLAGE("MSCI Village", "Index", StockType.ETF),
    SANDPCRAFTING("S&P Crafting", "Index", StockType.ETF),
    VILLAGEVANGUARD("Village Vanguard", "Index", StockType.ETF),
    REDSTONETECHNOLOGIES("Redstone Technologies", "Index", StockType.ETF),
    BIOMEBILDERS("Biome Builders", "Index", StockType.ETF),
    NETHERNEXUS("Nether Nexus", "Index", StockType.ETF),
    ENDEREXCHANGE("Ender Exchange", "Index", StockType.ETF),
    OVERWORLDINDEX("Overworld Index", "Index", StockType.ETF),
    PIXELREALMS("Pixel Realms", "Index", StockType.ETF),
    ENDCOIN("Endcoin", "ENC", StockType.CRYPTO),
    CREEPERIUM("Creeperium", "CRC", StockType.CRYPTO),
    MINECOIN("Minecoin", "MNC", StockType.CRYPTO),
    DIAMONDFORGE("Diamondforge", "DFG", StockType.CRYPTO),
    ENCHANTX("EnchantX", "ENX", StockType.CRYPTO),
    SLIMECORE("Slimecore", "SLC", StockType.CRYPTO),
    BEACONBOOST("Beaconboost", "BBT", StockType.CRYPTO);

    private final String name;
    private final String shortName;
    private final StockType type;

    StockEnum(String name, String shortName, StockType type) {
        this.name = name;
        this.shortName = shortName;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public StockType getType() {
        return type;
    }

}
