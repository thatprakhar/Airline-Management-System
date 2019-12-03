import java.io.IOException;


/**
 * Project 5, CS 180
 * Airline Management System
 *
 * @author Prakhar Nahar, Vivek Natarajan
 * @version 12/3/19
 */
public final class ServerRunner {


    public ServerRunner() {
    }

    /**
     * Creates a {@code CountdownServer} instance, then begins to serve clients.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        ReservationServer server;

        server = new ReservationServer();

        server.serveClients();
    } //main
}
