/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mainPanels;

import app.editPanels.OvcaPrikaz;
import app.report.ListaOvacaAktuelnoIzvestaj;
import app.report.ListaOvacaIzvestaj;
import app.logic.Logic;
import app.model.Ovca;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ivantadic
 */
public class Podaci_ovaca extends javax.swing.JPanel {
    private List<Ovca> listOfSheep;
    private Logic logic;
    private JPanel mainPanel;
    private TableRowSorter<TableModel> sorter;


    /**
     * Creates new form Podaci_ovaca
     */
    public Podaci_ovaca(JPanel mainPanel, Logic logic) {
        this.logic = logic;
        this.mainPanel = mainPanel;
        initComponents();
        jScrollPane1.getViewport().setBackground(Color.white);
        setBoldFontToColumn(3, Color.black);
        setBoldFontToColumn(0, Color.red.darker());
        setBoldFontToColumn(12, Color.black);
        listOfSheep = logic.getAllSheep();
        //System.err.println("Velicina liste ovaca: " + listOfSheep.size());
        resetTable(listOfSheep);

    }
    
    private List<RowFilter<TableModel,Object>> getFilters(){
         List<RowFilter<TableModel,Object>> filters = new ArrayList<RowFilter<TableModel,Object>>();
         RowFilter<TableModel, Object> comboFilter = RowFilter.regexFilter("");
         if (jNaFarmi.isSelected()){
             comboFilter = RowFilter.regexFilter("na farmi");
         }
         String polFilter = "";
         if (jTrazi3.getSelectedIndex()==2){
             polFilter = "muško";
         } else if(jTrazi3.getSelectedIndex()==1){
             polFilter = "žensko";
         }  
         RowFilter<TableModel, Object> filter1 = RowFilter.regexFilter(polFilter);
         RowFilter<TableModel, Object> filter2 = RowFilter.regexFilter(jTrazi1.getText());
         RowFilter<TableModel, Object> filter3 = RowFilter.regexFilter(jTrazi2.getText());          
         filters.add(comboFilter);
         filters.add(filter1);
         filters.add(filter2);
         filters.add(filter3);
         

         return filters;
    }
    
