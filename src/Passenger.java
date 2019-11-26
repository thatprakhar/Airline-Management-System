import java.io.Serializable;

public class Passenger implements Serializable {
    private String firstName;
    private String lastName;
    private int age;
    private boolean hasTicket;
    private BoardingPass boardingPass;

    public Passenger(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.hasTicket = false;
        this.boardingPass = null;
    }

    public void setHasTicket(boolean hasTicket) {
        this.hasTicket = hasTicket;
    }

    public void setBoardingPass(BoardingPass boardingPass) {
        if (hasTicket) {
            this.boardingPass = boardingPass;
        }
    }

    public String toString() {
        return this.firstName.charAt(0) + ". " + this.lastName + ", " + this.age;
    }
}
