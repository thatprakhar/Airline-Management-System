import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class FlightSelectViewController {
    private FlightSelectView flightSelectView;
    private JFrame mainFrame;
    private JFrame flightDetails;
    private String airline;

    public FlightSelectViewController(JFrame mainFrame, FlightSelectView flightSelectView) {
        this.flightSelectView = flightSelectView;
        this.mainFrame = mainFrame;
        this.flightSelectView.getFlightSelection().requestFocus();
        airline = (String) this.flightSelectView.getFlightSelection().getSelectedItem();
        this.flightSelectView.getFlightSelection().addActionListener(e -> updateTextSemantics());
        this.flightSelectView.getChoose().addActionListener(e -> bookFlightSemantics());

        this.flightSelectView.getFlightSelection().addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SLASH) {
                    flightDetails = new JFrame();
                    FlightDetailView flightDetailView = null;
                    try {
                        flightDetailView = new FlightDetailView(flightDetails, flightSelectView, flightSelectView.getClientSocket());
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    assert flightDetailView != null;
                    flightDetails.setContentPane(flightDetailView.getMainPanel());
                    new FlightDetailViewController(flightDetailView);
                    flightDetails.setSize(250, 250);
                    flightDetails.setVisible(true);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        this.flightSelectView.getExit().addActionListener(e -> exitButtonSemantics());
        this.flightSelectView.getFlightSelection().requestFocus();

    }

    public void updateTextSemantics() {
        String details;
        String flight = (String) this.flightSelectView.getFlightSelection().getSelectedItem();
        assert flight != null;
        if (flight.equals("Delta")) {
            details = new Delta().airlineDetails();
        } else {
            details = flight;
        }

        this.flightSelectView.getAirlineDetail().setText(details);
    }

    public void bookFlightSemantics() {

        this.mainFrame.getContentPane().removeAll();
        FlightBookView flightBookView = new FlightBookView(flightSelectView, new Alaska(), this.flightSelectView.getClientSocket());
        new FlightBookViewController(this.mainFrame, flightBookView);
        this.mainFrame.setContentPane(flightBookView.getMainPanel());
    }

    public void exitButtonSemantics() {
        this.mainFrame.setVisible(false);
    }

    public JFrame getFlightDetails() {
        return flightDetails;
    }
}
