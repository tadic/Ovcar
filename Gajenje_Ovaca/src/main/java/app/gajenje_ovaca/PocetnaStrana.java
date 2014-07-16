package app.gajenje_ovaca;

import app.database.DBServer;
import app.database.DataBase;
import app.gajenje_ovaca.gui.dnevnik.Dnevnik;
import app.gajenje_ovaca.gui.dnevnik.belezenjeAktivnosti.JagnjenjaPanel;
import app.logic.Logic;
import app.model.Aktivnost;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;


public class PocetnaStrana extends javax.swing.JFrame {
    Logic logic;
    
    public PocetnaStrana() {
        DataBase dataBase = new DataBase(false);
        logic = new Logic(dataBase);
        getContentPane().setBackground(Color.white);
        setTitle("Farma ovaca");
        initComponents();
     mainPanel.setLayout(new GridLayout());
     mainPanel.add(new Dnevnik(logic, mainPanel));
     mainPanel.revalidate();
     jToolBar1.setOpaque(true);
     //jToolBar1.setBackground(Color.yellow);
     repaint();
     setVisible(true);
        
    }
    
    private void setJagnjenja(Aktivnost aktivnost){
        mainPanel.setLayout(new GridLayout());
        mainPanel.removeAll();
        mainPanel.add(new JagnjenjaPanel(mainPanel, logic, aktivnost));
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        dnevnikButton = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        ovceButton = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jLinijeButton = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 750));
        jScrollPane1.setSize(new java.awt.Dimension(100, 750));

        jPanel1.setPreferredSize(new java.awt.Dimension(1331, 750));

        jToolBar1.setBackground(new java.awt.Color(51, 51, 51));
        jToolBar1.setForeground(new java.awt.Color(153, 0, 51));
        jToolBar1.setRollover(true);

        dnevnikButton.setFont(new java.awt.Font("Monaco", 0, 14)); // NOI18N
        dnevnikButton.setForeground(new java.awt.Color(212, 212, 212));
        dnevnikButton.setText("Dnevnik rada");
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
        jButton10.setForeground(new java.awt.Color(212, 212, 212));
        jButton10.setText("Početna strana");
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
        ovceButton.setForeground(new java.awt.Color(212, 212, 212));
        ovceButton.setText("Ovce");
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

        jButton18.setBackground(new java.awt.Color(0, 204, 153));
        jButton18.setFont(new java.awt.Font("Monaco", 0, 14)); // NOI18N
        jButton18.setForeground(new java.awt.Color(212, 212, 212));
        jButton18.setText("Statistike");
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
        jButton19.setForeground(new java.awt.Color(212, 212, 212));
        jButton19.setText("Napravi izveštaj");
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
        jButton20.setForeground(new java.awt.Color(212, 212, 212));
        jButton20.setText("Aktivnosti");
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

        jLinijeButton.setFont(new java.awt.Font("Monaco", 0, 14)); // NOI18N
        jLinijeButton.setForeground(new java.awt.Color(212, 212, 212));
        jLinijeButton.setText("Linije");
        jLinijeButton.setBorderPainted(false);
        jLinijeButton.setContentAreaFilled(false);
        jLinijeButton.setFocusPainted(false);
        jLinijeButton.setFocusable(false);
        jLinijeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLinijeButton.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jLinijeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLinijeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLinijeButtonMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLinijeButtonMouseEntered(evt);
            }
        });
        jLinijeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLinijeButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(jLinijeButton);

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setPreferredSize(new java.awt.Dimension(1305, 700));

        org.jdesktop.layout.GroupLayout mainPanelLayout = new org.jdesktop.layout.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 1331, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 748, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jToolBar1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1331, Short.MAX_VALUE)
            .add(mainPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1331, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jToolBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 37, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(mainPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 748, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1309, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void jButton10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseExited
          jButton10.setForeground(new Color(212,212,212));
    }//GEN-LAST:event_jButton10MouseExited

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseEntered
        jButton10.setForeground(Color.white);
    }//GEN-LAST:event_jButton10MouseEntered

    private void ovceButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ovceButtonMouseExited
        ovceButton.setForeground(new Color(212,212,212));
    }//GEN-LAST:event_ovceButtonMouseExited

    private void ovceButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ovceButtonMouseEntered
        ovceButton.setForeground(Color.white);
    }//GEN-LAST:event_ovceButtonMouseEntered

    private void ovceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ovceButtonActionPerformed
        mainPanel.setLayout(new GridLayout());
        mainPanel.removeAll();
        mainPanel.add(new Podaci_ovaca(mainPanel, logic));
        mainPanel.revalidate();
        repaint();
    }//GEN-LAST:event_ovceButtonActionPerformed

    private void jButton18MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton18MouseExited
        jButton18.setForeground(new Color(212,212,212));
    }//GEN-LAST:event_jButton18MouseExited

    private void jButton18MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton18MouseEntered
        jButton18.setForeground(Color.white);
    }//GEN-LAST:event_jButton18MouseEntered

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton19MouseExited
        jButton19.setForeground(new Color(212,212,212));
    }//GEN-LAST:event_jButton19MouseExited

    private void jButton19MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton19MouseEntered
        jButton19.setForeground(Color.white);
    }//GEN-LAST:event_jButton19MouseEntered

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton20MouseExited
        jButton20.setForeground(new Color(212,212,212));
    }//GEN-LAST:event_jButton20MouseExited

    private void jButton20MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton20MouseEntered
        jButton20.setForeground(Color.white);
    }//GEN-LAST:event_jButton20MouseEntered

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton20ActionPerformed

    private void dnevnikButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dnevnikButtonMouseExited
        dnevnikButton.setForeground(new Color(212,212,212));
    }//GEN-LAST:event_dnevnikButtonMouseExited

    private void dnevnikButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dnevnikButtonMouseEntered
        dnevnikButton.setForeground(Color.white);
    }//GEN-LAST:event_dnevnikButtonMouseEntered

    private void dnevnikButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dnevnikButtonActionPerformed
     mainPanel.setLayout(new GridLayout());
     mainPanel.removeAll();
     mainPanel.add(new Dnevnik(logic, mainPanel));mainPanel.revalidate();
     repaint();
     setVisible(true);
    }//GEN-LAST:event_dnevnikButtonActionPerformed

    private void jLinijeButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLinijeButtonMouseExited
        jLinijeButton.setForeground(new Color(212,212,212));
    }//GEN-LAST:event_jLinijeButtonMouseExited

    private void jLinijeButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLinijeButtonMouseEntered
        jLinijeButton.setForeground(Color.white);
    }//GEN-LAST:event_jLinijeButtonMouseEntered

    private void jLinijeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLinijeButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLinijeButtonActionPerformed

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
    private javax.swing.JButton jLinijeButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton ovceButton;
    // End of variables declaration//GEN-END:variables
}
