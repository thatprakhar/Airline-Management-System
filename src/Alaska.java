import java.io.*;
import java.util.ArrayList;


/**
 * Project 5, CS 180
 * Airline Management System
 *
 * @author Prakhar Nahar, Vivek Natarajan
 * @version 12/3/19
 */

public final class Alaska implements Airline {
    private ArrayList<Passenger> passengers;
    private int currentCapacity;
    private Gate gate;
    private File file;
    private ArrayList<String> passengerList;
    private final String FlightNumber = "18000";


    public Alaska() throws IOException {
        this.passengers = new ArrayList<Passenger>();
        currentCapacity = 0;
        this.gate = new Gate("A");
        this.file = new File("src/reservation.txt");
        passengerList = new ArrayList<>();
        this.readFromFile(file);
    }

    @Override
    public void addPassenger(Passenger passenger) throws IOException {
        if (currentCapacity < MAX_CAP) {
            this.passengers.add(passenger);
            this.currentCapacity++;
            this.passengerList.add(passenger.toString());
            this.writeIntoFile(passenger);

        }
    }

    @Override
    public ArrayList<String> returnPassengerList() {
        return this.passengerList;
    }

    @Override
    public String airlineDetails() {
        return "Alaska is proud to serve the strong and knowledgeable boilermakers at Purdue University.\n" +
                " We primarily fly westward, and have stops in Alaska and California.\n" +
                " We have First Class amenities, even in Coach Class.\n " +
                "We provide fun snacks, such as pretzels and goldfish.\n" +
                " We also have comfortable seats and free WiFi.\n " +
                "We hope you choose Alaska for your next flight!";
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

                if (s.contains("ALASKA")) {
                    strings.add("ALASKA " + this.currentCapacity + " / 100");
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


        for (int i = 0; i < strings.size() - 1; i++) {
            f += strings.get(i) + "\n";
        }

        f += strings.get(strings.size() - 1);

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

                if (s.contains("ALASKA")) {
                    s = bfr.readLine();
                    while (!s.contains("DELTA") && !s.contains("SOUTHWEST") && !s.equals("")) {
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
