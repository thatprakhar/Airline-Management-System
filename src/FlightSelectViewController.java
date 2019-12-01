import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

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

                        flightSelectView.getSocketWriter().writeObject(flightSelectView.getFlightSelection().getSelectedItem());
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
                        flightDetailView = new FlightDetailView(flightDetails, flightSelectView, flightSelectView.getClientSocket(), airline);
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

    public void bookFlightSemantics() throws IOException {

        this.flightSelectView.getSocketWriter().writeObject("final");

        String a = (String) this.flightSelectView.getFlightSelection().getSelectedItem();

        this.flightSelectView.getSocketWriter().writeObject(a);

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
