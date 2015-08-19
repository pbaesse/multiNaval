package cliente_servidor_thread_REMAKE;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Battle implements Runnable {

	private Socket client1;
	private String ipClient1;
	private PrintStream writer1;
	private BufferedReader reader1;
	
	private Scanner keyboard;
	private boolean won = false;
	
	private Socket client2;
	private String ipClient2;
	private PrintStream writer2;
	private BufferedReader reader2;
	
	public Battle(Socket client1, Socket client2) throws Exception {
		this.client1 = client1;
		this.ipClient1 = this.client1.getInetAddress().getHostAddress();
		this.writer1 = new PrintStream(this.client1.getOutputStream());
		this.reader1 = new BufferedReader(new InputStreamReader(this.client1.getInputStream()));

		this.keyboard = new Scanner(System.in);
		
		this.client2 = client2;
		this.ipClient2 = this.client2.getInetAddress().getHostAddress();
		this.writer2 = new PrintStream(this.client2.getOutputStream());
		this.reader2 = new BufferedReader(new InputStreamReader(this.client2.getInputStream()));
	}
	
	private void round(Socket client, String ipClient, PrintStream writer, BufferedReader reader, PrintStream writerEnemy) throws Exception {
		String command = keyboard.nextLine();
		
		System.out.println("Mensagem do Cliente [" + ipClient + "]: " + command);
	
		if( !this.readCommand(command, writer, writerEnemy)) {	
			System.out.println("Batalha parou!");
			this.wait();
		} else {}
	}
	
	private boolean readCommand(String command, PrintStream writer, PrintStream writerAttacked) {
		switch(command) {
			case "ATTACK":
				writerAttacked.println("Você foi atacado!");
				return true;
				
			case "SURRENDER":
				won = true;
				return false;
			
			default:
				return true;
		}
	}
	
	@Override
	public void run() {
		System.out.println("The battle begins!");
		System.out.println("O jogador desafiado joga primeiro. [ ");
		
		while(!won) {
			try {
				writer1.println("É a sua vez");
				writer1.print("O que deseja fazer? ");		
				writer2.println("É a vez do seu inimigo");
				
				round(client1, ipClient1, writer1, reader1, writer2);

				writer2.println("É a sua vez");
				writer2.println("O que deseja fazer? ");		
				writer1.println("É a vez do seu inimigo");
				round(client2, ipClient2, writer2, reader2, writer1);
			} 
			catch (Exception error) {
				System.out.println("Error: " + error);
			}
		}	
	}
}
