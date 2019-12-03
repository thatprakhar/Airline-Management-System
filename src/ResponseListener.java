import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


/**
 * Project 5, CS 180
 * Airline Management System
 *
 * @author Prakhar Nahar, Vivek Natarajan
 * @version 12/3/19
 */

public final class ResponseListener {
    private static ObjectInputStream socketReader;
    private static ObjectOutputStream socketWriter;
    private static Socket socket;

    public ResponseListener(Socket socket) throws IOException {
        socketWriter = new ObjectOutputStream(socket.getOutputStream());
        socketReader = new ObjectInputStream(socket.getInputStream());
    }

    public ObjectOutputStream getSocketWriter() {
        return socketWriter;
    }

    public ObjectInputStream getSocketReader() {
        return socketReader;
    }

    public Socket getSocket() {
        return socket;
    }
}
