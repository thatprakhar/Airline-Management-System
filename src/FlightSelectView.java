import javax.swing.*;
import java.awt.*;


public class FlightSelectView {
    private JPanel mainPanel;
    private JLabel jLabel;
    private JPanel upperPanel;
    private JComboBox<String> flightSelection ;
    private JLabel airlineDetail;
    private JPanel lowerPanel;
    private JButton exit;
    private JButton choose;

    public FlightSelectView() {
        createGUI();
    }

    public void createGUI() {
        this.mainPanel = new JPanel(new BorderLayout());
        this.lowerPanel = new JPanel(new BorderLayout());
        this.upperPanel = new JPanel(new BorderLayout());
        jLabel = new JLabel("<html> <b> <font size = \"8\"> Choose a flight from the drop down menu </font> </b> </html>");
        this.choose = new JButton("Choose this flight");
        this.exit = new JButton("Exit");
        String[] flights = {"Alaska", "Delta", "Southwest"};
        this.flightSelection = new JComboBox<>(flights);
        flightSelection.setSelectedIndex(0);
        airlineDetail = new JLabel("Alaska");
        this.upperPanel.add(jLabel, BorderLayout.NORTH);
        this.upperPanel.add(flightSelection, BorderLayout.SOUTH);
        this.lowerPanel.add(exit, BorderLayout.WEST);
        this.lowerPanel.add(choose, BorderLayout.EAST);
        this.mainPanel.add(upperPanel, BorderLayout.NORTH);
        this.mainPanel.add(airlineDetail, BorderLayout.CENTER);
        this.mainPanel.add(lowerPanel, BorderLayout.SOUTH);
        this.mainPanel.setSize(600,600);
        this.flightSelection.requestFocus();
    }

    public JPanel getMainPanel() {
        return this.mainPanel;
    }

    public JComboBox getFlightSelection() {
        return this.flightSelection;
    }

    public JLabel getAirlineDetail() {
        return this.airlineDetail;
    }

    public JButton getExit() {
        return exit;
    }
}
