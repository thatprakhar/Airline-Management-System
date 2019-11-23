import javax.swing.*;
import java.awt.*;

public class FirstView {
    private JPanel panel;
    private JLabel jLabel;
    private JLabel imageLabel;
    private JButton exitButton;
    private JButton bookAFlightButton;
    private ImageIcon imageIcon;

    public FirstView() {
        this.panel = new JPanel(new GridLayout());
        this.jLabel = new JLabel("");
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