    private void setBoldFontToColumn(int n, Color color){
        DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
            
            Font f = new Font ("Dialog", Font.BOLD, 14);
            
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                    row, column);
                setFont(f);
                return this;
            }
        
        };
        r.setForeground(color);
        jTable1.getColumnModel().getColumn(n).setCellRenderer(r);
    }
    
    private Vector vectorFrom(Ovca o){
        Vector v = new Vector();
        v.add(o.getOznaka());
        v.add(o.getNadimak());
        if (o.getPol()=='m'){
           v.add("muško");
        } else {
           v.add("žensko");
        }

        v.add(o.getProcR());
        v.add(o.getStarost());
        v.add(o.getLleglo());
        v.add(""+ o.procenatJagnjenja());
        if (o.getOtac()==null){
              v.add("nepoznat");
        }else{
             v.add(o.getOtac().toString());
        }
        if (o.getMajka()==null){
              v.add("nepoznata");
        }else{
             v.add(o.getMajka().toString());
        }

        v.add(o.getPoreklo());
        v.add(o.getOpis());
        v.add(o.getId());
        v.add(o.getAktuelno());
        v.add(o.getStatus());
        return v;
    }
    private void resetTable(List<Ovca> list){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        sorter = new TableRowSorter<TableModel>(model);
        jTable1.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.andFilter(getFilters()));
        model.setRowCount(0);
        for (Ovca o: list){
            model.addRow(vectorFrom(o));
        }
        jCounter.setText("("+ jTable1.getRowCount() + ")");
       
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jNaFarmi = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jTrazi1 = new javax.swing.JTextField();
        jTrazi2 = new javax.swing.JTextField();
        jCounter = new javax.swing.JLabel();
        jSnimi = new javax.swing.JButton();
        jPrikazi = new javax.swing.JButton();
        jStampajSve = new javax.swing.JButton();
        jTrazi3 = new javax.swing.JComboBox();
        jStampajSve1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Noteworthy", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("Podatci o ovcama");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel2.setAlignmentY(3.0F);
        jLabel2.setMaximumSize(new java.awt.Dimension(219, 40));
        jLabel2.setMinimumSize(new java.awt.Dimension(219, 40));
        jLabel2.setPreferredSize(new java.awt.Dimension(219, 40));
        jLabel2.setSize(new java.awt.Dimension(40, 16));
        jLabel2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTable1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "oznaka", "nadimak", "pol", "% R", "starost", "iz legla od", "% jagnjenja", "otac", "majka", "poreklo", "opis", "id", "aktuelno", "status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, false, false, false, false, false, false, false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setRowHeight(35);
        jTable1.setRowMargin(2);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTable1.setShowGrid(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable1MouseReleased(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable1MouseEntered(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTable1.getColumnModel().getColumn(0).setMinWidth(90);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(120);
        jTable1.getColumnModel().getColumn(1).setMinWidth(80);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(120);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(200);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(2).setMaxWidth(60);
        jTable1.getColumnModel().getColumn(3).setMinWidth(5);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(3).setMaxWidth(80);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(75);
        jTable1.getColumnModel().getColumn(4).setMaxWidth(100);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(70);
        jTable1.getColumnModel().getColumn(5).setMaxWidth(70);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(6).setMaxWidth(100);
        jTable1.getColumnModel().getColumn(7).setMinWidth(100);
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(8).setMinWidth(100);
        jTable1.getColumnModel().getColumn(8).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(9).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(10).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(11).setMinWidth(0);
        jTable1.getColumnModel().getColumn(11).setPreferredWidth(0);
        jTable1.getColumnModel().getColumn(11).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(13).setMinWidth(50);
        jTable1.getColumnModel().getColumn(13).setPreferredWidth(70);
        jTable1.getColumnModel().getColumn(13).setMaxWidth(80);

        jNaFarmi.setSelected(true);
        jNaFarmi.setText("na farmi");
        jNaFarmi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNaFarmiActionPerformed(evt);
            }
        });

        jLabel1.setText("Traži:");

        jTrazi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTrazi1KeyReleased(evt);
            }
        });

        jTrazi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTrazi2KeyReleased(evt);
            }
        });

        jCounter.setFont(new java.awt.Font("Lucida Grande", 0, 32)); // NOI18N
        jCounter.setForeground(new java.awt.Color(153, 0, 51));
        jCounter.setText("(124)");

        jSnimi.setFont(new java.awt.Font("Monaco", 0, 18)); // NOI18N
        jSnimi.setText("Snimi");
        jSnimi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSnimiActionPerformed(evt);
            }
        });

        jPrikazi.setBackground(new java.awt.Color(0, 153, 153));
        jPrikazi.setFont(new java.awt.Font("Monaco", 0, 18)); // NOI18N
        jPrikazi.setText("Prikaži");
        jPrikazi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPrikaziActionPerformed(evt);
            }
        });

        jStampajSve.setBackground(new java.awt.Color(0, 255, 0));
        jStampajSve.setFont(new java.awt.Font("Monaco", 0, 18)); // NOI18N
        jStampajSve.setText("Štampaj sve");
        jStampajSve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jStampajSveActionPerformed(evt);
            }
        });

        jTrazi3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "sva grla", "ženska grla", "muška grla" }));
        jTrazi3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTrazi3ActionPerformed(evt);
            }
        });

        jStampajSve1.setBackground(new java.awt.Color(255, 204, 51));
        jStampajSve1.setFont(new java.awt.Font("Monaco", 0, 18)); // NOI18N
        jStampajSve1.setText("Štampaj aktuelno");
        jStampajSve1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jStampajSve1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jCounter, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(34, 34, 34)
                                .addComponent(jTrazi3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTrazi1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTrazi2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jNaFarmi))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 239, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jStampajSve1)
                        .addGap(18, 18, 18)
                        .addComponent(jStampajSve)
                        .addGap(18, 18, 18)
                        .addComponent(jSnimi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPrikazi, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jNaFarmi)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jTrazi1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTrazi2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTrazi3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jCounter, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSnimi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPrikazi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jStampajSve, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jStampajSve1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased

    }//GEN-LAST:event_jTable1MouseReleased

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered

    }//GEN-LAST:event_jTable1MouseEntered

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased

    }//GEN-LAST:event_jTable1KeyReleased

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        char c = evt.getKeyChar();
        if (c== KeyEvent.VK_ENTER){
            int clickedRow = jTable1.convertRowIndexToModel(jTable1.getSelectedRow());
            String id = jTable1.getModel().getValueAt(clickedRow,11).toString();
//            Ovca o = logic.getOvca(Integer.parseInt(id));
//            if (o.getOtac()!=null){
//                o.getOtac().getOznaka();
//            }
//            if (o.getMajka()!=null){
//                o.getMajka().getOznaka();
//            }
            mainPanel.removeAll();
            mainPanel.add(new OvcaPrikaz(mainPanel, logic, Integer.parseInt(id)));
            mainPanel.revalidate();
            repaint();
        }
    }//GEN-LAST:event_jTable1KeyPressed

    private void jTrazi1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTrazi1KeyReleased
        sorter.setRowFilter(RowFilter.andFilter(getFilters()));
                 jCounter.setText("("+ jTable1.getRowCount() + ")");
    }//GEN-LAST:event_jTrazi1KeyReleased

    private void jNaFarmiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNaFarmiActionPerformed
       sorter.setRowFilter(RowFilter.andFilter(getFilters()));
                jCounter.setText("("+ jTable1.getRowCount() + ")");
    }//GEN-LAST:event_jNaFarmiActionPerformed

    private void jTrazi2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTrazi2KeyReleased
        sorter.setRowFilter(RowFilter.andFilter(getFilters()));
                 jCounter.setText("("+ jTable1.getRowCount() + ")");
    }//GEN-LAST:event_jTrazi2KeyReleased

    private void jPrikaziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPrikaziActionPerformed
            if (jTable1.getSelectedRow()!= -1){
            int clickedRow = jTable1.convertRowIndexToModel(jTable1.getSelectedRow());
            String id = jTable1.getModel().getValueAt(clickedRow,11).toString();
            mainPanel.removeAll();
            mainPanel.add(new OvcaPrikaz(mainPanel, logic, Integer.parseInt(id)));
            mainPanel.revalidate();
            repaint();
            }
    }//GEN-LAST:event_jPrikaziActionPerformed

    private void jSnimiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSnimiActionPerformed
            for (int i=0; i<jTable1.getRowCount(); i++){
                int selectedRow = jTable1.convertRowIndexToModel(i);
                String aktuelno = (String) jTable1.getModel().getValueAt(selectedRow,12);
                Object oznaka = jTable1.getModel().getValueAt(selectedRow,0);
                Object nadimak = jTable1.getModel().getValueAt(selectedRow,1);
               // if (aktuelno!=null){
                    System.out.println(aktuelno.toString());
                    String id = jTable1.getModel().getValueAt(selectedRow,11).toString();
                    Ovca o = logic.getOvca(Integer.parseInt(id));
                    o.setAktuelno(aktuelno.toString());
                    o.setOznaka(oznaka);
                    o.setNadimak(nadimak);
                    logic.updateOvca(o);
               // }
            }
            listOfSheep = logic.getAllSheep();
            resetTable(listOfSheep);
    }//GEN-LAST:event_jSnimiActionPerformed

    private void jStampajSveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jStampajSveActionPerformed
       ArrayList<Ovca> list = new ArrayList<Ovca>();
        String f1 = "-";
        if (jNaFarmi.isSelected()){
           f1 = "na farmi";
       }
        String f2 = jTrazi3.getSelectedItem().toString();
        String f3 = jTrazi1.getText();
        String f4 = jTrazi2.getText();
        for (int i=0; i<jTable1.getRowCount(); i++){
            int selectedRow = jTable1.convertRowIndexToModel(i);
            Integer id = Integer.parseInt(jTable1.getModel().getValueAt(selectedRow,11).toString());
            Ovca o = logic.getOvca(id);
            if (o.getOtac()!=null){
                o.setOtac(logic.getOvca(o.getOtac().getId()));
            }
            if (o.getMajka()!=null){
                o.setMajka(logic.getOvca(o.getMajka().getId()));
            }
            list.add(o);
        }
        new ListaOvacaIzvestaj(list, f1, f2, f3, f4).create();
    }//GEN-LAST:event_jStampajSveActionPerformed

    private void jTrazi3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTrazi3ActionPerformed
         sorter.setRowFilter(RowFilter.andFilter(getFilters()));
                 jCounter.setText("("+ jTable1.getRowCount() + ")");
    }//GEN-LAST:event_jTrazi3ActionPerformed

    private void jStampajSve1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jStampajSve1ActionPerformed
        ArrayList<Ovca> list = new ArrayList<Ovca>();
        String f1 = "-";
        if (jNaFarmi.isSelected()){
           f1 = "na farmi";
       }
        String f2 = jTrazi3.getSelectedItem().toString();
        String f3 = jTrazi1.getText();
        String f4 = jTrazi2.getText();
        for (int i=0; i<jTable1.getRowCount(); i++){
            int selectedRow = jTable1.convertRowIndexToModel(i);
            Integer id = Integer.parseInt(jTable1.getModel().getValueAt(selectedRow,11).toString());
            Ovca o = logic.getOvca(id);
            if (o.getOtac()!=null){
                o.setOtac(logic.getOvca(o.getOtac().getId()));
            }
            if (o.getMajka()!=null){
                o.setMajka(logic.getOvca(o.getMajka().getId()));
            }
            list.add(o);
        }
        new ListaOvacaAktuelnoIzvestaj(list, f1, f2, f3, f4).create();
    }//GEN-LAST:event_jStampajSve1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jCounter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JCheckBox jNaFarmi;
    private javax.swing.JButton jPrikazi;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jSnimi;
    private javax.swing.JButton jStampajSve;
    private javax.swing.JButton jStampajSve1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTrazi1;
    private javax.swing.JTextField jTrazi2;
    private javax.swing.JComboBox jTrazi3;
    // End of variables declaration//GEN-END:variables
}
