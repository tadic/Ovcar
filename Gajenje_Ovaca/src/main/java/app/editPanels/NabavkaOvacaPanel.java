/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.editPanels;

import app.helpers.JDateChooserCellEditor;
import app.helpers.JDateChooserRenderer;
import app.mainPanels.Dnevnik;
import app.logic.Logic;
import app.model.Aktivnost;
import app.model.Dan;
import app.model.NabavkaOvaca;
import app.model.Ovca;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author ivantadic
 */
public class NabavkaOvacaPanel extends javax.swing.JPanel {

private Logic logic;
private Aktivnost aktivnost;
private JPanel mainPanel;


    public NabavkaOvacaPanel(JPanel mainP, Logic logic, Aktivnost aktivnost) {
        super();
        this.aktivnost = aktivnost;
        this.mainPanel = mainP;
        this.logic = logic;
        initComponents();
        setOpaque(true);
        jTable1.getColumnModel().getColumn(4).setCellEditor(new JDateChooserCellEditor());
        jTable1.getColumnModel().getColumn(4).setCellRenderer(new JDateChooserRenderer());
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
        jSpinField1 = new com.toedter.components.JSpinField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jNameOfActivity = new javax.swing.JLabel();
        jDatum = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();

        jLabel7.setText("do:");

        jminutaPocetak.setModel(new javax.swing.SpinnerListModel(new String[] {"00", "15", "30", "45"}));
        jminutaPocetak.setAutoscrolls(true);
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

        jLabel13.setFont(new java.awt.Font("Noteworthy", 0, 14)); // NOI18N
        jLabel13.setText("Broj grla");

        jLabel18.setFont(new java.awt.Font("Noteworthy", 0, 14)); // NOI18N
        jLabel18.setText("Ukupni troškovi u ¢");

        jTextTroskovi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextTroskoviActionPerformed(evt);
            }
        });

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setFont(new java.awt.Font("Monaco", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "rbr", "pol", "oznaka", "% romanov", "datum rođenja", "oznaka oca", "oznaka majke", "cena €", "opis", "napomena", "iz legla od"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Object.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setRowHeight(30);
        jTable1.setRowMargin(0);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(3);
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(5);
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(3).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(15);
        jTable1.getColumnModel().getColumn(4).setResizable(false);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(90);
        jTable1.getColumnModel().getColumn(5).setResizable(false);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(6).setResizable(false);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(7).setResizable(false);
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(15);
        jTable1.getColumnModel().getColumn(8).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(9).setResizable(false);
        jTable1.getColumnModel().getColumn(9).setPreferredWidth(100);

        jSpinField1.setMinimum(1);
        jSpinField1.setValue(1);
        jSpinField1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jSpinField1PropertyChange(evt);
            }
        });

        jLabel10.setText("_________________________________________________");

        javax.swing.GroupLayout nabavkaPanelLayout = new javax.swing.GroupLayout(nabavkaPanel);
        nabavkaPanel.setLayout(nabavkaPanelLayout);
        nabavkaPanelLayout.setHorizontalGroup(
            nabavkaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nabavkaPanelLayout.createSequentialGroup()
                .addGroup(nabavkaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(nabavkaPanelLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinField1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(jTextTroskovi, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(nabavkaPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        nabavkaPanelLayout.setVerticalGroup(
            nabavkaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, nabavkaPanelLayout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(nabavkaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel18)
                    .addComponent(jTextTroskovi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinField1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel11.setText("_________________________________________________");

        jNameOfActivity.setFont(new java.awt.Font("Monaco", 1, 24)); // NOI18N
        jNameOfActivity.setForeground(new java.awt.Color(153, 0, 0));
        jNameOfActivity.setText("Hranjenje");

        jDatum.setDateFormatString("dd.MM.yyyy");

        jLabel1.setFont(new java.awt.Font("Monaco", 1, 18)); // NOI18N
        jLabel1.setText("Datum");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1062, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jsatiPocetak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addGap(1, 1, 1)
                        .addComponent(jminutaPocetak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jsatiKraj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addGap(1, 1, 1)
                        .addComponent(jminutaKraj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jColorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jNameOfActivity, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLokacija)
                    .addComponent(jDatum, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
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
                .addContainerGap()
                .addComponent(nabavkaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jNameOfActivity, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(jDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jminutaPocetak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel8))
                    .addComponent(jsatiPocetak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLokacija, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jminutaKraj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(jLabel9))
                                    .addComponent(jsatiKraj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addContainerGap(38, Short.MAX_VALUE))
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
        jDatum.setCalendar(aktivnost.getDan().getDate());
        setTableRows();
        
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


    private ArrayList<NabavkaOvaca> createListOfNabavka(int n){
        ArrayList<NabavkaOvaca> list = new ArrayList<NabavkaOvaca>();
        for (int i=0; i<n; i++){
            list.add(new NabavkaOvaca());
        }
        return list;
        
    }
    private void pickSveNabavke(){
        int listSize = jTable1.getRowCount();
        if (aktivnost.getId()==null){
            aktivnost.setNabavljenaGrla(createListOfNabavka(listSize));
        }
        for (int i=0; i<listSize; i++){
            pickNabavka(i);   
        }            

    }
    

    private void pickNabavka(int n){
        TableModel table = jTable1.getModel();

        aktivnost.getNabavljenaGrla().get(n).setCena(table.getValueAt(n, 7));
        aktivnost.getNabavljenaGrla().get(n).setNapomena(table.getValueAt(n, 9));
        aktivnost.getNabavljenaGrla().get(n).setSheep(pickOvca(table, n));    
    }

    private void pickOtac(Ovca ovca, TableModel table, int n){
        ovca.setOtac(new Ovca("zamisljena", "nepoznat", 'm'));
        if (table.getValueAt(n, 5)!=null){
            if (table.getValueAt(n, 5).toString().length()>0){
                ovca.getOtac().setOznaka(table.getValueAt(n, 5));
            }
        }
    }
    private void pickMajka(Ovca ovca, TableModel table, int n){
        ovca.setMajka(new Ovca("zamisljena", "nepoznata", 'ž'));
        if (table.getValueAt(n, 6)!=null){
             if (table.getValueAt(n, 6).toString().length()>0){
                    ovca.getMajka().setOznaka(table.getValueAt(n, 6));
             }
        }
    }
    
    private Ovca pickOvca(TableModel table, int n){
        Ovca ovca = new Ovca("na farmi");
        if (aktivnost.getId()!=null){
            ovca.setId(aktivnost.getNabavljenaGrla().get(n).getSheep().getId());
        }
        ovca.setPol(table.getValueAt(n, 1));
        ovca.setOznaka(table.getValueAt(n, 2));
        ovca.setProcenatR(table.getValueAt(n, 3));
        ovca.setDatumRodjenja(table.getValueAt(n, 4));
        pickOtac(ovca, table, n);
        pickMajka(ovca, table, n);
        ovca.setOpis(table.getValueAt(n, 8));
        ovca.setLeglo(table.getValueAt(n, 10));
        return ovca;
    }
    private void pickDataFromForm(){
        aktivnost.setVremePocetka(pickint(jsatiPocetak)*100 + pickint(jminutaPocetak));
        aktivnost.setVremeZavrsetka(pickint(jsatiKraj)*100 + pickint(jminutaKraj));
        aktivnost.setLokacija(jLokacija.getText());
        aktivnost.setTroskovi(jTextTroskovi.getText());
        aktivnost.setNapomena(jNapomena.getText());
        aktivnost.setDan(new Dan(jDatum.getCalendar()));
      
        pickSveNabavke();
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

    private void jSpinField1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jSpinField1PropertyChange
        if (aktivnost.getNabavljenaGrla()!=null){       // ako se edituje ne treba dodavati vrstu
            return;
        }
        int n = jSpinField1.getValue();
        if (n>0){
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            if (jTable1.getModel().getRowCount()>n){
                model.removeRow(n);
            } else {
                Vector row = new Vector();
                row.add(n);
                row.add("ž");
                model.addRow(row);
            }
        }    
    }//GEN-LAST:event_jSpinField1PropertyChange
       
    private void setTableRows(){
        if (aktivnost.getNabavljenaGrla()!=null){

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            int i=1;
            for (NabavkaOvaca no: aktivnost.getNabavljenaGrla()){
                model.addRow(vectorFrom(no, i++));
            }
        }
    }

    private int brojMuskihGrla(){
        int muskih = 0;
        for (NabavkaOvaca no: aktivnost.getNabavljenaGrla()){
            if (no.getSheep().getPol()=='m'){
                muskih++;
            }
        }
        return muskih;
    }
    private String createBilans(){
        int muskih = brojMuskihGrla();
        int zenskih = aktivnost.getNabavljenaGrla().size()-muskih;
        StringBuilder sb = new StringBuilder(Ovca.mnozinaGro(aktivnost.getNabavljenaGrla().size()));
        sb.append(": ");
        sb.append(Ovca.mnozinaOvan(muskih));
        sb.append(" i ").append(Ovca.mnozinaOvca(zenskih));
        return sb.toString();
        
    }
    private Vector vectorFrom(NabavkaOvaca no, int n){
        Ovca o = logic.getOvca(no.getSheep().getId());
        no.setSheep(o);
        Vector v = new Vector();
        v.add(n);
        v.add(o.getPol());
        v.add(o.getOznaka());
        v.add(o.getProcenatR());
        v.add(o.getDatumRodjenja());      
        if (o.getOtac()!=null){
             v.add(o.getOtac().getOznaka());
        } else {
            v.add(null);
        }
               
        if (o.getMajka()!=null){
             v.add(o.getMajka().getOznaka());
        } else {
            v.add(null);
        }
        v.add(no.getCena());
        v.add(o.getOpis());
        v.add(no.getNapomena());
        v.add(o.getLeglo());
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
    private com.toedter.calendar.JDateChooser jDatum;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JButton jSnimiButton;
    private com.toedter.components.JSpinField jSpinField1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextTroskovi;
    private javax.swing.JSpinner jminutaKraj;
    private javax.swing.JSpinner jminutaPocetak;
    private javax.swing.JSpinner jsatiKraj;
    private javax.swing.JSpinner jsatiPocetak;
    private javax.swing.JPanel nabavkaPanel;
    // End of variables declaration//GEN-END:variables
}