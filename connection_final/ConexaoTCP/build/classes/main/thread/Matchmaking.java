package main.thread;

import main.thread.BattleReader;
import java.io.IOException;
import java.net.*;
import main.Lobby;

public class Matchmaking implements Runnable {
    private final Lobby lobby;

    public Matchmaking(Lobby lobby) {
        this.lobby = lobby;
    }

    @Override
    public void run() {
        try {
            ServerSocket waiting = new ServerSocket(2525);
            
            Socket enemy = waiting.accept();

            lobby.showMessage("Jogador " + enemy.getInetAddress().getHostAddress() + " conectou-se a você");
            lobby.setEnemy(enemy);
            
            Thread thread = new Thread(new BattleReader(enemy, lobby));
            thread.setName("Battle" + thread.getId());
            thread.start();
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }

}
