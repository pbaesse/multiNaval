/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.iface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import main.helper_classes.Inbox;
import main.helper_classes.Jukebox;
import main.resources.SocketAddressSpliter;

/**
 *
 * @author luann
 */
public class InterfaceAlpha extends javax.swing.JFrame {

    /**
     * Attributes
     */
    private Cell[][] userTable = new Cell[11][11];
    private Jukebox jukebox;
    
    private final Cell[][] enemyTable;

    private final JPanel userPanel;
    private final JPanel enemyPanel;

    private int attacks = 0;
    private boolean gameOver = false;
    private String side = "Horizontal";
    private final String invalidMessage = "Posição inválida.";
    
    // Connection attributes
    private final Socket connection;
    private final PrintStream writer;
    private final BufferedReader reader;
    private boolean gameStarted = false;
    private boolean enemyReady = false;
    private boolean ready = false;
    private boolean myTurn = false;
    
    // Ship attributes
    private int shipLength;
    private int shipsHitted;
    private JButton btnSideShips;
    private final Color submarine = Color.YELLOW;
    private final Color shipTwo = Color.ORANGE;
    private final Color shipThree = Color.GREEN;
    private final Color shipFour = Color.CYAN;
    private final Color shotted = Color.RED;
    private final Color water = new Color(41,95,140);
    private final int[] amountShip = new int[4];
    private JPanel shipPanel;
    
    /**
     * Creates new form InterfaceAlpha
     * @param connection
     * @param iStart
     * @throws java.io.IOException
     */
    public InterfaceAlpha(Socket connection, boolean iStart) throws IOException {
        setContentPane(new JLabel(new ImageIcon("resources/tables.png")));
        initComponents();
        btnReady.setBackground(new Color(160,95,63));
        btnLose.setBackground(new Color(160,95,63));
        btnReady.setBorderPainted(false);
        btnLose.setBorderPainted(false);
        
        if (iStart) myTurn = true;
        
        jukebox = new Jukebox();
        
        this.connection = connection;
        
        this.writer = new PrintStream(connection.getOutputStream());
        this.reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        createThreads();        
        
        yourIP.setText(new SocketAddressSpliter(connection.getLocalSocketAddress()).IP);
        enemyIP.setText(new SocketAddressSpliter(connection.getRemoteSocketAddress()).IP);
        
        repaint();
        revalidate();
        
        userPanel = new JPanel();
        enemyPanel = new JPanel();
        userTable = new Cell[11][11];
        enemyTable = new Cell[11][11];
        createTable(30, 90, userPanel, userTable);
        createShips();
    }
    
    
    /*
     * Private Methods  
     */
    
