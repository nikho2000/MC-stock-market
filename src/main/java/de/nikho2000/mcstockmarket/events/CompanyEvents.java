package de.nikho2000.mcstockmarket.events;

public enum CompanyEvents {

    /**
     * A lot of events currently hard coded, but maybe in the future they will be configurable
     */
    CEO_DIES(-15, 0.0, 0.9),
    CEO_RETIRE(-15.0, 15, 3.0),
    COMPANY_SOLD(-20, 20.0, 5.0),
    COMPANY_GOOD_SELLS(0.0,30.0, 34.0),
    COMPANY_BAD_SELLS(-35.0, 0.0, 20.0),
    NEW_PRODUCT(-10, 10.0, 15.0),
    NEW_PRODUCT_SUCCESS(0, 20.0,  15.0),
    NEW_PRODUCT_FAIL(-20.0, 0,  8.0),
    BANKRUPTCY(-100.0, -100, 0.1);

    private final double percentageMin;
    private final double percentageMax;
    private final double chance;

    CompanyEvents(double percentageMin, double percentageMax, double chance) {
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
