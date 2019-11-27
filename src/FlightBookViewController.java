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
        Passenger p = new Passenger(this.flightBookView.getFirstNameField().getText(), this.flightBookView.getLastNameField().getText(), Integer.parseInt(this.flightBookView.getAgeField().getText()));
        this.flightBookView.getAirline().addPassenger(p);
        this.mainFrame.getContentPane().removeAll();
        FirstView firstView = new FirstView(this.flightBookView.getSocket());
        new FirstViewController(mainFrame, firstView);

        this.mainFrame.setContentPane(firstView.getPanel());
    }
}
