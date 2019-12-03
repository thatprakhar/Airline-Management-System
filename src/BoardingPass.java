import java.io.IOException;
import java.io.Serializable;


/**
 * Project 5, CS 180
 * Airline Management System
 *
 * @author Prakhar Nahar, Vivek Natarajan
 * @version 12/3/19
 */

public class BoardingPass implements Serializable {
    //airline’s name, passenger’s first and last names, the passenger’s age, and the gate.

    private String airline;
    private String name;
    private int age;
    private char terminal;
    private Gate gate;
    private String flightNumber;

    public BoardingPass(String airline, String name, int age, Gate gate) throws IOException {
        this.airline = airline;
        this.name = name;
        this.age = age;
        this.gate = gate;
        this.terminal = terminal;
        switch (airline) {
            case "Alaska":
                this.flightNumber = new Alaska().getFlightNumber();
                break;
            case "Southwest":
                this.flightNumber = new Southwest().getFlightNumber();
                break;
            case "Delta":
                this.flightNumber = new Delta().getFlightNumber();
                break;
        }
    }

    public Gate getGate() {
        return gate;
    }

    public char getTerminal() {
        return terminal;
    }

    public int getAge() {
        return age;
    }

    public String getAirline() {
        return airline;
    }

    public String getName() {
        return name;
    }

    public String print() {
        String b = "";
        for (int i = 0; i < 25; i++) {
            b += "-";
        }
        b += "\n";
        b += "Boarding pass for flight with " + this.airline + " Airlines" + "\n";
        b += "Flight Number is " + this.flightNumber + "\n";
        b += "Passenger first name : " + this.name.substring(0, name.indexOf(" ")) + "\n";
        b += "Passenger last name : " + this.name.substring(name.indexOf(" ") + 1) + "\n";
        b += "Passenger Age : " + this.age + "\n";
        b += "You can now begin boarding at Gate " + gate.getTerminal() + gate.getGate() + "\n";
        for (int i = 0; i < 25; i++) {
            b += "-";
        }
        return b;
    }
}
