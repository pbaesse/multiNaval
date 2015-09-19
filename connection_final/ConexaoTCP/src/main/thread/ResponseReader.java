package main.thread;

import main.thread.BattleReader;
import java.io.*;
import javax.swing.DefaultListModel;
import main.Lobby;

/*
 * execute RESPONSE
 */
public class ResponseReader implements Runnable {

    private Lobby lobby;
    private PrintStream toServer;
    private BufferedReader fromServer;
    private String yourIP;

    public ResponseReader(Lobby lobby) {
        this.lobby = lobby;
        this.toServer = lobby.getToServer();
        this.fromServer = lobby.getFromServer();
        this.yourIP = lobby.getServer().getLocalAddress().getHostAddress();
    }

    @Override
    public void run() {
        while (true) {

            try {
                //Recebendo do servidor;
                String received = fromServer.readLine();
                System.out.println(received);

                //Se o recebido for a lista de jogadores conectados, ele atualiza o lobby
                if (received.contains("PlayerList")) {
                    System.out.println("Entrou no if");
                    String[] who = received.split(" ");

                    DefaultListModel lobbylist = new DefaultListModel();
                    lobby.getLobbyList().setModel(lobbylist);

                    for (int i = 1; i < who.length; i++) {
                        lobbylist.addElement(who[i]);
                    }
                // Se a mensagem recebida conter uma mensagem de outro jogador;    

                } else if (received.equals("NEED UPDATE")) {
                    toServer.println("PLAYERLIST");

                } else if (received.contains("RECEIVED")) {
                    String[] identify = received.split("/");
                    lobby.showMessage("[Jogador " + identify[1] + "]: " + identify[2]);

                } else if (received.contains("NOTIFY")) {
                    String[] identify = received.split("/");
                    lobby.showMessage(identify[1]);

                } else if (received.equals("WAIT Connection")) {
                    Matchmaking mm = new Matchmaking(lobby);
                    new Thread(mm).start();
                    toServer.println("ADD TO MATCH/" + yourIP);
                
                } else if (received.contains("CONNECT")) {
                    String[] identify = received.split("/");
                    java.net.Socket oponent = new java.net.Socket(identify[1], 2525);
                    lobby.setEnemy(oponent);
                    Thread thread = new Thread(new BattleReader(oponent, lobby));
                    thread.start();
                    lobby.showMessage("Você conectou-se ao jogador " + oponent.getInetAddress().getHostAddress());
                    
                } else if (received.equals("ATTACK")) {
                                        
                } else if (received.equals("UNKNOWN HOST")) {
                    lobby.showMessage("Este IP não é válido para mandar mensagem!");
                }
            } catch (IOException ex) {
                System.out.println("Error: " + ex);
            } catch (NullPointerException ex) {
                break;
            }
        }
    }
}
