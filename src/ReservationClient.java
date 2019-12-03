import javax.swing.*;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;


/**
 * Project 5, CS 180
 * Airline Management System
 *
 * @author Prakhar Nahar, Vivek Natarajan
 * @version 12/3/19
 */

public class ReservationClient {

    public static void main(String[] args) throws IOException {
        final String TITLE = "Purdue University Flight Reservation System";

        //Parent JFrame
        JFrame parent = new JFrame(TITLE);
        parent.setSize(500, 500);
        parent.getDefaultCloseOperation();

        // host dialog
        String host = JOptionPane.showInputDialog(parent, "What is your " +
                "hostname you'd like to connect to?", "Hostname?", JOptionPane.PLAIN_MESSAGE);
        int port;

        Socket clientSocket = null;
        //to connect to server
        boolean connected = false;
        while (!connected) {
            try {
                port = Integer.parseInt(JOptionPane.showInputDialog(parent, "What is " +
                        "the port you'd like to connect to?", "Port?", JOptionPane.PLAIN_MESSAGE));

                clientSocket = new Socket(host, port);
                connected = true;
            } catch (ConnectException ce) {
                JOptionPane.showMessageDialog(parent, "Please enter a valid port",
                        "Invalid Port", JOptionPane.PLAIN_MESSAGE);
            }
        }


        ResponseListener dataExchanger = new ResponseListener(clientSocket);
        FirstView firstView = new FirstView(dataExchanger);
        new FirstViewController(parent, firstView);
        parent.setContentPane(firstView.getPanel());
        parent.setSize(600, 600);
        parent.setVisible(true);


    }
}
