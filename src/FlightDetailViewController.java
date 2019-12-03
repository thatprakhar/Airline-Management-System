import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * Project 5, CS 180
 * Airline Management System
 *
 * @author Prakhar Nahar, Vivek Natarajan
 * @version 12/3/19
 */
public class FlightDetailViewController {
    private FlightDetailView flightDetailView;

    public FlightDetailViewController(FlightDetailView flightDetailView) {
        this.flightDetailView = flightDetailView;
        this.flightDetailView.getExit().addActionListener(e -> exitButtonSemantics());
        this.flightDetailView.getExit().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

                    exitButtonSemantics();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        this.flightDetailView.getExit().requestFocus();
    }

    public void exitButtonSemantics() {
        this.flightDetailView.getMainFrame().setVisible(false);
    }

}
