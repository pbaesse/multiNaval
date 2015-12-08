package main;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Main extends javax.swing.JFrame {

    public Main() {
        initComponents();
        
        setTitle("MultiNaval - Multiplayer Battleship");
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(new JLabel(new ImageIcon("resources/images/background.png")));
        setSize(392,177);
        initComponents();
        
        btnEnterRoom.setBackground(new Color(160,95,63));
        btnCreateRoom.setBackground(new Color(160,95,63));
        btnEnterRoom.setBorderPainted(false);
        btnCreateRoom.setBorderPainted(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEnterRoom = new javax.swing.JButton();
        btnCreateRoom = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(419, 181));

        btnEnterRoom.setForeground(new java.awt.Color(255, 255, 255));
        btnEnterRoom.setText("Entrar na sala");
        btnEnterRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnterRoomActionPerformed(evt);
            }
        });

        btnCreateRoom.setForeground(new java.awt.Color(255, 255, 255));
        btnCreateRoom.setText("Criar sala");
        btnCreateRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateRoomActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(btnCreateRoom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEnterRoom)
                .addContainerGap(110, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(127, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEnterRoom)
                    .addComponent(btnCreateRoom))
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnterRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnterRoomActionPerformed
        new main.iface.EnterRoom();
        this.dispose();
    }//GEN-LAST:event_btnEnterRoomActionPerformed

    private void btnCreateRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateRoomActionPerformed
        setVisible(false);
        new main.iface.CreateRoom();
        this.dispose();
    }//GEN-LAST:event_btnCreateRoomActionPerformed

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateRoom;
    private javax.swing.JButton btnEnterRoom;
    // End of variables declaration//GEN-END:variables
}
