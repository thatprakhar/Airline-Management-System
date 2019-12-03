import java.io.*;
import java.util.ArrayList;


/**
 * Project 5, CS 180
 * Airline Management System
 *
 * @author Prakhar Nahar, Vivek Natarajan
 * @version 12/3/19
 */
public final class Delta implements Airline {
    private ArrayList<Passenger> passengers;
    private int currentCapacity;
    private Gate gate;
    private File file;
    private ArrayList<String> passengerList;
    private final String FlightNumber = "24000";


    public Delta() throws IOException {
        this.passengers = new ArrayList<Passenger>();
        this.passengerList = new ArrayList<>();
        currentCapacity = 0;
        this.gate = new Gate("B");
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
        return this.passengerList;
    }

    @Override
    public String airlineDetails() {
        return "Delta Airlines is proud to be one of the five" +
                " premier Airlines at Purdue University.\n We are offer exceptional services, with free limited " +
                "WiFi for all customers." +
                "\n Passengers who use T-Mobile as a cellphone carrier get additional benefits.\n We are also " +
                "happy to" +
                " offer power outlets in each seat for customer use.\n We hope you choose Delta for your next flight.";
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

                if (s.contains("DELTA")) {
                    strings.add("DELTA " + this.currentCapacity + " / 100");
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

                if (s.contains("DELTA")) {
                    s = bfr.readLine();
                    while (!s.contains("ALASKA") && !s.contains("SOUTHWEST") && !s.equals("")) {
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
