package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * GenericListAircraft extends GenericList<Aircraft>
 */
public class GenericListAircraft extends GenericList<Aircraft>  {

    Enum limitDistance = Enum.MAX_DISTANCE_AFTER_TIME_TAKE_OFF;
    Enum maxDistance = Enum.MAX_DISTANCE;
    Enum time = Enum.TIME_TAKE_OFF;
    Enum minDistance = Enum.MIN_DISTANCE;
    Enum passengers = Enum.PASSENGERS;
    Enum maxTime = Enum.MAX_TIME;

    /**
     * This method do evaluation for the presence of any aircraft in DDBB with the same number
     * @param number Flight number
     * @param air GenericList<Aircraft>
     * @return false in case if flight number is already exist
     */

    public boolean flightNumberExist(String number, GenericList<Aircraft> air) {
        boolean exist = false;
        for (Aircraft a : air.getData()) {
            if (a.getFlightNumber().equals(number)) {
                exist = true;
                break;
            }
        }
//        String a = "14.14";
//        String b = a.replace(".", ":");
//
//        LocalDateTime
        return exist;
    }

    /**
     * This method create an objext from the input data by command line
     * @param flightNumber int
     * @param timeTakeOf float
     * @param passengerNumber int
     * @param departureLocation String
     * @param arrivalLocation String
     * @param flightRange int
     * @param aircraft  GenericList<Aircraft>
     */
    public String objCreation(String flightNumber, float timeTakeOf, int passengerNumber, String departureLocation,
                              String arrivalLocation, int flightRange, GenericList<Aircraft> aircraft) {

        Aircraft aircraftObj = new Aircraft(flightNumber, timeTakeOf, passengerNumber, departureLocation, arrivalLocation, flightRange);
        aircraft.addData(aircraftObj);
        return "Data was added";
    }

    /** This method sows us all available Flights according to the conditions of time Calculation method
     * @param aircraft GenericList<Aircraft>
     * @return List of objects with aircraft data
     */

    public List<Aircraft> availableFlights(GenericList<Aircraft> aircraft) {

        List<Aircraft> possibleFlights = new ArrayList<>();

        for (Aircraft a : aircraft.getData()) {

            if (timeCalculation(a.getTimeTakeOf(), a.getFlightRange(), a.getPassengerNumber())) {
                possibleFlights.add(a);
            }

        }

        return possibleFlights;
    }

    /**
     * This method evaluation of flight range  is 12.000 km,If the flight has more than 250 pas it can travel 8.000 km.
     *  Flights taking off after 2:00 pm can only travel 9.000 km. And there shall be no take-offs after 8:00 pm.
     * @param timeTakeOf float
     * @param flightRange int
     * @param passengerNumber int
     * @return true or false
     */
    private boolean timeCalculation(float timeTakeOf, int flightRange, int passengerNumber) {

        Boolean result = false;

        if (timeTakeOf > maxTime.getNumVal()) {
            result = false;
        }
        if (passengerNumber <= passengers.getNumVal() && flightRange <= maxDistance.getNumVal() && timeTakeOf < time.getNumVal()) {
            result = true;
        }
        if (passengerNumber > passengers.getNumVal() && flightRange <= minDistance.getNumVal() && timeTakeOf < time.getNumVal()) {
            result = true;
        }
        if (timeTakeOf > time.getNumVal() && flightRange <= limitDistance.getNumVal() && passengerNumber <= passengers.getNumVal()) {
            result = true;
        }
        if (timeTakeOf > time.getNumVal() && flightRange <= minDistance.getNumVal() && passengerNumber > passengers.getNumVal()) {
            result = true;
        }

        return result;
    }

    /**
     * This method is reading data from the file.txt and adding all data to the aircraft for next evaluation
     * @param haversine list of data from the .txt
     * @param cities list of objects
     * @param aircraft obj of aircraft
     * @return string if was ok
     */

    public String addFromFileHaversine(GenericList<Haversine> haversine, GenericList<City> cities, GenericList<Aircraft> aircraft, String arrivalLocation) {

        //TODO: ask rout path from the view

        String path = arrivalLocation; //D:\Haversine.txt
        File file = new File(path);
        int roundedNum = 0;

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                StringTokenizer attribute = new StringTokenizer(line, " ");
                Haversine hav = new Haversine();
                Aircraft air = new Aircraft();
                while (attribute.hasMoreElements()) {
                    hav.setFlightNumber(attribute.nextElement().toString());
                    hav.setTimeTakeOf(Float.parseFloat(attribute.nextElement().toString()));
                    hav.setPassengerNumber(Integer.parseInt(attribute.nextElement().toString()));
                    hav.setLatitude1(Double.parseDouble(attribute.nextElement().toString()));
                    hav.setLongitude1(Double.parseDouble(attribute.nextElement().toString()));
                    hav.setLatitude2(Double.parseDouble(attribute.nextElement().toString()));
                    hav.setLongitude2(Double.parseDouble(attribute.nextElement().toString()));

                    double distance = Haversine.getDistance(hav.getLatitude1(), hav.getLongitude1(),
                            hav.getLatitude2(), hav.getLongitude2());
                    roundedNum = (int) distance;

                    String departure = "";
                    String arrivals = "";

                    for (City a : cities.getData()) {
                        if (a.getLatitude() == hav.latitude1 && a.getLongitude() == hav.getLongitude1()) {

                            departure = a.getName();

                        }
                        if (a.getLatitude() == hav.latitude2 && a.getLongitude() == hav.getLongitude2()) {

                            arrivals = a.getName();

                        }
                    }

                    // After evaluation, we need to add all correct data in our obj of Aircraft

                    air.setFlightNumber(hav.getFlightNumber());
                    air.setTimeTakeOf(hav.getTimeTakeOf());
                    air.setPassengerNumber(hav.getPassengerNumber());
                    air.setDepartureLocation(departure);
                    air.setArrivalLocation(arrivals);
                    air.setFlightRange(roundedNum);

                    aircraft.addData(air);
                }

            }
            scanner.close();

//            haversines.forEach(c -> System.out.println(c));

            return "-------------All Data has been correctly charged in DataBase";

        } catch (Exception e) {
            return "!!!!! Something went wrong, or file by this rout doesn't exist!!!!";

        }
    }

}






