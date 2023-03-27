package model;

/**
 * Class for creating of generics collections of objects
 */
public class Data {

    private GenericList<Aircraft> aircraft;
    private GenericList<Haversine> haversine;
    private GenericList<City> cities;


    /**
     * Default Class constructor
     */

    public Data() {
        super();
        this.aircraft = new GenericList<Aircraft>();
        this.haversine = new GenericList<Haversine>();
        this.cities = new GenericList<City>();
    }

//    /**
//     * GenericList<Aircraft>
//     * @return aircraft
//     */
//    public GenericList<Aircraft> getAircraft() {
//        return aircraft;
//    }
//
//    /**
//     * addFlightDetails
//     * @param a object
//     */
//    public void addFlightDetails(Aircraft a) {
//        this.aircraft.addData(a);
//    }

    public GenericList<Aircraft> getAircraft() {
        return aircraft;
    }

        public void addFlightDetails(Aircraft a) {
        this.aircraft.addData(a);
    }
    public GenericList<Haversine> getHaversine() {
        return haversine;
    }

   public void addHaversineDetails(Haversine b) {
        this.haversine.addData(b);
    }
    public GenericList<City> getCities() {
        return cities;
    }
    public void adCity(City ab) {
        this.cities.addData(ab);
    }
}
