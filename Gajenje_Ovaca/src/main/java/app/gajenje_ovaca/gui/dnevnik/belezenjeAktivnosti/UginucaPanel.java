/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.gajenje_ovaca.gui.dnevnik.belezenjeAktivnosti;

import app.gajenje_ovaca.gui.dnevnik.Dnevnik;
import app.logic.Logic;
import app.model.Aktivnost;
import app.model.Uginuce;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders;

/**
 *
 * @author ivantadic
 */
public class UginucaPanel extends javax.swing.JPanel {

private Logic logic;
private Aktivnost aktivnost;
private JPanel mainPanel;


    public UginucaPanel(JPanel mainP, Logic logic, Aktivnost aktivnost) {
        super();
        this.aktivnost = aktivnost;
        this.mainPanel = mainP;
        this.logic = logic;
        initComponents();
        setOpaque(true);
        setPanel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jColorLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jNapomena = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLokacija = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jSnimiButton = new javax.swing.JButton();
        nabavkaPanel = new javax.swing.JPanel();
        jGrlo = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jRazlog = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jNameOfActivity = new javax.swing.JLabel();

        jColorLabel.setBackground(new java.awt.Color(102, 255, 102));
        jColorLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jColorLabel.setOpaque(true);

        jNapomena.setColumns(20);
        jNapomena.setForeground(new java.awt.Color(51, 51, 0));
        jNapomena.setLineWrap(true);
        jNapomena.setRows(5);
        jNapomena.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 0), 1, true));
        jNapomena.setOpaque(false);
        jScrollPane1.setViewportView(jNapomena);

        jLabel5.setFont(new java.awt.Font("Monaco", 1, 18)); // NOI18N
        jLabel5.setText("Napomena");

        jLokacija.setFont(new java.awt.Font("Monaco", 0, 14)); // NOI18N
        jLokacija.setForeground(new java.awt.Color(51, 51, 0));
        jLokacija.setText("Farma");
        jLokacija.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 0), 1, true));
        jLokacija.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLokacijaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Monaco", 1, 18)); // NOI18N
        jLabel3.setText("Lokacija:");

        jButton2.setFont(new java.awt.Font("Monaco", 1, 18)); // NOI18N
        jButton2.setText("Otkaži");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jSnimiButton.setBackground(new java.awt.Color(153, 255, 255));
        jSnimiButton.setFont(new java.awt.Font("Monaco", 1, 18)); // NOI18N
        jSnimiButton.setText("Snimi");
        jSnimiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSnimiButtonActionPerformed(evt);
            }
        });

        nabavkaPanel.setBackground(new java.awt.Color(255, 255, 255));
        nabavkaPanel.setOpaque(false);

        jGrlo.setEditable(true);
        jGrlo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "jedan", "dva", "tri", "cetri" }));
        jGrlo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jGrloActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel14.setText("Uginulo grlo:");

        jLabel15.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel15.setText("Razlog:");

        javax.swing.GroupLayout nabavkaPanelLayout = new javax.swing.GroupLayout(nabavkaPanel);
        nabavkaPanel.setLayout(nabavkaPanelLayout);
        nabavkaPanelLayout.setHorizontalGroup(
            nabavkaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nabavkaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nabavkaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(nabavkaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jGrlo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRazlog, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(405, Short.MAX_VALUE))
        );
        nabavkaPanelLayout.setVerticalGroup(
            nabavkaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, nabavkaPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(nabavkaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jGrlo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(nabavkaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRazlog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel11.setText("_________________________________________________");

        jNameOfActivity.setFont(new java.awt.Font("Monaco", 1, 24)); // NOI18N
        jNameOfActivity.setForeground(new java.awt.Color(153, 0, 0));
        jNameOfActivity.setText("Uginuce");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jNameOfActivity, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLokacija, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jSnimiButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(28, 28, 28))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1062, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nabavkaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jNameOfActivity, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLokacija, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(0, 0, 0)
                .addComponent(nabavkaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSnimiButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    private void setPanel(){
        jGrlo.setModel(new DefaultComboBoxModel(logic.getSvaZivaGrla().toArray()));
        jNameOfActivity.setText(aktivnost.getVrstaAktivnosti().getName());
        jColorLabel.setBackground(new Color(aktivnost.getVrstaAktivnosti().getColor()));
        jLokacija.setText(aktivnost.getLokacija());
        jNapomena.setText(aktivnost.getNapomena());
        fillUginuce();
        
    }
    private void fillUginuce(){
        if (aktivnost.getUginuce()!=null){
            jGrlo.setSelectedItem(aktivnost.getUginuce().getO().getOznaka());
            jRazlog.setText(aktivnost.getUginuce().getRazlog());
        }
    }

    private void jLokacijaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLokacijaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLokacijaActionPerformed

 
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
            mainPanel.removeAll();
            mainPanel.add(new Dnevnik(logic, mainPanel));
            mainPanel.revalidate();
            repaint();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jSnimiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSnimiButtonActionPerformed
        if (pickDataFromForm()){
            logic.saveActivity(aktivnost);
            mainPanel.removeAll();
            mainPanel.add(new Dnevnik(logic, mainPanel));
            mainPanel.revalidate();
            repaint();
        }
    }//GEN-LAST:event_jSnimiButtonActionPerformed


    private boolean pickUginuce(){
        String oznaka = jGrlo.getSelectedItem().toString();
        Uginuce uginuce = new Uginuce();
        uginuce.setO(logic.getOvca(oznaka));
        uginuce.setRazlog(jRazlog.getText());
        aktivnost.setUginuce(uginuce);
        return uginuce.getO()!=null;
    }


    private boolean pickDataFromForm(){
        if (!pickUginuce()){return false;}
        aktivnost.setLokacija(jLokacija.getText());
        aktivnost.setNapomena(jNapomena.getText());
        aktivnost.setBilans(createBilans());
        return true;
    }

    private void jGrloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jGrloActionPerformed

    }//GEN-LAST:event_jGrloActionPerformed
       
    private String createBilans(){
        StringBuilder sb = new StringBuilder(aktivnost.getUginuce().getO().getOznaka());
        sb.append(" - ").append(aktivnost.getUginuce().getO().getNadimak());
        return sb.toString();
    }

    
    
    @Override  
    public void paintComponent(Graphics g) {  
        super.paintComponent(g);  
        Graphics2D g2d = (Graphics2D) g;  
        int w = getWidth();  
        int h = getHeight();  
     
        g2d.setColor(Color.white);
        g2d.fillRoundRect(0, 0, w, h, 20, 20);
        g2d.setColor(Color.gray);
        g2d.drawRoundRect(0, 0, w, h, 20, 20);

        g2d.setStroke(new BasicStroke());
        Border border = new BasicBorders.MarginBorder();
        
        this.setBorder(border);
        //g2d.fillRect(0, 0, w, h);  
    }  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jColorLabel;
    private javax.swing.JComboBox jGrlo;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jLokacija;
    private javax.swing.JLabel jNameOfActivity;
    private javax.swing.JTextArea jNapomena;
    private javax.swing.JTextField jRazlog;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jSnimiButton;
    private javax.swing.JPanel nabavkaPanel;
    // End of variables declaration//GEN-END:variables
}
