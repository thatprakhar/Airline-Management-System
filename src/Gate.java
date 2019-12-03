import java.io.Serializable;


/**
 * Project 5, CS 180
 * Airline Management System
 *
 * @author Prakhar Nahar, Vivek Natarajan
 * @version 12/3/19
 */
public class Gate implements Serializable {
    private String terminal;
    private int gate;

    public Gate(String terminal) {
        this.terminal = terminal;
        gate = (int) (Math.random() * 17) + 1;
    }

    public void setGate(int gate) {
        this.gate = gate;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getTerminal() {
        return terminal;
    }

    public String getGate() {
        return String.valueOf(gate);
    }

    public String toString() {
        return terminal + gate;
    }
}
