import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;


/**
 * Project 5, CS 180
 * Airline Management System
 *
 * @author Prakhar Nahar, Vivek Natarajan
 * @version 12/3/19
 */
public class RequestHandler implements Runnable {
    private Socket clientSocket;
    private final Alaska alaska;
    private final Delta delta;
    private final Southwest southwest;
    private Airline airline;


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
            Gate gate;
            String airline = "";
            String finalFlight;
            boolean gotData = false;
            boolean gotFinalFlight = false;

            ArrayList<String> flights = new ArrayList<String>();

            if (alaska.currentCapacity() < alaska.MAX_CAP) {
                flights.add("Alaska");
            }
            if (delta.currentCapacity() < delta.MAX_CAP) {
                flights.add("Delta");
            }
            if (southwest.currentCapacity() < southwest.MAX_CAP) {
                flights.add("Southwest");
            }

            Object[] f = flights.toArray();
            socketWriter.writeObject(f);


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
                    case "change":
                        flights = new ArrayList<String>();
                        if (alaska.currentCapacity() < alaska.MAX_CAP) {
                            flights.add("Alaska");
                        }
                        if (delta.currentCapacity() < delta.MAX_CAP) {
                            flights.add("Delta");
                        }
                        if (southwest.currentCapacity() < southwest.MAX_CAP) {
                            flights.add("Southwest");
                        }

                        f = flights.toArray();
                        socketWriter.writeObject(f);
                        break;
                }

            }


            gotData = false;
            while (!gotData) {
                try {
                    airline = (String) socketReader.readObject();
                    gotData = true;
                } catch (EOFException eof) {
                    eof.printStackTrace();
                    ;
                }
            }

            switch (airline) {
                case "Alaska":
                    gate = alaska.getGate();
                    break;
                case "Delta":
                    gate = delta.getGate();
                    break;
                case "Southwest":
                    gate = southwest.getGate();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + airline);
            }


            gotData = false;
            String firstName = null;
            String lastName = null;
            int age = 0;

            String s = "";


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


            Passenger passenger = new Passenger(firstName, lastName, age);

            BoardingPass boardingPass = new BoardingPass(airline, firstName + " " + lastName, age, gate);
            passenger.setBoardingPass(boardingPass);


            switch (airline) {
                case "Alaska":

                    alaska.addPassenger(passenger);
                    socketWriter.writeObject(alaska);

                    break;
                case "Delta":

                    delta.addPassenger(passenger);
                    socketWriter.writeObject(delta);
                    break;
                case "Southwest":

                    southwest.addPassenger(passenger);
                    socketWriter.writeObject(southwest);
                    break;
            }
            socketWriter.writeObject(boardingPass);


            String str = "refresh";

            while (!str.equals("exit")) {

                gotData = false;
                while (!gotData) {
                    try {

                        str = (String) socketReader.readObject();
                        gotData = true;
                    } catch (EOFException e) {
                        e.printStackTrace();
                    }
                }
                if (str.equals("refresh")) {
                    switch (airline) {
                        case "Alaska":

                            alaska.readFromFile(alaska.getFile());
                            socketWriter.writeObject(alaska.returnPassengerList());
                            for (String p : alaska.returnPassengerList()) {

                            }


                            break;
                        case "Delta":

                            delta.readFromFile(delta.getFile());
                            socketWriter.writeObject(delta.returnPassengerList());

                            break;
                        case "Southwest":

                            southwest.readFromFile(southwest.getFile());
                            socketWriter.writeObject(southwest.returnPassengerList());

                            break;
                    }
                }
            }

            socketReader.close();
            socketWriter.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
