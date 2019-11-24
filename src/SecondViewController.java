import javax.swing.*;

public class SecondViewController {
    private JFrame jFrame;
    private SecondView secondView;

    public SecondViewController(JFrame jFrame, SecondView secondView) {
        this.jFrame = jFrame;
        this.secondView = secondView;
        this.secondView.getYesIWantToButton().addActionListener(e -> bookFLightSemantics());
        this.secondView.getExitButton().addActionListener(e -> exitButtonSemantics());

    }

    public void bookFLightSemantics() {
        this.jFrame.getContentPane().removeAll();
        FlightSelectView flightSelectView = new FlightSelectView();
        new FlightSelectViewController(this.jFrame, flightSelectView);
        this.jFrame.setContentPane(flightSelectView.getMainPanel());
    }

    public void exitButtonSemantics() {
        this.jFrame.setVisible(false);
    }
}
