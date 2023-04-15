package de.nikho2000.mcstockmarket.events;

public enum NationalEvents {

    INFLATION(-10.0, 0, 20.0),
    DEFLATION(0, 10.0, 20.0),
    NATURAL_DISASTER(-30.0, 20, 20.0),
    ELECTIONS(-20.0, 20, 20.0),
    BANK_CRISIS(-20.0, 20, 20.0);

    private final double percentageMin;
    private final double percentageMax;
    private final double chance;

    NationalEvents(double percentageMin, double percentageMax, double chance) {
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
