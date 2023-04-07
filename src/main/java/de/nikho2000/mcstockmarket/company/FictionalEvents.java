package de.nikho2000.mcstockmarket.company;

public enum FictionalEvents {

    /**
     * A lot of events currently hard coded, but maybe in the future they will be configurable
     */
    CEO_DIES(-15, 0.0, EventScope.COMPANY),
    CEO_RETIRE(-15.0, 15, EventScope.COMPANY),
    COMPANY_SOLD(-20, 20.0, EventScope.COMPANY),
    COMPANY_GOOD_SELLS(0.0,30.0, EventScope.COMPANY),
    COMPANY_BAD_SELLS(-35.0, 0.0, EventScope.COMPANY),
    NEW_PRODUCT(-10, 10.0, EventScope.COMPANY),
    NEW_PRODUCT_SUCCESS(0, 20.0, EventScope.COMPANY),
    NEW_PRODUCT_FAIL(-20.0, 0, EventScope.COMPANY),
    BANKRUPTCY(-100.0, -100, EventScope.COMPANY),
    GLOBAL_CRISIS(-20.0, 20, EventScope.GLOBAL),
    GLOBAL_ECONOMY_BOOM(5, 20.0, EventScope.GLOBAL),
    INFLATION(-10.0, 0, EventScope.NATIONAL),
    DEFLATION(0, 10.0, EventScope.NATIONAL),
    NATURAL_DISASTER(-30.0, 20, EventScope.NATIONAL),
    ELECTIONS(-20.0, 20, EventScope.NATIONAL),
    NEW_TECHNOLOGY(-5, 20.0, EventScope.GLOBAL),
    NEW_TECHNOLOGY_FAIL(-20.0, 0, EventScope.GLOBAL),
    NEW_TECHNOLOGY_SUCCESS(0, 20.0, EventScope.GLOBAL),
    STOCK_MARKET_CRASH(-99.0, -0, EventScope.GLOBAL),
    STOCK_MARKET_BOOM(0, 99.0, EventScope.GLOBAL),
    BANK_CRISIS(-20.0, 20, EventScope.NATIONAL);

    private final double percentageMin;
    private final double percentageMax;
    private final EventScope scope;

    FictionalEvents(double percentageMin, double percentageMax, EventScope scope) {
        this.percentageMin = percentageMin;
        this.percentageMax = percentageMax;
        this.scope = scope;
    }

    public double getMaxPercentage() {
        return percentageMax;
    }

    public double getMinPercentage() {
        return percentageMin;
    }

    public EventScope getScope() {
        return scope;
    }

}
