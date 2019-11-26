import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


public class FlightDetailView {
    private JLabel airlineName;
    private JPanel mainPanel;
    private JFormattedTextField passengerList;
    private Airline airline;
    private JScrollPane passengerView;
    private JPanel topPanel;
    private JButton exit;


    public FlightDetailView(FlightSelectView flightSelectView) {
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
        this.mainPanel = new JPanel(new BorderLayout());
        this.topPanel = new JPanel(new BorderLayout());
        this.exit = new JButton("Exit");
        this.passengerList = new JFormattedTextField(airline.returnPassengerList());
        this.passengerList.setEditable(false);
        this.passengerView = new JScrollPane();
        this.passengerView.add(passengerList);

        this.passengerView.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.mainPanel.add(passengerView, BorderLayout.CENTER);
        airlineName.setText("<html> <b> <font size = \"8\"" + airlineName.getText() + " Airlines </font> </b> </html>");
        this.topPanel.add(airlineName, BorderLayout.NORTH);
        this.mainPanel.add(topPanel, BorderLayout.NORTH);
        this.passengerView.setSize(230, 150);
        this.mainPanel.add(passengerView, BorderLayout.CENTER);
        this.mainPanel.add(exit, BorderLayout.SOUTH);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
