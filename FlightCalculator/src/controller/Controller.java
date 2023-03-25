package controller;

import model.*;

import java.util.List;

/**
 * Controller Class returns data from the model Classes
 */
public class Controller {

    GenericListAircraft lAircraft = new GenericListAircraft();
    Data data = new Data();

    /**
     * Method List<Aircraft>
     * @return aircraftAvailable()
     */
    public List<Aircraft> aircraftAvailable() {
        return lAircraft.availableFlights(data.getAircraft());
    }

    /**
     * Method getlAircraft()
     * @return lAircraft
     */
    public GenericListAircraft getlAircraft() {
        return lAircraft;
    }

    /**
     * void setlAircraft(GenericListAircraft lAircraft)
     * @param lAircraft object
     */
    public void setlAircraft(GenericListAircraft lAircraft) {
        this.lAircraft = lAircraft;
    }

    /**
     * Data getData()
     * @return data
     */
    public Data getData() {
        return data;
    }

    /**
     * void setData
     * @param data
     */
    public void setData(Data data) {
        this.data = data;
    }

    /**
     * flightNumberExist
     * @param fNumber String
     * @return false if flight number already exist
     */
    public boolean flightNumberExist(String fNumber) {
        return lAircraft.flightNumberExist(fNumber, data.getAircraft());
    }

    /**
     * Receiving data from by console
     *
     * @param flightNumber String
     * @param timeTakeOf Float
     * @param passengerNumber int
     * @param departureLocation String
     * @param arrivalLocation String
     * @param flightRange int
     * @return Object creation
     */
    public String addFlightDetails(String flightNumber,
                                   float timeTakeOf,
                                   int passengerNumber,
                                   String departureLocation,
                                   String arrivalLocation,
                                   int flightRange) {


        return lAircraft.objCreation(flightNumber, timeTakeOf, passengerNumber, departureLocation,
                arrivalLocation, flightRange, data.getAircraft());
    }

    /**
     * Informative message about number of passengers and flight distance
     * @param passengerNumber int
     * @return string
     */
    public String numberOfPassengersLess(int passengerNumber) {
        String message = passengerNumber > 250 ?
                "Pay attention that, our max distance will be 8.000 km" :
                "Pay attention that with number of our max distance will be 12.000 km";
        return message;
    }

    /**
     * This method return calculation of distance from command line
     * @param latitude1 double
     * @param longitude1 double
     * @param latitude2 double
     * @param longitude2 double
     * @return Haversine.getDistance
     */
    public double Haversine(double latitude1, double longitude1, double latitude2, double longitude2) {
        return Haversine.getDistance(latitude1,longitude1,latitude2,longitude2);
    }

    /**
     * This method return
     * @return true if data has been added correctly after reading file .txt
     */
    public String saveObjectsHaversine(String arrivalLocation) {
        return lAircraft.addFromFileHaversine(data.getHaversine(),data.getCities(),data.getAircraft(),arrivalLocation);
    }
}
