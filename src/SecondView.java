import javax.swing.*;
import java.awt.*;


public class SecondView {
    private JPanel panel1;
    private JLabel jLabel;
    private JButton exitButton;
    private JButton yesIWantToButton;
    private JPanel lowerPanel;

    public SecondView() {
        createGUI();
    }

    public void createGUI() {
        this.panel1 = new JPanel(new BorderLayout());
        this.lowerPanel = new JPanel(new BorderLayout());
        this.yesIWantToButton = new JButton("Yes, I want to book a flight today");
        this.exitButton = new JButton("Exit.");
        this.jLabel = new JLabel("<html> <b> <font size = \"8\"> Do you want to book a flight today? </font> </b> </html>");
        panel1.add(jLabel, BorderLayout.NORTH);
        lowerPanel.add(yesIWantToButton, BorderLayout.EAST);
        lowerPanel.add(exitButton, BorderLayout.WEST);
        panel1.add(lowerPanel, BorderLayout.SOUTH);
        this.panel1.setSize(600,600);
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JButton getYesIWantToButton() {
        return yesIWantToButton;
    }
}
