import javax.swing.*;
import java.io.IOException;

public class FlightBookViewController {

    private FlightBookView flightBookView;
    private JFrame mainFrame;

    public FlightBookViewController(JFrame mainFrame, FlightBookView flightBookView) {
        this.mainFrame = mainFrame;
        this.flightBookView = flightBookView;
        this.flightBookView.getBook().addActionListener(e -> {
            try {
                Book();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void Book() throws IOException {
        this.flightBookView.getFlightSelectView().getSocketWriter().writeObject(this.flightBookView.getFirstNameField().getText());
        this.flightBookView.getFlightSelectView().getSocketWriter().writeObject(this.flightBookView.getLastNameField().getText());
        this.flightBookView.getFlightSelectView().getSocketWriter().writeObject(this.flightBookView.getAgeField().getText());

        this.mainFrame.getContentPane().removeAll();
        this.mainFrame.setVisible(false);

    }
}
