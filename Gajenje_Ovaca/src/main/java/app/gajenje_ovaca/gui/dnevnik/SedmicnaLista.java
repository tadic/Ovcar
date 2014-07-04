/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.gajenje_ovaca.gui.dnevnik;

import app.logic.Logic;
import app.model.Aktivnost;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ivantadic
 */
public class SedmicnaLista extends javax.swing.JPanel {
    private static final int TOP_MARGIN=0;
    /**
     * Creates new form SedmicnaLista
     */
    private Logic logic;
    private Calendar firstDay;
    private Dnevnik dnevnik;
    public SedmicnaLista() {
        initComponents();
    }
    public SedmicnaLista(Dnevnik p, Logic l){
        logic = l;
        dnevnik = p;
        initComponents();
        Calendar c = Calendar.getInstance();
        
        this.setComponentZOrder(panelPonedeljak, 0);
        this.setComponentZOrder(panelUtorak, 1);
        this.setComponentZOrder(panelSreda, 2);
        this.setComponentZOrder(panelCetvrtak, 3);
        this.setComponentZOrder(panelPetak, 4);
        this.setComponentZOrder(panelSubota, 5);
        this.setComponentZOrder(panelNedelja, 6);
        initDaysAction();
        setDate(c);
        
    }
    public void setDate(Calendar c){
        firstDay = firstDayInWeek(c);
        setUpDays(c);
        this.revalidate();
    }
    private void initDaysAction() {
         for (int i=0; i<7; i++){
            final JPanel dayPanel = (JPanel) this.getComponent(i);
            dayPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {  
                setUpDay(dayPanel);
                System.out.println("\nclick\n");
               
            } 
            @Override
            public void mouseEntered(MouseEvent e)
            {
                JLabel dayName = (JLabel) dayPanel.getComponent(0);
                dayName.setForeground(new Color(10, 200,200));
            }
            
                @Override
              public void mouseExited(MouseEvent e){
                JLabel dayName = (JLabel) dayPanel.getComponent(0);
                dayName.setForeground(new Color(0,153,153));
              }      
            } );
         }
    }
    private void setUpDays(Calendar c){
        Calendar cal = firstDayInWeek(c);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        
        for (int i=0; i<7; i++){
            final JPanel dayPanel = (JPanel) this.getComponent(i);
            int numberOfAktivities = setDayActivitiesToPanel(dayPanel, cal);
                setTextToPanel(dayPanel, numberOfAktivities, "" + format.format(cal.getTime()));
                setTextToPanel(dayPanel, numberOfAktivities, "" + format.format(cal.getTime()));
            
            dayPanel.setPreferredSize(new Dimension(400, 30+36*numberOfAktivities));
            cal.add(Calendar.DAY_OF_MONTH, +1);
        }
        revalidate();
    }
    
    private void setUpDay(JPanel panel){
        Calendar c = Calendar.getInstance();
        c.set(firstDay.get(Calendar.YEAR), firstDay.get(Calendar.MONTH), firstDay.get(Calendar.DAY_OF_MONTH));
        
        int p=this.getComponentZOrder(panel);
        if (p>0){
            c.add(Calendar.DAY_OF_MONTH, p);
        }
        logic.setSelectedDay(c);
        dnevnik.setDate();
    }
    
    private void setTextToPanel(JPanel panel,int numberOfActivities, String text){
        JLabel noActivities = (JLabel) panel.getComponent(1);
        noActivities.setVisible(numberOfActivities==0);
        JLabel textLabel = (JLabel) panel.getComponent(2);
        textLabel.setText(text);
    }
    
    private int setDayActivitiesToPanel(JPanel panel, Calendar day){
        List<Aktivnost> list   = logic.getActivitiesFrom(day);
        while (panel.getComponentCount()>3){
             panel.remove(3);
        }

        if (list.isEmpty()){return 0;}
        Collections.sort(list);
        int space=36;
        for (Aktivnost a: list){
            RoundedPanelSedmicni eventPanel = new RoundedPanelSedmicni(a, logic);
            eventPanel.setPlace(panel.getInsets(), 15, space);
            space += 36;
            panel.add(eventPanel);
        }
        panel.revalidate();
        return list.size();
    }
    
    private Calendar firstDayInWeek(Calendar c){
        Calendar cal = Calendar.getInstance();
        cal.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        int n = cal.get(Calendar.DAY_OF_WEEK);
       // if (n==2){return cal;}
        n = (n+5)%7;
        cal.add(Calendar.DAY_OF_MONTH, -n);
        return cal;
    }
    
    public void refresh(Calendar c){
        setUpDays(c);
    }
    
    @Override
    protected void paintComponent(Graphics g){
    super.paintComponent(g);
    g.setFont(new Font("Arial", Font.BOLD, 14));
    for (int i=0; i<=24; i++){
            g.setColor(new Color(202, 202, 202));
            //g.drawString(getTimeString(i), 10, TOP_MARGIN + i*64);
            
            g.drawLine(5, TOP_MARGIN + (i*72)-5, 400, TOP_MARGIN + (i*72)-5);
            g.setColor(new Color(230, 227, 227));
            g.drawLine(5, TOP_MARGIN + (i*72)+31, 400, TOP_MARGIN + (i*72)+31);
    }
    repaint();
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPonedeljak = new javax.swing.JPanel();
        dayNameLabel2 = new javax.swing.JLabel();
        dayNameLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        panelUtorak = new javax.swing.JPanel();
        dayNameLabel = new javax.swing.JLabel();
        dayNameLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panelSreda = new javax.swing.JPanel();
        dayNameLabel1 = new javax.swing.JLabel();
        dayNameLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        panelCetvrtak = new javax.swing.JPanel();
        dayNameLabel11 = new javax.swing.JLabel();
        dayNameLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panelPetak = new javax.swing.JPanel();
        dayNameLabel4 = new javax.swing.JLabel();
        dayNameLabel12 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        panelSubota = new javax.swing.JPanel();
        dayNameLabel6 = new javax.swing.JLabel();
        dayNameLabel13 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        panelNedelja = new javax.swing.JPanel();
        dayNameLabel10 = new javax.swing.JLabel();
        dayNameLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(0, 600));
        setPreferredSize(new java.awt.Dimension(400, 225));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        panelPonedeljak.setBackground(new java.awt.Color(255, 255, 255));
        panelPonedeljak.setOpaque(false);
        panelPonedeljak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panelPonedeljakMouseReleased(evt);
            }
        });

        dayNameLabel2.setFont(new java.awt.Font("Noteworthy", 1, 16)); // NOI18N
        dayNameLabel2.setForeground(new java.awt.Color(0, 153, 153));
        dayNameLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dayNameLabel2.setText("Понедељак");

        dayNameLabel7.setFont(new java.awt.Font("Noteworthy", 1, 13)); // NOI18N
        dayNameLabel7.setForeground(new java.awt.Color(0, 153, 153));
        dayNameLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dayNameLabel7.setText("Понедељак");
        dayNameLabel7.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        dayNameLabel7.setPreferredSize(new java.awt.Dimension(150, 22));

        jLabel1.setFont(new java.awt.Font("Noteworthy", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 0, 0));
        jLabel1.setText("(нема активности)");

        org.jdesktop.layout.GroupLayout panelPonedeljakLayout = new org.jdesktop.layout.GroupLayout(panelPonedeljak);
        panelPonedeljak.setLayout(panelPonedeljakLayout);
        panelPonedeljakLayout.setHorizontalGroup(
            panelPonedeljakLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, panelPonedeljakLayout.createSequentialGroup()
                .addContainerGap()
                .add(dayNameLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 140, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(dayNameLabel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 140, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelPonedeljakLayout.setVerticalGroup(
            panelPonedeljakLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelPonedeljakLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(dayNameLabel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(dayNameLabel2)
                .add(jLabel1))
        );

        panelUtorak.setBackground(new java.awt.Color(255, 255, 255));
        panelUtorak.setOpaque(false);

        dayNameLabel.setFont(new java.awt.Font("Noteworthy", 1, 13)); // NOI18N
        dayNameLabel.setForeground(new java.awt.Color(0, 153, 153));
        dayNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dayNameLabel.setText("4. април 2013");

        dayNameLabel8.setFont(new java.awt.Font("Noteworthy", 1, 16)); // NOI18N
        dayNameLabel8.setForeground(new java.awt.Color(0, 153, 153));
        dayNameLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dayNameLabel8.setText("Уторак");

        jLabel2.setFont(new java.awt.Font("Noteworthy", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 0, 0));
        jLabel2.setText("(нема активности)");

        org.jdesktop.layout.GroupLayout panelUtorakLayout = new org.jdesktop.layout.GroupLayout(panelUtorak);
        panelUtorak.setLayout(panelUtorakLayout);
        panelUtorakLayout.setHorizontalGroup(
            panelUtorakLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelUtorakLayout.createSequentialGroup()
                .addContainerGap()
                .add(dayNameLabel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 140, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 8, Short.MAX_VALUE)
                .add(dayNameLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 140, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelUtorakLayout.setVerticalGroup(
            panelUtorakLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, panelUtorakLayout.createSequentialGroup()
                .add(panelUtorakLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(dayNameLabel)
                    .add(dayNameLabel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2))
                .add(0, 0, Short.MAX_VALUE))
        );

        panelSreda.setBackground(new java.awt.Color(255, 255, 255));
        panelSreda.setOpaque(false);

        dayNameLabel1.setFont(new java.awt.Font("Noteworthy", 1, 13)); // NOI18N
        dayNameLabel1.setForeground(new java.awt.Color(0, 153, 153));
        dayNameLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dayNameLabel1.setText("Уторак, 14. април 2013");

        dayNameLabel9.setFont(new java.awt.Font("Noteworthy", 1, 16)); // NOI18N
        dayNameLabel9.setForeground(new java.awt.Color(0, 153, 153));
        dayNameLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dayNameLabel9.setText("Среда");

        jLabel3.setFont(new java.awt.Font("Noteworthy", 1, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 0, 0));
        jLabel3.setText("(нема активности)");

        org.jdesktop.layout.GroupLayout panelSredaLayout = new org.jdesktop.layout.GroupLayout(panelSreda);
        panelSreda.setLayout(panelSredaLayout);
        panelSredaLayout.setHorizontalGroup(
            panelSredaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, panelSredaLayout.createSequentialGroup()
                .addContainerGap()
                .add(dayNameLabel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 140, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(dayNameLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 134, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelSredaLayout.setVerticalGroup(
            panelSredaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, panelSredaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(dayNameLabel1)
                .add(dayNameLabel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(jLabel3))
        );

        panelCetvrtak.setBackground(new java.awt.Color(255, 255, 255));
        panelCetvrtak.setOpaque(false);

        dayNameLabel11.setFont(new java.awt.Font("Noteworthy", 1, 16)); // NOI18N
        dayNameLabel11.setForeground(new java.awt.Color(0, 153, 153));
        dayNameLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dayNameLabel11.setText("Четвртак");

        dayNameLabel3.setFont(new java.awt.Font("Noteworthy", 1, 13)); // NOI18N
        dayNameLabel3.setForeground(new java.awt.Color(0, 153, 153));
        dayNameLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dayNameLabel3.setText("Среда, 14. април 2013");

        jLabel4.setFont(new java.awt.Font("Noteworthy", 1, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 0, 0));
        jLabel4.setText("(нема активности)");

        org.jdesktop.layout.GroupLayout panelCetvrtakLayout = new org.jdesktop.layout.GroupLayout(panelCetvrtak);
        panelCetvrtak.setLayout(panelCetvrtakLayout);
        panelCetvrtakLayout.setHorizontalGroup(
            panelCetvrtakLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelCetvrtakLayout.createSequentialGroup()
                .addContainerGap()
                .add(dayNameLabel11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 140, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(dayNameLabel3)
                .add(2, 2, 2))
        );
        panelCetvrtakLayout.setVerticalGroup(
            panelCetvrtakLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, panelCetvrtakLayout.createSequentialGroup()
                .add(panelCetvrtakLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(dayNameLabel11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(dayNameLabel3)
                    .add(jLabel4))
                .add(0, 0, Short.MAX_VALUE))
        );

        dayNameLabel3.getAccessibleContext().setAccessibleName("");

        panelPetak.setBackground(new java.awt.Color(255, 255, 255));
        panelPetak.setOpaque(false);

        dayNameLabel4.setFont(new java.awt.Font("Noteworthy", 1, 13)); // NOI18N
        dayNameLabel4.setForeground(new java.awt.Color(0, 153, 153));
        dayNameLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dayNameLabel4.setText("Петак, 14. април 2013");

        dayNameLabel12.setFont(new java.awt.Font("Noteworthy", 1, 16)); // NOI18N
        dayNameLabel12.setForeground(new java.awt.Color(0, 153, 153));
        dayNameLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dayNameLabel12.setText("Петак");

        jLabel5.setFont(new java.awt.Font("Noteworthy", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 0, 0));
        jLabel5.setText("(нема активности)");

        org.jdesktop.layout.GroupLayout panelPetakLayout = new org.jdesktop.layout.GroupLayout(panelPetak);
        panelPetak.setLayout(panelPetakLayout);
        panelPetakLayout.setHorizontalGroup(
            panelPetakLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, panelPetakLayout.createSequentialGroup()
                .addContainerGap()
                .add(dayNameLabel12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 140, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(dayNameLabel4)
                .add(4, 4, 4))
        );
        panelPetakLayout.setVerticalGroup(
            panelPetakLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelPetakLayout.createSequentialGroup()
                .add(panelPetakLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(dayNameLabel4)
                    .add(dayNameLabel12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel5))
                .add(0, 0, Short.MAX_VALUE))
        );

        dayNameLabel4.getAccessibleContext().setAccessibleName("");

        panelSubota.setBackground(new java.awt.Color(255, 255, 255));
        panelSubota.setOpaque(false);

        dayNameLabel6.setFont(new java.awt.Font("Noteworthy", 1, 13)); // NOI18N
        dayNameLabel6.setForeground(new java.awt.Color(0, 153, 153));
        dayNameLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dayNameLabel6.setText("Недеља, 14. април 2013");

        dayNameLabel13.setFont(new java.awt.Font("Noteworthy", 1, 16)); // NOI18N
        dayNameLabel13.setForeground(new java.awt.Color(0, 153, 153));
        dayNameLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dayNameLabel13.setText("Субота");

        jLabel6.setFont(new java.awt.Font("Noteworthy", 1, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 0, 0));
        jLabel6.setText("(нема активности)");

        org.jdesktop.layout.GroupLayout panelSubotaLayout = new org.jdesktop.layout.GroupLayout(panelSubota);
        panelSubota.setLayout(panelSubotaLayout);
        panelSubotaLayout.setHorizontalGroup(
            panelSubotaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, panelSubotaLayout.createSequentialGroup()
                .addContainerGap()
                .add(dayNameLabel13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 148, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(dayNameLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 134, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelSubotaLayout.setVerticalGroup(
            panelSubotaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelSubotaLayout.createSequentialGroup()
                .add(panelSubotaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(dayNameLabel13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(dayNameLabel6)
                    .add(jLabel6))
                .add(0, 0, Short.MAX_VALUE))
        );

        dayNameLabel6.getAccessibleContext().setAccessibleName("");

        panelNedelja.setBackground(new java.awt.Color(255, 255, 255));
        panelNedelja.setOpaque(false);

        dayNameLabel10.setFont(new java.awt.Font("Noteworthy", 1, 16)); // NOI18N
        dayNameLabel10.setForeground(new java.awt.Color(0, 153, 153));
        dayNameLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dayNameLabel10.setText("Недеља");

        dayNameLabel5.setFont(new java.awt.Font("Noteworthy", 1, 13)); // NOI18N
        dayNameLabel5.setForeground(new java.awt.Color(0, 153, 153));
        dayNameLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dayNameLabel5.setText("Среда, 14. април 2013");
        dayNameLabel5.setPreferredSize(new java.awt.Dimension(200, 22));

        jLabel7.setFont(new java.awt.Font("Noteworthy", 1, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 0, 0));
        jLabel7.setText("(нема активности)");

        org.jdesktop.layout.GroupLayout panelNedeljaLayout = new org.jdesktop.layout.GroupLayout(panelNedelja);
        panelNedelja.setLayout(panelNedeljaLayout);
        panelNedeljaLayout.setHorizontalGroup(
            panelNedeljaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelNedeljaLayout.createSequentialGroup()
                .addContainerGap()
                .add(dayNameLabel10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dayNameLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 134, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelNedeljaLayout.setVerticalGroup(
            panelNedeljaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelNedeljaLayout.createSequentialGroup()
                .add(panelNedeljaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(dayNameLabel10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(dayNameLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel7))
                .add(0, 0, Short.MAX_VALUE))
        );

        dayNameLabel5.getAccessibleContext().setAccessibleName("");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelNedelja, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(panelUtorak, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(panelSreda, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(panelPonedeljak, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(panelCetvrtak, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(panelSubota, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(panelPetak, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(panelPonedeljak, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(panelUtorak, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(panelSreda, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(panelCetvrtak, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(panelPetak, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(panelSubota, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(panelNedelja, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(381, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseReleased

    private void panelPonedeljakMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelPonedeljakMouseReleased
        JPanel panel = this;// TODO add your handling code here:
        setUpDay(panel);
    }//GEN-LAST:event_panelPonedeljakMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dayNameLabel;
    private javax.swing.JLabel dayNameLabel1;
    private javax.swing.JLabel dayNameLabel10;
    private javax.swing.JLabel dayNameLabel11;
    private javax.swing.JLabel dayNameLabel12;
    private javax.swing.JLabel dayNameLabel13;
    private javax.swing.JLabel dayNameLabel2;
    private javax.swing.JLabel dayNameLabel3;
    private javax.swing.JLabel dayNameLabel4;
    private javax.swing.JLabel dayNameLabel5;
    private javax.swing.JLabel dayNameLabel6;
    private javax.swing.JLabel dayNameLabel7;
    private javax.swing.JLabel dayNameLabel8;
    private javax.swing.JLabel dayNameLabel9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel panelCetvrtak;
    private javax.swing.JPanel panelNedelja;
    private javax.swing.JPanel panelPetak;
    private javax.swing.JPanel panelPonedeljak;
    private javax.swing.JPanel panelSreda;
    private javax.swing.JPanel panelSubota;
    private javax.swing.JPanel panelUtorak;
    // End of variables declaration//GEN-END:variables
}
