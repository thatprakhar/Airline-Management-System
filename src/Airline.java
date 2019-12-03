import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Project 5, CS 180
 * Airline Management System
 *
 * @author Prakhar Nahar, Vivek Natarajan
 * @version 12/3/19
 */

public interface Airline extends Serializable {
    void addPassenger(Passenger passenger) throws IOException;

    ArrayList<String> returnPassengerList();

    String airlineDetails();

    int currentCapacity();

    int MAX_CAP = 100;

    void writeIntoFile(Passenger p) throws IOException;

    void readFromFile(File file) throws IOException;

    Gate getGate();
}
