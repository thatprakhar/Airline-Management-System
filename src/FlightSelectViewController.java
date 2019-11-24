import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FlightSelectViewController {
    private FlightSelectView flightSelectView;
    private JFrame mainFrame;
    private JFrame flightDetails;

    public FlightSelectViewController(JFrame mainFrame, FlightSelectView flightSelectView) {
        this.flightSelectView = flightSelectView;
        this.mainFrame = mainFrame;
        this.flightSelectView.getFlightSelection().addActionListener(e -> updateTextSemantics());
        this.flightSelectView.getFlightSelection().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SLASH) {
                    flightDetails = new JFrame();
                    flightDetails.setContentPane(new FlightDetailView().getMainPanel());
                    flightDetails.setSize(250, 250);
                    flightDetails.setVisible(true);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

    }

    public void updateTextSemantics() {
        String details;
        String flight = (String) this.flightSelectView.getFlightSelection().getSelectedItem();
        assert flight != null;

        this.flightSelectView.getAirlineDetail().setText(flight);
    }


}
