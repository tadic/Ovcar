/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.gajenje_ovaca.gui.dnevnik.belezenjeAktivnosti;

import app.gajenje_ovaca.OvcaPrikaz;
import app.gajenje_ovaca.gui.dnevnik.Dnevnik;
import app.logic.Logic;
import app.model.Aktivnost;
import app.model.NabavkaOvaca;
import app.model.Ovca;
import app.model.Prodaja;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.RowFilter;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ivantadic
 */
public class ProdajaPanel extends javax.swing.JPanel {

private Logic logic;
private Aktivnost aktivnost;
private JPanel mainPanel;
    private TableRowSorter<TableModel> sorter;


    public ProdajaPanel(JPanel mainP, Logic logic, Aktivnost aktivnost) {
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

        jLabel7 = new javax.swing.JLabel();
        jminutaPocetak = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jminutaKraj = new javax.swing.JSpinner();
        jsatiKraj = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jColorLabel = new javax.swing.JLabel();
        jsatiPocetak = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        jNapomena = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLokacija = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jSnimiButton = new javax.swing.JButton();
        nabavkaPanel = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextTroskovi = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jCounter = new javax.swing.JLabel();
        jFilter1 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jFilter2 = new javax.swing.JTextField();
        jSelekcija = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        jNameOfActivity = new javax.swing.JLabel();

        jLabel7.setText("do:");

        jminutaPocetak.setModel(new javax.swing.SpinnerListModel(new String[] {"00", "15", "30", "45"}));
        jminutaPocetak.setAutoscrolls(true);
        jminutaPocetak.setOpaque(true);
        jminutaPocetak.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jminutaPocetakStateChanged(evt);
            }
        });

        jLabel8.setText("-");

        jLabel9.setText("-");

        jLabel6.setText("od:");

        jminutaKraj.setModel(new javax.swing.SpinnerListModel(new String[] {"00", "15", "30", "45"}));
        jminutaKraj.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jminutaKrajStateChanged(evt);
            }
        });

        jsatiKraj.setModel(new javax.swing.SpinnerListModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
        jsatiKraj.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jsatiKrajStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Monaco", 1, 18)); // NOI18N
        jLabel2.setText("Trajanje");

        jColorLabel.setBackground(new java.awt.Color(102, 255, 102));
        jColorLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jColorLabel.setOpaque(true);

        jsatiPocetak.setModel(new javax.swing.SpinnerListModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22"}));
        jsatiPocetak.setOpaque(true);
        jsatiPocetak.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jsatiPocetakStateChanged(evt);
            }
        });

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

        jLabel13.setFont(new java.awt.Font("Monaco", 0, 14)); // NOI18N
        jLabel13.setText("Broj grla:");

        jLabel18.setFont(new java.awt.Font("Monaco", 0, 14)); // NOI18N
        jLabel18.setText("Ukupno u evrima");

        jTextTroskovi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextTroskoviActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Monaco", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "nadimak", "oznaka", "pol", "starost", "težina", "cena po kg(€)", "cena €", "kupac", "napomena", "aktuelno", "selekcija"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true, true, true, false, true
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
        jTable1.setRowHeight(30);
        jTable1.setRowMargin(0);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(5);
        jTable1.getColumnModel().getColumn(6).setResizable(false);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(15);
        jTable1.getColumnModel().getColumn(8).setResizable(false);
        jTable1.getColumnModel().getColumn(8).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(10).setMaxWidth(60);

        jLabel10.setText("_________________________________________________");

        jCounter.setFont(new java.awt.Font("Monaco", 1, 24)); // NOI18N
        jCounter.setForeground(new java.awt.Color(204, 0, 51));
        jCounter.setText("9");

        jFilter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFilter1ActionPerformed(evt);
            }
        });
        jFilter1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFilter1KeyReleased(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Monaco", 0, 14)); // NOI18N
        jLabel19.setText("Filteri:");

        jFilter2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFilter2ActionPerformed(evt);
            }
        });
        jFilter2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFilter2KeyReleased(evt);
            }
        });

        jSelekcija.setFont(new java.awt.Font("Monaco", 0, 14)); // NOI18N
        jSelekcija.setText("Selektovano");
        jSelekcija.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSelekcijaActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout nabavkaPanelLayout = new org.jdesktop.layout.GroupLayout(nabavkaPanel);
        nabavkaPanel.setLayout(nabavkaPanelLayout);
        nabavkaPanelLayout.setHorizontalGroup(
            nabavkaPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(nabavkaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 345, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(807, Short.MAX_VALUE))
            .add(nabavkaPanelLayout.createSequentialGroup()
                .add(17, 17, 17)
                .add(nabavkaPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1141, Short.MAX_VALUE)
                    .add(nabavkaPanelLayout.createSequentialGroup()
                        .add(jLabel13)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jCounter)
                        .add(28, 28, 28)
                        .add(jLabel18)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTextTroskovi, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 48, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jSelekcija)
                        .add(18, 18, 18)
                        .add(jLabel19)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jFilter1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 168, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jFilter2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 168, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        nabavkaPanelLayout.setVerticalGroup(
            nabavkaPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, nabavkaPanelLayout.createSequentialGroup()
                .add(jLabel10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(7, 7, 7)
                .add(nabavkaPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel13)
                    .add(jLabel18)
                    .add(jTextTroskovi, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jCounter)
                    .add(jFilter1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jFilter2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jSelekcija))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 209, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel11.setText("_________________________________________________");

        jNameOfActivity.setFont(new java.awt.Font("Monaco", 1, 24)); // NOI18N
        jNameOfActivity.setForeground(new java.awt.Color(153, 0, 0));
        jNameOfActivity.setText("Hranjenje");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(20, 20, 20)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jLabel5)
                        .add(1025, 1056, Short.MAX_VALUE))
                    .add(jScrollPane1)))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(layout.createSequentialGroup()
                        .add(12, 12, 12)
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel6)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jsatiPocetak, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel8)
                        .add(1, 1, 1)
                        .add(jminutaPocetak, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(13, 13, 13)
                        .add(jLabel7)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jsatiKraj, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel9)
                        .add(1, 1, 1)
                        .add(jminutaKraj, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(21, 21, 21)
                        .add(jColorLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jNameOfActivity, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 309, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jLabel3)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLokacija, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 132, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(21, 21, 21))
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jLabel11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 355, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(0, 0, Short.MAX_VALUE)
                        .add(jSnimiButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton2)
                        .add(28, 28, 28))))
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(nabavkaPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(15, 15, 15)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jNameOfActivity, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 38, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jColorLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 38, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(30, 30, 30)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jminutaPocetak, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createSequentialGroup()
                        .add(5, 5, 5)
                        .add(jLabel8))
                    .add(jsatiPocetak, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createSequentialGroup()
                        .add(4, 4, 4)
                        .add(jLabel7))
                    .add(layout.createSequentialGroup()
                        .add(2, 2, 2)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(jLokacija, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(jLabel3))
                            .add(layout.createSequentialGroup()
                                .add(1, 1, 1)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jminutaKraj, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(layout.createSequentialGroup()
                                        .add(5, 5, 5)
                                        .add(jLabel9))
                                    .add(jsatiKraj, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel2)
                        .add(jLabel6)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(nabavkaPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel11)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel5)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jSnimiButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 37, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 37, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void setPanel(){
        jNameOfActivity.setText(aktivnost.getVrstaAktivnosti().getName());
        jColorLabel.setBackground(new Color(aktivnost.getVrstaAktivnosti().getColor()));
        jsatiPocetak.setValue(convertHours(aktivnost.pocetakUSatima()));
        jminutaPocetak.setValue(convertHours(aktivnost.pocetakMinuta()));
        jsatiKraj.setValue(convertHours(aktivnost.zavrsetakUSatima()));
        jminutaKraj.setValue(convertHours(aktivnost.zavrsetakMinuta()));
        jLokacija.setText(aktivnost.getLokacija());
        jNapomena.setText(aktivnost.getNapomena());
        jTextTroskovi.setText(Float.toString(aktivnost.getTroskovi()));
//setTableRows();
        paintColumns();
        resetTable(logic.getSvaZivaGrla());
        
    }


    private void paintColumns(){
        for (int i=4; i<10; i++){
             TableColumn tm = jTable1.getColumnModel().getColumn(i);
             tm.setCellRenderer(new ColorColumnRenderer(new Color(((i%2)*217 + 255)%256, ((i%2)*217 + 255)%256,((i%2)*217 + 255)%256),Color.DARK_GRAY));
        }
    }
    
    private List<RowFilter<TableModel,Object>> getFilters(){
         List<RowFilter<TableModel,Object>> filters = new ArrayList<RowFilter<TableModel,Object>>();
         RowFilter<TableModel, Object> comboFilter = RowFilter.regexFilter("");
         if (jSelekcija.isSelected()){
             comboFilter = RowFilter.regexFilter("true");
         }
         RowFilter<TableModel, Object> filter1 = RowFilter.regexFilter(jFilter1.getText());
         RowFilter<TableModel, Object> filter2 = RowFilter.regexFilter(jFilter2.getText());
         filters.add(filter1);
         filters.add(filter2);
         filters.add(comboFilter);
         

         return filters;
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
        if (aktivnost.getId()!=null){
            for (Prodaja p: aktivnost.getProdaje()){
                model.addRow(vectorFrom(p.getOvca()));
            }
            jSelekcija.setSelected(true);
        }
        jCounter.setText("("+ jTable1.getRowCount() + ")");
       
    }
    private void keepTimePositive(){
        int vremePocetka =   pickint(jsatiPocetak)*100 + pickint(jminutaPocetak);
        int vremeZavrsetka = pickint(jsatiKraj)*100 + pickint(jminutaKraj);
        int trajanje = vremeZavrsetka-vremePocetka;
        if (trajanje <= 0){
            jsatiKraj.setValue(convertHours(pickint(jsatiPocetak)+1));
            jminutaKraj.setValue(convertHours(pickint(jminutaPocetak)));
        }
    }
    private void jsatiPocetakStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jsatiPocetakStateChanged
        keepTimePositive();
    }//GEN-LAST:event_jsatiPocetakStateChanged
  private String convertHours(int h){
        if (h<10){
            return "0" + h;
        } 
        return "" + h;
    }
    private void jLokacijaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLokacijaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLokacijaActionPerformed

     private int pickint(JSpinner s){
        return Integer.parseInt(s.getValue().toString());
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
            mainPanel.removeAll();
            mainPanel.add(new Dnevnik(logic, mainPanel));
            mainPanel.revalidate();
            repaint();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jSnimiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSnimiButtonActionPerformed
        pickDataFromForm();
        logic.saveActivity(aktivnost);
            mainPanel.removeAll();
            mainPanel.add(new Dnevnik(logic, mainPanel));
            mainPanel.revalidate();
            repaint();
    }//GEN-LAST:event_jSnimiButtonActionPerformed

    private List<Prodaja> pickSveProdaje(){
        List<Prodaja> list = new ArrayList<Prodaja>(); 
        TableModel table = jTable1.getModel();
        System.out.println("sve prodaja velicina tabele: " + table.getRowCount());
        for (int i=0; i<table.getRowCount(); i++){
            System.out.println(Boolean.getBoolean(table.getValueAt(i, 10).toString()));
            if (Boolean.valueOf(table.getValueAt(i, 10).toString())){
                list.add(pickProdaja(table, i));
            }
        }
        System.out.println("sve prodaja velicina liste: " + list.size());
        return list;
    }
    

    private Prodaja pickProdaja(TableModel table, int n){
        Prodaja p = new Prodaja();
        Ovca o = logic.getOvca(table.getValueAt(n, 1).toString());
        p.setTezina((table.getValueAt(n, 4)));
        p.setCenaKg((table.getValueAt(n, 5)));
        p.setCenaGrla((table.getValueAt(n, 6)));
        p.setKupac(table.getValueAt(n, 7));
        p.setNapomena(table.getValueAt(n, 8));
        p.setOvca(o);
        return p;
    }


    private void pickDataFromForm(){
        aktivnost.setVremePocetka(pickint(jsatiPocetak)*100 + pickint(jminutaPocetak));
        aktivnost.setVremeZavrsetka(pickint(jsatiKraj)*100 + pickint(jminutaKraj));
        aktivnost.setLokacija(jLokacija.getText());
        aktivnost.setTroskovi(jTextTroskovi.getText());
        aktivnost.setNapomena(jNapomena.getText());
      
        aktivnost.setProdaje(pickSveProdaje());
        aktivnost.setBilans(createBilans());
    }

    private void jminutaPocetakStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jminutaPocetakStateChanged
        keepTimePositive();
    }//GEN-LAST:event_jminutaPocetakStateChanged

    private void jsatiKrajStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jsatiKrajStateChanged
        keepTimePositive();  
    }//GEN-LAST:event_jsatiKrajStateChanged

    private void jminutaKrajStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jminutaKrajStateChanged
        keepTimePositive();
    }//GEN-LAST:event_jminutaKrajStateChanged

    private void jTextTroskoviActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextTroskoviActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextTroskoviActionPerformed

    private void jFilter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFilter1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFilter1ActionPerformed

    private void jFilter2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFilter2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFilter2ActionPerformed

    private void jFilter1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFilter1KeyReleased
        sorter.setRowFilter(RowFilter.andFilter(getFilters()));
        jCounter.setText("("+ jTable1.getRowCount() + ")");        // TODO add your handling code here:
    }//GEN-LAST:event_jFilter1KeyReleased

    private void jFilter2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFilter2KeyReleased
        sorter.setRowFilter(RowFilter.andFilter(getFilters()));
        jCounter.setText("("+ jTable1.getRowCount() + ")");        // TODO add your handling code here:
    }//GEN-LAST:event_jFilter2KeyReleased

    private void jSelekcijaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSelekcijaActionPerformed
        sorter.setRowFilter(RowFilter.andFilter(getFilters()));
        jCounter.setText("("+ jTable1.getRowCount() + ")"); 
    }//GEN-LAST:event_jSelekcijaActionPerformed


    private String createBilans(){
        int komada = aktivnost.getProdaje().size();
        StringBuilder sb = new StringBuilder("Prodato ");
        sb.append(Ovca.mnozinaGro(komada));
        return sb.toString();
        
    }
    private Vector vectorFrom(Ovca o){
        Vector v = new Vector(); 
        v.add(o.getNadimak());
        v.add(o.getOznaka());
        v.add(o.getPol());
        v.add(o.getStarost());
        if (o.getProdaja()==null){
            v.add(null);   
            v.add(null); 
            v.add(null); 
            v.add(null); 
            v.add(null); 
            v.add(o.getAktuelno());
            v.add(false);
        } else {
            v.add(o.getProdaja().getTezina());   
            v.add(o.getProdaja().getCenaKg()); 
            v.add(o.getProdaja().getCenaGrla()); 
            v.add(o.getProdaja().getKupac()); 
            v.add(o.getProdaja().getNapomena()); 
            v.add(o.getAktuelno());
            v.add(true);
        }
        return v;
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
    private javax.swing.JLabel jCounter;
    private javax.swing.JTextField jFilter1;
    private javax.swing.JTextField jFilter2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jLokacija;
    private javax.swing.JLabel jNameOfActivity;
    private javax.swing.JTextArea jNapomena;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JCheckBox jSelekcija;
    private javax.swing.JButton jSnimiButton;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextTroskovi;
    private javax.swing.JSpinner jminutaKraj;
    private javax.swing.JSpinner jminutaPocetak;
    private javax.swing.JSpinner jsatiKraj;
    private javax.swing.JSpinner jsatiPocetak;
    private javax.swing.JPanel nabavkaPanel;
    // End of variables declaration//GEN-END:variables
}
