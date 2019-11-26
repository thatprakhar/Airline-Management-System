import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


public class FlightDetailView {
    private JFrame mainFrame;
    private JLabel airlineName;
    private JPanel mainPanel;
    private Airline airline;
    private JTextArea passengerView;
    private JPanel topPanel;
    private JButton exit;
    private JPanel lowerPanel;
    private FlightSelectView flightSelectView;


    public FlightDetailView(JFrame mainFrame, FlightSelectView flightSelectView) {
        this.mainFrame = mainFrame;
        this.flightSelectView = flightSelectView;
        this.airlineName = new JLabel((String) flightSelectView.getFlightSelection().getSelectedItem());

        switch (airlineName.getText()) {
            case "Alaska":
                this.airline = new Alaska();
                break;
            case "Delta":
                this.airline = new Delta();

                break;
            case "Southwest":
                this.airline = new Southwest();
                break;
        }

        createGUI();
    }

    public void createGUI() {
        //initialising the panels
        this.mainPanel = new JPanel(new BorderLayout());
        this.topPanel = new JPanel(new BorderLayout());
        this.lowerPanel = new JPanel(new BorderLayout());

        //assigning the components
        this.exit = new JButton("Exit");
        this.passengerView = new JTextArea();
        passengerView.setEditable(false);
        passengerView.setLineWrap(true);
        this.passengerView.setText(airline.returnPassengerList());
        this.passengerView.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.mainPanel.add(passengerView, BorderLayout.CENTER);
        airlineName.setText(airlineName.getText() + "Airlines");

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
}
