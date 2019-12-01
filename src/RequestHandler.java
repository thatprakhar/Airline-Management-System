import java.io.*;
import java.net.Socket;

public class RequestHandler implements Runnable {
    private Socket clientSocket ;
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
            ObjectInputStream socketReader = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream socketWriter = new ObjectOutputStream(clientSocket.getOutputStream());
            String airline = "";
            boolean gotData = false;
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
            }
            socketWriter.flush();
            socketReader.close();
            socketWriter.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
