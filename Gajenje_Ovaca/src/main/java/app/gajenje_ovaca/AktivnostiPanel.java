/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.gajenje_ovaca;

import app.gajenje_ovaca.gui.dnevnik.belezenjeAktivnosti.BelezenjeAktivnosti;
import app.gajenje_ovaca.gui.dnevnik.belezenjeAktivnosti.JButtonCellEditor;
import app.gajenje_ovaca.gui.dnevnik.belezenjeAktivnosti.JButtonRenderer;
import app.logic.Logic;
import app.model.Aktivnost;
import app.model.Dan;
import app.model.Ovca;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ivantadic
 */
public class AktivnostiPanel extends javax.swing.JPanel {
    private List<Dan> listOfDays;
    private Logic logic;
    private JPanel mainPanel;
    private TableRowSorter<TableModel> sorter;


    /**
     * Creates new form Podaci_ovaca
     */
    public AktivnostiPanel(JPanel mainPanel, Logic logic) {
        this.logic = logic;
        this.mainPanel = mainPanel;
        initComponents();
        jScrollPane1.getViewport().setBackground(Color.white);
        setBoldFontToColumn(3);
        setBoldFontToColumn(0);
        listOfDays = logic.getAllDays();
        
        resetTable(listOfDays);

    }
    
    private List<RowFilter<TableModel,Object>> getFilters(){
         List<RowFilter<TableModel,Object>> filters = new ArrayList<RowFilter<TableModel,Object>>();

         RowFilter<TableModel, Object> filter1 = RowFilter.regexFilter(jTrazi.getText());
         RowFilter<TableModel, Object> filter2 = RowFilter.regexFilter(jTrazi1.getText());
         RowFilter<TableModel, Object> filter3 = RowFilter.regexFilter(jTrazi2.getText());          
         filters.add(filter1);
         filters.add(filter2);
         filters.add(filter3);
         

         return filters;
    }
    
    private void setBoldFontToColumn(int n){
        DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
            Font f = new Font ("Monaco", Font.BOLD, 16);

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                    row, column);
                setFont(f);
                return this;
            }
        
        };
        jTable1.getColumnModel().getColumn(n).setCellRenderer(r);
    }
    
    private Vector vectorFrom(Aktivnost a){
        Vector v = new Vector();
        v.add(a.getDan());
        v.add(a.getLokacija());
        v.add(a.getVrstaAktivnosti().getName());
        v.add(a.getBilans());
        v.add(Float.valueOf(Aktivnost.round(a.getTroskovi(), 1)) + "€");

        v.add(a.getNapomena());
        v.add(a.getId());
        return v;
    }
    private void resetTable(List<Dan> list){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        sorter = new TableRowSorter<TableModel>(model);
        jTable1.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.andFilter(getFilters()));
        model.setRowCount(0);
        for (Dan d: list){
            for (Aktivnost a: d.getAktivnosti()){
                  model.addRow(vectorFrom(a));
            }

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
        jTrazi = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTrazi1 = new javax.swing.JTextField();
        jTrazi2 = new javax.swing.JTextField();
        jCounter = new javax.swing.JLabel();
        jSnimi = new javax.swing.JButton();
        jPrikazi = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Noteworthy", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("Lista Aktivnosti");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel2.setAlignmentY(3.0F);
        jLabel2.setMaximumSize(new java.awt.Dimension(219, 40));
        jLabel2.setMinimumSize(new java.awt.Dimension(219, 40));
        jLabel2.setPreferredSize(new java.awt.Dimension(219, 40));
        jLabel2.setSize(new java.awt.Dimension(40, 16));
        jLabel2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jTable1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTable1.setFont(new java.awt.Font("Monaco", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Datum", "lokacija", "vrsta aktivnosti", "bilans", "troškovi", "napomena", "id"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoCreateRowSorter(true);
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
        jTable1.getColumnModel().getColumn(0).setMinWidth(100);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(120);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(120);
        jTable1.getColumnModel().getColumn(2).setMinWidth(35);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(180);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(260);
        jTable1.getColumnModel().getColumn(4).setMinWidth(50);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(4).setMaxWidth(100);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(75);
        jTable1.getColumnModel().getColumn(6).setMaxWidth(0);

        jTrazi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTraziActionPerformed(evt);
            }
        });
        jTrazi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTraziKeyReleased(evt);
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

        jCounter.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jCounter.setForeground(new java.awt.Color(153, 0, 51));

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jCounter, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTrazi, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTrazi1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTrazi2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 243, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTrazi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jTrazi1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTrazi2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jCounter, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSnimi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPrikazi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
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
            String id = jTable1.getModel().getValueAt(clickedRow,6).toString();
            Aktivnost a = logic.getActivityWithId(Integer.parseInt(id));
       
            logic.setSelectedDay(a.getDan().getDate());
            System.err.println(logic.getSelectedDay().toString());
            new BelezenjeAktivnosti(mainPanel, logic, a).showEditPanel();
        }
    }//GEN-LAST:event_jTable1KeyPressed

    private void jTraziKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTraziKeyReleased
        sorter.setRowFilter(RowFilter.andFilter(getFilters()));
                jCounter.setText("("+ jTable1.getRowCount() + ")");
    }//GEN-LAST:event_jTraziKeyReleased

    private void jTrazi1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTrazi1KeyReleased
        sorter.setRowFilter(RowFilter.andFilter(getFilters()));
                 jCounter.setText("("+ jTable1.getRowCount() + ")");
    }//GEN-LAST:event_jTrazi1KeyReleased

    private void jTrazi2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTrazi2KeyReleased
        sorter.setRowFilter(RowFilter.andFilter(getFilters()));
                 jCounter.setText("("+ jTable1.getRowCount() + ")");
    }//GEN-LAST:event_jTrazi2KeyReleased

    private void jPrikaziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPrikaziActionPerformed
            if (jTable1.getSelectedRow()!= -1){
            int clickedRow = jTable1.convertRowIndexToModel(jTable1.getSelectedRow());
            String id = jTable1.getModel().getValueAt(clickedRow,11).toString();
            Ovca o = logic.getOvca(Integer.parseInt(id));
            mainPanel.removeAll();
            mainPanel.add(new OvcaPrikaz(mainPanel, logic, o));
            mainPanel.revalidate();
            repaint();
            }
    }//GEN-LAST:event_jPrikaziActionPerformed

    private void jSnimiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSnimiActionPerformed
            for (int i=0; i<jTable1.getRowCount(); i++){
                int selectedRow = jTable1.convertRowIndexToModel(i);
                Object aktuelno = jTable1.getModel().getValueAt(selectedRow,12);
                if (aktuelno!=null){
                    System.out.println(aktuelno.toString());
                    String id = jTable1.getModel().getValueAt(selectedRow,11).toString();
                    Ovca o = logic.getOvca(Integer.parseInt(id));
                    o.setAktuelno(aktuelno.toString());
                    logic.updateOvca(o);
                }
            }
            listOfDays = logic.getAllDays();
            resetTable(listOfDays);
    }//GEN-LAST:event_jSnimiActionPerformed

    private void jTraziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTraziActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTraziActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jCounter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton jPrikazi;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jSnimi;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTrazi;
    private javax.swing.JTextField jTrazi1;
    private javax.swing.JTextField jTrazi2;
    // End of variables declaration//GEN-END:variables
}