package cliente_servidor_thread_REMAKE;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
	
	private String oponentIP;
	
	public String getOponentIP() { return oponentIP; }
	public void setOponentIP(String oponentIP) { this.oponentIP = oponentIP; }

	public static void main(String[] args) throws Exception {
		Scanner teclado = new Scanner(System.in);
		
		// usar o IP de ethX
		System.out.print("Seu IP: ");
		String ipCliente = teclado.nextLine(); 
		//String ipCliente = "10.0.0.100";
		
		Socket client = new Socket( ipCliente, 8080);
		System.out.println("Conexão com o servidor estabelecida!");
		
		System.out.println();
		System.out.println("QUIT: Encerra a conexão;");
		System.out.println("SEND/<IP>/<MENSAGEM>: Envia a mensagem para o IP inserido;");
		System.out.println("LOBBYLIST: Mostra quem está conectado;");
		System.out.println("INVITE <IP>: Convida um jogador para uma partida;");
		System.out.println("ACCEPT: Aceita o convite para jogar;");
		
		OutputStream out = client.getOutputStream();
		InputStream in = client.getInputStream();
	
		PrintStream writer = new PrintStream(out);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		
		ClientReader tratamento = new ClientReader(reader);
		Thread continuosRead = new Thread(tratamento);
		continuosRead.start();
		
		while(true) {
			String mensagem = teclado.nextLine();	
			writer.println(mensagem);
						
			if (mensagem.equals("QUIT")) { 
				tratamento.setStatus(false);
				continuosRead.interrupt();
				break;	
			}

			if(mensagem.equals("ACCEPT")) {
				String ipDesafiador = tratamento.getOponentIP();
				writer.println("AGREED " + ipDesafiador);
			}			
		}
			System.out.println("Encerrando cliente [...] ");
				
			tratamento.setStatus(false);
			continuosRead.interrupt();
				
			teclado.close();
			reader.close();
			writer.close();
			System.out.println("Fluxo de dados fechado.");
			
			client.close();
			System.out.println("Conexão com servidor finalizada.");
	}
}
