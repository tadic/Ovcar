/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.fragments;

import app.editPanels.JagnjenjaPanel;
import app.helpers.MyComboBoxModel;
import app.helpers.OvcaHelper;
import app.logic.Logic;
import app.model.Aktivnost;
import app.model.Jagnjenje;
import app.model.Ovca;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author ivantadic
 */
public class JagnjenjePanel extends javax.swing.JPanel {
private List<Jagnjenje> listaJagnjenja;
private List<Ovca> listaOvaca, listaOvnova;
private  Logic logic;
private JagnjenjaPanel parent;
    /**
     * Creates new form JagnjenjePanel
     */
    public JagnjenjePanel(List<Jagnjenje> listaJagnjenja, Logic logic, JagnjenjaPanel parent) {
        this.logic = logic;
        this.parent = parent;
        this.listaJagnjenja = listaJagnjenja;;
        initComponents();

        setPanel();
        
    }
    
    private void setPanel(){
        setComboBox();
        jScrollPane2.setVisible(false);
        jScrollPane2.getViewport().setBackground(Color.white);      
        AutoCompleteDecorator.decorate(this.jOvca);
        AutoCompleteDecorator.decorate(this.jOvan);

        fillPanel();
        this.setVisible(true);
  
    }
    private void fillPanel(){
      //  System.out.println("Velicina1 liste: "+listaJagnjenja.size());
        if (listaJagnjenja!=null && !listaJagnjenja.isEmpty()){
            DefaultTableModel tb = (DefaultTableModel) jTableJagnjaci.getModel();
            int n=1;
            
            for (Jagnjenje j: listaJagnjenja){
                tb.addRow(vectorFrom(j, n));
              //  System.err.println("Dodao, ");
                n++;
            }
             jSpinField2.setValue(listaJagnjenja.size());
        }
    }
    
    private Vector vectorFrom(Jagnjenje j, int n){
        Vector v = new Vector();
        v.add(n);
        v.add(j.isJelZivo());
        v.add(j.getSheep().getPol());
        v.add(j.getSheep().getOznaka());      
        
        v.add(j.getSheep().getProcenatR());
        v.add(j.getSheep().getOpis());
        v.add(j.getNapomena());
        v.add(j.getSheep().getTezinaNaRodjenju());
        v.add(j.getId());
        return v;
    }
    
    private void setComboBox(){
        listaOvaca = Arrays.asList(ovceZaJagnjenje());
        jOvca.setModel(new MyComboBoxModel(ovceZaJagnjenje()));
        listaOvnova = logic.listaOvnova();
        jOvan.setModel(new MyComboBoxModel(logic.listaOvnova().toArray(new Ovca[listaOvnova.size()])));
        if (listaJagnjenja!=null && !listaJagnjenja.isEmpty()){
                   Ovca ovca = listaJagnjenja.get(0).getOvca();
                   Ovca otac = listaJagnjenja.get(0).getSheep().getOtac();
                   jOvca.setSelectedItem(ovca);
                   jOvan.setSelectedItem(otac);
        }
    }
    
    private Ovca[] ovceZaJagnjenje(){
       List<Ovca> list = logic.getOvceZaJagnjenje();
       Ovca[] olist = list.toArray(new Ovca[list.size()]);
       
       return olist;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel14 = new javax.swing.JLabel();
        jOvca = new javax.swing.JComboBox<Ovca>();
        jLabel15 = new javax.swing.JLabel();
        jSpinField2 = new com.toedter.components.JSpinField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableJagnjaci = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jOvan = new javax.swing.JComboBox<Ovca>();
        jButton1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel14.setText("Ovca:");

        jOvca.setEditable(true);
        jOvca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOvcaActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel15.setText("ojagnjila");

        jSpinField2.setBackground(new java.awt.Color(255, 255, 255));
        jSpinField2.setMinimum(1);
        jSpinField2.setValue(1);
        jSpinField2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jSpinField2PropertyChange(evt);
            }
        });

        jTableJagnjaci.setAutoCreateRowSorter(true);
        jTableJagnjaci.setFont(new java.awt.Font("Monaco", 0, 14)); // NOI18N
        jTableJagnjaci.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "rbr", "jel živo", "pol", "oznaka jagnjeta", "% romanov", "opis jagnjeta", "napomena", "težina u kg", "id"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Object.class, java.lang.String.class, java.lang.Float.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableJagnjaci.setColumnSelectionAllowed(true);
        jTableJagnjaci.setRowHeight(30);
        jTableJagnjaci.setRowMargin(0);
        jTableJagnjaci.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTableJagnjaci);
        jTableJagnjaci.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jTableJagnjaci.getColumnModel().getColumn(0).setMaxWidth(36);
        jTableJagnjaci.getColumnModel().getColumn(1).setMaxWidth(50);
        jTableJagnjaci.getColumnModel().getColumn(2).setMaxWidth(120);
        jTableJagnjaci.getColumnModel().getColumn(3).setMinWidth(120);
        jTableJagnjaci.getColumnModel().getColumn(3).setPreferredWidth(120);
        jTableJagnjaci.getColumnModel().getColumn(3).setMaxWidth(140);
        jTableJagnjaci.getColumnModel().getColumn(4).setMaxWidth(80);
        jTableJagnjaci.getColumnModel().getColumn(7).setMaxWidth(90);
        jTableJagnjaci.getColumnModel().getColumn(8).setMinWidth(0);
        jTableJagnjaci.getColumnModel().getColumn(8).setPreferredWidth(15);
        jTableJagnjaci.getColumnModel().getColumn(8).setMaxWidth(5);

        jLabel16.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel16.setText("Otac");

        jOvan.setEditable(true);
        jOvan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOvanActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 204, 204));
        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel11.setText("___________________________________________________________________________________________________________________________________________________________________________________");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1266, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jOvca, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinField2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jOvan, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(jOvca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15)
                        .addComponent(jButton1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jOvan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jSpinField2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jSpinField2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jSpinField2PropertyChange
        int n = jSpinField2.getValue(); 
            if (n>0){
                DefaultTableModel model = (DefaultTableModel) jTableJagnjaci.getModel();
                if (jTableJagnjaci.getModel().getRowCount()>n){
                    model.removeRow(n);
                } else if (jTableJagnjaci.getModel().getRowCount()<n){
                    Vector row = new Vector();
                    row.add(n);
                    row.add(true);
                    row.add("ž");
                    model.addRow(row);
                }
            } 
    }//GEN-LAST:event_jSpinField2PropertyChange
