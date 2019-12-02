import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.ByteOrder;
import java.util.function.BiPredicate;

public class FlightBookView {
    private JPanel jPanel;

    private FlightSelectView flightSelectView;

    private JLabel firstName;
    private JTextField firstNameField;
    private JLabel lastName;
    private JTextField lastNameField;
    private JLabel age;
    private JTextField ageField;
    private Airline airline;
    private JButton book;




    public JTextField getFirstNameField() {
        return firstNameField;
    }

    public FlightBookView(FlightSelectView flightSelectView, Airline airline) throws IOException {

        this.airline = airline;
        this.flightSelectView = flightSelectView;


        createGUI();
    }

    public void createGUI() {
        jPanel = new JPanel(new GridLayout(6,1));
        firstName = new JLabel("First name");
        firstNameField = new JTextField();

        lastName = new JLabel(" Last name");
        lastNameField = new JTextField();

        age = new JLabel("Age");
        ageField = new JTextField();

        book = new JButton("Book this flight");


        jPanel.add(firstName);
        jPanel.add(firstNameField);
        jPanel.add(lastName);
        jPanel.add(lastNameField);
        jPanel.add(age);
        jPanel.add(ageField);
        jPanel.add(book);
        jPanel.setSize(600,600);
    }

    public JPanel getMainPanel() {
        return this.jPanel;
    }

    public JButton getBook() {
        return book;
    }

    public Airline getAirline() {
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




}
