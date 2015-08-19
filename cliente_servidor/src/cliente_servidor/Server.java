package cliente_servidor_thread_REMAKE;

import java.util.*;
import java.net.*;

public class Server {
	private ServerSocket server;
	private ArrayList<Socket> lobbyList;
	private int port;
	
	public Server(int port) throws Exception {
		this.port = port;
		this.server = new ServerSocket(port);
		lobbyList = new ArrayList<Socket>();
	}

	public ArrayList<Socket> getLobbyList() { return lobbyList; } 

	public boolean startBattle(String yourIP, String oponentIP) throws Exception {
		Socket client1 = null;
		Socket client2 = null;

		for(Socket c : lobbyList) {
			if(c.toString().contains(yourIP)) {
				client1 = c; 
			} else if(c.toString().contains(oponentIP)) {
				client2 = c;
			}
		}
		
		if(client1 == null|| client2 == null) {
			System.out.println("Batalha impossível! Um dos clientes é nulo"); 
			return false;
		} else {
			Battle battle = new Battle(client1, client2);
			new Thread(battle).start();
			return true;
		}
	}

	private void run() throws Exception {
		System.out.println("Servidor iniciado na porta " + this.port);

		while(true) {
			System.out.println("Esperando conexão [...]");
			System.out.println();
			
			Socket client = this.server.accept();
			String ipClient = client.getInetAddress().getHostAddress();
			
			System.out.println("Conexão estabelecida com Cliente [" + ipClient + "]");
			
			System.out.println(client);
			this.lobbyList.add(client);
			System.out.println("Cliente [" + ipClient + "] adicionado ao LobbyList!");
			System.out.println();
			
			new Transmitter(client, this);			
		}	
	}
	
	// MAIN
	public static void main(String[] args) throws Exception {
		new Server(8080).run();
	}
 	
}