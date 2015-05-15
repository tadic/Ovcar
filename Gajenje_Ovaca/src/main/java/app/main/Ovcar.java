package app.main;

import app.config.DataBase;
import app.editPanels.JagnjenjaPanel;
import app.logic.Logic;
import app.mainPanels.AktivnostiPanel;
import app.mainPanels.Dnevnik;
import app.mainPanels.Merenja;
import app.mainPanels.NapraviIzvestaj;
import app.mainPanels.PlanPripusta;
import app.mainPanels.Podaci_ovaca_opsti;
import app.mainPanels.Podaci_ovaca_statistika;
import app.model.Aktivnost;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.plaf.basic.BasicComboPopup;


public class Ovcar extends javax.swing.JFrame {
    Logic logic;
    
    public Ovcar() {
        login();
        
        DataBase dataBase = new DataBase(false);
        logic = new Logic(dataBase);
        
        init();
        
    }
    
    private void init(){
        getContentPane().setBackground(Color.white);
        setTitle("Farma ovaca");
        initComponents();
        mainPanel.setLayout(new GridLayout());
        mainPanel.add(new Dnevnik(logic, mainPanel));
        mainPanel.revalidate();
        jToolBar1.setOpaque(true);

        Object child = jComboBox1.getAccessibleContext().getAccessibleChild(0);
        BasicComboPopup popup = (BasicComboPopup)child;
        JList list = popup.getList();
        list.setSelectionBackground(Color.RED);
        repaint();
        setVisible(true);
    }
    private void login(){
        JPasswordField pf = new JPasswordField();
        while (true){
        int okCxl = JOptionPane.showConfirmDialog(null, pf, "Lozinka", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (okCxl == JOptionPane.OK_OPTION) {
              String password = new String(pf.getPassword());
              if (password.equals("r")){
                  break;
              } else {
                  pf.setText("");
              }
            } else {
                System.exit(0);
            }
        }
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

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        jPopupMenu4 = new javax.swing.JPopupMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jComboBox1 = new javax.swing.JComboBox();
        dnevnikButton = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jPlanPripustaButton = new javax.swing.JButton();
        jLinijeButton1 = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();

        jMenu1.setText("File");
        jMenuBar2.add(jMenu1);

        jMenu3.setText("Edit");
        jMenuBar2.add(jMenu3);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 750));

        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 750));

        jToolBar1.setBackground(new java.awt.Color(51, 51, 51));
        jToolBar1.setFloatable(false);
        jToolBar1.setForeground(new java.awt.Color(153, 0, 51));
        jToolBar1.setRollover(true);
        jToolBar1.setMinimumSize(new java.awt.Dimension(500, 31));
        jToolBar1.setPreferredSize(new java.awt.Dimension(500, 31));

        jComboBox1.setBackground(new java.awt.Color(255, 0, 0));
        jComboBox1.setFont(new java.awt.Font("Monaco", 0, 14)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(255, 0, 0));
        jComboBox1.setMaximumRowCount(3);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ovce - opšti podatci", "Ovce - statistika" }));
        jComboBox1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jComboBox1.setPreferredSize(new java.awt.Dimension(200, 46));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jComboBox1);

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

        jButton18.setBackground(new java.awt.Color(0, 204, 153));
        jButton18.setFont(new java.awt.Font("Monaco", 0, 14)); // NOI18N
        jButton18.setForeground(new java.awt.Color(212, 212, 212));
        jButton18.setText("Merenja");
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

        jPlanPripustaButton.setFont(new java.awt.Font("Monaco", 0, 14)); // NOI18N
        jPlanPripustaButton.setForeground(new java.awt.Color(212, 212, 212));
        jPlanPripustaButton.setText("Plan pripusta");
        jPlanPripustaButton.setBorderPainted(false);
        jPlanPripustaButton.setContentAreaFilled(false);
        jPlanPripustaButton.setFocusPainted(false);
        jPlanPripustaButton.setFocusable(false);
        jPlanPripustaButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPlanPripustaButton.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jPlanPripustaButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPlanPripustaButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPlanPripustaButtonMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPlanPripustaButtonMouseEntered(evt);
            }
        });
        jPlanPripustaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPlanPripustaButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(jPlanPripustaButton);

        jLinijeButton1.setFont(new java.awt.Font("Monaco", 0, 14)); // NOI18N
        jLinijeButton1.setForeground(new java.awt.Color(212, 212, 212));
        jLinijeButton1.setText("Linije");
        jLinijeButton1.setBorderPainted(false);
        jLinijeButton1.setContentAreaFilled(false);
        jLinijeButton1.setFocusPainted(false);
        jLinijeButton1.setFocusable(false);
        jLinijeButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLinijeButton1.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jLinijeButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLinijeButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLinijeButton1MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLinijeButton1MouseEntered(evt);
            }
        });
        jLinijeButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLinijeButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jLinijeButton1);

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setPreferredSize(new java.awt.Dimension(1305, 700));

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 753, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1209, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 1209, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1213, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void jButton18MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton18MouseExited
        jButton18.setForeground(new Color(212,212,212));
    }//GEN-LAST:event_jButton18MouseExited

    private void jButton18MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton18MouseEntered
        jButton18.setForeground(Color.white);
    }//GEN-LAST:event_jButton18MouseEntered

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
            mainPanel.setLayout(new GridLayout());
            mainPanel.removeAll();
            mainPanel.add(new Merenja(mainPanel, logic));
            mainPanel.revalidate();
            repaint();
            setVisible(true);  
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton19MouseExited
        jButton19.setForeground(new Color(212,212,212));
    }//GEN-LAST:event_jButton19MouseExited

    private void jButton19MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton19MouseEntered
        jButton19.setForeground(Color.white);
    }//GEN-LAST:event_jButton19MouseEntered

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
     mainPanel.setLayout(new GridLayout());
     mainPanel.removeAll();
     mainPanel.add(new NapraviIzvestaj(logic, mainPanel));
     mainPanel.revalidate();
     repaint();
     setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton20MouseExited
        jButton20.setForeground(new Color(212,212,212));
    }//GEN-LAST:event_jButton20MouseExited

    private void jButton20MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton20MouseEntered
        jButton20.setForeground(Color.white);
    }//GEN-LAST:event_jButton20MouseEntered

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        mainPanel.setLayout(new GridLayout());
        mainPanel.removeAll();
        mainPanel.add(new AktivnostiPanel(mainPanel, logic));
        mainPanel.revalidate();
        repaint();
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

    private void jPlanPripustaButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPlanPripustaButtonMouseExited
        jPlanPripustaButton.setForeground(new Color(212,212,212));
    }//GEN-LAST:event_jPlanPripustaButtonMouseExited

    private void jPlanPripustaButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPlanPripustaButtonMouseEntered
        jPlanPripustaButton.setForeground(Color.white);
    }//GEN-LAST:event_jPlanPripustaButtonMouseEntered

    private void jPlanPripustaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPlanPripustaButtonActionPerformed
        mainPanel.setLayout(new GridLayout());
        mainPanel.removeAll();
        mainPanel.add(new PlanPripusta(mainPanel, logic));
        mainPanel.revalidate();
        repaint();
        setVisible(true);
    }//GEN-LAST:event_jPlanPripustaButtonActionPerformed

    private void jLinijeButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLinijeButton1MouseExited
       jPlanPripustaButton.setForeground(new Color(212,212,212));
    }//GEN-LAST:event_jLinijeButton1MouseExited

    private void jLinijeButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLinijeButton1MouseEntered
        jPlanPripustaButton.setForeground(Color.white);
    }//GEN-LAST:event_jLinijeButton1MouseEntered

    private void jLinijeButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLinijeButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLinijeButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        mainPanel.setLayout(new GridLayout());
        mainPanel.removeAll();
        if (jComboBox1.getSelectedIndex()==1){
            mainPanel.add(new Podaci_ovaca_statistika(mainPanel, logic));
        } else {
           mainPanel.add(new Podaci_ovaca_opsti(mainPanel, logic));
        }
        mainPanel.revalidate();
        repaint(); 
    }//GEN-LAST:event_jComboBox1ActionPerformed

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
            java.util.logging.Logger.getLogger(Ovcar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ovcar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ovcar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ovcar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ovcar().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton dnevnikButton;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton20;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JButton jLinijeButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jPlanPripustaButton;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JPopupMenu jPopupMenu4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}
