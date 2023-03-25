package model;

/**
 * Class tha calculate the distance between ariports
 */
public class Haversine {
    private String flightNumber;
    private String timeTakeOf;
    private int passengerNumber;
    double latitude1;
    double longitude1;
    double latitude2;
    double longitude2;
    static final int R = 6371;

    public Haversine() {}

    /**
     *
     * @param flightNumber string
     * @param timeTakeOf string
     * @param passengerNumber integer
     * @param latitude1 double
     * @param longitude1 double
     * @param latitude2 double
     * @param longitude2 bouble
     */
    public Haversine(String flightNumber, String timeTakeOf, int passengerNumber, double latitude1, double longitude1, double latitude2, double longitude2) {
        this.flightNumber = flightNumber;
        this.timeTakeOf = timeTakeOf;
        this.passengerNumber = passengerNumber;
        this.latitude1 = latitude1;
        this.longitude1 = longitude1;
        this.latitude2 = latitude2;
        this.longitude2 = longitude2;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getTimeTakeOf() {
        return timeTakeOf;
    }

    public String setTimeTakeOf(String timeTakeOf) {
        this.timeTakeOf = timeTakeOf;
        return timeTakeOf;
    }

    public int getPassengerNumber() {
        return passengerNumber;
    }

    public void setPassengerNumber(int passengerNumber) {
        this.passengerNumber = passengerNumber;
    }

    public double getLatitude1() {
        return latitude1;
    }

    public void setLatitude1(double latitude1) {
        this.latitude1 = latitude1;
    }

    public double getLongitude1() {
        return longitude1;
    }

    public void setLongitude1(double longitude1) {
        this.longitude1 = longitude1;
    }

    public double getLatitude2() {
        return latitude2;
    }

    public void setLatitude2(double latitude2) {
        this.latitude2 = latitude2;
    }

    public double getLongitude2() {
        return longitude2;
    }

    public void setLongitude2(double longitude2) {
        this.longitude2 = longitude2;
    }

    private static Double toRad (Double value) {
        return value * Math.PI / 180;
    }

    /**
     *
     * @param latitude1 double
     * @param longitude1 double
     * @param latitude2 double
     * @param longitude2 double
     * @return double value after calculation
     */
    public  static double getDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        double latDistance = toRad(latitude2 - latitude1);
        double longDistance = toRad(longitude2 - longitude1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance /2) +
                Math.cos(toRad(latitude1)) * Math.cos(toRad(latitude2)) *
                Math.sin(longDistance / 2) * Math.sin(longDistance /2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return R * c;
    }


}

