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
        waitOponent();
        
    }
    public void waitOponent(){
        ServerSocket skt;
        Socket connection;
        try {
            skt = new ServerSocket(3128);
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
        lblIP = new javax.swing.JLabel();
        yourIP = new javax.swing.JLabel();
        yourIP1 = new javax.swing.JLabel();
        yourIP2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblPort.setForeground(new java.awt.Color(255, 255, 255));
        lblPort.setText("Port:");
        lblPort.setMaximumSize(new java.awt.Dimension(40, 14));
        lblPort.setMinimumSize(new java.awt.Dimension(40, 14));

        lblIP.setForeground(new java.awt.Color(255, 255, 255));
        lblIP.setText("IP:");

        yourIP.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        yourIP.setForeground(new java.awt.Color(255, 255, 255));
        yourIP.setText("xxx.xxx.xxx.xxx");

        yourIP1.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        yourIP1.setForeground(new java.awt.Color(255, 255, 255));
        yourIP1.setText("xxxx");

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
                    .addComponent(lblPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIP, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(yourIP2)
                    .addComponent(yourIP)
                    .addComponent(yourIP1))
                .addContainerGap(159, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIP)
                    .addComponent(yourIP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yourIP1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(yourIP2)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblIP;
    private javax.swing.JLabel lblPort;
    private javax.swing.JLabel yourIP;
    private javax.swing.JLabel yourIP1;
    private javax.swing.JLabel yourIP2;
    // End of variables declaration//GEN-END:variables
}
