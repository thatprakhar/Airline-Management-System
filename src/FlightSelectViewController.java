import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.EOFException;
import java.io.IOException;


/**
 * Project 5, CS 180
 * Airline Management System
 *
 * @author Prakhar Nahar, Vivek Natarajan
 * @version 12/3/19
 */
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
        this.flightSelectView.getFlightSelection().addActionListener(e -> {
            try {
                updateTextSemantics();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        this.flightSelectView.getChoose().addActionListener(e -> {
            try {
                bookFlightSemantics();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

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


                        flightSelectView.getSocketWriter().writeObject(
                                flightSelectView.getFlightSelection().getSelectedItem());
                        flightSelectView.getSocketWriter().flush();

                        boolean gotData = false;
                        Airline airline = null;
                        while (!gotData && airline == null) {
                            try {
                                airline = (Airline) flightSelectView.getSocketReader().readObject();
                                gotData = true;
                            } catch (EOFException eof) {
                                eof.printStackTrace();
                            }
                        }
                        flightDetailView = new FlightDetailView(flightDetails, flightSelectView, airline);
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    assert flightDetailView != null;
                    flightDetails.setContentPane(flightDetailView.getMainPanel());
                    new FlightDetailViewController(flightDetailView);
                    flightDetails.setSize(250, 250);
                    flightDetails.setVisible(true);
                    flightDetailView.getExit().requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        this.flightSelectView.getExit().addActionListener(e -> exitButtonSemantics());
        this.flightSelectView.getFlightSelection().requestFocus();

    }

    public void updateTextSemantics() throws IOException {
        String details;
        String flight = (String) this.flightSelectView.getFlightSelection().getSelectedItem();
        assert flight != null;
        if (flight.equals("Delta")) {
            details = new Delta().airlineDetails();
        } else if (flight.equals("Alaska")) {
            details = new Alaska().airlineDetails();
        } else {
            details = new Southwest().airlineDetails();
        }

        this.flightSelectView.getAirlineDetail().setText(details);
    }

    public void bookFlightSemantics() throws IOException {
        this.mainFrame.getContentPane().removeAll();
        FlightConfirmationView flightConfirmationView = new FlightConfirmationView(this.flightSelectView);
        new FlightConfirmationViewController(this.mainFrame, flightConfirmationView);
        this.mainFrame.setContentPane(flightConfirmationView.getjPanel());
        flightConfirmationView.getConfirmFlight().requestFocus();
    }

    public void exitButtonSemantics() {
        this.mainFrame.setVisible(false);
    }

    public JFrame getFlightDetails() {
        return flightDetails;
    }
}
