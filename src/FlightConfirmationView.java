import javax.swing.*;
import java.awt.*;


/**
 * Project 5, CS 180
 * Airline Management System
 *
 * @author Prakhar Nahar, Vivek Natarajan
 * @version 12/3/19
 */
public class FlightConfirmationView {
    private JPanel jPanel;
    private JLabel jLabel;
    private JButton exit;
    private JButton changeFLight;
    private JButton confirmFlight;
    private String airline;
    private FlightSelectView flightSelectView;

    public FlightConfirmationView(FlightSelectView flightSelectView) {
        this.flightSelectView = flightSelectView;
        this.airline = (String) flightSelectView.getFlightSelection().getSelectedItem();
        createGUI();
        this.confirmFlight.requestFocus();
    }

    public void createGUI() {
        this.jPanel = new JPanel(new BorderLayout());
        JPanel lowerPanel = new JPanel(new FlowLayout());
        this.jLabel = new JLabel("Are you sure you want to book a flight on " + airline + " Airlines?");
        this.exit = new JButton("Exit");
        this.changeFLight = new JButton("No, I want to change my flight");
        this.confirmFlight = new JButton("Yes, book this flight");
        exit.setSize(60, 30);
        confirmFlight.setSize(100, 30);
        changeFLight.setSize(120, 30);


        lowerPanel.add(exit);
        lowerPanel.add(changeFLight);
        lowerPanel.add(confirmFlight);

        this.jPanel.add(jLabel, BorderLayout.NORTH);
        this.jPanel.add(lowerPanel, BorderLayout.CENTER);
        jPanel.setSize(600, 600);
    }

    public JButton getExit() {
        return exit;
    }

    public JButton getChangeFLight() {
        return changeFLight;
    }

    public JButton getConfirmFlight() {
        return confirmFlight;
    }

    public JPanel getjPanel() {
        return jPanel;
    }

    public FlightSelectView getFlightSelectView() {
        return flightSelectView;
    }
}

