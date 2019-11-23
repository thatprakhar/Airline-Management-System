import javax.swing.*;


public class FirstViewController {
    private JFrame jFrame;
    private  FirstView firstView;

    public FirstViewController(JFrame jFrame, FirstView firstView) {
        this.jFrame = jFrame;
        this.firstView = firstView;
        this.firstView.getBookAFlightButton().addActionListener(e -> bookFlightSemantics());
    }

    public void bookFlightSemantics() {
        JOptionPane.showMessageDialog(null,"Book a flight pressed");
        this.jFrame.getContentPane().removeAll();
        jFrame.setContentPane(new SecondView().getPanel1());
    }
}
