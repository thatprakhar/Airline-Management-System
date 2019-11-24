import javax.swing.*;


public class FlightDetailView {
    private JPanel mainPanel;
    private JTextArea passengerList;

    public FlightDetailView() {
        createGUI();
    }

    public void createGUI() {
        this.mainPanel = new JPanel();
        this.passengerList = new JTextArea("adsdsad");
        this.mainPanel.add(passengerList);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
