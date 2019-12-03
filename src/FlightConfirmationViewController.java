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

public class FlightConfirmationViewController {
    private FlightConfirmationView flightConfirmationView;
    private JFrame mainFrame;
    private JFrame flightDetails;
    private FlightSelectView flightSelectView;


    public FlightConfirmationViewController(JFrame jFrame, FlightConfirmationView flightConfirmationView) {
        this.flightSelectView = flightConfirmationView.getFlightSelectView();
        this.mainFrame = jFrame;
        this.flightConfirmationView = flightConfirmationView;
        this.flightConfirmationView.getConfirmFlight().addActionListener(e -> {
            try {
                BookFlight();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        this.flightConfirmationView.getExit().addActionListener(ge -> exit());
        this.flightConfirmationView.getChangeFLight().addActionListener(c -> {
            try {
                change();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        this.flightConfirmationView.getConfirmFlight().addKeyListener(new KeyListener() {

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

        this.flightConfirmationView.getConfirmFlight().requestFocus();
    }

    public void BookFlight() throws IOException {
        this.flightConfirmationView.getFlightSelectView().getSocketWriter().writeObject("final");
        this.flightConfirmationView.getFlightSelectView().getSocketWriter().flush();

        String a = (String) this.flightConfirmationView.getFlightSelectView().getFlightSelection().getSelectedItem();

        this.flightConfirmationView.getFlightSelectView().getSocketWriter().writeObject(a);
        this.flightConfirmationView.getFlightSelectView().getSocketWriter().flush();

        this.mainFrame.getContentPane().removeAll();
        FlightBookView flightBookView = new FlightBookView(this.flightConfirmationView.getFlightSelectView(), a);
        new FlightBookViewController(this.mainFrame, flightBookView);
        this.mainFrame.setContentPane(flightBookView.getMainPanel());

    }

    public void exit() {
        this.mainFrame.setVisible(false);
    }

    public void change() throws IOException, ClassNotFoundException {
        this.mainFrame.getContentPane().removeAll();
        this.flightConfirmationView.getFlightSelectView().getSocketWriter().writeObject("change");
        FlightSelectView flightSelectView =
                new FlightSelectView(this.flightConfirmationView.getFlightSelectView().getDataExchanger());
        new FlightSelectViewController(this.mainFrame, flightSelectView);
        this.mainFrame.setContentPane(flightSelectView.getMainPanel());
    }
}
