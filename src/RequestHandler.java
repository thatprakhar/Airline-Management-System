import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RequestHandler implements Runnable {
    private Socket clientSocket;
    private final Alaska alaska;
    private final Delta delta;
    private final Southwest southwest;


    public RequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        alaska = new Alaska();
        delta = new Delta();
        southwest = new Southwest();
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream socketWriter = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream socketReader = new ObjectInputStream(clientSocket.getInputStream());

            String airline = "";
            String finalFlight;
            boolean gotData = false;
            boolean gotFinalFlight = false;

            while (!gotFinalFlight) {
                gotData = false;
                while (!gotData) {
                    try {
                        airline = (String) socketReader.readObject();
                        gotData = true;
                    } catch (EOFException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(airline);

                switch (airline) {
                    case "Alaska":
                        socketWriter.writeObject(alaska);
                        socketWriter.flush();
                        break;
                    case "Delta":
                        socketWriter.writeObject(delta);
                        socketWriter.flush();
                        break;
                    case "Southwest":
                        socketWriter.writeObject(southwest);
                        socketWriter.flush();
                        break;
                    case "final":
                        gotFinalFlight = true;
                        break;
                }

            }


            gotData = false;
            while (!gotData) {
                try {
                    airline = (String) socketReader.readObject();
                    gotData = true;
                } catch (EOFException eof) {
                    eof.printStackTrace();;
                }
            }
            gotData = false;
            String firstName = null;
            String lastName = null;
            int age = 0;


            while (!gotData) {
                try {
                    firstName = (String) socketReader.readObject();
                    gotData = true;
                } catch (EOFException e) {
                    e.printStackTrace();
                }
            }

            gotData = false;

            while (!gotData) {
                try {
                    lastName = (String) socketReader.readObject();
                    gotData = true;
                } catch (EOFException e) {
                    e.printStackTrace();
                }
            }

            gotData = false;

            while (!gotData) {
                try {
                    age = (int) socketReader.readObject();
                    gotData = true;
                } catch (EOFException e) {
                    e.printStackTrace();
                }
            }

            Passenger passenger = new Passenger(firstName, lastName, age);

            switch (airline) {
                case "Alaska":
                    alaska.addPassenger(passenger);
                    break;
                case "Delta":
                    delta.addPassenger(passenger);
                    break;
                case "Southwest":
                    southwest.addPassenger(passenger);
                    break;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
