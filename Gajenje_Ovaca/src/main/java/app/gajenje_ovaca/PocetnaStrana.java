package app.gajenje_ovaca;

import app.database.DBServer;
import app.database.DataBase;
import app.gajenje_ovaca.gui.dnevnik.Dnevnik;
import app.logic.Logic;
import java.awt.Color;
import java.awt.GridLayout;


public class PocetnaStrana extends javax.swing.JFrame {
    Logic logic;
    
    public PocetnaStrana() {
        DataBase dataBase = new DataBase(true);
        logic = new Logic(dataBase);
        getContentPane().setBackground(Color.white);
        setTitle("Farma ovaca");
        initComponents();
     mainPanel.setLayout(new GridLayout());
     mainPanel.add(new Dnevnik(logic));
     mainPanel.revalidate();
     repaint();
     setVisible(true);
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        dnevnikButton = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        ovceButton = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jToolBar1.setRollover(true);

        dnevnikButton.setFont(new java.awt.Font("Monaco", 0, 14)); // NOI18N
        dnevnikButton.setText("Дневник рада");
        dnevnikButton.setBorderPainted(false);
        dnevnikButton.setContentAreaFilled(false);
        dnevnikButton.setFocusPainted(false);
        dnevnikButton.setFocusable(false);
        dnevnikButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        dnevnikButton.setMargin(new java.awt.Insets(2, 10, 2, 10));
        dnevnikButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        dnevnikButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dnevnikButtonMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dnevnikButtonMouseEntered(evt);
            }
        });
        dnevnikButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dnevnikButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(dnevnikButton);

        jButton10.setFont(new java.awt.Font("Monaco", 0, 14)); // NOI18N
        jButton10.setText("Почетна страна");
        jButton10.setBorderPainted(false);
        jButton10.setContentAreaFilled(false);
        jButton10.setFocusPainted(false);
        jButton10.setFocusable(false);
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton10.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jButton10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton10MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton10MouseEntered(evt);
            }
        });
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton10);

        ovceButton.setFont(new java.awt.Font("Monaco", 0, 14)); // NOI18N
        ovceButton.setText("Овце");
        ovceButton.setBorderPainted(false);
        ovceButton.setContentAreaFilled(false);
        ovceButton.setFocusPainted(false);
        ovceButton.setFocusable(false);
        ovceButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ovceButton.setMargin(new java.awt.Insets(2, 10, 2, 10));
        ovceButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ovceButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ovceButtonMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ovceButtonMouseEntered(evt);
            }
        });
        ovceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ovceButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(ovceButton);

        jButton18.setFont(new java.awt.Font("Monaco", 0, 14)); // NOI18N
        jButton18.setText("Статистике");
        jButton18.setBorderPainted(false);
        jButton18.setContentAreaFilled(false);
        jButton18.setFocusPainted(false);
        jButton18.setFocusable(false);
        jButton18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton18.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jButton18.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton18MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton18MouseEntered(evt);
            }
        });
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton18);

        jButton19.setFont(new java.awt.Font("Monaco", 0, 14)); // NOI18N
        jButton19.setText("Направи извештај");
        jButton19.setBorderPainted(false);
        jButton19.setContentAreaFilled(false);
        jButton19.setFocusPainted(false);
        jButton19.setFocusable(false);
        jButton19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton19.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jButton19.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton19MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton19MouseEntered(evt);
            }
        });
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton19);

        jButton20.setFont(new java.awt.Font("Monaco", 0, 14)); // NOI18N
        jButton20.setText("Активности");
        jButton20.setBorderPainted(false);
        jButton20.setContentAreaFilled(false);
        jButton20.setFocusPainted(false);
        jButton20.setFocusable(false);
        jButton20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton20.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jButton20.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton20MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton20MouseEntered(evt);
            }
        });
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton20);

        jButton21.setFont(new java.awt.Font("Monaco", 0, 14)); // NOI18N
        jButton21.setText("Помоћ");
        jButton21.setBorderPainted(false);
        jButton21.setContentAreaFilled(false);
        jButton21.setFocusPainted(false);
        jButton21.setFocusable(false);
        jButton21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton21.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jButton21.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton21MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton21MouseEntered(evt);
            }
        });
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton21);

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        org.jdesktop.layout.GroupLayout mainPanelLayout = new org.jdesktop.layout.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 718, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jToolBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 785, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(345, Short.MAX_VALUE))
                    .add(mainPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jToolBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(mainPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void jButton10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseExited
          jButton10.setForeground(Color.black);
    }//GEN-LAST:event_jButton10MouseExited

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseEntered
        jButton10.setForeground(Color.BLUE);
    }//GEN-LAST:event_jButton10MouseEntered

    private void ovceButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ovceButtonMouseExited
        ovceButton.setForeground(Color.black);
    }//GEN-LAST:event_ovceButtonMouseExited

    private void ovceButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ovceButtonMouseEntered
        ovceButton.setForeground(Color.BLUE);
    }//GEN-LAST:event_ovceButtonMouseEntered

    private void ovceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ovceButtonActionPerformed
        mainPanel.setLayout(new GridLayout());
        mainPanel.removeAll();
        mainPanel.add(new Podaci_ovaca(mainPanel, logic));
        mainPanel.revalidate();
        repaint();
    }//GEN-LAST:event_ovceButtonActionPerformed

    private void jButton18MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton18MouseExited
        jButton18.setForeground(Color.BLACK);
    }//GEN-LAST:event_jButton18MouseExited

    private void jButton18MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton18MouseEntered
        jButton18.setForeground(Color.BLUE);
    }//GEN-LAST:event_jButton18MouseEntered

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton19MouseExited
        jButton19.setForeground(Color.BLACK);
    }//GEN-LAST:event_jButton19MouseExited

    private void jButton19MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton19MouseEntered
        jButton19.setForeground(Color.BLUE);
    }//GEN-LAST:event_jButton19MouseEntered

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton20MouseExited
        jButton20.setForeground(Color.BLACK);
    }//GEN-LAST:event_jButton20MouseExited

    private void jButton20MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton20MouseEntered
        jButton20.setForeground(Color.BLUE);
    }//GEN-LAST:event_jButton20MouseEntered

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton20ActionPerformed

    private void dnevnikButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dnevnikButtonMouseExited
        dnevnikButton.setForeground(Color.black);
    }//GEN-LAST:event_dnevnikButtonMouseExited

    private void dnevnikButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dnevnikButtonMouseEntered
        dnevnikButton.setForeground(Color.BLUE);
    }//GEN-LAST:event_dnevnikButtonMouseEntered

    private void dnevnikButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dnevnikButtonActionPerformed
     mainPanel.setLayout(new GridLayout());
     mainPanel.removeAll();
     mainPanel.add(new Dnevnik(logic));mainPanel.revalidate();
     repaint();
     setVisible(true);
    }//GEN-LAST:event_dnevnikButtonActionPerformed

    private void jButton21MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton21MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton21MouseExited

    private void jButton21MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton21MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton21MouseEntered

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton21ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PocetnaStrana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PocetnaStrana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PocetnaStrana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PocetnaStrana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PocetnaStrana().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton dnevnikButton;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton ovceButton;
    // End of variables declaration//GEN-END:variables
}
