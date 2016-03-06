/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.editPanels;

import app.helpers.OvcaHelper;
import app.helpers.OvcaReport;
import app.logic.Logic;
import app.mainPanels.Podaci_ovaca_opsti;
import app.model.Aktivnost;
import app.model.Dan;
import app.model.Jagnjenje;
import app.model.Linija;
import app.model.Merenje;
import app.model.Ovca;
import app.model.Parenje;
import app.model.Vakcinacija;
import app.report.OvcaIzvestaj;
import app.report.OvcaIzvestaj2;
import com.sun.java.swing.plaf.motif.MotifBorders;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author ivantadic
 */
public class OvcaPrikaz extends javax.swing.JPanel {
    private Ovca ovca;
    private Logic logic;
    private JPanel mainPanel;

    /**
     * Creates new form Podaci_ovaca_opsti
     */
    public OvcaPrikaz(JPanel mainpanel, Logic logic, int id) {

        this.logic = logic;
        Ovca o = logic.getOvca(id);
            if (o.getOtac()!=null){
                o.getOtac().getOznaka();
            }
            if (o.getMajka()!=null){
                o.getMajka().getOznaka();
            }
        this.ovca = o;
        this.mainPanel = mainpanel;
        initComponents();
        jScrollPane1.getViewport().setBackground(Color.white);
        setSheepToPanel(ovca);
        ((JTextField) jMajka.getEditor().getEditorComponent()).setDisabledTextColor(Color.BLACK);
        ((JTextField) jOtac.getEditor().getEditorComponent()).setDisabledTextColor(Color.BLACK);
    }
    
    private void setTitleToPanel(JPanel panel, String title){
        TitledBorder tb = (TitledBorder)panel.getBorder();
        tb.setTitle(title);
        tb.setTitleFont(new Font("Dialog", Font.PLAIN, 18));
        //tb.setTitleColor(Color.red.darker());
        panel.setBorder(tb); 
    }

