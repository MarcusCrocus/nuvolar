package model;

import java.time.LocalTime;

/**
 * Class of object Aircraft
 */
public class Aircraft {

    /**
     * Class Variables
     *     private String flightNumber;
     *     private float timeTakeOf;
     *     private int passengerNumber;
     *     private String departureLocation;
     *     private String arrivalLocation;
     *     private double flightRange;
     */
    private String flightNumber;
    //TODO : Change float to String, and change javadoc description
    private float timeTakeOf;
    private int passengerNumber;
    private String departureLocation;
    private String arrivalLocation;
    private int flightRange;


    public Aircraft() {}

    /**
     * Constructor of the Class Aircraft
     * @param flightNumber variable
     * @param timeTakeOf variable
     * @param passengerNumber variable
     * @param departureLocation variable
     * @param arrivalLocation variable
     * @param flightRange variable
     */
    public Aircraft(String flightNumber, float timeTakeOf, int passengerNumber, String departureLocation, String arrivalLocation, int flightRange) {
        this.flightNumber = flightNumber;
        this.timeTakeOf = timeTakeOf;
        this.passengerNumber = passengerNumber;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.flightRange = flightRange;
    }

    /**
     * getFlightNumber()
     * @return flightNumber
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * setFlightNumber
     * @param flightNumber String
     */
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    /**
     * getTimeTakeOf()
     * @return getTimeTakeOf()
     */
    public float getTimeTakeOf() {
        return timeTakeOf;
    }

    /**
     * setTimeTakeOf
     * @param timeTakeOf float
     */
    public void setTimeTakeOf(float timeTakeOf) {
        this.timeTakeOf = timeTakeOf;
    }

    /**
     * getPassengerNumber()
     * @return getPassengerNumber()
     */
    public int getPassengerNumber() {
        return passengerNumber;
    }

    /**
     * setPassengerNumber
     * @param passengerNumber int
     */
    public void setPassengerNumber(int passengerNumber) {
        this.passengerNumber = passengerNumber;
    }

    /**
     * getDepartureLocation()
     * @return departureLocation
     */
    public String getDepartureLocation() {
        return departureLocation;
    }

    /**
     * setDepartureLocation
     * @param departureLocation String
     */
    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    /**
     * getArrivalLocation()
     * @return arrivalLocation
     */
    public String getArrivalLocation() {
        return arrivalLocation;
    }

    /**
     * setArrivalLocation
     * @param arrivalLocation String
     */
    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    /**
     * getFlightRange()
     * @return flightRange
     */
    public int getFlightRange() {
        return flightRange;
    }

    /**
     * setFlightRange
     * @param flightRange int
     */
    public void setFlightRange(int flightRange) {
        this.flightRange = flightRange;
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "flightNumber='" + flightNumber + '\'' +
                ", timeTakeOf=" + timeTakeOf +
                ", passengerNumber=" + passengerNumber +
                ", departureLocation='" + departureLocation + '\'' +
                ", arrivalLocation='" + arrivalLocation + '\'' +
                ", flightRange=" + flightRange +
                '}';
    }
}
