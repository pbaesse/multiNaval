package cliente_servidor_thread_REMAKE;

import java.io.*;

/*
 * reads MESSAGES from SERVER
 */

public class ClientReader implements Runnable {

	private String oponentIP;
	private BufferedReader reader;
	private boolean keepRunning = true;
	private boolean kp = true;
	
	public boolean keepRunning() {
		return kp;
	}
	
	public String getOponentIP() { return oponentIP; }
	public ClientReader(BufferedReader reader) { this.reader = reader; }
	public void setStatus(boolean status) {
		if (status)	keepRunning = true;
		else keepRunning = false;
	}
	
	@Override
	public void run() {
		while(keepRunning) {
			try {
				String message = reader.readLine();
				System.out.println("Mensagem recebida: " + message);
				
				if(message.contains("[NEW INVITE]")) {
					String[] IP = message.split("  ");
					System.out.println("IP do Oponente: " + IP[0]);
					oponentIP = IP[0];	// PASSAR PARA O TRANSMITTER (IP DO DESAFIADOR)
				}
				
				if(message.contains("#(QUIT)")) {
					kp = false;
				}
				
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
	}	
}