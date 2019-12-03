import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Project 5, CS 180
 * Airline Management System
 *
 * @author Prakhar Nahar, Vivek Natarajan
 * @version 12/3/19
 */

public class BoardingPassViewController {
    private BoardingPassView boardingPassView;
    private JFrame mainFrame;

    public BoardingPassViewController(JFrame jFrame, BoardingPassView boardingPassView) {
        this.boardingPassView = boardingPassView;
        this.mainFrame = jFrame;

        this.boardingPassView.getExit().addActionListener(e -> {
            try {
                exit();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        this.boardingPassView.getRefresh().addActionListener(e -> {
            try {
                refresh();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void exit() throws IOException {
        this.mainFrame.setVisible(false);
        this.boardingPassView.getFlightBookView().getFlightSelectView().getSocketWriter().writeObject("exit");
    }

    public void refresh() throws IOException {
        this.boardingPassView.getFlightBookView().getFlightSelectView().getSocketWriter().writeObject("refresh");
        boolean got = false;
        Object object = null;
        while (!got) {
            try {
                object = this.boardingPassView.getFlightBookView().getFlightSelectView().getSocketReader().readObject();
                got = true;
            } catch (EOFException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        ArrayList<Object> objects = (ArrayList<Object>) object;
        String[] s = new String[objects.size()];

        for (int i = 0; i < objects.size(); i++) {
            s[i] = String.valueOf(objects.get(i));
        }

        this.boardingPassView.getPassengerView().getViewport().remove(this.boardingPassView.getPassengerView());
        this.boardingPassView.setPassengerView(new JScrollPane(new JList<>(s)));
        this.boardingPassView.getPassengerView().setBackground(Color.RED);
        Border b = BorderFactory.createLineBorder(Color.BLACK);
        this.boardingPassView.getPassengerView().setBorder(b);
        this.boardingPassView.getPassengerView().setSize(230, 150);

    }
}
