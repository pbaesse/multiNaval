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

        lblPort = new javax.swing.JLabel();
        yourIP1 = new javax.swing.JLabel();
        yourIP2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblPort.setForeground(new java.awt.Color(255, 255, 255));
        lblPort.setText("Port:");
        lblPort.setMaximumSize(new java.awt.Dimension(40, 14));
        lblPort.setMinimumSize(new java.awt.Dimension(40, 14));

        yourIP1.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        yourIP1.setForeground(new java.awt.Color(255, 255, 255));
        yourIP1.setText("3128");

        yourIP2.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        yourIP2.setForeground(new java.awt.Color(255, 255, 255));
        yourIP2.setText("Esperando oponete...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(yourIP2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(yourIP1)))
                .addContainerGap(203, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(yourIP2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yourIP1))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblPort;
    private javax.swing.JLabel yourIP1;
    private javax.swing.JLabel yourIP2;
    // End of variables declaration//GEN-END:variables
}