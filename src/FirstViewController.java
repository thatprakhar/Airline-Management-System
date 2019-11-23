import javax.swing.*;
import java.awt.*;


public class FirstViewController {
    private JFrame jFrame;
    private  FirstView firstView;

    public FirstViewController(JFrame jFrame, FirstView firstView) {
        this.jFrame = jFrame;
        this.firstView = firstView;
        this.firstView.getBookAFlightButton().addActionListener(e -> bookFlightSemantics());
        jFrame.pack();
    }

    public void bookFlightSemantics() {
        this.jFrame.getContentPane().removeAll();
        SecondView secondView = new SecondView();
        new SecondViewController(this.jFrame, secondView);
        jFrame.setContentPane(secondView.getPanel1());
    }
}
