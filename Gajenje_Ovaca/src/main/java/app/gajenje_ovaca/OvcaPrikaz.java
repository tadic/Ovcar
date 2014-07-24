/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.gajenje_ovaca;

import app.logic.Logic;
import app.model.Jagnjenje;
import app.model.Linija;
import app.model.Ovca;
import com.apple.laf.AquaTextFieldBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ivantadic
 */
public class OvcaPrikaz extends javax.swing.JPanel {
    private Ovca ovca;
    private Logic logic;
    private JPanel mainPanel;

    /**
     * Creates new form Podaci_ovaca
     */
    public OvcaPrikaz(JPanel mainpanel, Logic logic, Ovca o) {
        this.logic = logic;
        this.ovca = o;
        this.mainPanel = mainpanel;
        initComponents();
        jScrollPane1.getViewport().setBackground(Color.white);

        //setBoldFontToColumn(3);
        
        setSheepToPanel(o);
    }
    
    private void setTitleToPanel(JPanel panel, String title){
        TitledBorder tb = new TitledBorder(title);
        tb.setTitleFont(new Font("Noteworthy", Font.BOLD, 18));
        tb.setTitleColor(Color.red.darker());
        panel.setBorder(tb); 
    }

    private void setSheepToPanel(Ovca o){
        setOsnovniPodaci(o);
        setRodjenje(o);
        setNabavka(o);  
        setJagnjenja(o);
    }
    private void setRodjenje(Ovca o){
        setTitleToPanel(jPanelRodjenje, "Rođenje " + o.getDatumRodjenja() + " -  -");
        if (!o.getStatus().equals("zamisljena")){
            jOtac.setText(o.getOtac().getOznaka());
            jMajka.setText(o.getMajka().getOznaka());
            // jRodjenjeNapomena.setText(o.getNabavka().getNapomena());
               
        }
                if (o.getLeglo()!=null){
            jLeglo.setText(o.getLeglo().toString());
        } else {
            jLeglo.setText("?");
        }
        
    }
    private void setOsnovniPodaci(Ovca o){
                if (o.getPol()=='m'){
            jPol.setSelectedIndex(0);
        } else {
            jPol.setSelectedIndex(1);
        }
        jProcenatR.setText(Float.toString(o.getProcenatR()));
        jStarost.setText(o.getStarost());
        jLinija.setModel(new DefaultComboBoxModel(sveLinije().toArray()));

        if (o.getLinija()!=null){
             System.out.println("Linija :" + o.getLinija().getImeLinije());
            jLinija.setSelectedIndex(o.getLinija().getId()-1);
        }    
        jOznaka.setText(o.getOznaka());
        jNadimak.setText(o.getNadimak());
        jStarost.setText(o.getStarost());
        jOpis.setText(o.getOpis());
        jDosije.setText(o.getPracenje());
        
    }
    private void setNabavka(Ovca o){
       if (o.getNabavka()!=null){
          setTitleToPanel(jPanelNabavka, "Nabavka " + o.getNabavka().getAktivnost().getDan().toString() + " - " + o.getNabavka().getAktivnost().getLokacija() + " -");
         jCenaNabavke.setText(Float.toString(o.getNabavka().getCena()));
         jDetaljiNabavke.setText(o.getNabavka().getNapomena());
       } else {
          jPanelNabavka.setVisible(false);
       }
    }
    private void setJagnjenja(Ovca o){
         
        DefaultTableModel tm = (DefaultTableModel) jTableJagnjenja.getModel();
        tm.setRowCount(0);
        if (o.getListaJagnjenja()!=null && !o.getListaJagnjenja().isEmpty()){
            
            String datum = o.getListaJagnjenja().get(0).getJagnje().getDatumRodjenja();
            int brojJagnjadi = 0;
            int brojMuskih = 0;
            int zivih = 0;
            String napomena = "";
            for (Jagnjenje j: o.getListaJagnjenja()){
                if (j.getJagnje().getDatumRodjenja().equals(datum)){
                    brojJagnjadi++;
                    if (j.isJelZivo()){
                        zivih ++;
                        napomena = j.getNapomena();
                        
                    }
                    if (j.getJagnje().getPol()=='m'){
                        brojMuskih++;
                    }
                } else {
                    insertRowInTable(tm, vectorFrom(datum, brojJagnjadi, zivih, brojJagnjadi-brojMuskih, napomena));
                    napomena="";
                    zivih=0;
                    brojJagnjadi=1;
                    brojMuskih=0;
                    if (j.isJelZivo()){
                        zivih ++;
                        napomena = j.getNapomena();
                        
                    }
                    if (j.getJagnje().getPol()=='m'){
                        brojMuskih++;
                    }
                    datum = j.getAktivnost().getDan().toString();
                }
            }
            insertRowInTable(tm, vectorFrom(datum, brojJagnjadi, zivih, brojJagnjadi-brojMuskih, napomena));
            jTableJagnjenja.setModel(tm);        
        }
         setTitleToPanel(jPanelJagnjenja, "Jagnjenja (" + tm.getRowCount() + ")");
       
    }
    private Vector vectorFrom(Object ...params){
         Vector vrsta = new Vector();
         for (int i=0; i<params.length; i++){
                    vrsta.add(params[i]);
         }
         return vrsta;
    }
    private void insertRowInTable(DefaultTableModel model, Vector v){
       model.addRow(v);
    }
    private List<Linija> sveLinije(){
        return  logic.getLinije();
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
        jTableJagnjenja.getColumnModel().getColumn(n).setCellRenderer(r);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanelRodjenje = new javax.swing.JPanel();
        jTezina = new javax.swing.JTextField();
        jLeglo = new javax.swing.JTextField();
        jMajka = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jRodjenjeNapomena = new javax.swing.JTextField();
        jOtac = new javax.swing.JTextField();
        jTezina1 = new javax.swing.JTextField();
        jPanelNabavka = new javax.swing.JPanel();
        jCenaNabavke = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jDetaljiNabavke = new javax.swing.JTextField();
        jCenaNabavke1 = new javax.swing.JTextField();
        jPanel25 = new javax.swing.JPanel();
        jScrollPane19 = new javax.swing.JScrollPane();
        jDosije = new javax.swing.JTextArea();
        jPanelOpstipodatci = new javax.swing.JPanel();
        jNadimak = new javax.swing.JTextField();
        jProcenatR = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPol = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jOpis = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLinija = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jStatus1 = new javax.swing.JTextField();
        jOznaka = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jStarost = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanelJagnjenja = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableJagnjenja = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane18 = new javax.swing.JScrollPane();
        jTable18 = new javax.swing.JTable();
        jPanel24 = new javax.swing.JPanel();
        jOznaka8 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jStatus10 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jOznaka9 = new javax.swing.JTextField();
        jCenaNabavke2 = new javax.swing.JTextField();
        jPanel23 = new javax.swing.JPanel();
        jOznaka7 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPDFSnimiButton = new javax.swing.JButton();
        jOtkaziButton = new javax.swing.JButton();
        jIzmeniButton = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanelRodjenje.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Rođenje 24-12-2014 selo Korićani", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Monaco", 1, 18), new java.awt.Color(153, 0, 51))); // NOI18N

        jTezina.setEditable(false);
        jTezina.setFont(new java.awt.Font("Damascus", 0, 14)); // NOI18N
        jTezina.setForeground(new java.awt.Color(0, 0, 153));
        jTezina.setText("    ");
        jTezina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTezinaActionPerformed(evt);
            }
        });

        jLeglo.setEditable(false);
        jLeglo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLeglo.setForeground(new java.awt.Color(0, 0, 153));
        jLeglo.setText("2");
        jLeglo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLegloActionPerformed(evt);
            }
        });

        jMajka.setEditable(false);
        jMajka.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jMajka.setForeground(new java.awt.Color(0, 0, 153));
        jMajka.setText("Nema");
        jMajka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMajkaActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel20.setText("Težina");

        jLabel21.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel21.setText("Otac");

        jLabel22.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel22.setText("Iz legla od");

        jLabel23.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel23.setText("Majka");

        jLabel24.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel24.setText("Napomena");

        jRodjenjeNapomena.setEditable(false);
        jRodjenjeNapomena.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jRodjenjeNapomena.setForeground(new java.awt.Color(0, 0, 153));
        jRodjenjeNapomena.setText("napomena");
        jRodjenjeNapomena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRodjenjeNapomenaActionPerformed(evt);
            }
        });

        jOtac.setEditable(false);
        jOtac.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jOtac.setForeground(new java.awt.Color(0, 0, 153));
        jOtac.setText("Nema");
        jOtac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOtacActionPerformed(evt);
            }
        });

        jTezina1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTezina1.setForeground(new java.awt.Color(0, 0, 153));
        jTezina1.setText("kg");
        jTezina1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTezina1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanelRodjenjeLayout = new org.jdesktop.layout.GroupLayout(jPanelRodjenje);
        jPanelRodjenje.setLayout(jPanelRodjenjeLayout);
        jPanelRodjenjeLayout.setHorizontalGroup(
            jPanelRodjenjeLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelRodjenjeLayout.createSequentialGroup()
                .addContainerGap()
                .add(jPanelRodjenjeLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanelRodjenjeLayout.createSequentialGroup()
                        .add(jPanelRodjenjeLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel22, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabel23, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .add(6, 6, 6))
                    .add(jPanelRodjenjeLayout.createSequentialGroup()
                        .add(jLabel24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 89, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(26, 26, 26)))
                .add(jPanelRodjenjeLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanelRodjenjeLayout.createSequentialGroup()
                        .add(jPanelRodjenjeLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanelRodjenjeLayout.createSequentialGroup()
                                .add(jMajka)
                                .add(31, 31, 31))
                            .add(jPanelRodjenjeLayout.createSequentialGroup()
                                .add(jLeglo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .add(jPanelRodjenjeLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jLabel21, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabel20, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanelRodjenjeLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jOtac, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 138, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jPanelRodjenjeLayout.createSequentialGroup()
                                .add(jTezina, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jTezina1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 38, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                    .add(jRodjenjeNapomena))
                .addContainerGap())
        );
        jPanelRodjenjeLayout.setVerticalGroup(
            jPanelRodjenjeLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelRodjenjeLayout.createSequentialGroup()
                .addContainerGap()
                .add(jPanelRodjenjeLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jMajka, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jOtac, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanelRodjenjeLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanelRodjenjeLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLeglo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jTezina1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jTezina, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanelRodjenjeLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jRodjenjeNapomena, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(0, 0, Short.MAX_VALUE))
        );

        jPanelNabavka.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Nabavljena 24-12-2014 s. Korićani", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Monaco", 1, 18), new java.awt.Color(153, 0, 51))); // NOI18N

        jCenaNabavke.setEditable(false);
        jCenaNabavke.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jCenaNabavke.setForeground(new java.awt.Color(0, 0, 153));
        jCenaNabavke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCenaNabavkeActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel13.setText("Cena");

        jLabel25.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel25.setText("Detalji nabavke");

        jDetaljiNabavke.setEditable(false);
        jDetaljiNabavke.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jDetaljiNabavke.setForeground(new java.awt.Color(0, 0, 153));
        jDetaljiNabavke.setText("jTextField1");
        jDetaljiNabavke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDetaljiNabavkeActionPerformed(evt);
            }
        });

        jCenaNabavke1.setEditable(false);
        jCenaNabavke1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jCenaNabavke1.setForeground(new java.awt.Color(0, 0, 153));
        jCenaNabavke1.setText("€");
        jCenaNabavke1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCenaNabavke1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanelNabavkaLayout = new org.jdesktop.layout.GroupLayout(jPanelNabavka);
        jPanelNabavka.setLayout(jPanelNabavkaLayout);
        jPanelNabavkaLayout.setHorizontalGroup(
            jPanelNabavkaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelNabavkaLayout.createSequentialGroup()
                .addContainerGap()
                .add(jPanelNabavkaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanelNabavkaLayout.createSequentialGroup()
                        .add(jLabel13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 121, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jCenaNabavke, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 61, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jCenaNabavke1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(0, 0, Short.MAX_VALUE))
                    .add(jPanelNabavkaLayout.createSequentialGroup()
                        .add(jLabel25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 121, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jDetaljiNabavke)))
                .addContainerGap())
        );
        jPanelNabavkaLayout.setVerticalGroup(
            jPanelNabavkaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelNabavkaLayout.createSequentialGroup()
                .addContainerGap()
                .add(jPanelNabavkaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jCenaNabavke, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 26, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jCenaNabavke1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 26, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanelNabavkaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jDetaljiNabavke, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 26, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(0, 0, Short.MAX_VALUE))
        );

        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Dosije", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Monaco", 1, 18), new java.awt.Color(153, 0, 51))); // NOI18N

        jDosije.setEditable(false);
        jDosije.setColumns(20);
        jDosije.setFont(new java.awt.Font("Osaka", 0, 14)); // NOI18N
        jDosije.setForeground(new java.awt.Color(0, 0, 153));
        jDosije.setRows(5);
        jScrollPane19.setViewportView(jDosije);

        org.jdesktop.layout.GroupLayout jPanel25Layout = new org.jdesktop.layout.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane19)
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel25Layout.createSequentialGroup()
                .add(jScrollPane19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 120, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, Short.MAX_VALUE))
        );

        jNadimak.setEditable(false);
        jNadimak.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jNadimak.setForeground(new java.awt.Color(153, 0, 0));
        jNadimak.setText("Na farmi");
        jNadimak.setActionCommand("<Not Set>");
        jNadimak.setAlignmentX(0.0F);
        jNadimak.setAlignmentY(0.0F);
        jNadimak.setMinimumSize(new java.awt.Dimension(0, 16));
        jNadimak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNadimakActionPerformed(evt);
            }
        });

        jProcenatR.setEditable(false);
        jProcenatR.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jProcenatR.setForeground(new java.awt.Color(0, 0, 153));
        jProcenatR.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jProcenatR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jProcenatRActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel6.setText("Status:");

        jPol.setBackground(new java.awt.Color(255, 255, 255));
        jPol.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jPol.setForeground(new java.awt.Color(0, 0, 153));
        jPol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "muško", "žensko" }));
        jPol.setEnabled(false);
        jPol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPolActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel5.setText("proc. R:");

        jOpis.setEditable(false);
        jOpis.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jOpis.setForeground(new java.awt.Color(0, 0, 153));
        jOpis.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jOpis.setText("malo crno sa belom desnom carrrrgggrapom");
        jOpis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOpisActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel14.setText("Opis grla:");

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel7.setText("Linija:");

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 153));
        jLabel8.setText("%");

        jLinija.setBackground(new java.awt.Color(255, 255, 255));
        jLinija.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLinija.setForeground(new java.awt.Color(0, 0, 153));
        jLinija.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "muško", "žensko" }));
        jLinija.setEnabled(false);
        jLinija.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLinijaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel1.setText("Grlo:");

        jStatus1.setEditable(false);
        jStatus1.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jStatus1.setForeground(new java.awt.Color(0, 51, 51));
        jStatus1.setText("Na farmi");
        jStatus1.setActionCommand("<Not Set>");
        jStatus1.setAlignmentX(0.0F);
        jStatus1.setAlignmentY(0.0F);
        jStatus1.setMinimumSize(new java.awt.Dimension(0, 16));
        jStatus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jStatus1ActionPerformed(evt);
            }
        });

        jOznaka.setEditable(false);
        jOznaka.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jOznaka.setForeground(new java.awt.Color(153, 0, 0));
        jOznaka.setText("Na farmi");
        jOznaka.setActionCommand("<Not Set>");
        jOznaka.setAlignmentX(0.0F);
        jOznaka.setAlignmentY(0.0F);
        jOznaka.setMinimumSize(new java.awt.Dimension(0, 16));
        jOznaka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOznakaActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel9.setText("Starost:");

        jStarost.setEditable(false);
        jStarost.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jStarost.setForeground(new java.awt.Color(0, 0, 153));
        jStarost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jStarostActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel10.setText("Oznaka");

        org.jdesktop.layout.GroupLayout jPanelOpstipodatciLayout = new org.jdesktop.layout.GroupLayout(jPanelOpstipodatci);
        jPanelOpstipodatci.setLayout(jPanelOpstipodatciLayout);
        jPanelOpstipodatciLayout.setHorizontalGroup(
            jPanelOpstipodatciLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelOpstipodatciLayout.createSequentialGroup()
                .addContainerGap()
                .add(jPanelOpstipodatciLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanelOpstipodatciLayout.createSequentialGroup()
                        .add(jPanelOpstipodatciLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel14)
                            .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 67, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(jPanelOpstipodatciLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanelOpstipodatciLayout.createSequentialGroup()
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jOpis, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE))
                            .add(jPanelOpstipodatciLayout.createSequentialGroup()
                                .add(6, 6, 6)
                                .add(jStatus1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 156, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                    .add(jPanelOpstipodatciLayout.createSequentialGroup()
                        .add(jPanelOpstipodatciLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jPanelOpstipodatciLayout.createSequentialGroup()
                                .add(jPol, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 102, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel5)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jProcenatR))
                            .add(jPanelOpstipodatciLayout.createSequentialGroup()
                                .add(jLabel1)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jNadimak, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 193, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .add(jPanelOpstipodatciLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanelOpstipodatciLayout.createSequentialGroup()
                                .add(25, 25, 25)
                                .add(jLabel9)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(jStarost))
                            .add(jPanelOpstipodatciLayout.createSequentialGroup()
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanelOpstipodatciLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanelOpstipodatciLayout.createSequentialGroup()
                                        .add(jLabel7)
                                        .add(21, 21, 21)
                                        .add(jLinija, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .add(jPanelOpstipodatciLayout.createSequentialGroup()
                                        .add(jLabel10)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                        .add(jOznaka, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                .addContainerGap())
        );
        jPanelOpstipodatciLayout.setVerticalGroup(
            jPanelOpstipodatciLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelOpstipodatciLayout.createSequentialGroup()
                .add(10, 10, 10)
                .add(jPanelOpstipodatciLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jNadimak, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jOznaka, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanelOpstipodatciLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jPol, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel7)
                    .add(jLabel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLinija, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jProcenatR, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanelOpstipodatciLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanelOpstipodatciLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jStatus1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanelOpstipodatciLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jStarost, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanelOpstipodatciLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jOpis, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanelJagnjenja.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Jagnjenja (2)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Monaco", 1, 18), new java.awt.Color(153, 0, 51))); // NOI18N

        jTableJagnjenja.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jTableJagnjenja.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Datum", "ojagnjeno", "zivih", "zenskih", "Napomena"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableJagnjenja.setAutoCreateRowSorter(true);
        jTableJagnjenja.setEnabled(false);
        jScrollPane1.setViewportView(jTableJagnjenja);
        jTableJagnjenja.getColumnModel().getColumn(1).setMaxWidth(60);
        jTableJagnjenja.getColumnModel().getColumn(2).setMaxWidth(60);
        jTableJagnjenja.getColumnModel().getColumn(3).setMaxWidth(60);

        org.jdesktop.layout.GroupLayout jPanelJagnjenjaLayout = new org.jdesktop.layout.GroupLayout(jPanelJagnjenja);
        jPanelJagnjenja.setLayout(jPanelJagnjenjaLayout);
        jPanelJagnjenjaLayout.setHorizontalGroup(
            jPanelJagnjenjaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelJagnjenjaLayout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelJagnjenjaLayout.setVerticalGroup(
            jPanelJagnjenjaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelJagnjenjaLayout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 78, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Redovna vakcinacija (5)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Monaco", 1, 18), new java.awt.Color(153, 0, 51))); // NOI18N

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable5.setEnabled(false);
        jScrollPane5.setViewportView(jTable5);

        org.jdesktop.layout.GroupLayout jPanel9Layout = new org.jdesktop.layout.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane5)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lečenja (1)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Monaco", 1, 18), new java.awt.Color(153, 0, 51))); // NOI18N

        jTable18.setFont(new java.awt.Font("Damascus", 0, 14)); // NOI18N
        jTable18.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable18.setEnabled(false);
        jScrollPane18.setViewportView(jTable18);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane18)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 71, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Prodata 25-09-2015", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Monaco", 1, 18), new java.awt.Color(153, 0, 51))); // NOI18N
        jPanel24.setEnabled(false);

        jOznaka8.setEditable(false);
        jOznaka8.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jOznaka8.setForeground(new java.awt.Color(0, 0, 153));
        jOznaka8.setText("jTextField1");
        jOznaka8.setBorder(null);
        jOznaka8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOznaka8ActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel28.setText("Kome");

        jLabel29.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel29.setText("Cena");

        jStatus10.setEditable(false);
        jStatus10.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jStatus10.setForeground(new java.awt.Color(0, 0, 153));
        jStatus10.setBorder(null);
        jStatus10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jStatus10ActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel30.setText("Detalji prodaje");

        jOznaka9.setEditable(false);
        jOznaka9.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jOznaka9.setForeground(new java.awt.Color(0, 0, 153));
        jOznaka9.setText("jTextField1");
        jOznaka9.setBorder(null);
        jOznaka9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOznaka9ActionPerformed(evt);
            }
        });

        jCenaNabavke2.setEditable(false);
        jCenaNabavke2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jCenaNabavke2.setForeground(new java.awt.Color(0, 0, 153));
        jCenaNabavke2.setText("€");
        jCenaNabavke2.setBorder(null);
        jCenaNabavke2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCenaNabavke2ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel24Layout = new org.jdesktop.layout.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel24Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel24Layout.createSequentialGroup()
                        .add(jLabel30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 135, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jOznaka9))
                    .add(jPanel24Layout.createSequentialGroup()
                        .add(jLabel28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 51, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jOznaka8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 152, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(jLabel29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 51, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jStatus10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 39, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jCenaNabavke2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel24Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jOznaka8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jCenaNabavke2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jStatus10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel24Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jOznaka9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Uginula 25-09-2015", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Monaco", 1, 18), new java.awt.Color(153, 0, 51))); // NOI18N
        jPanel23.setForeground(new java.awt.Color(0, 0, 153));

        jOznaka7.setEditable(false);
        jOznaka7.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jOznaka7.setForeground(new java.awt.Color(0, 0, 153));
        jOznaka7.setText("jTextField1");
        jOznaka7.setBorder(null);
        jOznaka7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOznaka7ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel26.setText("Razlog");

        org.jdesktop.layout.GroupLayout jPanel23Layout = new org.jdesktop.layout.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel26, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 68, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jOznaka7)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel23Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel23Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel26, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jOznaka7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Noteworthy", 1, 44)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("Podatci o grlu");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel2.setAlignmentY(3.0F);
        jLabel2.setMaximumSize(new java.awt.Dimension(219, 40));
        jLabel2.setMinimumSize(new java.awt.Dimension(219, 40));
        jLabel2.setPreferredSize(new java.awt.Dimension(219, 40));
        jLabel2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jPDFSnimiButton.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jPDFSnimiButton.setText("Preuzmi PDF");
        jPDFSnimiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPDFSnimiButtonActionPerformed(evt);
            }
        });

        jOtkaziButton.setBackground(new java.awt.Color(0, 204, 255));
        jOtkaziButton.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jOtkaziButton.setText("Izadji");
        jOtkaziButton.setSelected(true);
        jOtkaziButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOtkaziButtonActionPerformed(evt);
            }
        });

        jIzmeniButton.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jIzmeniButton.setText("Izmeni");
        jIzmeniButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIzmeniButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanelNabavka, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanelRodjenje, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel25, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 410, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(0, 0, Short.MAX_VALUE))
                    .add(jPanelOpstipodatci, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(89, 89, 89)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(jIzmeniButton)
                        .add(27, 27, 27)
                        .add(jPDFSnimiButton)
                        .add(18, 18, 18)
                        .add(jOtkaziButton)
                        .add(20, 20, 20))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanelJagnjenja, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jPanel9, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jPanel24, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jPanel23, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanelJagnjenja, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 18, Short.MAX_VALUE)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jIzmeniButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 37, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jPDFSnimiButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 37, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jOtkaziButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 37, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(20, 20, 20))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 65, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanelOpstipodatci, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanelRodjenje, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanelNabavka, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel25, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 298;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 0, 28, 0);
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jProcenatRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jProcenatRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jProcenatRActionPerformed

    private void jNadimakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNadimakActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jNadimakActionPerformed

    private void jPolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPolActionPerformed

    private void jOpisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOpisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jOpisActionPerformed

    private void jTezinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTezinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTezinaActionPerformed

    private void jLegloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLegloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLegloActionPerformed

    private void jMajkaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMajkaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMajkaActionPerformed

    private void jRodjenjeNapomenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRodjenjeNapomenaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRodjenjeNapomenaActionPerformed

    private void jOtacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOtacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jOtacActionPerformed

    private void jCenaNabavkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCenaNabavkeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCenaNabavkeActionPerformed

    private void jDetaljiNabavkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDetaljiNabavkeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDetaljiNabavkeActionPerformed

    private void jOznaka7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOznaka7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jOznaka7ActionPerformed

    private void jOznaka8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOznaka8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jOznaka8ActionPerformed

    private void jStatus10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jStatus10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jStatus10ActionPerformed

    private void jOznaka9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOznaka9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jOznaka9ActionPerformed

    private void jCenaNabavke1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCenaNabavke1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCenaNabavke1ActionPerformed

    private void jTezina1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTezina1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTezina1ActionPerformed

    private void jCenaNabavke2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCenaNabavke2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCenaNabavke2ActionPerformed

    private void prepareForEdit(JTextField jtext){
        jtext.setEditable(true);
        jtext.setBorder(new AquaTextFieldBorder());
        jtext.setBackground(new Color(245,245,200));
    }
    private void disableEdit(JTextField jtext){
        jtext.setEditable(false);
        jtext.setBorder(null);
        jtext.setBackground(new Color(255,255,255));
    }    
    
    private void jIzmeniButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIzmeniButtonActionPerformed
       prepareForEdit(jOznaka);
       prepareForEdit(jNadimak);
        jPol.setEnabled(true);
       jPol.setBackground(Color.yellow.brighter().brighter());
       jLinija.setEnabled(true);
       jLinija.setBackground(Color.yellow.brighter().brighter());
       prepareForEdit(jProcenatR);
      
       prepareForEdit(jOpis);
       prepareForEdit(jTezina);
       jDosije.setEditable(true);
       jDosije.setBackground(new Color(245,245,200));
       
       jPDFSnimiButton.setText("Snimi izmene");
       jIzmeniButton.setEnabled(false);
       
    }//GEN-LAST:event_jIzmeniButtonActionPerformed

    private void jOtkaziButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOtkaziButtonActionPerformed
        mainPanel.setLayout(new GridLayout());
        mainPanel.removeAll();
        mainPanel.add(new Podaci_ovaca(mainPanel, logic));
        mainPanel.revalidate();
        repaint();
    }//GEN-LAST:event_jOtkaziButtonActionPerformed

    private void azurirajOvcu(){
        pickOvcaFromForm();
        logic.updateOvca(ovca);
        mainPanel.removeAll();
        mainPanel.add(new OvcaPrikaz(mainPanel, logic, ovca));
        mainPanel.revalidate();
        repaint();
    }
    
    private void pickOvcaFromForm() {
        System.out.println("id "+ovca.getId());
        ovca.setOznaka(jOznaka.getText());
        ovca.setNadimak(jNadimak.getText());
        ovca.setProcenatR(jProcenatR.getText());
        ovca.setPol(jPol.getSelectedItem().toString().charAt(0));
        ovca.setLinija((Linija) jLinija.getSelectedItem());
        ovca.setOpis(jOpis.getText());
        ovca.setPracenje(jDosije.getText());
    }
    private void jPDFSnimiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPDFSnimiButtonActionPerformed
        if (jPDFSnimiButton.getText().equals("Preuzmi PDF")){
            
        } else {
            azurirajOvcu();        
        }
    }//GEN-LAST:event_jPDFSnimiButtonActionPerformed

    private void jLinijaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLinijaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLinijaActionPerformed

    private void jStatus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jStatus1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jStatus1ActionPerformed

    private void jOznakaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOznakaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jOznakaActionPerformed

    private void jStarostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jStarostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jStarostActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField jCenaNabavke;
    private javax.swing.JTextField jCenaNabavke1;
    private javax.swing.JTextField jCenaNabavke2;
    private javax.swing.JTextField jDetaljiNabavke;
    private javax.swing.JTextArea jDosije;
    private javax.swing.JButton jIzmeniButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jLeglo;
    private javax.swing.JComboBox jLinija;
    private javax.swing.JTextField jMajka;
    private javax.swing.JTextField jNadimak;
    private javax.swing.JTextField jOpis;
    private javax.swing.JTextField jOtac;
    private javax.swing.JButton jOtkaziButton;
    private javax.swing.JTextField jOznaka;
    private javax.swing.JTextField jOznaka7;
    private javax.swing.JTextField jOznaka8;
    private javax.swing.JTextField jOznaka9;
    private javax.swing.JButton jPDFSnimiButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelJagnjenja;
    private javax.swing.JPanel jPanelNabavka;
    private javax.swing.JPanel jPanelOpstipodatci;
    private javax.swing.JPanel jPanelRodjenje;
    private javax.swing.JComboBox jPol;
    private javax.swing.JTextField jProcenatR;
    private javax.swing.JTextField jRodjenjeNapomena;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jStarost;
    private javax.swing.JTextField jStatus1;
    private javax.swing.JTextField jStatus10;
    private javax.swing.JTable jTable18;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTableJagnjenja;
    private javax.swing.JTextField jTezina;
    private javax.swing.JTextField jTezina1;
    // End of variables declaration//GEN-END:variables


}
