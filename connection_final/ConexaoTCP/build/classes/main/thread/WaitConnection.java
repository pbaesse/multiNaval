package main.thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import main.Server;

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
                // Esperando conexão
                Socket client = this.server.accept();
                String ipClient = client.getInetAddress().getHostAddress();

                // Avisando ao servidor que o cliente conectou-se, e logo após o adiciona na lista
                // de jogadores online;
                serverClass.addText("Conexão estabelecida com Cliente [" + ipClient + "]\n");
                serverClass.getPlayerList().add(client);
                serverClass.broadcast("NEED UPDATE");
                
                new RequestReader(client, serverClass);
            } catch (SocketException ex) {
                break;
            } catch (IOException ex) {
                System.out.println("Error: " + ex);
            }
        }
    }
}
