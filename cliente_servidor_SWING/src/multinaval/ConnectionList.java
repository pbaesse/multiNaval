package multinaval;

import java.io.*;
import javax.swing.DefaultListModel;

public class ConnectionList implements Runnable {

    private Lobby lobby;
    private PrintStream writer;
    private BufferedReader reader;

    public ConnectionList(Lobby lobby) {
        this.lobby = lobby;
        this.writer = lobby.getWriter();
        this.reader = lobby.getReader();
    }

    @Override
    public void run() {
        while (true) {
            writer.println("SEND PlayerList");
            
            try {
                String received = reader.readLine();

                if (received.contains("PlayerList")) {
                    String[] who = received.split(" ");

                    DefaultListModel lobbylist = new DefaultListModel();
                    lobby.getLobbyList().setModel(lobbylist);

                    for (int i = 1; i < who.length; i++) {
                        lobbylist.addElement(who[i]);
                    }
                } else if (received.contains("RECEIVED")) {
                    String[] identify = received.split("/");
                    lobby.createPopup("[Jogador " + identify[1] + "]: " + identify[2]);
                } else if (received.equals("UNKNOWN HOST")) {
                    lobby.createPopup("Este IP não é válido para mandar mensagem!");
                }
            } catch (IOException ex) {
                System.out.println("Error: " + ex);
            } catch (NullPointerException ex) {
                break;
            }
        }
    }
}
