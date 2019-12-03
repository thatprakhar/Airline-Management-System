import javax.swing.*;
import java.awt.*;
import java.io.IOException;


/**
 * Project 5, CS 180
 * Airline Management System
 *
 * @author Prakhar Nahar, Vivek Natarajan
 * @version 12/3/19
 */
public class FlightBookView {
    private JPanel jPanel;

    private FlightSelectView flightSelectView;

    private JLabel firstName;
    private JTextField firstNameField;
    private JLabel lastName;
    private JTextField lastNameField;
    private JLabel age;
    private JTextField ageField;
    private String airline;
    private JButton exit;
    private JButton book;


    public JTextField getFirstNameField() {
        return firstNameField;
    }

    public FlightBookView(FlightSelectView flightSelectView, String airline) throws IOException {

        this.airline = airline;
        this.flightSelectView = flightSelectView;
        createGUI();
    }

    public void createGUI() {

        jPanel = new JPanel(new GridLayout(5, 2));
        JLabel jLabel = new JLabel("Please enter your details : ");
        JPanel first = new JPanel(new GridLayout(1, 2));
        firstName = new JLabel("First name");
        firstNameField = new JTextField();
        firstNameField.setPreferredSize(new Dimension(50, 18));

        first.add(firstName);
        first.add(firstNameField);


        JPanel last = new JPanel(new GridLayout(1, 2));
        lastName = new JLabel(" Last name");
        lastNameField = new JTextField();
        lastNameField.setPreferredSize(new Dimension(50, 18));

        last.add(lastName);
        last.add(lastNameField);

        JPanel agePanel = new JPanel(new GridLayout(1, 2));
        age = new JLabel("Age");
        ageField = new JTextField();
        ageField.setPreferredSize(new Dimension(50, 18));

        agePanel.add(age);
        agePanel.add(ageField);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        exit = new JButton("Exit");
        book = new JButton("Book this flight");
        buttonPanel.add(exit);
        buttonPanel.add(book);
        jPanel.add(jLabel);
        jPanel.add(first);
        jPanel.add(last);
        jPanel.add(agePanel);
        jPanel.add(buttonPanel);
        jPanel.setSize(600, 600);
    }

    public JPanel getMainPanel() {
        return this.jPanel;
    }

    public JButton getBook() {
        return book;
    }

    public String getAirline() {
        return this.airline;
    }

    public JTextField getAgeField() {
        return ageField;
    }

    public JTextField getLastNameField() {
        return lastNameField;
    }

    public FlightSelectView getFlightSelectView() {
        return flightSelectView;
    }

    public JButton getExit() {
        return this.exit;
    }

}
