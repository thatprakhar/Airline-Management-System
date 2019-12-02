import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class FlightSelectView {
    private JPanel mainPanel;
    private JLabel jLabel;
    private JPanel upperPanel;
    private JComboBox<String> flightSelection ;
    private JTextArea airlineDetail;
    private JPanel lowerPanel;
    private JButton exit;
    private JButton choose;
    private JPanel midPanel;
    private Socket clientSocket;
    private ObjectInputStream socketReader;
    private ObjectOutputStream socketWriter;


    public FlightSelectView(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        socketWriter = new ObjectOutputStream(this.clientSocket.getOutputStream());
        socketReader = new ObjectInputStream(this.clientSocket.getInputStream());
        createGUI();
    }

    public void createGUI() {

        //initialising the panels
        this.mainPanel = new JPanel(new BorderLayout());
        this.lowerPanel = new JPanel(new BorderLayout());
        this.upperPanel = new JPanel(new BorderLayout());
        this.midPanel = new JPanel(new BorderLayout());

        //assigning the components
        jLabel = new JLabel("<html> <b> <font size = \"8\"> Choose a flight from the drop down menu </font> </b> </html>");
        this.choose = new JButton("Choose this flight");
        this.exit = new JButton("Exit");
        String[] flights = {"Alaska", "Delta", "Southwest"};
        this.flightSelection = new JComboBox<>(flights);
        flightSelection.setSelectedIndex(0);
        airlineDetail = new JTextArea("Alaska");
        airlineDetail.setEditable(false);
        airlineDetail.setLineWrap(true);

        //adding the components
        this.upperPanel.add(jLabel, BorderLayout.NORTH);
        this.upperPanel.add(flightSelection, BorderLayout.SOUTH);
        this.lowerPanel.add(exit, BorderLayout.WEST);
        this.lowerPanel.add(choose, BorderLayout.EAST);
        this.mainPanel.add(upperPanel, BorderLayout.NORTH);
        this.midPanel.add(airlineDetail, BorderLayout.CENTER);
        this.mainPanel.add(midPanel, BorderLayout.CENTER);
        this.mainPanel.add(lowerPanel, BorderLayout.SOUTH);
        this.mainPanel.setSize(600,600);
        this.flightSelection.requestFocus();
    }

    public JPanel getMainPanel() {
        return this.mainPanel;
    }

    public JComboBox getFlightSelection() {
        return this.flightSelection;
    }

    public JTextArea getAirlineDetail() {
        return this.airlineDetail;
    }

    public JButton getExit() {
        return exit;
    }

    public JButton getChoose() {
        return this.choose;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public ObjectOutputStream getSocketWriter() {
        return socketWriter;
    }

    public ObjectInputStream getSocketReader() {
        return socketReader;
    }
}