//problem
    private float calculatePercentR(){
        Ovca majka;
        majka = (Ovca) jOvca.getSelectedItem();
        Ovca otac = (Ovca) jOvan.getSelectedItem();
        float percentR = 0;
        if (otac!=null && majka!=null){
            percentR = Aktivnost.round((majka.getProcenatR()+otac.getProcenatR())/2, 1);
        }    
        return percentR;
    }
    private void jOvcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOvcaActionPerformed
        DefaultTableModel tm = (DefaultTableModel) jTableJagnjaci.getModel();
        Ovca ovca = (Ovca)jOvca.getSelectedItem();
        if (ovca!=null && ovca.getParenja()!=null && !ovca.getParenja().isEmpty()){
            jOvan.setSelectedItem(ovca.getParenja().get(ovca.getParenja().size()-1).getOvan());
        }
        setPercentRToTable(tm, calculatePercentR());
    }//GEN-LAST:event_jOvcaActionPerformed

    private void setPercentRToTable(DefaultTableModel tm, float percentR){
        for (int i=0; i<tm.getRowCount(); i++){
            tm.setValueAt(percentR, i, 4);
        }
    }
    private void jOvanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOvanActionPerformed
        DefaultTableModel tm = (DefaultTableModel) jTableJagnjaci.getModel();
        setPercentRToTable(tm, calculatePercentR());
    }//GEN-LAST:event_jOvanActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        jTableJagnjaci.setVisible(true);
    }//GEN-LAST:event_formFocusGained

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost
        jScrollPane2.setVisible(false);
    }//GEN-LAST:event_formFocusLost

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
       boolean bool = jScrollPane2.isVisible();
        jScrollPane2.setVisible(!bool);
        
    }//GEN-LAST:event_formMousePressed
    
    public void smanjiSe(){
        jScrollPane2.setVisible(false);
        jButton1.setText("+");
        this.revalidate();
        this.repaint();
    }
    public void uvecajSe(){
        parent.smanjiSvePaneleJagnjenja();
        jScrollPane2.setVisible(true);
        jButton1.setText("-");
       // this.setPreferredSize(new Dimension(1266, 100));
        this.revalidate();
        this.repaint();
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jButton1.getText().equals("+")){
            uvecajSe();
        } else {
            smanjiSe();
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    private Jagnjenje pickJagnjenjeFromTable(int n, TableModel tb){
        Jagnjenje j = new Jagnjenje();
        Ovca jagnje  = new Ovca();
        jagnje.setOznaka(tb.getValueAt(n, 3));
        jagnje.setProcenatR(tb.getValueAt(n, 4));
        jagnje.setPol(tb.getValueAt(n, 2));
        jagnje.setOpis(tb.getValueAt(n, 5));
        jagnje.setLeglo(tb.getRowCount());
        jagnje.setTezinaNaRodjenju(tb.getValueAt(n, 7));
        
        j.setSheep(jagnje);    
        j.setJelZivo(tb.getValueAt(n, 1));
        if (j.isJelZivo()){
           jagnje.setStatus("na farmi"); 
        } else {
            jagnje.setStatus("mrtvo rođeno");
        }
        j.setNapomena(tb.getValueAt(n, 6));
        
        j.setId(tb.getValueAt(n, 8));
       // j.setNapomena(tb.getValueAt(n, 6));
        return j;
         
    }
    
    public ArrayList<Jagnjenje> getJagnjenja(){
        ArrayList<Jagnjenje> list = new ArrayList<Jagnjenje>();
        Ovca majka = (Ovca) jOvca.getSelectedItem();
        Ovca otac = (Ovca) jOvan.getSelectedItem();
        if (majka==null){
            return null;
        }
        if (otac==null){
                otac = new Ovca("zamišljen", OvcaHelper.parseOznaka(jOvan.getSelectedItem().toString()), 'm');
        }
        TableModel tb = jTableJagnjaci.getModel();
        
        for (int i=0; i<jTableJagnjaci.getRowCount(); i++){
            Jagnjenje jagnjenje = pickJagnjenjeFromTable(i, tb);
            jagnjenje.setOvca(majka);
            jagnjenje.getSheep().setMajka(majka);
            jagnjenje.getSheep().setOtac(otac);

            list.add(jagnjenje);
        }
       // System.out.println("Prvo jagnje tezina na rodjenju: " + list.get(0).getSheep().getTezinaNaRodjenju());
        return list;
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JComboBox<Ovca> jOvan;
    private javax.swing.JComboBox<Ovca> jOvca;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.components.JSpinField jSpinField2;
    private javax.swing.JTable jTableJagnjaci;
    // End of variables declaration//GEN-END:variables
}
