import javax.swing.*;
import java.awt.*;

public class MasterView {
    private JPanel panel1;
    private FirstView firstView;
    private SecondView secondView;

    public MasterView() {
        FirstView firstView = new FirstView();
        SecondView secondView = new SecondView();
        panel1 = new JPanel(new CardLayout());
        panel1.add(firstView.getPanel(), "Book a flight");
        panel1.add(secondView.getPanel1(), "Cancel");
    }

    public JPanel getPanel1() {
        return panel1;
    }
}
