package multinaval;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class WaitConnection implements Runnable {

    private ServerSocket server;
    private Server serverClass;

    public WaitConnection(ServerSocket server, Server serverClass) {
        this.server = server;
        this.serverClass = serverClass;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket client = this.server.accept();
                String ipClient = client.getInetAddress().getHostAddress();

                serverClass.addText("Conexão estabelecida com Cliente [" + ipClient + "]\n");
                serverClass.getPlayerList().add(client);

                new Transmitter(client, serverClass);

            } catch (SocketException ex) {
                break;
            } catch (IOException ex) {
                System.out.println("Error: " + ex);
            }
        }
    }
}
