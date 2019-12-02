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


    public RequestHandler(Socket clientSocket, ReservationServer reservationServer) {
        this.clientSocket = clientSocket;
        alaska = reservationServer.getAlaska();
        delta = reservationServer.getDelta();
        southwest = reservationServer.getSouthwest();
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
            System.out.println(airline);
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
                    age = Integer.parseInt((String) socketReader.readObject());
                    gotData = true;
                } catch (EOFException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(firstName + " " + lastName + " added");

            Passenger passenger = new Passenger(firstName, lastName, age);
            System.out.println("Created a new passenger");
            BoardingPass boardingPass = new BoardingPass(airline, firstName + " " + lastName, age,'A', 12);
            passenger.setBoardingPass(boardingPass);

            switch (airline) {
                case "Alaska":
                    System.out.println("Writing in alaska");
                    alaska.addPassenger(passenger);
                    break;
                case "Delta":
                    System.out.println("Writing in delta");
                    delta.addPassenger(passenger);
                    break;
                case "Southwest":
                    System.out.println("Writing in southwest");
                    southwest.addPassenger(passenger);
                    break;
            }
            socketReader.close();
            socketWriter.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
