package cliente_servidor_thread_REMAKE;

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
	
	public Transmitter(Socket client, Server server) throws Exception {
		this.server = server;
		this.client = client;
		this.yourIP = client.getInetAddress().getHostAddress();
		
		in = client.getInputStream();
		reader = new BufferedReader(new InputStreamReader(in));
		out = client.getOutputStream();
		writer = new PrintStream(out);
		new Thread(this).start();
	}
	
	public void setStatus(boolean status) {
		if (status)	keepRunning = true;
		else keepRunning = false;
	}
	
	private void sendMessage(String request) throws Exception {
		String[] cutted = request.split("/");
		String ip = cutted[1];
		String message = cutted[2];
		boolean sent = false;
		
		for(Socket c : server.getLobbyList()) {
			if(c.getInetAddress().getHostAddress().equals(ip)) {
				new PrintStream(c.getOutputStream()).println(message);
				writer.println("Mensagem enviada!");
				sent = true;
			}
		}
		
		if(!sent) { writer.println("Mensagem não enviada. IP inexistente"); }
	}
	
	private void writerEnemy(String enemyIP, String message) throws Exception {
		for(Socket client : server.getLobbyList()) {
			if (client.toString().contains(enemyIP))
				new PrintStream(client.getOutputStream()).println(message);
		}
	}

	private void checkLobbyList() {
		String answer = "";
		for(Socket c : server.getLobbyList()) {
			answer = answer + "[" + c.getInetAddress().getHostAddress() + ":" + c.getPort() + "], ";
		}
		writer.println(answer);
	}
	
	private void duel(String request) throws Exception {
		String[] ip = request.split(" ");
		
		if(server.getLobbyList().toString().contains(ip[1])) {
			sendMessage("SEND/"+ip[1]+"/" + yourIP + "  está te convidando para jogar [NEW INVITE]" ); 
		}
		oponentIP = ip[1];
	}

	private void disconnect() throws Exception {
		
		System.out.println();
		System.out.print("Finalizado ");
		
		reader.close();
		writer.close();
		System.out.print("fluxo de dados e ");

		server.getLobbyList().remove(client);
		client.close();
		System.out.println("conexão com cliente " + this.yourIP);
	}
	
	private void battleBegins(String request) throws Exception {
		String[] ip = request.split(" ");
		oponentIP = ip[1];
		
		if(server.startBattle(yourIP, oponentIP)) {
			writer.println("Você entrou em batalha [Você vs. "+oponentIP+"]");
			writerEnemy(oponentIP, "Você entrou em batalha [Você vs. "+yourIP+"]");
		} else {
			writer.println("Falha ao entrar em Batalha. IP inválido");
			writerEnemy(oponentIP, "Falha ao entrar em Batalha. IP inválido");
		}
	}
		
	public void processRequest(String request) throws Exception {
		if(request.equals("LOBBYLIST")) { checkLobbyList(); }
		else if(request.contains("SEND")) { sendMessage(request); }
		else if(request.equals("QUIT")) { disconnect(); keepRunning = false; }
		else if(request.contains("AGREED")) { battleBegins(request); }
		else if(request.contains("INVITE")) { duel(request); }
		else { writer.println("~(Ecoando): " + request); } 
	}

	@Override
	public void run() {
		while(keepRunning) {
			try {
				String request = reader.readLine();
				System.out.println("Mensagem recebida do cliente [" + yourIP + "]: " + request);
			
				processRequest(request);
				
			} catch (Exception e) { 
				System.out.println("Erro: " + e); 
			}
		}
	}

	@Override
	public String toString() {
		return "client: "+client;
	}
}
