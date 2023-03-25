package View;

import controller.Controller;
import model.Aircraft;
import model.City;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * View Class
 *
 * @author Marcus
 */

public class FlightCalculator {

    Scanner keyboard = new Scanner(System.in);
    Controller ctrl = new Controller();


    public static void main(String[] args) {

        FlightCalculator fl = new FlightCalculator();
        fl.addData();
        fl.menu();


    }

    private void addData() {

        City Melbourne = new City("Melbourne",-37.6732,144.8431);
        ctrl.getData().getCities().addData(Melbourne);
        City Dublin = new City("Dublin",53.4264,-6.2499);
        ctrl.getData().getCities().addData(Dublin);
        City Vancouver = new City("Vancouver",49.1966,-123.1815);
        ctrl.getData().getCities().addData(Vancouver);
        City Barcelona= new City("Barcelona",41.2974,2.0832);
        ctrl.getData().getCities().addData(Barcelona);
        City Paris= new City("Paris",48.8566,2.3522);
        ctrl.getData().getCities().addData(Paris);
        City Los_Angeles= new City("Los_Angeles",33.941,-118.4085);
        ctrl.getData().getCities().addData(Los_Angeles);
        City London= new City("London",51.5073,-0.1277);
        ctrl.getData().getCities().addData(London);


        Aircraft aircraft1 = new Aircraft("1", 12.35F, 250, "Barcelona", "Madrid", 12000); //ok
        ctrl.getData().getAircraft().addData(aircraft1);
        Aircraft aircraft2 = new Aircraft("2", 12.35F, 251, "Tarragona", "Barcelona", 9000);
        ctrl.getData().getAircraft().addData(aircraft2);
        Aircraft aircraft3 = new Aircraft("3", 12.35F, 251, "Oporto", "Salamanca", 8000);// ok
        ctrl.getData().getAircraft().addData(aircraft3);
        Aircraft aircraft4 = new Aircraft("4", 12.35F, 250, "Alicante", "Oporto", 16000);
        ctrl.getData().getAircraft().addData(aircraft4);

        //Testing time loops

        Aircraft aircraft5 = new Aircraft("5", 14.00F, 250, "London", "Glasgow", 12000);
        ctrl.getData().getAircraft().addData(aircraft5);
        Aircraft aircraft6 = new Aircraft("6", 14.35F, 251, "Manchester", "Edinburg", 7000); //ok
        ctrl.getData().getAircraft().addData(aircraft6);
        Aircraft aircraft7 = new Aircraft("7", 14.35F, 251, "Dublin", "Birmingham", 8000); //ok
        ctrl.getData().getAircraft().addData(aircraft7);
        Aircraft aircraft8 = new Aircraft("8", 14.35F, 251, "Paris", "Luxembourg", 9000); //ok
        ctrl.getData().getAircraft().addData(aircraft8);
    }


    void menu() {
        boolean goOught = false;
        String option;

        do {
            System.out.println("***************************************");
            System.out.println();
            System.out.println("Marcus' calculator");
            System.out.println("***************************************");
            System.out.println();
            System.out.println(" 1. Introduce flight details");
            System.out.println(" 2. Show all flights (by default we have some data already charged)");
            System.out.println(" 3. Show possible flights");
            System.out.println(" 4. Add Flights from file.txt");
            System.out.println(" 5. read from Json");
            option = askData();
            switch (option) {
                case "1":
                    addFlightDetails();
                    break;
                case "2":
                    showAllFlights();
                    break;
                case "3":
                    showPossibleFlights();
                    break;
                case "4":
                    System.out.println(readFromHaversine());
                    break;
                case "5":
                    System.out.println("read from Json");
                    break;
                case "0":
                    goOught = true;
                default:
                    System.out.println("no one option with this number exist");
            }
        } while (!goOught);
    }

    String askData() {
        String incomeOption;
        keyboard = new Scanner(System.in);

        System.out.println("Please, chose some option or type 0 to (Exit): ");

        incomeOption = keyboard.nextLine();
        if (incomeOption.isEmpty()) {
            incomeOption = " ";
        }
        return incomeOption;
    }


    void addFlightDetails() {
        System.out.println("Please, introduce a Flight number: ");
        String fNumber = keyboard.nextLine();

        if (ctrl.flightNumberExist(fNumber)) {
            System.out.println("The flight with this number " + fNumber + " already exist");
            addFlightDetails();
        } else {
            //TODO: Alert of time
            System.out.println("Time of take off: ");
            float timeTakeOf = Float.parseFloat(keyboard.nextLine());

            System.out.println("Number of Passengers: ");
            int passengerNumber = Integer.parseInt(keyboard.nextLine());
            System.out.println(ctrl.numberOfPassengersLess(passengerNumber));

            System.out.println("Airport of departure: ");
            String departureLocation = keyboard.nextLine();

            System.out.println("Airport of arrival: ");
            String arrivalLocation = keyboard.nextLine();

            System.out.println("If you have latitude and longitude data press 1 otherwise press 2:");

            boolean goOught = false;
            String option;

            double flightRange = 0;

            do {
            option = askData();
                switch (option) {
                    case "1":
                        System.out.println("Introduce latitude1: ");
                        double latitude1 = Double.parseDouble(keyboard.nextLine());
                        System.out.println("Introduce latitude1: ");
                        double longitude1 = Double.parseDouble(keyboard.nextLine());
                        System.out.println("Introduce latitude2: ");
                        double latitude2 = Double.parseDouble(keyboard.nextLine());
                        System.out.println("Introduce latitude2: ");
                        double longitude2 = Double.parseDouble(keyboard.nextLine());

                        flightRange = ctrl.Haversine(latitude1,longitude1,latitude2,longitude2);
                        int roundedNum = (int) flightRange;

                        System.out.println(ctrl.addFlightDetails(fNumber, timeTakeOf, passengerNumber,
                                departureLocation, arrivalLocation, roundedNum));
                        goOught = true;
                        break;
                    case "2":
                        boolean validInput = false;
                        while (!validInput) {
                            try {
                                System.out.println("Introduce the distance in between: ");
                                roundedNum = keyboard.nextInt();
                                System.out.println(ctrl.addFlightDetails(fNumber, timeTakeOf, passengerNumber,
                                        departureLocation, arrivalLocation, roundedNum));
                                validInput = true;

                            }
                            catch (InputMismatchException e) {
                                System.out.println("The number should to be Integer. Please try again");
                                keyboard.nextLine();
                            }
                        }





                        goOught = true;
                        break;
                    case "0":
                        goOught = true;
                    default:
                        System.out.println("no one option with this number exist");
                }
            }while (!goOught);
        }
    }

    public void showAllFlights() {
        for (Aircraft a : ctrl.getData().getAircraft().getData()) {
            System.out.println(a + "\n");

        }
    }

    private void showPossibleFlights() {
        for (Aircraft air : ctrl.aircraftAvailable()) {
            System.out.println(air.toString());
        }
    }

    private String readFromHaversine() {

        return ctrl.saveObjectsHaversine();
    }

}