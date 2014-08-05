/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.gajenje_ovaca;

import app.Reports.OvcaIzvestaj;
import app.logic.Logic;
import app.model.Aktivnost;
import app.model.Dan;
import app.model.Jagnjenje;
import app.model.Linija;
import app.model.Ovca;
import app.model.Vakcinacija;
import com.apple.laf.AquaTextFieldBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
        
        setSheepToPanel(ovca);
    }
    
    private void setTitleToPanel(JPanel panel, String title){
        TitledBorder tb = new TitledBorder(title);
        tb.setTitleFont(new Font("Dialog", Font.PLAIN, 18));
        tb.setTitleColor(Color.red.darker());
        panel.setBorder(tb); 
    }

    private void setSheepToPanel(Ovca o){
        setOsnovniPodaci(o);
        setRodjenje(o);
        setNabavka(o);  
        setJagnjenja(o);
        setVakcinacijeLecenja(o);
        setProdaja(o);
        setUginuce(o);
    }
    private void setUginuce(Ovca o){
        if (o.getUginuce()==null){
            jPanelUginuce.setVisible(false);
        } else {
            setTitleToPanel(jPanelUginuce, "Uginulo " + o.getUginuce().getA().getDan().toString());
            jRazlogUginuca.setText(o.getUginuce().getRazlog());
        }
    }
    private void setProdaja(Ovca o){
        if (o.getProdaja()==null){
            jPanelProdaja.setVisible(false);
        }
    }
    private void setRodjenje(Ovca o){
        setTitleToPanel(jPanelRodjenje, "Rođenje " + o.getDatumRodjenja() + " -  -");
        if (o.getOtac()!=null){
            jOtac.setText(o.getOtac().toString());
        }
        if (o.getMajka()!=null){
            jMajka.setText(o.getMajka().toString());
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
        jStatus1.setText(o.getStatus());
        jProcenatR.setText(Float.toString(o.getProcenatR()));
        jStarost.setText(o.getStarost());
        jLinija.setModel(new DefaultComboBoxModel(sveLinije().toArray()));

        if (o.getLinija()!=null){
            // System.out.println("Linija :" + o.getLinija().getImeLinije());
            jLinija.setSelectedIndex(o.getLinija().getId()-1);
        }    
        jOznaka.setText(o.getOznaka());
        jNadimak.setText(o.getNadimak());
        jStarost.setText(o.getStarost());
        jOpis.setText(o.getOpis());
        jDosije.setText(o.getPracenje());
        setTitleToPanel(jPanelDosije, "Dosije");
        
    }
    private void setNabavka(Ovca o){
       if (o.getNabavka()!=null){
           Dan d = logic.getDan(o.getNabavka().getAktivnost().getDan().getId());
          setTitleToPanel(jPanelNabavka, "Nabavka " + d.toString() + " - " + o.getNabavka().getAktivnost().getLokacija() + " -");
         jCenaNabavke.setText(Float.toString(Aktivnost.round(o.getNabavka().getCena(), 1)));
         jDetaljiNabavke.setText(o.getNabavka().getNapomena());
       } else {
          jPanelNabavka.setVisible(false);
       }
    }
    private void setJagnjenja(Ovca o){
         
        DefaultTableModel tm = (DefaultTableModel) jTableJagnjenja.getModel();
        tm.setRowCount(0);
        if (o.getListaJagnjenja()!=null && !o.getListaJagnjenja().isEmpty()){
            
            String datum = o.getListaJagnjenja().get(0).getSheep().getDatumRodjenja();
            int brojJagnjadi = 0;
            int brojMuskih = 0;
            int zivih = 0;
            String napomena = "";
            for (Jagnjenje j: o.getListaJagnjenja()){
                if (j.getSheep().getDatumRodjenja().equals(datum)){
                    brojJagnjadi++;
                    if (j.isJelZivo()){
                        zivih ++;
                        napomena = j.getNapomena();
                        
                    }
                    if (j.getSheep().getPol()=='m'){
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
                    if (j.getSheep().getPol()=='m'){
                        brojMuskih++;
                    }
                    datum = j.getAktivnost().getDan().toString();
                }
            }
            insertRowInTable(tm, vectorFrom(datum, brojJagnjadi, zivih, brojJagnjadi-brojMuskih, napomena));
            jTableJagnjenja.setModel(tm);        
        }
         setTitleToPanel(jPanelJagnjenja, "Jagnjenja (" + tm.getRowCount() + ")");
         setBoldFontToColumn(jTableJagnjenja, 1);
    }
    
    private void setVakcinacijeLecenja(Ovca o){
        if (o.getVakcinacije()==null || o.getVakcinacije().isEmpty()){
            setTitleToPanel(jPanelVakcinacije,  "Redovne vakcinacije (0)");
            setTitleToPanel(jPanelLecenja,  "Lečenja (0)");
            return;
        }
        DefaultTableModel tm1 = (DefaultTableModel) jTableVakcinacije.getModel();
        tm1.setRowCount(0);
        DefaultTableModel tm2 = (DefaultTableModel) jTableLecenja.getModel();
        tm2.setRowCount(0);
        for (Vakcinacija v: o.getVakcinacije()){
            if (v.getJelRedovno()){
                insertRowInTable(tm1, vectorFrom(v.getAktivnost().getDan().toString(), 
                        v.getRazlog(), v.getSredstvo(), v.getNapomena()));
            } else {
                insertRowInTable(tm2, vectorFrom(v.getAktivnost().getDan().toString(), 
                        v.getRazlog(), v.getSredstvo(), v.getNapomena()));
            }
        }
        setTitleToPanel(jPanelVakcinacije, "Redovne vakcinacije (" + tm1.getRowCount() + ")");
        setTitleToPanel(jPanelLecenja, "Lečenja (" + tm2.getRowCount() + ")");
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
    
    private void setBoldFontToColumn(JTable table, int n){
        DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
            Font f = new Font ("Dialog", Font.BOLD, 12);

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                    row, column);
                setFont(f);
                return this;
            }
        
        };
        table.getColumnModel().getColumn(n).setCellRenderer(r);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jPanelDosije = new javax.swing.JPanel();
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
        jPanelVakcinacije = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableVakcinacije = new javax.swing.JTable();
        jPanelLecenja = new javax.swing.JPanel();
        jScrollPane18 = new javax.swing.JScrollPane();
        jTableLecenja = new javax.swing.JTable();
        jPanelProdaja = new javax.swing.JPanel();
        jOznaka8 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jStatus10 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jOznaka9 = new javax.swing.JTextField();
        jCenaNabavke2 = new javax.swing.JTextField();
        jPanelUginuce = new javax.swing.JPanel();
        jRazlogUginuca = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPDFSnimiButton = new javax.swing.JButton();
        jOtkaziButton = new javax.swing.JButton();
        jIzmeniButton = new javax.swing.JButton();

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
        jMajka.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jMajkaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jMajkaKeyReleased(evt);
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
        jOtac.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jOtacKeyPressed(evt);
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

        javax.swing.GroupLayout jPanelRodjenjeLayout = new javax.swing.GroupLayout(jPanelRodjenje);
        jPanelRodjenje.setLayout(jPanelRodjenjeLayout);
        jPanelRodjenjeLayout.setHorizontalGroup(
            jPanelRodjenjeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRodjenjeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelRodjenjeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelRodjenjeLayout.createSequentialGroup()
                        .addGroup(jPanelRodjenjeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6))
                    .addGroup(jPanelRodjenjeLayout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)))
                .addGroup(jPanelRodjenjeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelRodjenjeLayout.createSequentialGroup()
                        .addGroup(jPanelRodjenjeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelRodjenjeLayout.createSequentialGroup()
                                .addComponent(jMajka)
                                .addGap(31, 31, 31))
                            .addGroup(jPanelRodjenjeLayout.createSequentialGroup()
                                .addComponent(jLeglo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanelRodjenjeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelRodjenjeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jOtac, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelRodjenjeLayout.createSequentialGroup()
                                .addComponent(jTezina, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTezina1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jRodjenjeNapomena))
                .addContainerGap())
        );
        jPanelRodjenjeLayout.setVerticalGroup(
            jPanelRodjenjeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRodjenjeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelRodjenjeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jMajka, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jOtac, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelRodjenjeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelRodjenjeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLeglo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTezina1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTezina, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelRodjenjeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRodjenjeNapomena, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
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

        javax.swing.GroupLayout jPanelNabavkaLayout = new javax.swing.GroupLayout(jPanelNabavka);
        jPanelNabavka.setLayout(jPanelNabavkaLayout);
        jPanelNabavkaLayout.setHorizontalGroup(
            jPanelNabavkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNabavkaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelNabavkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelNabavkaLayout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCenaNabavke, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCenaNabavke1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelNabavkaLayout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDetaljiNabavke)))
                .addContainerGap())
        );
        jPanelNabavkaLayout.setVerticalGroup(
            jPanelNabavkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNabavkaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelNabavkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCenaNabavke, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCenaNabavke1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelNabavkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDetaljiNabavke, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanelDosije.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Dosije", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Monaco", 1, 18), new java.awt.Color(153, 0, 51))); // NOI18N

        jDosije.setEditable(false);
        jDosije.setColumns(20);
        jDosije.setFont(new java.awt.Font("Osaka", 0, 14)); // NOI18N
        jDosije.setForeground(new java.awt.Color(0, 0, 153));
        jDosije.setRows(5);
        jScrollPane19.setViewportView(jDosije);

        javax.swing.GroupLayout jPanelDosijeLayout = new javax.swing.GroupLayout(jPanelDosije);
        jPanelDosije.setLayout(jPanelDosijeLayout);
        jPanelDosijeLayout.setHorizontalGroup(
            jPanelDosijeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDosijeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane19)
                .addContainerGap())
        );
        jPanelDosijeLayout.setVerticalGroup(
            jPanelDosijeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDosijeLayout.createSequentialGroup()
                .addComponent(jScrollPane19, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                .addContainerGap())
        );

        jNadimak.setEditable(false);
        jNadimak.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
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

        javax.swing.GroupLayout jPanelOpstipodatciLayout = new javax.swing.GroupLayout(jPanelOpstipodatci);
        jPanelOpstipodatci.setLayout(jPanelOpstipodatciLayout);
        jPanelOpstipodatciLayout.setHorizontalGroup(
            jPanelOpstipodatciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOpstipodatciLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelOpstipodatciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelOpstipodatciLayout.createSequentialGroup()
                        .addGroup(jPanelOpstipodatciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanelOpstipodatciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelOpstipodatciLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jOpis, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE))
                            .addGroup(jPanelOpstipodatciLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jStatus1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanelOpstipodatciLayout.createSequentialGroup()
                        .addGroup(jPanelOpstipodatciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelOpstipodatciLayout.createSequentialGroup()
                                .addComponent(jPol, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jProcenatR))
                            .addGroup(jPanelOpstipodatciLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jNadimak, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanelOpstipodatciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelOpstipodatciLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jStarost))
                            .addGroup(jPanelOpstipodatciLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelOpstipodatciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelOpstipodatciLayout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(21, 21, 21)
                                        .addComponent(jLinija, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanelOpstipodatciLayout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jOznaka, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                .addContainerGap())
        );
        jPanelOpstipodatciLayout.setVerticalGroup(
            jPanelOpstipodatciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOpstipodatciLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelOpstipodatciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jNadimak, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jOznaka, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelOpstipodatciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPol, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLinija, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProcenatR, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelOpstipodatciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelOpstipodatciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jStatus1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelOpstipodatciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jStarost, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelOpstipodatciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jOpis, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        javax.swing.GroupLayout jPanelJagnjenjaLayout = new javax.swing.GroupLayout(jPanelJagnjenja);
        jPanelJagnjenja.setLayout(jPanelJagnjenjaLayout);
        jPanelJagnjenjaLayout.setHorizontalGroup(
            jPanelJagnjenjaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJagnjenjaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelJagnjenjaLayout.setVerticalGroup(
            jPanelJagnjenjaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJagnjenjaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelVakcinacije.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Redovna vakcinacija (5)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Monaco", 1, 18), new java.awt.Color(153, 0, 51))); // NOI18N

        jTableVakcinacije.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Dtum", "razlog", "lek", "napomena"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableVakcinacije.setEnabled(false);
        jScrollPane5.setViewportView(jTableVakcinacije);

        javax.swing.GroupLayout jPanelVakcinacijeLayout = new javax.swing.GroupLayout(jPanelVakcinacije);
        jPanelVakcinacije.setLayout(jPanelVakcinacijeLayout);
        jPanelVakcinacijeLayout.setHorizontalGroup(
            jPanelVakcinacijeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVakcinacijeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
        );
        jPanelVakcinacijeLayout.setVerticalGroup(
            jPanelVakcinacijeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVakcinacijeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelLecenja.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lečenja (1)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Monaco", 1, 18), new java.awt.Color(153, 0, 51))); // NOI18N

        jTableLecenja.setFont(new java.awt.Font("Damascus", 0, 14)); // NOI18N
        jTableLecenja.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableLecenja.setEnabled(false);
        jScrollPane18.setViewportView(jTableLecenja);

        javax.swing.GroupLayout jPanelLecenjaLayout = new javax.swing.GroupLayout(jPanelLecenja);
        jPanelLecenja.setLayout(jPanelLecenjaLayout);
        jPanelLecenjaLayout.setHorizontalGroup(
            jPanelLecenjaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLecenjaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane18)
                .addContainerGap())
        );
        jPanelLecenjaLayout.setVerticalGroup(
            jPanelLecenjaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLecenjaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelProdaja.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Prodata 25-09-2015", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Monaco", 1, 18), new java.awt.Color(153, 0, 51))); // NOI18N
        jPanelProdaja.setEnabled(false);

        jOznaka8.setEditable(false);
        jOznaka8.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jOznaka8.setForeground(new java.awt.Color(0, 0, 153));
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

        javax.swing.GroupLayout jPanelProdajaLayout = new javax.swing.GroupLayout(jPanelProdaja);
        jPanelProdaja.setLayout(jPanelProdajaLayout);
        jPanelProdajaLayout.setHorizontalGroup(
            jPanelProdajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProdajaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelProdajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelProdajaLayout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jOznaka9))
                    .addGroup(jPanelProdajaLayout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jOznaka8, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jStatus10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCenaNabavke2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelProdajaLayout.setVerticalGroup(
            jPanelProdajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProdajaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelProdajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jOznaka8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCenaNabavke2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jStatus10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelProdajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jOznaka9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanelUginuce.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Uginula 25-09-2015", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Monaco", 1, 18), new java.awt.Color(153, 0, 51))); // NOI18N
        jPanelUginuce.setForeground(new java.awt.Color(0, 0, 153));

        jRazlogUginuca.setEditable(false);
        jRazlogUginuca.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jRazlogUginuca.setForeground(new java.awt.Color(0, 0, 153));
        jRazlogUginuca.setText("jTextField1");
        jRazlogUginuca.setBorder(null);
        jRazlogUginuca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRazlogUginucaActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel26.setText("Razlog");

        javax.swing.GroupLayout jPanelUginuceLayout = new javax.swing.GroupLayout(jPanelUginuce);
        jPanelUginuce.setLayout(jPanelUginuceLayout);
        jPanelUginuceLayout.setHorizontalGroup(
            jPanelUginuceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUginuceLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRazlogUginuca)
                .addContainerGap())
        );
        jPanelUginuceLayout.setVerticalGroup(
            jPanelUginuceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUginuceLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelUginuceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRazlogUginuca, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelRodjenje, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelDosije, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanelOpstipodatci, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelNabavka, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(107, 107, 107)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jIzmeniButton)
                        .addGap(27, 27, 27)
                        .addComponent(jPDFSnimiButton)
                        .addGap(18, 18, 18)
                        .addComponent(jOtkaziButton)
                        .addGap(20, 20, 20))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelJagnjenja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelVakcinacije, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelLecenja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelProdaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelUginuce, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanelJagnjenja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelVakcinacije, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelLecenja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelProdaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelUginuce, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jIzmeniButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPDFSnimiButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jOtkaziButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelOpstipodatci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelRodjenje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelNabavka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelDosije, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(10, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
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

    private void jRazlogUginucaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRazlogUginucaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRazlogUginucaActionPerformed

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
       prepareForEdit(jMajka);
       prepareForEdit(jOtac);
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
    private Ovca pickOtac(){
        Ovca o= new Ovca("zamisljena", "nepoznat", 'm');
        if (!jOtac.getText().isEmpty()){
            o.setOznaka(Ovca.parseOznaka(jOtac.getText()));
        }
        return o;
    }
    private Ovca pickMajka(){
        Ovca o= new Ovca("zamisljena", "nepoznata", 'ž');
        if (!jMajka.getText().isEmpty()){
            o.setOznaka(Ovca.parseOznaka(jMajka.getText()));
        }
        return o;
    }
    
    private void pickOvcaFromForm() {
     //   System.out.println("id "+ovca.getId());
        ovca.setOtac(pickOtac());
        ovca.setMajka(pickMajka());
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
            OvcaIzvestaj report = new OvcaIzvestaj(logic, ovca);
            report.create();
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

    private void jMajkaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMajkaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jMajkaKeyReleased

    private void jMajkaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMajkaKeyPressed
        char c = evt.getKeyChar();
        if (c== KeyEvent.VK_ENTER){
            openNewOvcaPanel(ovca.getMajka());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jMajkaKeyPressed

    private void jOtacKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jOtacKeyPressed
        char c = evt.getKeyChar();
        if (c== KeyEvent.VK_ENTER){
            openNewOvcaPanel(ovca.getOtac());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jOtacKeyPressed

    private void openNewOvcaPanel(Ovca o){
        
        if (o!=null){
            if (o.getOtac()!=null){
                o.getOtac().getOznaka();
            }
            if (o.getMajka()!=null){
                o.getMajka().getOznaka();
            }
            mainPanel.removeAll();
            mainPanel.add(new OvcaPrikaz(mainPanel, logic, o));
            mainPanel.revalidate();
            repaint();
        }
    }
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
    private javax.swing.JTextField jOznaka8;
    private javax.swing.JTextField jOznaka9;
    private javax.swing.JButton jPDFSnimiButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelDosije;
    private javax.swing.JPanel jPanelJagnjenja;
    private javax.swing.JPanel jPanelLecenja;
    private javax.swing.JPanel jPanelNabavka;
    private javax.swing.JPanel jPanelOpstipodatci;
    private javax.swing.JPanel jPanelProdaja;
    private javax.swing.JPanel jPanelRodjenje;
    private javax.swing.JPanel jPanelUginuce;
    private javax.swing.JPanel jPanelVakcinacije;
    private javax.swing.JComboBox jPol;
    private javax.swing.JTextField jProcenatR;
    private javax.swing.JTextField jRazlogUginuca;
    private javax.swing.JTextField jRodjenjeNapomena;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jStarost;
    private javax.swing.JTextField jStatus1;
    private javax.swing.JTextField jStatus10;
    private javax.swing.JTable jTableJagnjenja;
    private javax.swing.JTable jTableLecenja;
    private javax.swing.JTable jTableVakcinacije;
    private javax.swing.JTextField jTezina;
    private javax.swing.JTextField jTezina1;
    // End of variables declaration//GEN-END:variables


}
