
package app.mainPanels;

import app.editPanels.OvcaPrikaz;
import app.helpers.RacunanjeKolena;
import app.logic.Logic;
import app.model.Merenje;
import app.model.Ovca;
import app.report.ListaOvacaAktuelnoIzvestaj;
import app.report.Ukrvljenost;
import app.report.UkrvljenostIzvestaj;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author ivantadic
 */
public class PlanPripusta extends javax.swing.JPanel {
    private List<Ovca> listOfSheep;
    private Logic logic;
    private JPanel mainPanel;
    private TableRowSorter<TableModel> sorter1, sorter2;


    /**
     * Creates new form Podaci_ovaca
     */
    public PlanPripusta(JPanel mainPanel, Logic logic) {
        this.logic = logic;
        this.mainPanel = mainPanel;
        initComponents();
        jScrollPane1.getViewport().setBackground(Color.white);

        listOfSheep = logic.getSvaZivaGrla();
        //System.err.println("Velicina liste ovaca: " + listOfSheep.size());
        fillTable(listOfSheep);

    }
    
    private List<RowFilter<TableModel,Object>> getFilters2(){
         List<RowFilter<TableModel,Object>> filters = new ArrayList<RowFilter<TableModel,Object>>();

         String polFilter = "";
         if (jTrazi3.getSelectedIndex()==2){
             polFilter = "muško";
         } else if(jTrazi3.getSelectedIndex()==1){
             polFilter = "žensko";
         }  
         RowFilter<TableModel, Object> filter1 = RowFilter.regexFilter(polFilter);
         RowFilter<TableModel, Object> filter2 = RowFilter.regexFilter(jTrazi1.getText());
         filters.add(filter1);
         filters.add(filter2);
         

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
    
//    private Vector vectorFrom(Ovca o){
//        Vector v = new Vector();
//        v.add(o.getOznaka());
//        v.add(o.getNadimak());
//        if (o.getPol()=='m'){
//           v.add("muško");
//        } else {
//           v.add("žensko");
//        }
//
//        v.add(o.getProcR());
//        v.add(o.getStarost());
//        v.add(o.getLleglo());
//        v.add(""+ o.getProcenatJagnjenja());
//        if (o.getOtac()==null){
//              v.add("nepoznat");
//        }else{
//             v.add(o.getOtac().toString());
//        }
//        if (o.getMajka()==null){
//              v.add("nepoznata");
//        }else{
//             v.add(o.getMajka().toString());
//        }
//
//        v.add(o.getPoreklo());
//        v.add(o.getOpis());
//        v.add(o.getId());
//        v.add(o.getAktuelno());
//        v.add(o.getStatus());
//        return v;
//    }
    private void fillCobmo(JComboBox jCombo){
        List ovnovi = logic.getSveOvnove();
        ovnovi.add(null);
        jCombo.setModel(new DefaultComboBoxModel(ovnovi.toArray()));
        jCombo.setSelectedItem(null);
        AutoCompleteDecorator.decorate(jCombo);
    }
    
    private void fillTable(List<Ovca> list){
        DefaultTableModel model1 = (DefaultTableModel) jTable1.getModel();
        fillCobmo(jComboOvan1);
        fillCobmo(jComboOvan2);
        fillCobmo(jComboOvan3);
        fillCobmo(jComboOvan4);
        fillCobmo(jComboOvan5);
        model1.setRowCount(0);
                for (Ovca o: list){
                    if (o.getPol()=='m'){
                    }
                    ubaciOvcuTable1(model1, o);
                }
       setSorter();
       setBoldFontToColumn(5, Color.RED.darker());
       jCounter.setText("("+ jTable1.getRowCount() + ")");
    }

    private void setSorter(){
        DefaultTableModel model2 = (DefaultTableModel) jTable1.getModel();
        sorter2 = new TableRowSorter<TableModel>(model2);
        jTable1.setRowSorter(sorter2);
     }

    private void ubaciOvcuTable1(DefaultTableModel model, Ovca o){
        Vector v = new Vector();
        v.add(o.getOznaka());
        v.add(o.getNadimak());
        if (o.getPol()=='ž'){
            v.add("žensko");
        } else {
        v.add("muško");
        }
        v.add(o.getStarost());
        v.add(o.getLeglo());
        v.add(o.getAktuelno());
        v.add(o.getId());
        v.add("-");
        v.add("-");
        v.add("-");
        v.add("-");
        v.add("-");
        
        model.addRow(v);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTrazi1 = new javax.swing.JTextField();
        jCounter = new javax.swing.JLabel();
        jSnimi = new javax.swing.JButton();
        jTrazi3 = new javax.swing.JComboBox();
        jStampajSve1 = new javax.swing.JButton();
        jComboOvan1 = new javax.swing.JComboBox();
        jComboOvan2 = new javax.swing.JComboBox();
        jComboOvan3 = new javax.swing.JComboBox();
        jComboOvan4 = new javax.swing.JComboBox();
        jComboOvan5 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Noteworthy", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Planiranje pripusta");
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
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "oznaka", "nadimak", "pol", "starost", "iz legla od", "aktuelno", "id", "ukrvljenost", "ukrvljenost", "ukrvljenost", "ukrvljenost", "ukrvljenost"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false, false, false, true, true, true
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
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getColumnModel().getColumn(0).setMinWidth(90);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(120);
        jTable1.getColumnModel().getColumn(1).setMinWidth(80);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(120);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(200);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(2).setMaxWidth(60);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(75);
        jTable1.getColumnModel().getColumn(3).setMaxWidth(100);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(70);
        jTable1.getColumnModel().getColumn(4).setMaxWidth(70);
        jTable1.getColumnModel().getColumn(6).setMinWidth(1);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(2);
        jTable1.getColumnModel().getColumn(6).setMaxWidth(2);

        jLabel1.setText("Traži:");

        jTrazi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTrazi1KeyReleased(evt);
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

        jTrazi3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "sva grla", "ženska grla", "muška grla" }));
        jTrazi3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTrazi3ActionPerformed(evt);
            }
        });

        jStampajSve1.setBackground(new java.awt.Color(255, 204, 51));
        jStampajSve1.setFont(new java.awt.Font("Monaco", 0, 18)); // NOI18N
        jStampajSve1.setText("Štampaj");
        jStampajSve1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jStampajSve1ActionPerformed(evt);
            }
        });

        jComboOvan1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboOvan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboOvan1ActionPerformed(evt);
            }
        });

        jComboOvan2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboOvan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboOvan2ActionPerformed(evt);
            }
        });

        jComboOvan3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboOvan3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboOvan3ActionPerformed(evt);
            }
        });

        jComboOvan4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboOvan4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboOvan4ActionPerformed(evt);
            }
        });

        jComboOvan5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboOvan5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboOvan5ActionPerformed(evt);
            }
        });

        jLabel3.setText("Ovnovi:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jStampajSve1)
                        .addGap(18, 18, 18)
                        .addComponent(jSnimi))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jCounter, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTrazi3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTrazi1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 267, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(35, 35, 35)
                        .addComponent(jComboOvan1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboOvan2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboOvan3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboOvan4, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jComboOvan5, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTrazi1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTrazi3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCounter, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboOvan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboOvan2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboOvan3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboOvan4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboOvan5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSnimi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        sorter2.setRowFilter(RowFilter.andFilter(getFilters2()));
                 jCounter.setText("("+ jTable1.getRowCount() + ")");
    }//GEN-LAST:event_jTrazi1KeyReleased

    private void jSnimiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSnimiActionPerformed
            for (int i=0; i<jTable1.getRowCount(); i++){
                int selectedRow = jTable1.convertRowIndexToModel(i);
                String aktuelno = (String) jTable1.getModel().getValueAt(selectedRow,5);
               // if (aktuelno!=null){
                    String id = jTable1.getModel().getValueAt(selectedRow,6).toString();
                    Ovca o = logic.getOvca(Integer.parseInt(id));
                    o.setAktuelno(aktuelno);
                    logic.updateOvcaFromList(o);
               // }
            }
            listOfSheep = logic.getAllSheep();
            //fillTable(null);
    }//GEN-LAST:event_jSnimiActionPerformed

    private Ovca getOvan(JComboBox combo){
        if (combo.getSelectedItem()==null){
            return null;
        }
        return (Ovca) combo.getSelectedItem();
    }
    private void jStampajSve1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jStampajSve1ActionPerformed
        List<Ukrvljenost> list = new ArrayList<Ukrvljenost>();
        String f1 = "-";
        String f2 = jTrazi3.getSelectedItem().toString();
        String f3 = jTrazi1.getText();
        Ovca ovan1 = getOvan(jComboOvan1);
        Ovca ovan2 = getOvan(jComboOvan2);
        Ovca ovan3 = getOvan(jComboOvan3);
        Ovca ovan4 = getOvan(jComboOvan4);
        Ovca ovan5 = getOvan(jComboOvan5);
        for (int i=0; i<jTable1.getRowCount(); i++){
            int selectedRow = jTable1.convertRowIndexToModel(i);
            Integer id = Integer.parseInt(jTable1.getModel().getValueAt(selectedRow,6).toString());
            Ovca o = logic.getOvca(id);
            Ukrvljenost u = new Ukrvljenost(o);
            u.setProcOvan1(jTable1.getModel().getValueAt(selectedRow,7).toString());
            u.setProcOvan2(jTable1.getModel().getValueAt(selectedRow,8).toString());
            u.setProcOvan3(jTable1.getModel().getValueAt(selectedRow,9).toString());
            u.setProcOvan4(jTable1.getModel().getValueAt(selectedRow,10).toString());
            u.setProcOvan5(jTable1.getModel().getValueAt(selectedRow,11).toString());
            list.add(u);
        }
        new UkrvljenostIzvestaj(list, ovan1, ovan2, ovan3, ovan4, ovan5).create();
    }//GEN-LAST:event_jStampajSve1ActionPerformed

    private void popuniKolena(Ovca ovan, int n){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        RacunanjeKolena racun = new RacunanjeKolena(logic);
        for (int i=0; i<model.getRowCount(); i++){
            Ovca o = logic.getOvca((Integer)model.getValueAt(i, 6));
            String ukrvljenost = racun.getUkrvljenost(o, ovan);
            model.setValueAt(ukrvljenost, i, 6+n);
        }
    }
    
    private void jTrazi3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTrazi3ActionPerformed
           sorter2.setRowFilter(RowFilter.andFilter(getFilters2()));
                 jCounter.setText("("+ jTable1.getRowCount() + ")");
    }//GEN-LAST:event_jTrazi3ActionPerformed

    private void jComboOvan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboOvan1ActionPerformed
        if (jComboOvan1.getSelectedItem()==null){
            return;
        }
        Ovca ovan = (Ovca)jComboOvan1.getSelectedItem();
        popuniKolena(ovan, 1);
    }//GEN-LAST:event_jComboOvan1ActionPerformed

    private void jComboOvan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboOvan2ActionPerformed
        if (jComboOvan2.getSelectedItem()==null){
            return;
        }
        Ovca ovan = (Ovca)jComboOvan2.getSelectedItem();
        popuniKolena(ovan, 2);
    }//GEN-LAST:event_jComboOvan2ActionPerformed

    private void jComboOvan3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboOvan3ActionPerformed
        if (jComboOvan3.getSelectedItem()==null){
            return;
        }
        Ovca ovan = (Ovca)jComboOvan3.getSelectedItem();
        popuniKolena(ovan, 3);
    }//GEN-LAST:event_jComboOvan3ActionPerformed

    private void jComboOvan4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboOvan4ActionPerformed
        if (jComboOvan4.getSelectedItem()==null){
            return;
        }
        Ovca ovan = (Ovca)jComboOvan4.getSelectedItem();
        popuniKolena(ovan, 4);
    }//GEN-LAST:event_jComboOvan4ActionPerformed

    private void jComboOvan5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboOvan5ActionPerformed
        if (jComboOvan5.getSelectedItem()==null){
            return;
        }
        Ovca ovan = (Ovca)jComboOvan5.getSelectedItem();
        popuniKolena(ovan, 5);   
    }//GEN-LAST:event_jComboOvan5ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboOvan1;
    private javax.swing.JComboBox jComboOvan2;
    private javax.swing.JComboBox jComboOvan3;
    private javax.swing.JComboBox jComboOvan4;
    private javax.swing.JComboBox jComboOvan5;
    private javax.swing.JLabel jCounter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jSnimi;
    private javax.swing.JButton jStampajSve1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTrazi1;
    private javax.swing.JComboBox jTrazi3;
    // End of variables declaration//GEN-END:variables

}
