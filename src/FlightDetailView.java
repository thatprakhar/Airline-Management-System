import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;


public class FlightDetailView {
    private JFrame mainFrame;
    private JLabel airlineName;
    private JPanel mainPanel;
    private Airline airline;
    private JTextArea passengerView;
    private JButton exit;
    private FlightSelectView flightSelectView;
    private Socket clientSocket;


    public FlightDetailView(JFrame mainFrame, FlightSelectView flightSelectView, Socket clientSocket, Airline airline) throws IOException, ClassNotFoundException {
        this.clientSocket = clientSocket;
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
        this.passengerView = new JTextArea();
        passengerView.setEditable(false);
        passengerView.setLineWrap(true);
        this.passengerView.setText(airline.returnPassengerList());
        this.passengerView.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.mainPanel.add(passengerView, BorderLayout.CENTER);
        airlineName.setText(airlineName.getText() + " Airlines " + this.airline.currentCapacity() + "/100");

        //adding the components
        this.mainPanel.add(airlineName, BorderLayout.NORTH);
        this.passengerView.setSize(230, 150);
        this.mainPanel.add(passengerView, BorderLayout.CENTER);
        this.mainPanel.add(exit, BorderLayout.SOUTH);
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

    public Socket getClientSocket() {
        return this.clientSocket;
    }
}
