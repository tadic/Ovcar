/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mainPanels;

import app.fragments.DnevniPanel;
import app.fragments.Kalendar;
import app.fragments.SedmicnaLista;
import app.logic.Logic;
import app.model.VrsteAktivnosti;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;

/**
 *
 * @author ivantadic
 */
public class Dnevnik extends javax.swing.JPanel {
    private Logic logic;
    private JPanel mainPanel;
    
    private DnevniPanel dnevniPanel;
    private SedmicnaLista sedmicnaLista; 
    private Kalendar kalendar;
   
    public Dnevnik(Logic logic, JPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.logic = logic;
        initComponents();
        initTimePanels();
        
        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
    }
    
    private void initTimePanels(){
        dnevniPanel = new DnevniPanel(this, logic, mainPanel);
        kalendar = new Kalendar(this, logic);
        sedmicnaLista = new SedmicnaLista(this, logic); 
        kalendar.setUpKalendar(logic.getSelectedDay());
        
       // jScrollPane1.setPreferredSize(new Dimension(500,500));
        
        jScrollPane1.setViewportView(dnevniPanel);
        jScrollPane1.getVerticalScrollBar().setValue(5000);
        jScrollPane2.setViewportView(sedmicnaLista);
        
        jNazivAktivnosti.setModel(new DefaultComboBoxModel(sveVrsteAktivnosti().toArray()));
        jPanel1.setLayout(new FlowLayout());
        jPanel1.add(kalendar);
        jPanel1.revalidate();
       repaint();
       setVisible(true);
    }
     private ArrayList<String> sveVrsteAktivnosti(){
       ArrayList<String> list = new ArrayList<String>();
       for (VrsteAktivnosti vr:logic.getTypesOfActivities()){
          // System.out.println("Usao ");
           list.add(vr.getName());
       }
       return list;
    }
        
    public void setDate(){
        kalendar.setDate();
        dnevniPanel.setDate();
        sedmicnaLista.setDate(logic.getSelectedDay());
        dayLabel.setText(""+logic.getSelectedDay().get(Calendar.DAY_OF_MONTH));
        monthLabel.setText(kalendar.getMonthName(logic.getSelectedDay().get(Calendar.MONTH)));
        punDatumLabel.setText(kalendar.selectedDateToString());
    }
    
    public void setUpSedmicnaLista(){
        sedmicnaLista.setDate(logic.getSelectedDay());
    }
    public VrsteAktivnosti getChoosenActivityType(){
        return logic.getVrstaAktivnosti(jNazivAktivnosti.getSelectedItem().toString());
    }
    void setUpDayLabel(int day) {
        dayLabel.setText(""+day);
    }

    void setUpMonthLabel(String monthName) {
        monthLabel.setText(monthName);
    }
    void setUpPunDatumLabel(String fullDate){
        punDatumLabel.setText(fullDate);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        dayLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        monthLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        punDatumLabel = new javax.swing.JLabel();
        jNazivAktivnosti = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();

        jScrollPane1.setVerifyInputWhenFocusTarget(false);
        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        dayLabel.setFont(new java.awt.Font("Noteworthy", 0, 98)); // NOI18N
        dayLabel.setForeground(new java.awt.Color(0, 153, 153));
        dayLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dayLabel.setText("30");
        dayLabel.setAlignmentX(10.0F);
        dayLabel.setAlignmentY(10.0F);
        dayLabel.setIconTextGap(0);
        dayLabel.setMaximumSize(new java.awt.Dimension(138, 109));
        dayLabel.setMinimumSize(new java.awt.Dimension(138, 109));
        dayLabel.setPreferredSize(new java.awt.Dimension(138, 109));
        dayLabel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jPanel1.setBackground(new java.awt.Color(51, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));
        jPanel1.setOpaque(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 229, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jButton1.setFont(new java.awt.Font("Noteworthy", 1, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 153, 153));
        jButton1.setText("Danas");
        jButton1.setAlignmentY(0.0F);
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton1.setMaximumSize(new java.awt.Dimension(79, 50));
        jButton1.setPreferredSize(new java.awt.Dimension(79, 50));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(204, 0, 0));
        jButton2.setText(">");
        jButton2.setFocusPainted(false);
        jButton2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton2.setMinimumSize(new java.awt.Dimension(25, 25));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(204, 0, 0));
        jButton3.setText("<");
        jButton3.setFocusPainted(false);
        jButton3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        monthLabel.setFont(new java.awt.Font("Noteworthy", 1, 28)); // NOI18N
        monthLabel.setForeground(new java.awt.Color(153, 0, 0));
        monthLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        monthLabel.setText("Септембар");
        monthLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        monthLabel.setAlignmentY(0.0F);
        monthLabel.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        jLabel1.setFont(new java.awt.Font("Noteworthy", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("Dnevnik rada");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel1.setAlignmentY(3.0F);
        jLabel1.setMaximumSize(new java.awt.Dimension(219, 40));
        jLabel1.setMinimumSize(new java.awt.Dimension(219, 40));
        jLabel1.setPreferredSize(new java.awt.Dimension(219, 40));
        jLabel1.setSize(new java.awt.Dimension(40, 16));
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        punDatumLabel.setFont(new java.awt.Font("Noteworthy", 2, 16)); // NOI18N
        punDatumLabel.setForeground(new java.awt.Color(102, 0, 0));
        punDatumLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        punDatumLabel.setText("четвртак, 10.септембар 2013");

        jNazivAktivnosti.setBackground(new java.awt.Color(255, 255, 255));
        jNazivAktivnosti.setFont(new java.awt.Font("Monaco", 0, 13)); // NOI18N
        jNazivAktivnosti.setForeground(new java.awt.Color(0, 102, 102));
        jNazivAktivnosti.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1" }));
        jNazivAktivnosti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNazivAktivnostiActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Monaco", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 0, 0));
        jLabel2.setText("nova aktivnost:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(monthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(dayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addGap(95, 95, 95)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(punDatumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jNazivAktivnosti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(5, 5, 5)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(punDatumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addComponent(jNazivAktivnosti, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(monthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2)))
                .addGap(21, 21, 21))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        logic.setToday();
        setDate();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        logic.increaseSelectedDay();
        setDate();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        logic.decreaseSelectedDate();
        setDate();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jNazivAktivnostiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNazivAktivnostiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jNazivAktivnostiActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dayLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JComboBox jNazivAktivnosti;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel monthLabel;
    private javax.swing.JLabel punDatumLabel;
    // End of variables declaration//GEN-END:variables
}
