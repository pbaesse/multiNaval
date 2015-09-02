package multinaval;

import java.io.*;
import java.net.*;

/*
 * transmit COMMANDS from CLIENT to SERVER
 */
public class Transmitter implements Runnable {

    private Server server;
    private Socket client;
    private String yourIP;
    private String oponentIP;
    private boolean keepRunning = true;

    private BufferedReader reader;
    private InputStream in;
    private PrintStream writer;
    private OutputStream out;

    public Transmitter(Socket client, Server server) throws IOException {
        this.server = server;
        this.client = client;
        this.yourIP = client.getInetAddress().getHostAddress();

        in = client.getInputStream();
        reader = new BufferedReader(new InputStreamReader(in));
        out = client.getOutputStream();
        writer = new PrintStream(out);
        new Thread(this).start();
    }
    
    private void checkPlayerList() {
        String answer = "";
        // Analisa a lista de jogadores online e a formata de forma que possa ser transmitida
        for (Socket c : server.getPlayerList()) {
            answer = answer + c.getInetAddress().getHostAddress() + " ";
        }

        // Envia para o cliente;
        writer.println("PlayerList " + answer);
    }
    
    private void disconnect() throws IOException {

        reader.close();
        writer.close();

        server.getPlayerList().remove(client);
        client.close();
        keepRunning = false;
        server.addText("Conexão com cliente " + this.yourIP + " encerrada. \n");
    }
    
    private void sendMessage(String request) throws IOException {
        // Recorta a mensagem, de forma a separar IP, Mensagem e quem quer envia-la
        String[] dados = request.split("/");
        boolean sent = false;
        
        // Procura pelo cliente de IP indicado para enviar a mensagem
        for(Socket c : server.getPlayerList()) {
            if(dados[2].equals(c.getInetAddress().getHostAddress())) {
                new PrintStream(c.getOutputStream()).println("RECEIVED/" + yourIP +
                        "/" + dados[1]);
                sent = true;
            }
        }
        
        // Caso o IP não esteja presente nesta lista, avisa ao Cliente
        if(!sent) {
            writer.println("UNKNOWN HOST");
        }
    }
    
    public void processRequest(String request) throws IOException {
    // Criar routes para os requests; Rotas para os pedidos;
        if (request.equals("SEND PlayerList")) {
            checkPlayerList();
        } else if (request.equals("QUIT")) {
            disconnect();
            keepRunning = false;
        } else if (request.contains("CHAT")) {
            System.out.println("Enviado de processRequest: " + request);
            sendMessage(request);
        } else {
            writer.println("~(Ecoando): " + request);
        }
    }

    @Override
    public void run() {
        while (keepRunning) {
            try {
                // Lê pedidos do cliente;
                String request = reader.readLine();
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
