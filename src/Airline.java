import java.io.File;
import java.io.IOException;
import java.io.Serializable;


public interface Airline extends Serializable {
    void addPassenger(Passenger passenger) throws IOException;
    String returnPassengerList();
    String airlineDetails();
    int currentCapacity();
    int MAX_CAP = 100;
    void writeIntoFile(File file, Passenger p) throws IOException;
    void readFromFile(File file);
    Gate getGate();
}
