import java.io.*;
import java.util.ArrayList;


/**
 * Project 5, CS 180
 * Airline Management System
 *
 * @author Prakhar Nahar, Vivek Natarajan
 * @version 12/3/19
 */

public final class Southwest implements Airline {
    private ArrayList<Passenger> passengers;
    private int currentCapacity;
    private Gate gate;
    private File file;
    private ArrayList<String> passengerList;
    private final String FlightNumber = "45000";


    public Southwest() throws IOException {
        this.passengers = new ArrayList<Passenger>();
        this.passengerList = new ArrayList<>();
        currentCapacity = 0;
        this.gate = new Gate("C");
        this.file = new File("src/reservation.txt");
        this.readFromFile(file);
    }

    @Override
    public void addPassenger(Passenger passenger) throws IOException {
        if (currentCapacity < MAX_CAP) {
            this.passengers.add(passenger);
            this.currentCapacity++;
            this.passengerList.add(passenger.toString());
            writeIntoFile(passenger);
        }
    }

    @Override
    public ArrayList<String> returnPassengerList() {
        return passengerList;
    }

    @Override
    public String airlineDetails() {
        return "Southwest is proud to offer flights to Purdue University.\n " +
                "We are happy to offer free in flight wifi, as well as our amazing snacks.\n" +
                " In addition, we offer flights for much cheaper than other " +
                "airlines, and\n offer two free checked bags.\n" +
                " We hope you choose Southwest for your next flight.";
    }

    @Override
    public int currentCapacity() {
        return this.currentCapacity;
    }

    @Override
    public void writeIntoFile(Passenger p) throws IOException {
        BufferedReader bfr = new BufferedReader(new FileReader(file));
        ArrayList<String> strings = new ArrayList<String>();

        String s = "";

        while (s != null) {
            try {
                s = bfr.readLine();
                if (s.contains("SOUTHWEST")) {
                    strings.add("SOUTHWEST " + this.currentCapacity + " / 100");
                    strings.add(p.toString());
                } else {
                    strings.add(s);
                }
            } catch (NullPointerException n) {
                break;
            }

        }
        bfr.close();

        BufferedWriter bfw = new BufferedWriter(new FileWriter(file));
        String f = "";
        for (String str : strings) {
            f += str + "\n";

        }

        bfw.write(f);
        bfw.close();
        bfw.close();
    }

    @Override
    public void readFromFile(File file) throws IOException {
        BufferedReader bfr = new BufferedReader(new FileReader(file));
        ArrayList<String> passengers = new ArrayList<String>();

        String s;
        try {
            while ((s = bfr.readLine()) != null && !s.equals("")) {

                if (s.contains("SOUTHWEST")) {
                    s = bfr.readLine();
                    while (!s.contains("ALASKA") && !s.contains("DELTA") && !s.equals("")) {
                        this.passengerList.add(s);
                        currentCapacity++;
                        s = bfr.readLine();
                        if (s.equals("")) {
                            break;
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            // do nothing
        }


    }

    @Override
    public Gate getGate() {
        return this.gate;
    }

    public File getFile() {
        return this.file;
    }

    public String getFlightNumber() {
        return FlightNumber;
    }
}
