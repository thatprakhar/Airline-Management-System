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

public class FlightDetailView {
    private JFrame mainFrame;
    private JLabel airlineName;
    private JPanel mainPanel;
    private Airline airline;
    private JScrollPane passengerView;
    private JButton exit;
    private FlightSelectView flightSelectView;


    public FlightDetailView(JFrame mainFrame, FlightSelectView flightSelectView,
                            Airline airline) throws IOException, ClassNotFoundException {

        this.mainFrame = mainFrame;
        this.flightSelectView = flightSelectView;
        this.airlineName = new JLabel((String) flightSelectView.getFlightSelection().getSelectedItem());
        this.airline = airline;
        /*boolean gotData = false;
        while (!gotData) {
            try {
                this.airline = (Airline) new ObjectInputStream(clientSocket.getInputStream()).readObject();
                gotData = true;
            } catch (EOFException e) {
                e.printStackTrace();
            }
        }
         */
        createGUI();
    }

    public void createGUI() {
        //initialising the panels
        this.mainPanel = new JPanel(new BorderLayout());

        //assigning the components
        this.exit = new JButton("Exit");
        this.passengerView = new JScrollPane();

        Object[] objects = airline.returnPassengerList().toArray();
        String[] strings = new String[objects.length];

        for (int i = 0; i < objects.length; i++) {
            strings[i] = (String) objects[i];
        }

        JList<String> f = new JList<>(strings);
        passengerView = new JScrollPane(f);


        this.passengerView.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.mainPanel.add(passengerView, BorderLayout.CENTER);
        airlineName.setText(airlineName.getText() + " Airlines " + this.airline.currentCapacity() + "/100");

        //adding the components
        this.mainPanel.add(airlineName, BorderLayout.NORTH);
        this.passengerView.setSize(230, 150);
        this.mainPanel.add(passengerView, BorderLayout.CENTER);
        this.mainPanel.add(exit, BorderLayout.SOUTH);
        this.exit.requestFocus();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getExit() {
        return this.exit;
    }

    public FlightSelectView getFlightSelectView() {
        return this.flightSelectView;
    }

    public JFrame getMainFrame() {
        return this.mainFrame;
    }

}
