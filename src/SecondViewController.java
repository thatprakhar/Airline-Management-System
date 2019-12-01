import javax.swing.*;
import java.io.IOException;

public class SecondViewController {
    private JFrame jFrame;
    private SecondView secondView;

    public SecondViewController(JFrame jFrame, SecondView secondView) {
        this.jFrame = jFrame;
        this.secondView = secondView;
        this.secondView.getYesIWantToButton().addActionListener(e -> {
            try {
                bookFLightSemantics();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        this.secondView.getExitButton().addActionListener(e -> exitButtonSemantics());

    }

    public void bookFLightSemantics() throws IOException {
        this.jFrame.getContentPane().removeAll();
        FlightSelectView flightSelectView = new FlightSelectView(this.secondView.getClientSocket());
        new FlightSelectViewController(this.jFrame, flightSelectView);
        this.jFrame.setContentPane(flightSelectView.getMainPanel());
        flightSelectView.getFlightSelection().requestFocus();
    }

    public void exitButtonSemantics() {
        this.jFrame.setVisible(false);
    }
}
