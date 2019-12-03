import javax.swing.*;
import java.io.IOException;


/**
 * Project 5, CS 180
 * Airline Management System
 *
 * @author Prakhar Nahar, Vivek Natarajan
 * @version 12/3/19
 */
public class FlightBookViewController {

    private FlightBookView flightBookView;
    private JFrame mainFrame;

    public FlightBookViewController(JFrame mainFrame, FlightBookView flightBookView) {
        this.mainFrame = mainFrame;
        this.flightBookView = flightBookView;
        this.flightBookView.getBook().addActionListener(e -> {
            try {
                Book();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        this.flightBookView.getExit().addActionListener(e -> exit());

    }

    private void exit() {
        this.mainFrame.setVisible(false);
    }

    public void Book() throws IOException {
        String firstName = this.flightBookView.getFirstNameField().getText();
        String lastName = this.flightBookView.getLastNameField().getText();

        if (firstName == null || firstName.equals("") || firstName.equals(" ")) {
            JOptionPane.showMessageDialog(null, "Please " +
                    "enter correct first name", "Invalid info", JOptionPane.ERROR_MESSAGE);
            return;
        }
        char[] f = firstName.toCharArray();
        for (int i = 0; i < firstName.length(); i++) {
            if (!Character.isLetter(f[i]) && !(f[i] == '-')) {
                JOptionPane.showMessageDialog(null, "First name cannot " +
                        "have special characters!", "Invalid info", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if (lastName == null || lastName.equals(" ") || lastName.equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter correct " +
                    "last name", "Invalid info", JOptionPane.ERROR_MESSAGE);
            return;
        }
        char[] n = lastName.toCharArray();

        for (int i = 0; i < lastName.length(); i++) {
            if (!Character.isLetter(n[i]) && !(n[i] == '-')) {
                JOptionPane.showMessageDialog(null, "Last name cannot " +
                        "have special characters!", "Invalid info", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        String age;
        try {
            age = this.flightBookView.getAgeField().getText();
            int a = Integer.parseInt(age);
        } catch (NumberFormatException a) {
            JOptionPane.showMessageDialog(null, "Please enter correct " +
                    "age", "Invalid info", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String c = "Are all the details you entered correct?" + "\n" +
                "The passenger's name is " + firstName + " " + lastName +
                " and their age is " + age + ".\n" + " If the information is correct, click " +
                "Yes.\n Otherwise, click No";

        int y = JOptionPane.showConfirmDialog(this.mainFrame, c, "confirm Info", JOptionPane.YES_NO_OPTION);

        if (y == 0) {
            this.flightBookView.getFlightSelectView().getSocketWriter().writeObject(firstName);
            this.flightBookView.getFlightSelectView().getSocketWriter().writeObject(lastName);
            this.flightBookView.getFlightSelectView().getSocketWriter().writeObject(age);
            this.mainFrame.getContentPane().removeAll();
            BoardingPassView boardingPassView = new BoardingPassView(this.flightBookView);
            new BoardingPassViewController(this.mainFrame, boardingPassView);
            this.mainFrame.setContentPane(boardingPassView.getMainPanel());
        } else {
            this.flightBookView.getFirstNameField().setText(null);
            this.flightBookView.getLastNameField().setText(null);
            this.flightBookView.getAgeField().setText(null);
        }


    }

}
