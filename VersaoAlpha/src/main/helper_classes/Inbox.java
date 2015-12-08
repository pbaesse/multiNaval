package main.helper_classes;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import main.iface.Cell;
import main.iface.InterfaceAlpha;

/**
 *
 * @author luann
 * 
 * Class that receives messages from the another client and
 * then does something based on the message received
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
    
    /**
     * @param table
     * @throws IOException 
     * 
     * Method that transform the String message received from the another client
     * and then create the enemy's table based on the message
     */
    private void createEnemyTable(String table) throws IOException {
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

    /**
     * @param message 
     * Method that does an comparation between your table before an attack and after an attack
     */
    private void compareTable(String message) {
        // an array with all cell's names 
        String[] elements = message.split(" ");
        Cell[][] UserTable = iface.getUserTable();
        int counter = 1;

        iface.playSound("shotNotLoud.wav");
        for (int row = 0; row < 11; row++) {
            for (int col = 0; col < 11; col++) {
                // if both cell's name are equals (The same cell before/after attacked)
                if (UserTable[row][col].getCellName().equals(elements[counter])) {
                    // Do nothing
                // else if the cell name isn't equivalent to a hit water cell AND
                // cell's name contains X (it means that this cell was hit)
                } else if (!elements[counter].equals("0X") && elements[counter].contains("X")) {
                    // set the button color to red
                    UserTable[row][col].getButton().setBackground(Color.red);
                    
                // else if, the cell's name is equivalent to a hit water cell
                } else if (elements[counter].equals("0X")) {
                    UserTable[row][col].getButton().setVisible(false);
                    UserTable[row][col].setCellName("X");
                }

                counter++;
            }
        }
    }

    @Override
    public void run() {
        while (!iface.isGameOver()) {
            try {
                String message = reader.readLine();

                if (message.equals("READY")) {
                    iface.setEnemyReady(true);
                    iface.displayMessage("Seu oponente está pronto!");
                } else if (message.contains("TABLE")) {
                    createEnemyTable(message);
                } else if (message.contains("UPDATE")) {
                    compareTable(message);
                } else if (message.equals("LOSE")) {
                    iface.displayMessage("Você perdeu!");
                    iface.gameOver();
                    reader.close();
                    connection.close();
                } else if (message.equals("YOUR TURN")) {
                    iface.setTurn(true);
                    iface.displayMessage("É seu turno!");
                } else if (message.equals("WIN")) {
                    iface.playSound("win.wav");
                    iface.displayMessage("Seu oponente desistiu! Você ganhou");
                    iface.gameOver();
                }
            } catch (java.lang.NullPointerException | IOException ex) {
                iface.playSound("win.wav");
                iface.displayMessage("Seu oponente saiu. Você ganhou!");
                iface.gameOver();
            }
        }
    }
}