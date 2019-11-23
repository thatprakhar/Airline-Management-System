import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;

public final class Delta implements Airline {
    private ArrayList<Passenger> passengers;
    private int currentCapacity;
    private Gate gate;
    private File file;

    public Delta() {
        this.passengers = new ArrayList<Passenger>();
        currentCapacity = 0;
        this.gate = new Gate('B');
    }

    @Override
    public void addPassenger(Passenger passenger) throws IOException {
        this.passengers.add(passenger);
        this.writeIntoFile(file, passenger);
        this.currentCapacity++;
    }

    @Override
    public ArrayList<String> returnPassengerList() {
        ArrayList<String> passengerList = new ArrayList<String>();
        for (Passenger p : this.passengers) {
            passengerList.add(p.toString());
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
    public void writeIntoFile(File file, Passenger p) throws IOException {
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
