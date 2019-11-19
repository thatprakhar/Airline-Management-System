import javax.swing.*;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

public class ReservationClient {

    public static void main(String[] args) throws IOException {

        final String TITLE = "Purdue University Flight Reservation System";
        JFrame parent = new JFrame(TITLE);
        parent.setSize(500,500);
        parent.getDefaultCloseOperation();
        parent.setVisible(true);


        String host = JOptionPane.showInputDialog(parent, "What is your hostname you'd like to connect to?", "Hostname?", JOptionPane.PLAIN_MESSAGE);
        int port;
        boolean connected = false;

        while (!connected) {
            try {
                port = Integer.parseInt(JOptionPane.showInputDialog(parent, "What is the port you'd like to connect to?", "Port?", JOptionPane.PLAIN_MESSAGE));
                Socket clientSocket = new Socket(host, port);
                connected = true;
            } catch (ConnectException ce) {
                JOptionPane.showMessageDialog(parent, "Please enter a valid port", "Invalid Port", JOptionPane.PLAIN_MESSAGE);
            }
        }
        ImageIcon purdue_icon = new ImageIcon("purdue_icon.png");
        String[] options = {"Exit", "Book a Flight"};
        JOptionPane.showOptionDialog(parent, "<html> <b> Welcome to the Purdue University Airline <br> Reservation Management System! </b> <br>  </html>", TITLE, 0, JOptionPane.PLAIN_MESSAGE, purdue_icon, options, null);
        String[] options2 = {"Exit", "Yes, I want to book a flight."};
        JOptionPane.showOptionDialog(parent, "<html> <b> Do you want to book flight today? <html>", TITLE, 0, JOptionPane.PLAIN_MESSAGE, null, options2, null);
    }
}
