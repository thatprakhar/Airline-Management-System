import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;


/**
 * Project 5, CS 180
 * Airline Management System
 *
 * @author Prakhar Nahar, Vivek Natarajan
 * @version 12/3/19
 */
public class BoardingPassView {
    private JPanel mainPanel;
    private FlightBookView flightBookView;
    private Airline airline;
    private BoardingPass boardingPass;
    private JButton exit;
    private JButton refresh;
    private JScrollPane passengerView;

    public BoardingPassView(FlightBookView flightBookView) {
        this.flightBookView = flightBookView;
        boolean got = false;

        while (!got) {
            try {
                airline = (Airline) flightBookView.getFlightSelectView().getSocketReader().readObject();

                got = true;
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }

        got = false;

        while (!got) {
            try {
                boardingPass = (BoardingPass) flightBookView.getFlightSelectView().getSocketReader().readObject();
                got = true;
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }


        createGUI();

    }

    public void createGUI() {
        mainPanel = new JPanel(new GridLayout(4, 1));

        JLabel jLabel = new JLabel();
        String str = "<html> Flight detail now showing for " + flightBookView.getAirline() + " Airlines <br>" + "" +
                "Enjoy your flight<br>" +
                "Flight is now boarding at Gate " + airline.getGate() + " </html>";
        jLabel.setText(str);
        Object[] obj = airline.returnPassengerList().toArray();
        String[] s = new String[obj.length];
        String p = "";
        for (int i = 0; i < s.length; i++) {
            s[i] = String.valueOf(obj[i]);
        }


        passengerView = new JScrollPane((new JList<>(s)));
        passengerView.setBackground(Color.GRAY);
        Border b = BorderFactory.createLineBorder(Color.BLACK);
        passengerView.setBorder(b);
        passengerView.setSize(230, 150);


        JTextArea boardingpass = new JTextArea(boardingPass.print());
        boardingpass.setEditable(false);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        exit = new JButton("Exit");
        refresh = new JButton("Refresh Flight Status");
        buttonPanel.add(exit);
        buttonPanel.add(refresh);
        JPanel top = new JPanel(new BorderLayout());
        top.add(jLabel);
        mainPanel.add(top);
        mainPanel.add(passengerView);
        mainPanel.add(boardingpass);
        mainPanel.add(buttonPanel);
        mainPanel.setSize(600, 600);
        mainPanel.setVisible(true);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getExit() {
        return exit;
    }

    public JButton getRefresh() {
        return refresh;
    }

    public FlightBookView getFlightBookView() {
        return flightBookView;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public void setPassengerView(JScrollPane passengerView) {
        this.passengerView = passengerView;
    }

    public JScrollPane getPassengerView() {
        return this.passengerView;
    }
}
