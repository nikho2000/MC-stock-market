package de.nikho2000.mcstockmarket.events;

public enum GlobalEvents {
    GLOBAL_CRISIS(-20.0, 20, 15.0),
    GLOBAL_ECONOMY_BOOM(5, 20.0, 15.0),
    NEW_TECHNOLOGY(-5, 20.0, 10.0),
    NEW_TECHNOLOGY_FAIL(-20.0, 0, 15.0),
    NEW_TECHNOLOGY_SUCCESS(0, 20.0, 15.0),
    STOCK_MARKET_CRASH(-99.0, -0, 15.0),
    STOCK_MARKET_BOOM(0, 99.0, 15.0);

    private final double percentageMin;
    private final double percentageMax;
    private final double chance;

    GlobalEvents(double percentageMin, double percentageMax, double chance) {
        this.percentageMin = percentageMin;
        this.percentageMax = percentageMax;
        this.chance = chance;
    }

    public double getMaxPercentage() {
        return percentageMax;
    }

    public double getMinPercentage() {
        return percentageMin;
    }

    public double getChance() { return chance; }
}
