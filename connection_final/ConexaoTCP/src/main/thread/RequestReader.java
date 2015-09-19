package main.thread;

import java.io.*;
import java.net.*;
import main.Server;

/*
 * process REQUEST
 */
public class RequestReader implements Runnable {

    private Server server;
    private Socket client;
    private Socket enemy;
    private String yourIP;
    private String enemyIP;

    private BufferedReader fromClient;
    private BufferedReader fromEnemy;
    private PrintStream toClient;
    private PrintStream toEnemy;

    public RequestReader(Socket client, Server server) throws IOException {
        this.server = server;
        this.client = client;
        this.yourIP = client.getInetAddress().getHostAddress();

        fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        toClient = new PrintStream(client.getOutputStream());
        new Thread(this).start();
    }

    public PrintStream getToClient() {
        return this.toClient;
    }

    private void checkPlayerList() {
        String answer = "";
        // Analisa a lista de jogadores online e a formata de forma que possa ser transmitida
        for (Socket c : server.getPlayerList()) {
            answer = answer + c.getInetAddress().getHostAddress() + " ";
        }

        // Envia para o cliente;
        toClient.println("PlayerList " + answer);
    }

    private void disconnect() throws IOException {
        fromClient.close();
        toClient.close();

        server.getPlayerList().remove(client);
        client.close();
        server.addText("Conexão com cliente " + this.yourIP + " encerrada. \n");
        
        if(server.getMatchmaking().contains(client)) {
            server.getMatchmaking().remove(client);
        }
        
        server.broadcast("NEED UPDATE");
    }

    private void sendMessage(String request) throws IOException {
        // Recorta a mensagem, de forma a separar IP, Mensagem e quem quer envia-la
        String[] dados = request.split("/");
        boolean sent = false;

        // Procura pelo cliente de IP indicado para enviar a mensagem
        for (Socket c : server.getPlayerList()) {
            if (dados[2].equals(c.getInetAddress().getHostAddress())) {
                new PrintStream(c.getOutputStream()).println("RECEIVED/" + yourIP
                        + "/" + dados[1]);
                sent = true;
            }
        }

        // Caso o IP não esteja presente nesta lista, avisa ao Cliente
        if (!sent) {
            toClient.println("UNKNOWN HOST");
        }
    }

    private void matchmaking() throws IOException {
        if (server.getMatchmaking().isEmpty()) {
            toClient.println("WAIT Connection");
        } else {
            for (Socket c : server.getMatchmaking()) {
                toClient.println("CONNECT/" + c.getInetAddress().getHostAddress());
                
                server.addText("O jogador " + yourIP + " conectou-se ao jogador "
                        + c.getInetAddress().getHostAddress() + "\n");

                //Thread thread = new Thread(new BattleReader(oponent, toClient));
                //thread.setName("Battle" + thread.getId());
                break;
            }
        }
    }

    private void attack() throws IOException {
        toClient.println("ATTACK");
    }

    public void processRequest(String request) throws IOException {
        // Criar routes para os requests; Rotas para os pedidos;
        if (request.equals("PLAYERLIST")) {
            checkPlayerList();
        } else if (request.equals("QUIT")) {
            disconnect();
        } else if (request.contains("CHAT")) {
            sendMessage(request);
        } else if (request.equals("FIND")) {
            matchmaking();
        } else if (request.equals("ATTACK")) {
            attack();
        } else if (request.contains("SHOW")) {
            String[] show = request.split("/");
            server.addText(show[1]);
        } else if (request.contains("ADD TO MATCH")) {
            String[] add = request.split("/");

            for (Socket client : server.getPlayerList()) {
                if (add[1].equals(client.getInetAddress().getHostAddress())) {
                    server.getMatchmaking().add(client);
                    server.addText("O jogador " + yourIP + " entrou no Matchmaking\n");
                }
            }
        } else {
            System.out.println("~(Ecoando): " + request);
        }
    }

    @Override
    public void run() {
        while (!client.isClosed()) {
            try {
                // Lê pedidos do cliente;
                String request = fromClient.readLine();
                processRequest(request);
            } catch (IOException e) {
                System.out.println("Erro: " + e);
            }
        }
    }

    @Override
    public String toString() {
        return "client: " + client;
    }
}
