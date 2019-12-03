import javax.swing.*;
import java.awt.*;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


/**
 * Project 5, CS 180
 * Airline Management System
 *
 * @author Prakhar Nahar, Vivek Natarajan
 * @version 12/3/19
 */
public class FlightSelectView {
    private JPanel mainPanel;
    private JLabel jLabel;
    private JPanel upperPanel;
    private JComboBox<Object> flightSelection;
    private JTextArea airlineDetail;
    private JPanel lowerPanel;
    private JButton exit;
    private JButton choose;
    private JPanel midPanel;
    private Socket clientSocket;

    private ResponseListener dataExchanger;


    public FlightSelectView(ResponseListener dataExchanger) throws IOException, ClassNotFoundException {
        this.dataExchanger = dataExchanger;
        this.clientSocket = dataExchanger.getSocket();
        createGUI();
    }

    public void createGUI() throws IOException, ClassNotFoundException {

        //initialising the panels
        this.mainPanel = new JPanel(new BorderLayout());
        this.lowerPanel = new JPanel(new BorderLayout());
        this.upperPanel = new JPanel(new BorderLayout());
        this.midPanel = new JPanel(new BorderLayout());

        //assigning the components
        jLabel = new JLabel("<html> <b> <font size = \"8\"> Choose a " +
                "flight from the drop down menu </font> </b> </html>");
        this.choose = new JButton("Choose this flight");
        this.exit = new JButton("Exit");
        Object[] flights = new String[0];

        boolean gotData = false;

        while (!gotData) {
            try {
                flights = (Object[]) dataExchanger.getSocketReader().readObject();
                gotData = true;
            } catch (EOFException e) {
                e.printStackTrace();
            }

        }

        this.flightSelection = new JComboBox<>(flights);
        flightSelection.setSelectedIndex(0);
        airlineDetail = new JTextArea(new Alaska().airlineDetails());

        airlineDetail.setEditable(false);
        airlineDetail.setLineWrap(true);

        //adding the components
        this.upperPanel.add(jLabel, BorderLayout.NORTH);
        this.upperPanel.add(flightSelection, BorderLayout.SOUTH);
        this.lowerPanel.add(exit, BorderLayout.WEST);
        this.lowerPanel.add(choose, BorderLayout.EAST);
        this.mainPanel.add(upperPanel, BorderLayout.NORTH);
        this.midPanel.add(airlineDetail, BorderLayout.CENTER);
        this.mainPanel.add(midPanel, BorderLayout.CENTER);
        this.mainPanel.add(lowerPanel, BorderLayout.SOUTH);
        this.mainPanel.setSize(600, 600);
        this.flightSelection.requestFocus();
    }

    public JPanel getMainPanel() {
        return this.mainPanel;
    }

    public JComboBox getFlightSelection() {
        return this.flightSelection;
    }

    public JTextArea getAirlineDetail() {
        return this.airlineDetail;
    }

    public JButton getExit() {
        return exit;
    }

    public JButton getChoose() {
        return this.choose;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public ObjectOutputStream getSocketWriter() {
        return dataExchanger.getSocketWriter();
    }

    public ObjectInputStream getSocketReader() {
        return dataExchanger.getSocketReader();
    }

    public ResponseListener getDataExchanger() {
        return dataExchanger;
    }
}
