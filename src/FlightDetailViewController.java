import javax.swing.plaf.ColorUIResource;

public class FlightDetailViewController {
    private FlightDetailView flightDetailView;

    public FlightDetailViewController(FlightDetailView flightDetailView) {
        this.flightDetailView = flightDetailView;
        this.flightDetailView.getExit().addActionListener(e -> exitButtonSemantics());
    }

    public void exitButtonSemantics() {
        this.flightDetailView.getMainFrame().setVisible(false);
    }

}
