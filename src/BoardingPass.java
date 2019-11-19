import java.io.Serializable;

public class BoardingPass implements Serializable {
    //airline’s name, passenger’s first and last names, the passenger’s age, and the gate.

    private String airline;
    private String name;
    private int age;
    private char terminal;
    private int gate;

    public BoardingPass(String airline, String name, int age, char terminal, int gate) {
        this.airline = airline;
        this.name = name;
        this.age = age;
        this.gate = gate;
        this.terminal = terminal;
    }

    public void printBoardingPass() {

    }

    public int getGate() {
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
}
