package main.thread;

import java.net.*;
import java.io.*;
import main.Lobby;

public class BattleReader implements Runnable {

    private final Socket connection;
    private final BufferedReader fromEnemy;
    private final Lobby lobby;

    public BattleReader(Socket connection, Lobby lobby) throws IOException {
        System.out.println("BattleReader criado. Você: " + connection.getLocalAddress().getHostAddress() + "; Inimigo: " + connection.getInetAddress().getHostAddress());
        this.connection = connection;
        this.lobby = lobby;
        fromEnemy = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    }

    @Override
    public void run() {
        try {
            while (lobby.hasEnemy()) {

                String state = fromEnemy.readLine();

                if (state.equals("ATTACKED")) {
                    lobby.showMessage("Você foi atacado!");
                } else if (state.equals("QUITTING")) {
                    lobby.setEnemy(null);
                }
            }
            
            fromEnemy.close();
            connection.close();
            
        } catch (IOException e) {
            System.out.println("Error" + e);
        }
    }

}
