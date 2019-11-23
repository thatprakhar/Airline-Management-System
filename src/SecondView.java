import javax.swing.*;
import java.awt.*;

public class SecondView {
    private JPanel panel1;
    private JButton exitButton;
    private JButton yesIWantToButton;

    public SecondView() {
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
