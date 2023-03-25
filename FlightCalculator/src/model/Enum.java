package model;

/**
 * Enumeration Class
 */
public enum Enum {
    /**
     * 12000
     */
    MAX_DISTANCE(12000),
    /**
     * 8000
     */
    MIN_DISTANCE(8000),
    /**
     * 250
     */
    PASSENGERS(250),
    /**
     * 14:00 pm
     */
    TIME_TAKE_OFF(14.00),

    /**
     * 9000
     */
    MAX_DISTANCE_AFTER_TIME_TAKE_OFF (9000),

    MAX_TIME(20.00);


    private double numVal;

    Enum(double numVal) {
        this.numVal = numVal;
    }

    public double getNumVal() {
        return numVal;
    }
}
