import javax.swing.*;
import java.awt.*;
import java.net.Socket;


/**
 * Project 5, CS 180
 * Airline Management System
 *
 * @author Prakhar Nahar, Vivek Natarajan
 * @version 12/3/19
 */

public class FirstView {
    private JPanel panel;
    private JButton exitButton;
    private JButton bookAFlightButton;
    private ResponseListener dataExchanger;
    private Socket clientSocket;


    public FirstView(ResponseListener dataExchanger) {
        this.dataExchanger = dataExchanger;
        this.clientSocket = dataExchanger.getSocket();
        createGUI();
    }

    public void createGUI() {
        //initialising the panels
        this.panel = new JPanel(new BorderLayout());
        JPanel lowerPanel = new JPanel(new BorderLayout());

        // assigning the components
        JLabel jLabel = new JLabel("<html> <b> <font size=\"8\">Welcome to " +
                "Purdue Airline Management System </font> </b></html>");
        JLabel imageLabel = new JLabel(new ImageIcon("src/Unknown.png"));
        this.bookAFlightButton = new JButton("Book a flight");
        this.exitButton = new JButton("Exit");

        //adding the components
        panel.add(jLabel, BorderLayout.NORTH);
        panel.add(imageLabel, BorderLayout.CENTER);
        lowerPanel.add(bookAFlightButton, BorderLayout.EAST);
        lowerPanel.add(exitButton, BorderLayout.WEST);
        panel.add(lowerPanel, BorderLayout.SOUTH);
        panel.setSize(600, 600);

    }

    public JPanel getPanel() {
        return panel;
    }

    public JButton getBookAFlightButton() {
        return bookAFlightButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public ResponseListener getDataExchanger() {
        return dataExchanger;
    }
}
