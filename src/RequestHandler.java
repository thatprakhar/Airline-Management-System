import java.net.Socket;

public class RequestHandler implements Runnable {
    Socket clientSocket ;
    public RequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {

    }
}
