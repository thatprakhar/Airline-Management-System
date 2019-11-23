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
        panel.setSize(400, 400);
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
