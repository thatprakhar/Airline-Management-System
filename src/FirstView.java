import javax.swing.*;
import java.awt.*;

public class FirstView {
    private JPanel panel;
    private JLabel jLabel;
    private JLabel imageLabel;
    private JPanel lowerPanel;
    private JButton exitButton;
    private JButton bookAFlightButton;


    public FirstView() {
        createGUI();
    }

    public void createGUI() {
        this.panel = new JPanel(new BorderLayout());
        this.lowerPanel = new JPanel(new BorderLayout());
        this.jLabel = new JLabel("<html> <b> <font size=\"8\">Welcome to Purdue Airline Management System </font> </b></html>");
        this.imageLabel = new JLabel(new ImageIcon("Unknown.png"));
        this.bookAFlightButton = new JButton("Book a flight");
        this.exitButton = new JButton("Exit");
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
}
