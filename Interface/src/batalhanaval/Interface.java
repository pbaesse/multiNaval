package batalhanaval;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.OK_CANCEL_OPTION;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class Interface extends javax.swing.JFrame {

    // misera = quantidade de tiros acertados, se igual a 30 ganha a partida
    Cell[][] user = new Cell[11][11]; //tem um button, um vlr boolean se pintado, e um vlr vlr boolean se atirado  
    Cell[][] oponent = new Cell[11][11]; //tem um button, um vlr boolean se pintado, e um vlr vlr boolean se atirado
    int comando = 0, quant[] = new int[5], seg = 60, misera = 0;  // quant = quantidade de barcos já posicionados
    Boolean or = false, pronto = false; // or = se falso posicionara barcos na horizontal, se true na vertical; pronto = inicar batalha
    Color temn = Color.BLACK, tem = Color.GRAY, c1 = Color.YELLOW, c2 = Color.RED, c3 = Color.BLUE, c4 = Color.GREEN, c5 = Color.ORANGE;
    JPanel voceTAB = new JPanel(); // painel onde está o tabuleiro usuario
    JPanel oponenteTAB = new JPanel(); // painel onde está o tabuleiro oponente
    JTextArea updates = new JTextArea();
    JScrollPane scroll = new JScrollPane(updates);
    Timer timer;

    public Interface() {
        setTitle("MultiNaval - Em desenvolvimento");
        initComponents();
        layoutPIN();
        geradorTAB(voceTAB, 30, 95, user);
        geradorTAB(oponenteTAB, 575, 95, oponent);
        iniciarContagem();
        this.add(scroll);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelTempo = new javax.swing.JPanel();
        jLabelCron = new javax.swing.JLabel();
        jButtonPronto = new javax.swing.JButton();
        jButtonDesistir = new javax.swing.JButton();
        jButtonPA5 = new javax.swing.JButton();
        jButtonPA4 = new javax.swing.JButton();
        jButtonPA3 = new javax.swing.JButton();
        jButtonPA2 = new javax.swing.JButton();
        jButtonPA1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButtonPA7 = new javax.swing.JButton();
        jButtonPA8 = new javax.swing.JButton();
        jButtonPA9 = new javax.swing.JButton();
        jButtonPA10 = new javax.swing.JButton();
        jButtonPA13 = new javax.swing.JButton();
        jButtonPA14 = new javax.swing.JButton();
        jButtonPA15 = new javax.swing.JButton();
        jButtonPA19 = new javax.swing.JButton();
        jButtonPA20 = new javax.swing.JButton();
        jButtonPA25 = new javax.swing.JButton();
        JToggleVertical = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setForeground(new java.awt.Color(255, 255, 255));

        jPanelTempo.setBackground(new java.awt.Color(255, 255, 255));
        jPanelTempo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabelCron.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabelCron.setText("00 : 00 : 00");

        javax.swing.GroupLayout jPanelTempoLayout = new javax.swing.GroupLayout(jPanelTempo);
        jPanelTempo.setLayout(jPanelTempoLayout);
        jPanelTempoLayout.setHorizontalGroup(
            jPanelTempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTempoLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabelCron)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanelTempoLayout.setVerticalGroup(
            jPanelTempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTempoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelCron)
                .addContainerGap())
        );

        jButtonPronto.setText("Pronto");
        jButtonPronto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProntoActionPerformed(evt);
            }
        });

        jButtonDesistir.setText("Desistir");
        jButtonDesistir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDesistirActionPerformed(evt);
            }
        });

        jButtonPA5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPA5ActionPerformed(evt);
            }
        });

        jButtonPA4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPA4ActionPerformed(evt);
            }
        });

        jButtonPA3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPA3ActionPerformed(evt);
            }
        });

        jButtonPA2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPA2ActionPerformed(evt);
            }
        });

        jButtonPA1.setForeground(new java.awt.Color(255, 51, 51));
        jButtonPA1.setBorder(null);
        jButtonPA1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPA1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel3.setText("MultiNaval");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel2.setText("Você");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Oponente");

        jButtonPA7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPA7ActionPerformed(evt);
            }
        });

        jButtonPA8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPA8ActionPerformed(evt);
            }
        });

        jButtonPA9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPA9ActionPerformed(evt);
            }
        });

        jButtonPA10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPA10ActionPerformed(evt);
            }
        });

        jButtonPA13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPA13ActionPerformed(evt);
            }
        });

        jButtonPA14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPA14ActionPerformed(evt);
            }
        });

        jButtonPA15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPA15ActionPerformed(evt);
            }
        });

        jButtonPA19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPA19ActionPerformed(evt);
            }
        });

        jButtonPA20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPA20ActionPerformed(evt);
            }
        });

        jButtonPA25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPA25ActionPerformed(evt);
            }
        });

        JToggleVertical.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JToggleVertical.setText("Vertical");
        JToggleVertical.setBorderPainted(false);
        JToggleVertical.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JToggleVerticalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButtonPA10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jButtonPA9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jButtonPA8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jButtonPA7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButtonPA1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jButtonPA2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jButtonPA3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jButtonPA4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jButtonPA5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButtonPA15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jButtonPA14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jButtonPA13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButtonPA20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jButtonPA19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButtonPA25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JToggleVertical, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(391, 391, 391))
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 218, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jPanelTempo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(207, 207, 207)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
            .addGroup(layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jButtonPronto, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonDesistir, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanelTempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonPA1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPA2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPA3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPA4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPA5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonPA10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPA9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPA8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPA7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonPA15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPA14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPA13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonPA20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPA19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonPA25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JToggleVertical, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 228, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDesistir, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPronto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonProntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProntoActionPerformed
        // para iniciar a partida clicar em pronto, pronto só iniciará a partida se todos os barcos tiverem posicionados
        if ((quant[0] == 2) && (quant[1] == 2) && (quant[2] == 2) && (quant[3] == 2) && (quant[4] == 2)) {
            pronto = true;
        } else {
            JOptionPane.showMessageDialog(null, "Você ainda não posicionou todos os barcos para iniciar a batalha");
        }
    }//GEN-LAST:event_jButtonProntoActionPerformed

    private void jButtonDesistirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDesistirActionPerformed
        // se apertar em sim sai da jframe, se aperta em não ou em cancelar não acontecerá nada(por enquanto);
        int resp;
        resp = JOptionPane.showConfirmDialog(null, "Você deseja sair do jogo?");
        if (resp == JOptionPane.YES_OPTION) {
            super.dispose();
        }
    }//GEN-LAST:event_jButtonDesistirActionPerformed

    private void jButtonPA25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPA25ActionPerformed
        //submarino
        comando = 1;
    }//GEN-LAST:event_jButtonPA25ActionPerformed

    private void jButtonPA20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPA20ActionPerformed
        //barco duas posiçoes
        comando = 2;
    }//GEN-LAST:event_jButtonPA20ActionPerformed

    private void jButtonPA19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPA19ActionPerformed
        //barco duas posições
        comando = 2;
    }//GEN-LAST:event_jButtonPA19ActionPerformed

    private void jButtonPA15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPA15ActionPerformed
        //barco três posições
        comando = 3;
    }//GEN-LAST:event_jButtonPA15ActionPerformed

    private void jButtonPA14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPA14ActionPerformed
        //barco três posições
        comando = 3;
    }//GEN-LAST:event_jButtonPA14ActionPerformed

    private void jButtonPA13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPA13ActionPerformed
        //barco três posições
        comando = 3;
    }//GEN-LAST:event_jButtonPA13ActionPerformed

    private void jButtonPA10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPA10ActionPerformed
        //barco quatro posições
        comando = 4;
    }//GEN-LAST:event_jButtonPA10ActionPerformed

    private void jButtonPA9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPA9ActionPerformed
        //barco quatro posições
        comando = 4;
    }//GEN-LAST:event_jButtonPA9ActionPerformed

    private void jButtonPA8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPA8ActionPerformed
        //barco quatro posições
        comando = 4;
    }//GEN-LAST:event_jButtonPA8ActionPerformed

    private void jButtonPA7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPA7ActionPerformed
        //barco quatro posições
        comando = 4;
    }//GEN-LAST:event_jButtonPA7ActionPerformed

    private void jButtonPA1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPA1ActionPerformed
        //Cinco posições
        comando = 5;
    }//GEN-LAST:event_jButtonPA1ActionPerformed

    private void jButtonPA2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPA2ActionPerformed
        //Cinco posições
        comando = 5;
    }//GEN-LAST:event_jButtonPA2ActionPerformed

    private void jButtonPA3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPA3ActionPerformed
        //Cinco posições
        comando = 5;
    }//GEN-LAST:event_jButtonPA3ActionPerformed

    private void jButtonPA4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPA4ActionPerformed
        //Cinco posições
        comando = 5;
    }//GEN-LAST:event_jButtonPA4ActionPerformed

    private void jButtonPA5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPA5ActionPerformed
        //Cinco posições
        comando = 5;
    }//GEN-LAST:event_jButtonPA5ActionPerformed

    private void JToggleVerticalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JToggleVerticalActionPerformed
        // se o botão não estiver presionado posicionara em horizontal, se estiver presionado posicionara em vertical
        if (pronto == false) {
            if (JToggleVertical.isSelected()) {
                JToggleVertical.setText("Horizontal");
                or = true;
                updates.append("[CONSOLE] Barcos serão posicionados na vertical. \n");
            } else {
                JToggleVertical.setText("Vertical");
                or = false;
                updates.append("[CONSOLE] Barcos serão posicionados na horizontal. \n");
            }
        }
    }//GEN-LAST:event_JToggleVerticalActionPerformed

    private void geradorTAB(JPanel pane, int left, int top, Cell[][] usu) {
        pane.setLocation(left, top);
        pane.setSize(330, 300);
        pane.setLayout(new GridLayout(11, 11));
        for (int i = 0; i < usu.length; i++) {
            for (int j = 0; j < usu.length; j++) {
                int ii = i, jj = j;
                usu[i][j] = new Cell();
                JButton jb = new JButton();
                Boolean obj = false;
                usu[i][j].setButton(jb);
                usu[i][j].getButton().setSize(30, 30);
                usu[i][j].setBooleano(obj);
                usu[i][j].setBooleanTiro(obj);
                usu[i][j].getButton().addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (usu == user) {
                            if (usu[ii][jj].getBooleano() == false) {
                                pintabarcos(usu, ii, jj, comando);
                            } else {
                                JOptionPane.showMessageDialog(null, "Posição já se encontra ocupada.");
                            }
                        } else if ((usu == oponent) && (pronto == true)) {
                            batalhar(usu, ii, jj);
                        }
                    }
                }
                );
                pane.add(usu[i][j].getButton());
            }
        }
        getContentPane().add(pane);
    }

    private void pintabarcos(Cell[][] usu, int ii, int jj, int cmd) {
        int larg = jj, cont = 0, alt = ii;
        Boolean pin = true;

        if (comando == 1) { //submarino
            if (quant[0] < 2) {
                quant[0] = (quant[0] + 1);
                usu[ii][jj].setBooleano(pin);
                usu[ii][jj].getButton().setBackground(c1);
            } else {
                JOptionPane.showMessageDialog(null, "A quantidade máxima de barcos de um mesmo tipo no tabuleiro é duas.");
            }
            ////////////////////////////////////
        } else if (comando == 2) { //barco 2

            if ((or == false && larg < 10 && whileVerif(usu, ii, jj) == false) || (or == true && alt < 10 && whileVerif(usu, ii, jj) == false)) {
                if (quant[1] < 2) {
                    while (cont <= 2) {
                        if (cont == 2) {
                            break;
                        } else {
                            usu[alt][larg].setBooleano(pin);
                            usu[alt][larg].getButton().setBackground(c2);
                            cont++;
                            if (or == false) {
                                larg++;
                            } else {
                                alt++;
                            }
                        }
                    }
                    quant[1] = (quant[1] + 1);
                } else {
                    JOptionPane.showMessageDialog(null, "A quantidade máxima de barcos de um mesmo tipo no tabuleiro é duas.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Não é possivel posicionar esse barco aqui.");
            }
            ////////////////////////////////////
        } else if (comando == 3) { //barco 3
            if ((whileVerif(usu, ii, jj) == false) && ((or == false && larg < 9) || (or == true && alt < 9))) {
                if (quant[2] < 2) {
                    while (cont <= 3) {
                        if (cont == 3) {
                            break;
                        } else {
                            usu[alt][larg].setBooleano(pin);
                            usu[alt][larg].getButton().setBackground(c3);
                            cont++;
                            if (or == false) {
                                larg++;
                            } else {
                                alt++;
                            }
                        }
                    }
                    quant[2] = (quant[2] + 1);
                } else {
                    JOptionPane.showMessageDialog(null, "A quantidade máxima de barcos de um mesmo tipo no tabuleiro é duas.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Não é possivel posicionar esse barco aqui.");
            }
            //////////////////////////////////// 
        } else if (comando == 4) { //barco 4

            if ((whileVerif(usu, ii, jj) == false) && ((or == false && larg < 8) || (or == true && alt < 8))) {
                if (quant[3] < 2) {
                    while (cont <= 4) {
                        if (cont == 4) {
                            break;
                        } else {
                            usu[alt][larg].setBooleano(pin);
                            usu[alt][larg].getButton().setBackground(c4);
                            cont++;
                            if (or == false) {
                                larg++;
                            } else {
                                alt++;
                            }
                        }
                    }
                    quant[3] = (quant[3] + 1);
                } else {
                    JOptionPane.showMessageDialog(null, "A quantidade máxima de barcos de um mesmo tipo no tabuleiro é duas.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Não é possivel posicionar esse barco aqui.");
            }
            ////////////////////////////////////
        } else if (comando == 5) { //barco 5
            if ((whileVerif(usu, ii, jj) == false) && ((or == false && larg < 7) || (or == true && alt < 7))) {
                if (quant[4] < 2) {
                    while (cont <= 5) {
                        if (cont == 5) {
                            break;
                        } else {
                            usu[alt][larg].setBooleano(pin);
                            usu[alt][larg].getButton().setBackground(c5);
                            cont++;
                            if (or == false) {
                                larg++;
                            } else {
                                alt++;
                            }
                        }
                    }
                    quant[4] = (quant[4] + 1);
                } else {
                    JOptionPane.showMessageDialog(null, "A quantidade máxima de barcos de um mesmo tipo no tabuleiro é duas.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Não é possivel posicionar esse barco aqui.");
            }
        }
    }

    private Boolean whileVerif(Cell[][] usu, int altura, int largura) {
        int valor = 0, vlr2 = 0, alt = altura, larg = largura;
        Boolean verd = false;
        if (comando == 2) {valor = 10;vlr2 = 2;} 
        else if (comando == 3) {valor = 9;vlr2 = 3;} 
        else if (comando == 4) {valor = 8;vlr2 = 4;} 
        else if (comando == 5) {valor = 7;vlr2 = 5;}

        if ((or == false && larg < valor) || (or == true && alt < valor)) {
            
            int cont = 0;
            
            while (cont <= vlr2) {
                if (cont == vlr2) {
                    break;
                } else {
                    if (usu[alt][larg].getBooleano() == true) {
                        verd = true;
                        break;
                    }
                    cont++;
                    if (or == false){larg++;} 
                    else {alt++;}
                }
            }
            alt = altura;
            larg = largura;
            cont = 0;
            
        }
        if (verd == true) {
            return true;
        }
        return false;
    }

    private void batalhar(Cell[][] usu, int ii, int jj) {
        oponent[ii][jj].setBooleano(user[ii][jj].getBooleano()); // será apagado futuramente

        Boolean obj = true;

        if (usu[ii][jj].getBooleanTiro() == false) {
            if (usu[ii][jj].getBooleano() == true) {
                misera = (misera + 1);
                usu[ii][jj].setBooleanTiro(obj);
                usu[ii][jj].getButton().setBackground(tem);
                JOptionPane.showMessageDialog(null, "Acertou miseravi");
                if (misera == 30) {
                    int resp;
                    resp = JOptionPane.showConfirmDialog(null, "Tu ganhou miserávi, deseja sair do jogo?");
                    if (resp == JOptionPane.YES_OPTION) {
                        super.dispose();
                    }
                }

            } else {
                JOptionPane.showMessageDialog(null, "Errrouuu");
                usu[ii][jj].setBooleanTiro(obj);
                usu[ii][jj].getButton().setBackground(temn);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Você já atirou aqui.");
        }
    }

    private void iniciarContagem() {
        int vel = 1000;
        ActionListener action = new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                seg--;

                if (seg == 59) {
                    updates.append("[CRONOMETRO] 60 segundos restantes para posicionar os barcos \n");
                } else if (seg == 50) {
                    updates.append("[CRONOMETRO] 50 segundos restantes para posicionar os barcos \n");
                } else if (seg == 40) {
                    updates.append("[CRONOMETRO] 40 segundos restantes para posicionar os barcos \n");
                } else if (seg == 30) {
                    updates.append("[CRONOMETRO] 30 segundos restantes para posicionar os barcos \n");
                } else if (seg == 20) {
                    updates.append("[CRONOMETRO] 20 segundos restantes para posicionar os barcos \n");
                } else if (seg == 10) {
                    updates.append("[CRONOMETRO] 10 segundos restantes para posicionar os barcos \n");
                } else if (seg == 6) {
                    updates.append("[CONSOLE] O jogo será inicado em 5 segundos. \n");
                }

                if ((seg == 0) && ((quant[0] != 2) || (quant[1] != 2) || (quant[2] != 2) || (quant[3] != 2) || (quant[4] != 2))) {
                    jLabelCron.setText("00 : 00: 00");
                    seg = 0;
                    int resp;
                    timer.stop();
                    resp = JOptionPane.showConfirmDialog(null, "Você não posicionou todos os barcos, isso será entendido como desistência. Deseja sair do jogo?", null, OK_CANCEL_OPTION);
                    if (resp == JOptionPane.OK_OPTION) {
                        dispose();
                    }
                }
                if ((pronto == true) || ((seg == 0) && ((quant[0] == 2) && (quant[1] == 2) && (quant[2] == 2) && (quant[3] == 2) && (quant[4] == 2)))) {
                    jLabelCron.setText("00 : 00: 00");
                    updates.append("[CONSOLE] Tenha um bom jogo! \n");
                    seg = 0;
                    timer.stop();
                    pronto = true;

                }
                String segun = seg <= 9 ? "0" + seg : seg + "";
                jLabelCron.setText("00 : 00 : " + segun);
            }
        };
        this.timer = new Timer(vel, action);
        this.timer.start();
    }

    private void layoutPIN() { // ;;;
        //5
        jButtonPA5.setBackground(c5);
        jButtonPA4.setBackground(c5);
        jButtonPA3.setBackground(c5);
        jButtonPA2.setBackground(c5);
        jButtonPA1.setBackground(c5);
        //4
        jButtonPA7.setBackground(c4);
        jButtonPA8.setBackground(c4);
        jButtonPA9.setBackground(c4);
        jButtonPA10.setBackground(c4);
        //3
        jButtonPA13.setBackground(c3);
        jButtonPA14.setBackground(c3);
        jButtonPA15.setBackground(c3);
        //2
        jButtonPA19.setBackground(c2);
        jButtonPA20.setBackground(c2);
        //1
        jButtonPA25.setBackground(c1);

        jPanelTempo.setSize(166, 49);

        //textarea atualizações
        updates.setLineWrap(true);
        updates.setWrapStyleWord(true);
        updates.setEditable(false);

        scroll = new JScrollPane(updates);
        scroll.setSize(820, 138);
        scroll.setVisible(true);
        scroll.setLocation(50, 420);
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interface.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton JToggleVertical;
    private javax.swing.JButton jButtonDesistir;
    public javax.swing.JButton jButtonPA1;
    public javax.swing.JButton jButtonPA10;
    public javax.swing.JButton jButtonPA13;
    public javax.swing.JButton jButtonPA14;
    public javax.swing.JButton jButtonPA15;
    public javax.swing.JButton jButtonPA19;
    public javax.swing.JButton jButtonPA2;
    public javax.swing.JButton jButtonPA20;
    public javax.swing.JButton jButtonPA25;
    public javax.swing.JButton jButtonPA3;
    public javax.swing.JButton jButtonPA4;
    public javax.swing.JButton jButtonPA5;
    public javax.swing.JButton jButtonPA7;
    public javax.swing.JButton jButtonPA8;
    public javax.swing.JButton jButtonPA9;
    private javax.swing.JButton jButtonPronto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabelCron;
    private javax.swing.JPanel jPanelTempo;
    // End of variables declaration//GEN-END:variables
}
