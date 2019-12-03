import javax.swing.*;


/**
 * Project 5, CS 180
 * Airline Management System
 *
 * @author Prakhar Nahar, Vivek Natarajan
 * @version 12/3/19
 */
public class FirstViewController {
    private JFrame jFrame;
    private FirstView firstView;

    public FirstViewController(JFrame jFrame, FirstView firstView) {
        this.jFrame = jFrame;
        this.firstView = firstView;
        this.firstView.getBookAFlightButton().addActionListener(e -> bookFlightSemantics());
        this.firstView.getExitButton().addActionListener(e -> exitButtonSemantics());
        jFrame.pack();
    }

    public void bookFlightSemantics() {
        this.jFrame.getContentPane().removeAll();
        SecondView secondView = new SecondView(this.firstView.getDataExchanger());
        new SecondViewController(this.jFrame, secondView);
        jFrame.setContentPane(secondView.getPanel1());
    }

    public void exitButtonSemantics() {
        this.jFrame.setVisible(false);
    }
}