    private void setSheepToPanel(Ovca o){
        setOsnovniPodaci(o);
        setRodjenje(o);
        setNabavka(o);  
        setJagnjenja(o);
        setTrenutnoPari(o);
        setParenjaOvca(o);
        setParenjaOvan(o);
        setMerenja(o);
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
        } else {
            setTitleToPanel(jPanelProdaja, "Prodato " + o.getProdaja().getAktivnost().getDatum());
            jKome.setText(o.getProdaja().getKupac());
            jCenaProdaje.setText(String.valueOf(o.getProdaja().getCenaGrla()));
            jDetaljiProdaje.setText(o.getProdaja().getNapomena());
        }
        
    }
    private void setRodjenje(Ovca o){
        setTitleToPanel(jPanelRodjenje, "Rođenje " + o.getDatumRodjenja() + " - " + o.getPoreklo());
        jMajka.setModel(new DefaultComboBoxModel(logic.getAllSheep().toArray()));
         jOtac.setModel(new DefaultComboBoxModel(logic.listaOvnova().toArray()));
         jPoreklo.setText(o.getPoreklo());
        AutoCompleteDecorator.decorate(this.jMajka);
        AutoCompleteDecorator.decorate(this.jOtac);
        if (o.getOtac()!=null){
            jOtac.setSelectedItem(o.getOtac());
        }
        if (o.getMajka()!=null){
            jMajka.setSelectedItem(o.getMajka());
        }
        if (o.getLeglo()!=null){
            jLeglo.setText(o.getLeglo().toString());
        } else {
            jLeglo.setText("?");
        }
        
        jTezina.setText(String.valueOf(Aktivnost.round(o.getTezinaNaRodjenju(),2)));
        
        
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
        jAktuelno.setText(o.getAktuelno());
        jStarost.setText(o.getStarost());
        jOpis.setText(o.getOpis());
        jDosije.setText(o.getPracenje());
        jTezina60.setText(Float.toString(Math.round(o.getTezinaDvaMeseca()*100)/100));
        jTezina120.setText(Float.toString(Math.round(o.getTezinaCetiriMeseca()*100)/100));
        jPrirast.setText(Float.toString(Math.round(logic.getPrirast(o))));
        setTitleToPanel(jPanelDosije, "Dosije");
        
    }
    private void setNabavka(Ovca o){
       if (o.getNabavka()!=null){
           Dan d = logic.getDan(o.getNabavka().getAktivnost().getDan().getId());
          setTitleToPanel(jPanelNabavka, "Nabavka " + d.toString() + " - " + o.getNabavka().getAktivnost().getLokacija());
         jCenaNabavke.setText(Float.toString(Aktivnost.round(o.getNabavka().getCena(), 1)));
         jDetaljiNabavke.setText(o.getNabavka().getNapomena());
       } else {
          jPanelNabavka.setVisible(false);
       }
    }
    private void setTrenutnoPari(Ovca o){
         if (o.getPol()!='m'){
             jTrenutnoPari.setVisible(false);
             return;
         }
         List<Parenje> listaParenja = logic.getZadnjaParenja(o);
         
         DefaultTableModel tm = (DefaultTableModel) jTableParenja.getModel();
         tm.setRowCount(0);
         
         for (Parenje p: listaParenja){
             int dana = OvcaHelper.razlikaUDanima(p.getAktivnost().getDan(), new Dan());
             insertRowInTable(tm, vectorFrom(p.getOvca().getOznaka(), p.getOvca().getNadimak(), String.valueOf(dana), 
                            p.getOvca().getAktuelno(), p.getOvca().getId()));
         }
         jTableParenja.setModel(tm);
         setTitleToPanel(jTrenutnoPari, "Trenutno pari (" + tm.getRowCount() + ")");
         setBoldFontToColumn(jTableParenja, 1);
    }
    
    private void setJagnjenja(Ovca o){
         if (o.getPol()=='m'){
             jPanelJagnjenja.setVisible(false);
             return;
         }
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
                    datum = logic.getDan(j.getAktivnost().getDan().getId()).toString();
                }
            }
            insertRowInTable(tm, vectorFrom(datum, brojJagnjadi, zivih, brojJagnjadi-brojMuskih, napomena));
            jTableJagnjenja.setModel(tm);        
        }
      
         setTitleToPanel(jPanelJagnjenja, "Jagnjenja (" + tm.getRowCount() + ") - " + o.getProcenatJagnjenja());
         setBoldFontToColumn(jTableJagnjenja, 1);
    }
    
    private void setParenjaOvan(Ovca o){
        if (o.getPol()=='m'){
             jParenjaOvan.setVisible(true);
         } else {
            jParenjaOvan.setVisible(false);
            return;
         }
         Dan datumSpajanja = null;
         Dan datumOdvajanja = null;
         int brojOvaca = 0;
         int dana = 0;
         DefaultTableModel tm = (DefaultTableModel) jTableParenjeOvan.getModel();
         tm.setRowCount(0);
         List<Parenje> listaParenja = logic.getSpajanjaOdvajanjaOvna(o);
        
        if (listaParenja!=null && ! listaParenja.isEmpty()){
            datumSpajanja = listaParenja.get(0).getAktivnost().getDan();
        }

        for (Iterator<Parenje> it = listaParenja.iterator(); it.hasNext();) {
            Parenje p = it.next();
                if (!datumSpajanja.equals(p.getAktivnost().getDan())){ // bilo da je Odvajanje ili Spajanje romena se zapisuje
                    datumOdvajanja = p.getAktivnost().getDan();
                    dana = OvcaHelper.razlikaUDanima(datumSpajanja, datumOdvajanja);
                    insertRowInTable(tm, vectorFrom(brojOvaca, datumSpajanja, datumOdvajanja, dana));
                    datumSpajanja = p.getAktivnost().getDan();
                    
                }
                if (p.getTip().equals("Spajanje")){
                        brojOvaca++;
                    } else {
                        brojOvaca--;
                }
            
        }
        jTableParenjeOvan.setModel(tm);
        setTitleToPanel(jParenjaOvan, "Parenja (" + tm.getRowCount() + ")");
        setBoldFontToColumn(jTableParenjeOvan, 0);
        setBoldFontToColumn(jTableParenjeOvan, 2);
    }
    
    private void setParenjaOvca(Ovca o){
        if (o.getPol()=='ž'){
             jParenja.setVisible(true);
             jParenjaOvan.setVisible(false);
         } else {
            jParenja.setVisible(false);
            return;
         }
         
         Collections.sort(o.getParenja());
         DefaultTableModel tm = (DefaultTableModel) jTableParenje.getModel();
         tm.setRowCount(0);
         Dan datumSpajanja = new Dan();
         Dan datumOdvajanja = null;
         Dan datumParenja = null;
         int dana = 0;
         Ovca ovan = new Ovca();
         for (Parenje p: o.getParenja()){
             if (p.getTip().equals("Spajanje")){
                 ovan= p.getOvan();
                 datumSpajanja = p.getAktivnost().getDan();
                 dana = OvcaHelper.razlikaUDanima(p.getAktivnost().getDan(), new Dan());
                 insertRowInTable(tm, vectorFrom(p.getOvan().getNadimak(), datumSpajanja, "", dana));
             } else if (p.getTip().equals("Odvajanje") && p.getOvan().equals(ovan)){
                 datumOdvajanja = p.getAktivnost().getDan();
                 dana = OvcaHelper.razlikaUDanima(datumSpajanja, datumOdvajanja);
                 deletelastRow(tm);
                 insertRowInTable(tm, vectorFrom(p.getOvan().getNadimak(), datumSpajanja, datumParenja, datumOdvajanja, dana));
             }else if (p.getOvan().equals(ovan)){
                 datumParenja = p.getAktivnost().getDan();
                 deletelastRow(tm);
                 insertRowInTable(tm, vectorFrom(p.getOvan().getNadimak(), datumSpajanja, datumParenja, datumOdvajanja, dana));
             }
             
         }
         jTableParenje.setModel(tm);
         setTitleToPanel(jParenja, "Parenja (" + tm.getRowCount() + ")");
         setBoldFontToColumn(jTableParenje, 0);
         setBoldFontToColumn(jTableParenje, 3);
        
        
        
    }
    
    
    private void setMerenja(Ovca o){
        if (o.getMerenja()==null || o.getMerenja().isEmpty()){
            return;
        }
        DefaultTableModel tm2 = (DefaultTableModel) jTableLecenja.getModel();
        tm2.setRowCount(0);
        List<Merenje> merenja = o.getMerenja();
        Collections.sort(merenja);
        for (Merenje m: merenja){
            Dan dan = logic.getDan(m.getAktivnost().getDan().getId());
            insertRowInTable(tm2, vectorFrom(dan.toString(), 
                        o.starostUDanima(dan.getDate()), m.getTezina(), m.getNapomena()));
        }
        
       setTitleToPanel(jPanelLecenja, "Merenja ("  + tm2.getRowCount() + ")");
       setBoldFontToColumn(jTableLecenja, 2);
    }
    private void setVakcinacijeLecenja(Ovca o){
        if (o.getVakcinacije()==null || o.getVakcinacije().isEmpty()){
            setTitleToPanel(jPanelVakcinacije,  "Redovne vakcinacije (0)");
            return;
        }
        
        DefaultTableModel tm1 = (DefaultTableModel) jTableVakcinacije.getModel();
        tm1.setRowCount(0);
//        DefaultTableModel tm2 = (DefaultTableModel) jTableLecenja.getModel();
//        tm2.setRowCount(0);
        List<Vakcinacija> vakcinacije = o.getVakcinacije();
        Collections.sort(vakcinacije);
        for (Vakcinacija v: vakcinacije){
            
                insertRowInTable(tm1, vectorFrom(logic.getDan(v.getAktivnost().getDan().getId()).toString(), 
                        v.getRazlog(), v.getSredstvo(), v.getNapomena()));
        
//                insertRowInTable(tm2, vectorFrom(logic.getDan(v.getAktivnost().getDan().getId()).toString(), 
//                        v.getRazlog(), v.getSredstvo(), v.getNapomena()));
            
        }
        setTitleToPanel(jPanelVakcinacije, "Preventiva/Lečenje (" + tm1.getRowCount() + ")");
        setBoldFontToColumn(jTableVakcinacije, 2);
//        setTitleToPanel(jPanelLecenja, "Lečenja  + tm2.getRowCount() + ")");
    }
    private Vector vectorFrom(Object ...params){
         Vector vrsta = new Vector();
         for (int i=0; i<params.length; i++){
                    vrsta.add(params[i]);
         }
         return vrsta;
    }
    private void deletelastRow(DefaultTableModel model){
        model.removeRow(model.getRowCount()-1);
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
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jRodjenjeNapomena = new javax.swing.JTextField();
        jTezina1 = new javax.swing.JTextField();
        jMajka = new javax.swing.JComboBox();
        jOtac = new javax.swing.JComboBox();
        jLabel27 = new javax.swing.JLabel();
        jPoreklo = new javax.swing.JTextField();
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
        jLabel19 = new javax.swing.JLabel();
        jAktuelno = new javax.swing.JTextField();
        jPanelJagnjenja = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableJagnjenja = new javax.swing.JTable();
        jPanelLecenja = new javax.swing.JPanel();
        jScrollPane18 = new javax.swing.JScrollPane();
        jTableLecenja = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jPrirast = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTezina120 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTezina60 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanelProdaja = new javax.swing.JPanel();
        jKome = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jCenaProdaje = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jDetaljiProdaje = new javax.swing.JTextField();
        jCenaNabavke2 = new javax.swing.JTextField();
        jPanelUginuce = new javax.swing.JPanel();
        jRazlogUginuca = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPDFSnimiButton = new javax.swing.JButton();
        jOtkaziButton = new javax.swing.JButton();
        jIzmeniButton = new javax.swing.JButton();
        jPanelNabavka = new javax.swing.JPanel();
        jCenaNabavke = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jDetaljiNabavke = new javax.swing.JTextField();
        jCenaNabavke1 = new javax.swing.JTextField();
        jTrenutnoPari = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableParenja = new javax.swing.JTable();
        jPDFSnimiButton1 = new javax.swing.JButton();
        jParenjaOvan = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableParenjeOvan = new javax.swing.JTable();
        jPanelVakcinacije = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableVakcinacije = new javax.swing.JTable();
        jParenja = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableParenje = new javax.swing.JTable();

        jPanelRodjenje.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Rođenje 24-12-2014 selo Korićani", 0, 0, new java.awt.Font("Monaco", 1, 18), new java.awt.Color(153, 0, 51))); // NOI18N

        jTezina.setEditable(false);
        jTezina.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
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

        jTezina1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTezina1.setForeground(new java.awt.Color(0, 0, 153));
        jTezina1.setText("kg");
        jTezina1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTezina1ActionPerformed(evt);
            }
        });

        jMajka.setBackground(new java.awt.Color(255, 255, 51));
        jMajka.setEditable(true);
        jMajka.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jMajka.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jMajka.setEnabled(false);

        jOtac.setEditable(true);
        jOtac.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jOtac.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jOtac.setEnabled(false);

        jLabel27.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel27.setText("Poreklo:");

        jPoreklo.setEditable(false);
        jPoreklo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jPoreklo.setForeground(new java.awt.Color(0, 0, 153));
        jPoreklo.setText("    ");
        jPoreklo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPorekloActionPerformed(evt);
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
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRodjenjeNapomena))
                    .addGroup(jPanelRodjenjeLayout.createSequentialGroup()
                        .addGroup(jPanelRodjenjeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelRodjenjeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelRodjenjeLayout.createSequentialGroup()
                                .addComponent(jLeglo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTezina, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTezina1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(105, 105, 105))
                            .addGroup(jPanelRodjenjeLayout.createSequentialGroup()
                                .addComponent(jMajka, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jOtac, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8))
                            .addGroup(jPanelRodjenjeLayout.createSequentialGroup()
                                .addComponent(jPoreklo)
                                .addContainerGap())))))
        );
        jPanelRodjenjeLayout.setVerticalGroup(
            jPanelRodjenjeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRodjenjeLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelRodjenjeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jPoreklo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelRodjenjeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jOtac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jMajka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelRodjenjeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTezina1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTezina, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLeglo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelRodjenjeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRodjenjeNapomena, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanelDosije.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Dosije", 0, 0, new java.awt.Font("Monaco", 1, 18), new java.awt.Color(153, 0, 51))); // NOI18N

        jDosije.setEditable(false);
        jDosije.setColumns(20);
        jDosije.setFont(new java.awt.Font("Osaka", 0, 14)); // NOI18N
        jDosije.setForeground(new java.awt.Color(0, 0, 153));
        jDosije.setLineWrap(true);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDosijeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jNadimak.setEditable(false);
        jNadimak.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jNadimak.setForeground(new java.awt.Color(204, 0, 0));
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
        jOpis.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
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
        jOznaka.setForeground(new java.awt.Color(204, 0, 0));
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

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel19.setText("Aktuelno:");

        jAktuelno.setEditable(false);
        jAktuelno.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jAktuelno.setForeground(new java.awt.Color(204, 0, 0));
        jAktuelno.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jAktuelno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAktuelnoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelOpstipodatciLayout = new javax.swing.GroupLayout(jPanelOpstipodatci);
        jPanelOpstipodatci.setLayout(jPanelOpstipodatciLayout);
        jPanelOpstipodatciLayout.setHorizontalGroup(
            jPanelOpstipodatciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOpstipodatciLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelOpstipodatciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelOpstipodatciLayout.createSequentialGroup()
                        .addGroup(jPanelOpstipodatciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelOpstipodatciLayout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9))
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(jPanelOpstipodatciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelOpstipodatciLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jOpis))
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
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLinija, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelOpstipodatciLayout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jOznaka, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                    .addGroup(jPanelOpstipodatciLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jAktuelno)
                        .addGap(1, 1, 1)))
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
                .addGroup(jPanelOpstipodatciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jOpis, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelOpstipodatciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jAktuelno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanelJagnjenja.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Jagnjenja (2)", 0, 0, new java.awt.Font("Monaco", 1, 18), new java.awt.Color(153, 0, 51))); // NOI18N

        jTableJagnjenja.setAutoCreateRowSorter(true);
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelJagnjenjaLayout.setVerticalGroup(
            jPanelJagnjenjaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJagnjenjaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jPanelLecenja.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Merenja (0)", 0, 0, new java.awt.Font("Monaco", 1, 18), new java.awt.Color(153, 0, 51))); // NOI18N

        jTableLecenja.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jTableLecenja.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Datum", "starost dana", "težina (kg)", "napomena"
            }
        ));
        jTableLecenja.setEnabled(false);
        jScrollPane18.setViewportView(jTableLecenja);

        jLabel16.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel16.setText("g/dan");

        jPrirast.setEditable(false);
        jPrirast.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jPrirast.setForeground(new java.awt.Color(204, 0, 0));
        jPrirast.setText("0");
        jPrirast.setActionCommand("<Not Set>");
        jPrirast.setAlignmentX(0.0F);
        jPrirast.setAlignmentY(0.0F);
        jPrirast.setMinimumSize(new java.awt.Dimension(0, 16));
        jPrirast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPrirastActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel15.setText("Prirast:");

        jLabel17.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel17.setText("kg");

        jTezina120.setEditable(false);
        jTezina120.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTezina120.setForeground(new java.awt.Color(0, 51, 51));
        jTezina120.setText("0");
        jTezina120.setActionCommand("<Not Set>");
        jTezina120.setAlignmentX(0.0F);
        jTezina120.setAlignmentY(0.0F);
        jTezina120.setMinimumSize(new java.awt.Dimension(0, 16));
        jTezina120.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTezina120ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel12.setText("120 dana;");

        jLabel18.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel18.setText("kg");

        jTezina60.setEditable(false);
        jTezina60.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTezina60.setForeground(new java.awt.Color(0, 0, 153));
        jTezina60.setText("0");
        jTezina60.setActionCommand("<Not Set>");
        jTezina60.setAlignmentX(0.0F);
        jTezina60.setAlignmentY(0.0F);
        jTezina60.setMinimumSize(new java.awt.Dimension(0, 16));
        jTezina60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTezina60ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel11.setText("60 dana:");

        javax.swing.GroupLayout jPanelLecenjaLayout = new javax.swing.GroupLayout(jPanelLecenja);
        jPanelLecenja.setLayout(jPanelLecenjaLayout);
        jPanelLecenjaLayout.setHorizontalGroup(
            jPanelLecenjaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLecenjaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLecenjaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLecenjaLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTezina60, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTezina120, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPrirast, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)))
                .addContainerGap())
        );
        jPanelLecenjaLayout.setVerticalGroup(
            jPanelLecenjaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLecenjaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLecenjaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTezina60, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTezina120, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPrirast, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        jPanelProdaja.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Prodata 25-09-2015", 0, 0, new java.awt.Font("Monaco", 1, 20), java.awt.SystemColor.controlHighlight)); // NOI18N

        jKome.setEditable(false);
        jKome.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jKome.setForeground(new java.awt.Color(0, 0, 153));
        jKome.setBorder(null);
        jKome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKomeActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel28.setText("Kome");

        jLabel29.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel29.setText("Cena");

        jCenaProdaje.setEditable(false);
        jCenaProdaje.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jCenaProdaje.setForeground(new java.awt.Color(0, 0, 153));
        jCenaProdaje.setBorder(null);
        jCenaProdaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCenaProdajeActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel30.setText("Detalji prodaje");

        jDetaljiProdaje.setEditable(false);
        jDetaljiProdaje.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jDetaljiProdaje.setForeground(new java.awt.Color(0, 0, 153));
        jDetaljiProdaje.setBorder(null);
        jDetaljiProdaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDetaljiProdajeActionPerformed(evt);
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
                        .addComponent(jDetaljiProdaje))
                    .addGroup(jPanelProdajaLayout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jKome, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCenaProdaje, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jKome, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCenaNabavke2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCenaProdaje, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelProdajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDetaljiProdaje, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanelUginuce.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Uginula 25-09-2015", 0, 0, new java.awt.Font("Monaco", 1, 20), new java.awt.Color(255, 0, 51))); // NOI18N
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

        jPDFSnimiButton.setBackground(new java.awt.Color(255, 153, 0));
        jPDFSnimiButton.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jPDFSnimiButton.setText("Štampaj 4 kolena");
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

        jPanelNabavka.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Nabavljena 24-12-2014 s. Korićani", 0, 0, new java.awt.Font("Monaco", 1, 18), new java.awt.Color(153, 0, 51))); // NOI18N

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

        jTrenutnoPari.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Trenutno pari (5)", 0, 0, new java.awt.Font("Monaco", 1, 18), new java.awt.Color(153, 0, 51))); // NOI18N

        jTableParenja.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Oznaka", "nadimak", "spojeni (dana)", "aktuelno", "id"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableParenja.setEnabled(false);
        jScrollPane6.setViewportView(jTableParenja);
        jTableParenja.getColumnModel().getColumn(2).setPreferredWidth(95);
        jTableParenja.getColumnModel().getColumn(2).setMaxWidth(95);
        jTableParenja.getColumnModel().getColumn(3).setPreferredWidth(150);
        jTableParenja.getColumnModel().getColumn(3).setMaxWidth(150);
        jTableParenja.getColumnModel().getColumn(4).setMinWidth(1);
        jTableParenja.getColumnModel().getColumn(4).setPreferredWidth(1);
        jTableParenja.getColumnModel().getColumn(4).setMaxWidth(1);

        javax.swing.GroupLayout jTrenutnoPariLayout = new javax.swing.GroupLayout(jTrenutnoPari);
        jTrenutnoPari.setLayout(jTrenutnoPariLayout);
        jTrenutnoPariLayout.setHorizontalGroup(
            jTrenutnoPariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jTrenutnoPariLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE))
        );
        jTrenutnoPariLayout.setVerticalGroup(
            jTrenutnoPariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTrenutnoPariLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPDFSnimiButton1.setBackground(new java.awt.Color(0, 255, 102));
        jPDFSnimiButton1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jPDFSnimiButton1.setText("Štampaj 3 kolena");
        jPDFSnimiButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPDFSnimiButton1ActionPerformed(evt);
            }
        });

        jParenjaOvan.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Parenja (5)", 0, 0, new java.awt.Font("Monaco", 1, 18), new java.awt.Color(153, 0, 51))); // NOI18N

        jTableParenjeOvan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Broj ženki", "spojeni", "razdvojeni", "trajanje dana"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableParenjeOvan.setEnabled(false);
        jScrollPane7.setViewportView(jTableParenjeOvan);
        jTableParenjeOvan.getColumnModel().getColumn(1).setPreferredWidth(95);
        jTableParenjeOvan.getColumnModel().getColumn(1).setMaxWidth(95);
        jTableParenjeOvan.getColumnModel().getColumn(2).setPreferredWidth(150);
        jTableParenjeOvan.getColumnModel().getColumn(2).setMaxWidth(150);

        javax.swing.GroupLayout jParenjaOvanLayout = new javax.swing.GroupLayout(jParenjaOvan);
        jParenjaOvan.setLayout(jParenjaOvanLayout);
        jParenjaOvanLayout.setHorizontalGroup(
            jParenjaOvanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jParenjaOvanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7))
        );
        jParenjaOvanLayout.setVerticalGroup(
            jParenjaOvanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jParenjaOvanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        jPanelVakcinacije.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Redovna vakcinacija (5)", 0, 0, new java.awt.Font("Monaco", 1, 18), new java.awt.Color(153, 0, 51))); // NOI18N

        jTableVakcinacije.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Datum", "razlog", "sredstvo", "napomena"
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
        jTableVakcinacije.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTableVakcinacije.getColumnModel().getColumn(0).setMaxWidth(100);
        jTableVakcinacije.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTableVakcinacije.getColumnModel().getColumn(2).setMaxWidth(100);
        jTableVakcinacije.getColumnModel().getColumn(3).setPreferredWidth(95);
        jTableVakcinacije.getColumnModel().getColumn(3).setMaxWidth(95);

        javax.swing.GroupLayout jPanelVakcinacijeLayout = new javax.swing.GroupLayout(jPanelVakcinacije);
        jPanelVakcinacije.setLayout(jPanelVakcinacijeLayout);
        jPanelVakcinacijeLayout.setHorizontalGroup(
            jPanelVakcinacijeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVakcinacijeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelVakcinacijeLayout.setVerticalGroup(
            jPanelVakcinacijeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVakcinacijeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addContainerGap())
        );

        jParenja.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Parenja (5)", 0, 0, new java.awt.Font("Monaco", 1, 18), new java.awt.Color(153, 0, 51))); // NOI18N

        jTableParenje.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Ovan", "spojeni", "parena", "razdvojeni", "dana"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableParenje.setEnabled(false);
        jScrollPane8.setViewportView(jTableParenje);
        jTableParenje.getColumnModel().getColumn(0).setHeaderValue("Ovan");
        jTableParenje.getColumnModel().getColumn(1).setPreferredWidth(95);
        jTableParenje.getColumnModel().getColumn(1).setMaxWidth(95);
        jTableParenje.getColumnModel().getColumn(2).setHeaderValue("parena");
        jTableParenje.getColumnModel().getColumn(3).setPreferredWidth(150);
        jTableParenje.getColumnModel().getColumn(3).setMaxWidth(150);

        javax.swing.GroupLayout jParenjaLayout = new javax.swing.GroupLayout(jParenja);
        jParenja.setLayout(jParenjaLayout);
        jParenjaLayout.setHorizontalGroup(
            jParenjaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jParenjaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8))
        );
        jParenjaLayout.setVerticalGroup(
            jParenjaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jParenjaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jIzmeniButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPDFSnimiButton, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPDFSnimiButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jOtkaziButton, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelNabavka, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanelOpstipodatci, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelRodjenje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanelDosije, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(1, 1, 1)))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelVakcinacije, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelUginuce, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTrenutnoPari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelLecenja, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelProdaja, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelJagnjenja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jParenja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jParenjaOvan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanelJagnjenja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTrenutnoPari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jParenja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jParenjaOvan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelVakcinacije, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelLecenja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelProdaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelUginuce, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jIzmeniButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPDFSnimiButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jOtkaziButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPDFSnimiButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jPanelOpstipodatci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanelRodjenje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jPanelNabavka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelDosije, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)))
                .addGap(20, 20, 20))
        );

        jPanelLecenja.getAccessibleContext().setAccessibleName("Merenja (1)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
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

    private void jRodjenjeNapomenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRodjenjeNapomenaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRodjenjeNapomenaActionPerformed

    private void jCenaNabavkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCenaNabavkeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCenaNabavkeActionPerformed

    private void jDetaljiNabavkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDetaljiNabavkeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDetaljiNabavkeActionPerformed

    private void jRazlogUginucaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRazlogUginucaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRazlogUginucaActionPerformed

    private void jKomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jKomeActionPerformed

    private void jCenaProdajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCenaProdajeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCenaProdajeActionPerformed

    private void jDetaljiProdajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDetaljiProdajeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDetaljiProdajeActionPerformed

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
        jtext.setBorder(new MotifBorders.BevelBorder(true, Color.darkGray, Color.lightGray));
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
       prepareForEdit(jTezina60);
       prepareForEdit(jPoreklo);
       prepareForEdit(jAktuelno);
       prepareForEdit(jTezina120);
       jPol.setEnabled(true);
       jPol.setBackground(Color.yellow.brighter().brighter());
       jLinija.setEnabled(true);
       jLinija.setBackground(Color.yellow.brighter().brighter());
       jMajka.setEnabled(true);
       jOtac.setEnabled(true);
       jMajka.setBackground(Color.yellow.brighter().brighter());
       prepareForEdit(jProcenatR);
//       prepareForEdit(jMajka);
//       prepareForEdit(jOtac);
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
        mainPanel.add(new Podaci_ovaca_opsti(mainPanel, logic));
        mainPanel.revalidate();
        repaint();
    }//GEN-LAST:event_jOtkaziButtonActionPerformed

    private void azurirajOvcu(){
        pickOvcaFromForm();
        logic.updateOvca(ovca);
        mainPanel.removeAll();
        mainPanel.add(new OvcaPrikaz(mainPanel, logic, ovca.getId()));
        mainPanel.revalidate();
        repaint();
    }
    private Ovca pickOtac(){
        Ovca o= new Ovca("zamisljena", "nepoznat", 'm');
        Object obj = jOtac.getSelectedItem();
        if (obj==null){
            return o;
        }
        if (obj instanceof Ovca){
            return (Ovca) obj;
        }
        o.setOznaka(obj.toString().trim());

        return o;
    }
  
    private Ovca pickMajka(){
        Ovca o= new Ovca("zamisljena", "nepoznata", 'ž');
        Object obj = jMajka.getSelectedItem();
        if (obj==null){
            return o;
        }
        if (obj instanceof Ovca){
            return (Ovca) obj;
        }
        o.setOznaka(obj.toString().trim());

        return o;
    }
    
    private void pickTezine(){
        try{
             ovca.setTezinaNaRodjenju(Float.parseFloat(jTezina.getText()));
        } catch (Exception e){
            ovca.setTezinaNaRodjenju(0.0f);
        } 
        try{
             ovca.setTezinaDvaMeseca(Float.parseFloat(jTezina60.getText()));
        } catch (Exception e){
            ovca.setTezinaDvaMeseca(0.0f);
        }
        try{
             ovca.setTezinaCetiriMeseca(Float.parseFloat(jTezina120.getText()));
        } catch (Exception e){
            ovca.setTezinaCetiriMeseca(0.0f);
        }
    }
    private void pickOvcaFromForm() {
     //   System.out.println("id "+ovca.getId());
        ovca.setOtac(pickOtac());
        ovca.setMajka(pickMajka());

        pickTezine();
        ovca.setOznaka(jOznaka.getText());
        ovca.setNadimak(jNadimak.getText());
        ovca.setProcenatR(jProcenatR.getText());
        ovca.setPol(jPol.getSelectedItem().toString().charAt(0));
        ovca.setLinija((Linija) jLinija.getSelectedItem());
        ovca.setOpis(jOpis.getText());
        ovca.setAktuelno(jAktuelno.getText());
        ovca.setPracenje(jDosije.getText());
        ovca.setPoreklo(jPoreklo.getText());
 
 
    }
    private void jPDFSnimiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPDFSnimiButtonActionPerformed
        if (jPDFSnimiButton.getText().equals("Štampaj 4 kolena")){
            ArrayList<OvcaReport> list = new ArrayList<OvcaReport>();
            list.add(new OvcaReport(ovca, logic));
            OvcaIzvestaj report = new OvcaIzvestaj(logic, list);
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

    private void jTezina60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTezina60ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTezina60ActionPerformed

    private void jPrirastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPrirastActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPrirastActionPerformed

    private void jTezina120ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTezina120ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTezina120ActionPerformed

    private void jPorekloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPorekloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPorekloActionPerformed

    private void jPDFSnimiButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPDFSnimiButton1ActionPerformed
            ArrayList<OvcaReport> list = new ArrayList<OvcaReport>();
            list.add(new OvcaReport(ovca, logic));
            OvcaIzvestaj2 report = new OvcaIzvestaj2(logic, list);
            report.create();        // TODO add your handling code here:
    }//GEN-LAST:event_jPDFSnimiButton1ActionPerformed

    private void jAktuelnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAktuelnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jAktuelnoActionPerformed

    private void openNewOvcaPanel(Ovca o){
        if (o!=null){
            mainPanel.removeAll();
            mainPanel.add(new OvcaPrikaz(mainPanel, logic, o.getId()));
            mainPanel.revalidate();
            repaint();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField jAktuelno;
    private javax.swing.JTextField jCenaNabavke;
    private javax.swing.JTextField jCenaNabavke1;
    private javax.swing.JTextField jCenaNabavke2;
    private javax.swing.JTextField jCenaProdaje;
    private javax.swing.JTextField jDetaljiNabavke;
    private javax.swing.JTextField jDetaljiProdaje;
    private javax.swing.JTextArea jDosije;
    private javax.swing.JButton jIzmeniButton;
    private javax.swing.JTextField jKome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
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
    private javax.swing.JComboBox jMajka;
    private javax.swing.JTextField jNadimak;
    private javax.swing.JTextField jOpis;
    private javax.swing.JComboBox jOtac;
    private javax.swing.JButton jOtkaziButton;
    private javax.swing.JTextField jOznaka;
    private javax.swing.JButton jPDFSnimiButton;
    private javax.swing.JButton jPDFSnimiButton1;
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
    private javax.swing.JPanel jParenja;
    private javax.swing.JPanel jParenjaOvan;
    private javax.swing.JComboBox jPol;
    private javax.swing.JTextField jPoreklo;
    private javax.swing.JTextField jPrirast;
    private javax.swing.JTextField jProcenatR;
    private javax.swing.JTextField jRazlogUginuca;
    private javax.swing.JTextField jRodjenjeNapomena;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTextField jStarost;
    private javax.swing.JTextField jStatus1;
    private javax.swing.JTable jTableJagnjenja;
    private javax.swing.JTable jTableLecenja;
    private javax.swing.JTable jTableParenja;
    private javax.swing.JTable jTableParenje;
    private javax.swing.JTable jTableParenjeOvan;
    private javax.swing.JTable jTableVakcinacije;
    private javax.swing.JTextField jTezina;
    private javax.swing.JTextField jTezina1;
    private javax.swing.JTextField jTezina120;
    private javax.swing.JTextField jTezina60;
    private javax.swing.JPanel jTrenutnoPari;
    // End of variables declaration//GEN-END:variables


}