    private void createThreads() throws IOException {
        new Thread(new Inbox(this, connection)).start();
        
        // Check if both of players are ready
        new Thread(() -> {
            while(!gameStarted) {
                if (enemyReady && ready) {                    
                    // Check the turns
                    new Thread(() -> {
                        while(!gameOver) {
                            
                            if (myTurn) {
                                labelTurn.setText("Turno: você");
                                
                                if (attacks >= 3) {
                                    myTurn = false;                                    
                                    writer.println("YOUR TURN");
                                    attacks = 0;
                                    yourIP.setFocusable(false);
                                    labelTurn.setText("Turno: oponente");
                                }
                            } else {
                                labelTurn.setText("Turno: oponente");
                            }
                                
                            repaint();
                            revalidate();

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                System.out.println("Error (Thread CheckIn): " + ex);
                            }                            
                        }
                    }).start();
                    
                    this.gameStarted = true;
                }
                
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println("Error (Thread CheckIn): " + ex);
                }
            }
        }).start();
        
        // Total ship cells = 20 (1*2 + 2*2 + 3*2 + 4*2)
        // Check if all ship cells has been hitted
        new Thread(() -> {
            while(!gameOver) {
                try {
                    if (getShipsHitted() == 20) {
                        displayMessage("Você venceu!");
                        writer.println("LOSE");
                        gameOver();
                    }
                    
                    Thread.sleep(1000);
                
                } catch (InterruptedException ex) {
                    Logger.getLogger(InterfaceAlpha.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }
    
    private void putShip(Cell[][] table, int x, int y) {
        String amountMessage = "A quantidade máxima deste tipo de barco foi atingida";
        
        switch (shipLength) {
            case 1:
                if (amountShip[0] < 2 && checkPosition(table, x, y)) {
                    table[x][y].getButton().setBackground(submarine);
                    table[x][y].setClicked(true);
                    
                    switch (amountShip[0]) {
                        case 0: table[x][y].setCellName("SA"); break;
                        case 1: table[x][y].setCellName("SB"); break;                            
                    }
                    
                    amountShip[0]++;
                } else if (amountShip[0] >= 2) {
                    displayMessage(amountMessage);                    
                }
            break;
                
            case 2:
                if (amountShip[1] < 2 && checkPosition(table, x, y)) {
                    switch (side) {
                        case "Horizontal":
                            table[x][y].getButton().setBackground(shipTwo);
                            table[x][y].setClicked(true);
                            table[x][y + 1].getButton().setBackground(shipTwo);
                            table[x][y + 1].setClicked(true);
                            
                            switch(amountShip[1]) {
                                case 0:
                                    table[x][y].setCellName("HA1"); 
                                    table[x][y + 1].setCellName("HA2");
                                break;
                                    
                                case 1:
                                    table[x][y].setCellName("HB1");
                                    table[x][y + 1].setCellName("HB2");
                                break;
                            }
                            
                            amountShip[1]++;
                        break;
                            
                        case "Vertical":
                            table[x][y].getButton().setBackground(shipTwo);
                            table[x][y].setClicked(true);
                            table[x + 1][y].getButton().setBackground(shipTwo);
                            table[x + 1][y].setClicked(true);
                            
                            switch(amountShip[1]) {
                                case 0:
                                    table[x][y].setCellName("HA1"); 
                                    table[x + 1][y].setCellName("HA2");
                                break;
                                    
                                case 1:
                                    table[x][y].setCellName("HB1");
                                    table[x + 1][y].setCellName("HB2");
                                break;
                            }
                            
                            amountShip[1]++;
                        break;
                    }
                } else if (amountShip[1] >= 2) {
                    displayMessage(amountMessage);
                                       
                }
            break;
                
            case 3:
                if (amountShip[2] < 2 && checkPosition(table, x, y)) {
                    switch (side) {
                        case "Horizontal":
                            table[x][y].getButton().setBackground(shipThree);
                            table[x][y].setClicked(true);
                            table[x][y + 1].getButton().setBackground(shipThree);
                            table[x][y + 1].setClicked(true);
                            table[x][y + 2].getButton().setBackground(shipThree);
                            table[x][y + 2].setClicked(true);
                            
                            switch(amountShip[2]) {
                                case 0:
                                    table[x][y].setCellName("IA1");
                                    table[x][y + 1].setCellName("IA2");
                                    table[x][y + 2].setCellName("IA3");
                                break;
                                    
                                case 1:
                                    table[x][y].setCellName("IB1");
                                    table[x][y + 1].setCellName("IB2");
                                    table[x][y + 2].setCellName("IB3");
                                break;
                            }
                            
                            amountShip[2]++;
                        break;
                        
                        case "Vertical":
                            table[x][y].getButton().setBackground(shipThree);
                            table[x][y].setClicked(true);
                            table[x + 1][y].getButton().setBackground(shipThree);
                            table[x + 1][y].setClicked(true);
                            table[x + 2][y].getButton().setBackground(shipThree);
                            table[x + 2][y].setClicked(true);
                            
                            switch(amountShip[2]) {
                                case 0:
                                    table[x][y].setCellName("IA1");
                                    table[x + 1][y].setCellName("IA2");
                                    table[x + 2][y].setCellName("IA3");
                                break;
                                    
                                case 1:
                                    table[x][y].setCellName("IB1");
                                    table[x + 1][y].setCellName("IB2");
                                    table[x + 2][y].setCellName("IB3");
                                break;
                            }
                            
                            amountShip[2]++;
                        break;
                    }
                } else if (amountShip[2] >= 2) {
                    displayMessage(amountMessage);
                }
    
            break;
                
            case 4:
                if (amountShip[3] < 2 && checkPosition(table, x, y)) {
                    switch (side) {
                        case "Horizontal":
                            table[x][y].getButton().setBackground(shipFour);
                            table[x][y].setClicked(true);
                            table[x][y + 1].getButton().setBackground(shipFour);
                            table[x][y + 1].setClicked(true);
                            table[x][y + 2].getButton().setBackground(shipFour);
                            table[x][y + 2].setClicked(true);
                            table[x][y + 3].getButton().setBackground(shipFour);
                            table[x][y + 3].setClicked(true);
                            
                            switch(amountShip[3]) {
                                case 0:
                                    table[x][y].setCellName("PA1");
                                    table[x][y + 1].setCellName("PA2");
                                    table[x][y + 2].setCellName("PA3");
                                    table[x][y + 3].setCellName("PA4");
                                break;
                                    
                                case 1:
                                    table[x][y].setCellName("PB1");
                                    table[x][y + 1].setCellName("PB2");
                                    table[x][y + 2].setCellName("PB3");
                                    table[x][y + 3].setCellName("PB4");
                                break;
                            }
                            
                            amountShip[3]++;
                        break;
                            
                        case "Vertical":
                            table[x][y].getButton().setBackground(shipFour);
                            table[x][y].setClicked(true);
                            table[x + 1][y].getButton().setBackground(shipFour);
                            table[x + 1][y].setClicked(true);
                            table[x + 2][y].getButton().setBackground(shipFour);
                            table[x + 2][y].setClicked(true);
                            table[x + 3][y].getButton().setBackground(shipFour);
                            table[x + 3][y].setClicked(true);
                            
                            switch(amountShip[3]) {
                                case 0:
                                    table[x][y].setCellName("PA1");
                                    table[x + 1][y].setCellName("PA2");
                                    table[x + 2][y].setCellName("PA3");
                                    table[x + 3][y].setCellName("PA4");
                                break;
                                    
                                case 1:
                                    table[x][y].setCellName("PB1");
                                    table[x + 1][y].setCellName("PB2");
                                    table[x + 2][y].setCellName("PB3");
                                    table[x + 3][y].setCellName("PB4");
                                break;
                            }
                            
                            amountShip[3]++;
                        break;
                    }
                } else if (amountShip[3] >= 2) {
                    displayMessage(amountMessage);
                }                
            break;
                
            default:
                break;
        }
    }
    
    private boolean checkPosition(Cell[][] table, int x, int y) {
        Cell position = new Cell();
        
        if (shipLength > 1) {
            try {
                for (int i = 0; i < shipLength; i++ ) {       
                    switch (side) {
                        case "Horizontal": position = table[x][y + i]; break;
                        case "Vertical": position = table[x + i][y]; break;
                    }
                    
                    if (position.isClicked()) { 
                        displayMessage(invalidMessage);
                        return false;
                    }
                }
            } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                displayMessage(invalidMessage);
                return false;
            }    
        }
        
        return true;
    }

    private void attack(Cell[][] table, int row, int col) {
        Cell position = enemyTable[row][col];
        
        // Verificar o turno
        if (isMyTurn()) {
            if (!position.isShotted()) {
                if (!"0".equals(position.getCellName())) {
                    if (position.getCellName().contains("S")) {                
                        position.getButton().setBackground(submarine);                
                    } else if (position.getCellName().contains("H")) {
                        position.getButton().setBackground(shipTwo);
                    } else if (position.getCellName().contains("I")) {
                        position.getButton().setBackground(shipThree);
                    } else if (position.getCellName().contains("P")) {
                        position.getButton().setBackground(shipFour);
                    }

                    setShipsHitted(getShipsHitted() + 1);
                    position.setShotted(true);
                    playSound("shotNotLoud.wav");
                } else {
                    position.getButton().setVisible(false);
                    playSound("waterExplosion.wav");
                }

                attacks++;
                position.setCellName(position.getCellName() + "X");
                updateTable();
            } else {
                displayMessage("Você já atirou aqui!");
            }
        } else {
            displayMessage("Não é seu turno!");
        }
    }

    private void createShips() {
        shipPanel = new JPanel();
        shipPanel.setLayout(new GridLayout(4, 4));
        shipPanel.setBounds(380, 90, 120, 110);
        shipPanel.setBackground(new Color(160,95,63));
        
        btnSideShips = new JButton();
        btnSideShips.setText("Vertical");
        btnSideShips.setBounds(380, 210, 120, 30);
        btnSideShips.setVisible(true);
        btnSideShips.setBackground(new Color(41,95,140));
        btnSideShips.setBorderPainted(false);
        btnSideShips.setForeground(Color.WHITE);
        getContentPane().add(btnSideShips);
        
        btnSideShips.addActionListener((java.awt.event.ActionEvent evt) -> {
            selectSide();
        });
        
        for (int i = 0; i < 4 * 4; i++ ) {
            JButton btn = new JButton();
            int cellSpace = 0;
            
            if (i < 4) { 
                btn.setBackground(shipFour);
                btn.setName("btnShipFour" + (i+1));
                cellSpace = 4;
            } else if (i < 8 && i > 4) { 
                btn.setBackground(shipThree); 
                btn.setName("btnShipThree" + (i+1));
                cellSpace = 3;
            } else if (i < 12 && i > 9) { 
                btn.setBackground(shipTwo); 
                btn.setName("btnShipTwo" + (i+1));
                cellSpace = 2;
            } else if (i == 15) {
                btn.setBackground(submarine); 
                btn.setName("btnSubmarine");
                cellSpace = 1;
            } else { 
                btn.setVisible(false);
            }
            
            int shipLengthFinal = cellSpace;
            
            btn.addActionListener((java.awt.event.ActionEvent evt) -> {
                selectShip(shipLengthFinal);
            });
            
            shipPanel.add(btn);
        }
        
        getContentPane().add(shipPanel);
    }
    
    private void selectShip(int shipLength) {
        this.shipLength = shipLength;
    }
    
    private void updateTable() {
        String table = "UPDATE ";
        
        for (int row = 0; row < 11; row++ ) {
            for (int col = 0; col < 11; col++ ) {
                table += enemyTable[row][col].getCellName() + " ";
            }
        }
        
        writer.println(table);
    }
    
    private void changeSide(String side) {        
        for (int i = 0; i < 4 * 4; i++ ) {
            this.shipPanel.getComponent(i).setVisible(false);
        }
        this.shipPanel.removeAll();
        
        switch (side) {
            case "Vertical":                
                for (int i = 0; i < 4 * 4; i++ ) {
                    JButton btn = new JButton();
                    int cellSpace = 0;
                    
                    switch(i) {
                        case 0: case 4: case 8: case 12:
                            btn.setBackground(shipFour);
                            btn.setName("btnShipFour" + (i+1));
                            cellSpace = 4;
                        break;
                            
                        case 1: case 5: case 9:
                            btn.setBackground(shipThree);
                            btn.setName("btnShipThree" + (i+1));
                            cellSpace = 3;
                        break;
                            
                        case 2: case 6:
                            btn.setBackground(shipTwo);
                            btn.setName("btnShipTwo" + (i+1));
                            cellSpace = 2;
                        break;
                            
                        case 3:
                            btn.setBackground(submarine);
                            btn.setName("btnSubmarine");
                            cellSpace = 1;
                            if(amountShip[0] == 2){btn.setBackground(shotted);}
                        break;
                            
                        case 7: case 10: case 11: case 13: case 14: case 15:
                            btn.setVisible(false);
                            break;
                    }
                    
                    int shipLengthFinal = cellSpace;
                    
                    btn.addActionListener((java.awt.event.ActionEvent evt) -> {
                        selectShip(shipLengthFinal);
                    });
                    
                    shipPanel.add(btn);
                }   
                this.btnSideShips.setText("Horizontal");
                this.side = "Vertical";
            break;

            case "Horizontal":
                for (int i = 0; i < 4 * 4; i++ ) {
                    JButton btn = new JButton();
                    int cellSpace = 0;
                    
                    switch(i) {
                        case 0: case 1: case 2: case 3:
                            btn.setBackground(shipFour);
                            btn.setName("btnShipFour" + (i+1));
                            cellSpace = 4;
                        break;
                            
                        case 5: case 6: case 7:
                            btn.setBackground(shipThree);
                            btn.setName("btnShipThree" + (i+1));
                            cellSpace = 3;
                        break;
                            
                        case 10: case 11:
                            btn.setBackground(shipTwo);
                            btn.setName("btnShipTwo" + (i+1));
                            cellSpace = 2;
                        break;
                            
                        case 15:
                            if(amountShip[0] < 2){btn.setBackground(submarine);}
                            else{btn.setBackground(shotted);}
                            
                            btn.setName("btnSubmarine");
                            cellSpace = 1;
                        break;
                            
                        case 4: case 8: case 9: case 12: case 13: case 14: 
                            btn.setVisible(false);
                        break;
                    }
                    
                    int shipLengthFinal = cellSpace;
                    
                    btn.addActionListener((java.awt.event.ActionEvent evt) -> {
                        selectShip(shipLengthFinal);
                    });

                shipPanel.add(btn);
            }   
                this.btnSideShips.setText("Vertical");
                this.side = "Horizontal";
                break;
        }
    }
    
    private void selectSide() {
        switch (btnSideShips.getText()) {
            case "Horizontal": changeSide("Horizontal"); break;
            case "Vertical": changeSide("Vertical"); break;
        }
    }
    
    private void sendTable() {
        String table = "TABLE ";
        
        for (int row = 0; row < 11; row++ ) {
            for (int col = 0; col < 11; col++ ) {
                table += userTable[row][col].getCellName() + " ";
            }
        }
        
        writer.println(table);
    }

    
    /*
     *  Public Methods
     */

    public boolean isMyTurn() {
        return myTurn;
    }

    public void setTurn(boolean turn) {
        this.myTurn = turn;
    }
    
    public boolean isGameOver() {
        return this.gameOver;
    }

    public void gameOver() {
        try {
            gameOver = true;
            
            writer.close();            
            reader.close();
            connection.close();
            
            this.dispose();
            System.exit(0);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void setShipsHitted(int shipsHitted) {
        this.shipsHitted = shipsHitted;
    }
    
    public int getShipsHitted() {
        return this.shipsHitted;
    }
    
    public void setReady(boolean state) {
        this.ready = state;
    }
    
    public boolean getReady() {
        return ready;
    }
    
    public void setEnemyReady(boolean state) {
        this.enemyReady = state;
    }
    
    public boolean getEnemyReady() {
        return enemyReady;
    }
    
    public JPanel getEnemyPanel() {
        return enemyPanel;
    }
    
    public Cell[][] getEnemyTable() {
        return enemyTable;
    }
 
    public Cell[][] getUserTable() {
        return this.userTable;
    }
    
    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }    
    
    public final void createTable(int x, int y, JPanel panel, Cell[][] table) {
        panel.setBounds(x, y, 330, 300);
        panel.setLayout(new GridLayout(11, 11));
        panel.setBackground(water);

        for (int row = 0; row < 11; row++) {
            for (int col = 0; col < 11; col++) {
                JPanel btn = new JPanel();
                btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
                btn.setBackground(water);
                table[row][col] = new Cell();

                /* Transform into final values for use on inner class */
                final Cell position = table[row][col];
                final int finalRow = row;
                final int finalCol = col;
                /* ================================================== */

                position.setButton(btn);
                position.setCellName("0");

                table[row][col].getButton().addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) { 
                        if (table == userTable) {
                            if (position.isClicked()) {
                                displayMessage(invalidMessage);
                            } else if ( shipLength != 0 ) {
                                putShip(table, finalRow, finalCol);
                            } else if ( shipLength == 0) {
                                displayMessage("Selecione um barco");
                            }
                        } else if (table == enemyTable && ready) {
                            attack(table, finalRow, finalCol);
                        }
                    }
                });

                panel.add(btn);
            }
        }
        
        getContentPane().add(panel);
    }
    
    public void playSound(String filename) {
        jukebox.play("resources/" + filename + "/");
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        btnReady = new javax.swing.JButton();
        yourIP = new javax.swing.JLabel();
        enemyIP = new javax.swing.JLabel();
        labelTurn = new javax.swing.JLabel();
        btnLose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(880, 500));
        setMinimumSize(new java.awt.Dimension(880, 500));
        setPreferredSize(new java.awt.Dimension(880, 500));

        btnReady.setForeground(new java.awt.Color(255, 255, 255));
        btnReady.setText("Pronto");
        btnReady.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReadyActionPerformed(evt);
            }
        });

        yourIP.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        yourIP.setForeground(new java.awt.Color(255, 255, 255));
        yourIP.setText("xxx.xxx.xxx.xxx");

        enemyIP.setForeground(new java.awt.Color(255, 255, 255));
        enemyIP.setText("xxx.xxx.xxx.xxx");

        labelTurn.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        labelTurn.setForeground(new java.awt.Color(255, 255, 255));
        labelTurn.setText("É a vez de ...");

        btnLose.setForeground(new java.awt.Color(255, 255, 255));
        btnLose.setText("Desistir");
        btnLose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(yourIP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 463, Short.MAX_VALUE)
                        .addComponent(enemyIP)
                        .addGap(29, 29, 29))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelTurn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnReady)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLose)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(labelTurn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yourIP)
                    .addComponent(enemyIP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 497, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReady)
                    .addComponent(btnLose))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReadyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReadyActionPerformed
        if (amountShip[0] == 2 && amountShip[1] == 2 && amountShip[2] == 2 && amountShip[3] == 2 && !ready) {
            writer.println("READY");
            sendTable();
            setReady(true);            
        } else if (ready) { 
            displayMessage("Você já está pronto!");
        } else {
            displayMessage("Você ainda não posicionou todos os barcos");
        }
    }//GEN-LAST:event_btnReadyActionPerformed

    private void btnLoseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoseActionPerformed
        int answer = JOptionPane.showConfirmDialog(null, "Você realmente quer desistir?");
        
        if (answer == JOptionPane.YES_OPTION) {
            gameOver = true;
            writer.println("WIN");

            gameOver();
        }
    }//GEN-LAST:event_btnLoseActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLose;
    private javax.swing.JButton btnReady;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel enemyIP;
    private javax.swing.JLabel labelTurn;
    private javax.swing.JLabel yourIP;
    // End of variables declaration//GEN-END:variables

}