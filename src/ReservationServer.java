import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ReservationServer {

    private ServerSocket serverSocket;
    private Alaska alaska;
    private Delta delta;
    private Southwest southwest;

    public ReservationServer() throws IOException {
        this.serverSocket = new ServerSocket(0);
        alaska = new Alaska();
        delta = new Delta();
        southwest = new Southwest();
    }

    public void serveClients() {
        Socket clientSocket;
        RequestHandler handler;
        Thread handlerThread;
        int connectionCount = 0;

        System.out.printf("<Now serving clients on port %d...>%n", this.serverSocket.getLocalPort());
        while (true) {
            try {
                clientSocket = this.serverSocket.accept();

            } catch (IOException e) {
                e.printStackTrace();

                break;
            } //end try catch
            handler = new RequestHandler(clientSocket);

            handlerThread = new Thread(handler);

            handlerThread.start();

            System.out.printf("<Client %d connected...>%n", connectionCount);

            connectionCount++;
        } //end while
    } //serveClients



}
