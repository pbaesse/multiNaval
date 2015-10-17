package main.resources;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import main.iface.Cell;
import main.iface.InterfaceAlpha;

/**
 *
 * @author luann
 */
public class Inbox implements Runnable {

    /**
     * Attributes
     */
    
    private final BufferedReader reader;
    private final Socket connection;
    private final InterfaceAlpha iface;
        
    public Inbox(InterfaceAlpha iface, Socket connection) throws IOException {
        this.connection = connection;
        this.reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        this.iface = iface;
    }
    
    private void splitTable(String table) throws IOException {
        String[] elements = table.split(" ");
        iface.createTable(520, 90, iface.getEnemyPanel(), iface.getEnemyTable());
        Cell[][] enemyTable = iface.getEnemyTable();
        int counter = 1;
        
        for (int row = 0; row < 11; row++) {
            for (int col = 0; col < 11; col++) {
                enemyTable[row][col].setCellName(elements[counter]);                
                counter++;                
            }
        }
    }
    
    private void compareTable(String message) {
        String[] elements = message.split(" ");
        Cell[][] UserTable = iface.getUserTable();
        int counter = 1;
        
        for (int row = 0; row < 11; row++) {
            for (int col = 0; col < 11; col++) {
                if (UserTable[row][col].getCellName().equals(elements[counter])) {
                    
                } else if (!elements[counter].equals("0X") && elements[counter].contains("X")) {                    
                    UserTable[row][col].getButton().setBackground(Color.red);
                } else if (elements[counter].equals("0X")) {
                    UserTable[row][col].getButton().setVisible(false);
                    UserTable[row][col].setCellName("X");
                }
                
                counter++;
            }
            
            System.out.println();
        }
    }

    @Override
    public void run() {
        while(!iface.isGameOver()) {            
            try {
                String message = reader.readLine();
                System.out.println(message);
                
                if(message.equals("READY")) {
                    iface.setEnemyReady(true); 
                    iface.displayMessage("Seu oponente está pronto!");
                } else if (message.contains("TABLE")) {
                    splitTable(message);
                } else if(message.contains("UPDATE")) {
                    compareTable(message);
                } else if(message.equals("LOSE")) {
                    iface.displayMessage("Você perdeu!");
                    iface.gameOver();
                    reader.close();
                    connection.close();
                }
            } catch (SocketException ex) {
                try {
                    reader.close();
                    connection.close();
                } catch (IOException ex1) {
                    System.out.println("Inbox's error: " + ex);
                }

            } catch (IOException ex) {
                System.out.println("Inbox's error: " + ex);
            }
        }
    }
}
