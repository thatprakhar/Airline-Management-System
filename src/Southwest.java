import java.io.*;
import java.util.ArrayList;

public final class Southwest implements Airline {
    private ArrayList<Passenger> passengers;
    private int currentCapacity;
    private Gate gate;
    private File file;

    public Southwest() {
        this.passengers = new ArrayList<Passenger>();
        currentCapacity = 0;
        this.gate = new Gate('C');
        this.file = new File("src/reservation.txt");
    }

    @Override
    public void addPassenger(Passenger passenger) throws IOException {
        if (currentCapacity < MAX_CAP) {
            this.passengers.add(passenger);
            writeIntoFile(passenger);
            this.currentCapacity++;
        }
    }

    @Override
    public String returnPassengerList() {
        String passengerList ="";
        for (Passenger p : this.passengers) {
            passengerList += p.toString() + "\n";
        }
        return passengerList;
    }

    @Override
    public String airlineDetails() {
        return null;
    }

    @Override
    public int currentCapacity() {
        return this.currentCapacity;
    }

    @Override
    public void writeIntoFile(Passenger p) throws IOException {

        BufferedWriter bfw = new BufferedWriter(new FileWriter(file));

        bfw.write(p.toString());

    }

    @Override
    public void readFromFile(File file) {

    }

    @Override
    public Gate getGate() {
        return this.gate;
    }
}
