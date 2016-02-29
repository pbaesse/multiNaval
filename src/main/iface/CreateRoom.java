package main.iface;

import java.awt.Color;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CreateRoom extends javax.swing.JFrame {

    public CreateRoom() {
        setTitle("Criando sala...");
        setSize(400,182);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        initComponents();
        getContentPane().setBackground(new Color(68,49,40));  
        
        
        new Thread(() -> {
            waitOponent();
        }).start();        
    }
    
    public void waitOponent(){
        ServerSocket skt;
        Socket connection;
        try {
            // Cria um Servidor
            skt = new ServerSocket(3128);
            
            // Faz com que esse servidor passe a aceitar conexões
            connection = skt.accept();
            
            System.out.println("Conectado ao ip " + connection.getInetAddress().getHostAddress());    
            
            this.dispose();
            new InterfaceAlpha(connection, true).setVisible(true);
        } catch (IOException ex) {
            System.out.println("Erro wait: " + ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        yourIP2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        yourIP2.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        yourIP2.setForeground(new java.awt.Color(255, 255, 255));
        yourIP2.setText("Esperando oponente...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(yourIP2)
                .addContainerGap(113, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(yourIP2)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel yourIP2;
    // End of variables declaration//GEN-END:variables
}