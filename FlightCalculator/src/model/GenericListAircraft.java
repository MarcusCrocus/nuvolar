package model;
//import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

/**
 * GenericListAircraft extends GenericList<Aircraft>
 */
public class GenericListAircraft extends GenericList<Aircraft> {
    Enum limitDistance = Enum.MAX_DISTANCE_AFTER_TIME_TAKE_OFF;
    Enum maxDistance = Enum.MAX_DISTANCE;
    Enum time = Enum.TIME_TAKE_OFF;
    Enum minDistance = Enum.MIN_DISTANCE;
    Enum passengers = Enum.PASSENGERS;
    Enum maxTime = Enum.MAX_TIME;

    /**
     * This method do evaluation for the presence of any aircraft in DDBB with the same number
     *
     * @param number Flight number
     * @param air    GenericList<Aircraft>
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

        return exist;
    }

    /**
     * This method create an objext from the input data by command line
     *
     * @param flightNumber      int
     * @param timeTakeOf        float
     * @param passengerNumber   int
     * @param departureLocation String
     * @param arrivalLocation   String
     * @param flightRange       int
     * @param aircraft          GenericList<Aircraft>
     */
    public String objCreation(String flightNumber, float timeTakeOf, int passengerNumber, String departureLocation,
                              String arrivalLocation, int flightRange, GenericList<Aircraft> aircraft) {

        Aircraft aircraftObj = new Aircraft(flightNumber, timeTakeOf, passengerNumber, departureLocation, arrivalLocation, flightRange);
        aircraft.addData(aircraftObj);
        return "Data was added";
    }

    /**
     * This method sows us all available Flights according to the conditions of time Calculation method
     *
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
     * Flights taking off after 2:00 pm can only travel 9.000 km. And there shall be no take-offs after 8:00 pm.
     *
     * @param timeTakeOf      float
     * @param flightRange     int
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
     *
     * @param haversine list of data from the .txt
     * @param cities    list of objects
     * @param aircraft  obj of aircraft
     * @return string if was ok
     */

    public String addFromFileHaversine(GenericList<Haversine> haversine, GenericList<City> cities, GenericList<Aircraft> aircraft, String arrivalLocation) {

        //TODO: ask rout path from the view

       // List<Haversine> haversines= new ArrayList<Haversine>();

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
                    String varTime = hav.setTimeTakeOf(attribute.nextElement().toString());
                    hav.setPassengerNumber(Integer.parseInt(attribute.nextElement().toString()));
                    hav.setLatitude1(Double.parseDouble(attribute.nextElement().toString()));
                    hav.setLongitude1(Double.parseDouble(attribute.nextElement().toString()));
                    hav.setLatitude2(Double.parseDouble(attribute.nextElement().toString()));
                    hav.setLongitude2(Double.parseDouble(attribute.nextElement().toString()));

                    double distance = Haversine.getDistance(hav.getLatitude1(), hav.getLongitude1(),
                            hav.getLatitude2(), hav.getLongitude2());
                    roundedNum = (int) distance;

                    //replacing string TimeFormat ot float
                    String StringTime = varTime.replace(":", ".");
                    float floatTime = Float.parseFloat(StringTime);

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

                    //Print in Json format - need to download Gson library from https://github.com/google/gson
                    // and import import com.google.gson.Gson;

//                    Gson gson = new Gson();
//                    String json = gson.toJson(hav);
//                    System.out.println(json);

                    // After evaluation, we need to add all correct data in our obj of Aircraft

                    air.setFlightNumber(hav.getFlightNumber());
                    air.setTimeTakeOf(floatTime);
                    air.setPassengerNumber(hav.getPassengerNumber());
                    air.setDepartureLocation(departure);
                    air.setArrivalLocation(arrivals);
                    air.setFlightRange(roundedNum);

                    aircraft.addData(air);
                }
                //haversines.add(hav); //required toString in objModel

            }
            scanner.close();

            //Simple sout
            //haversines.forEach(c -> System.out.println(c)); //required toString in objModel

            return "-------------All Data has been correctly charged in DataBase";

        } catch (Exception e) {
            return "!!!!! Something went wrong, or file by this rout doesn't exist!!!!";

        }
    }

    /**
     * This method will read data from json file and add them to the aircraft object
     * @param aircraft GenericList<Aircraft>
     * @param cities GenericList<City> cities
     * @param arrivalLocation String
     * @return
     * @throws IOException
     * @throws ParseException
     */
    public List<Aircraft> createListOfJson(GenericList<Aircraft> aircraft, GenericList<City> cities, String arrivalLocation) throws IOException, ParseException {
        int roundedNum = 0;

        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(arrivalLocation); //D:\\Flights.json"
        Object obj = parser.parse(reader);
        JSONObject pJsonObj = (JSONObject) obj;
        JSONArray array = (JSONArray) pJsonObj.get("Flights");

        for (int i = 0; i < array.size(); i++) {
            JSONObject flights = (JSONObject) array.get(i);
            String flightNumber = (String) flights.get("flightNumber");
            String timeTakeOf = (String) flights.get("timeTakeOf");
            Long passengerNumber = (Long) flights.get("passengerNumber");
            double latitude1 = (Double)flights.get("latitude1");
            double longitude1 = (Double)flights.get("longitude1");
            double latitude2 = (Double) flights.get("latitude2");
            double longitude2 = (Double) flights.get("longitude2");

            double distance = Haversine.getDistance(latitude1,latitude1,latitude2,longitude2);
            roundedNum = (int) distance;

            String parseString = timeTakeOf.replace(":", ".");
            float floatTime = Float.parseFloat(parseString);

            Integer parseIntPassengerNumber = Integer.parseInt(String.valueOf(passengerNumber));


//            System.out.println("\n Aircraft: ");
//            System.out.println("flightNumber: " + flightNumber);
//            System.out.println("timeTakeOf: " + timeTakeOf);
//            System.out.println("passengerNumber: " + passengerNumber);
//            System.out.println("latitude1: " + latitude1);
//            System.out.println("longitude1: " + longitude1);
//            System.out.println("latitude2: " + latitude2);
//            System.out.println("longitude2: " + longitude2);

            String departure = "";
            String arrivals = "";

            for (City a : cities.getData()) {
                if (a.getLatitude() == latitude1 && a.getLongitude() == longitude1) {

                    departure = a.getName();

                }
                if (a.getLatitude() == latitude2 && a.getLongitude() == longitude2) {

                    arrivals = a.getName();

                }
            }
            Aircraft air = new Aircraft();
            air.setFlightNumber(flightNumber);
            air.setTimeTakeOf(floatTime);
            air.setPassengerNumber(parseIntPassengerNumber);
            air.setDepartureLocation(departure);
            air.setArrivalLocation(arrivals);
            air.setFlightRange(roundedNum);


            aircraft.addData(air);
        }
        System.out.println("-------------All Data has been correctly charged in DataBase");
        return null;
    }
}






