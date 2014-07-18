/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.gajenje_ovaca.gui.dnevnik;

import app.logic.Logic;
import app.model.Dan;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import org.jdesktop.layout.GroupLayout;

/**
 *
 * @author ivantadic
 */
public class Kalendar extends javax.swing.JPanel {

    /**
     * Creates new form Kalendar
     */
    private final Dnevnik dnevnik;
    private Logic logic;
    private int year, month, day, selectedDay;
    private final String[] monthNames = {"januar","februar","mart","april","maj","jun",
                                            "jul","avgust","septembar","oktobar","novembar","decembar"};
    
    public Kalendar(Dnevnik parent, Logic l) {
        initComponents();
        this.dnevnik = parent;
        this.logic = l;
        FlowLayout gl = new FlowLayout();
        gl.setVgap(1);
    
        year = logic.getSelectedDay().get(Calendar.YEAR);
        month = logic.getSelectedDay().get(Calendar.MONTH);
        day = logic.getSelectedDay().get(Calendar.DAY_OF_MONTH);
        selectedDay =day;
        setSelectedDate(logic.getSelectedDay());
       // setUpKalendar(logic.getSelectedDay());
    }
    
    public final void setUpKalendar(Calendar c){
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);
        int start = (cal.get(Calendar.DAY_OF_WEEK)+5)%7 +1;
        int max = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        //System.out.println("start day" + start);
        setDays(start, max);
        setWeeks(c);
        setLastWeekVisibilty(start, max);
        selectToday(start);
        markSelectedDay(c.get(Calendar.DAY_OF_MONTH));
        
        markMonthAndYear(c);
    }
    
    private void setDays(int start, int max){
       int i=1;
       int j=1;
       for (Component comp: jPanel1.getComponents()){
            JLabel label = (JLabel)  comp;
            label.setOpaque(true);
            label.setBackground(Color.white);
            label.setBorder(null);
            if (i<start || j>max){
              label.setText(" ");
            } else {
                Calendar c = Calendar.getInstance();
                c.set(year, month, j);
                Dan d = logic.getDayWithDate(c);
                label.setText(""+ j++);
                //System.out.println("Boja: ");
                if (d.getAktivnosti().size()>0){
                  //  System.out.println("Boja labela: ");
                    Color color = new Color(d.getAktivnosti().get(0).getVrstaAktivnosti().getColor());
                    //label.setOpaque(true);
                    label.setBackground(color);
                    label.revalidate();
                    
                }
                       
                setActionToLabel(label);
            }
            i++;
       }
       
    }
    
    private void setActionToLabel(final JLabel label){
       label.addMouseListener(new MouseAdapter()  {  
         @Override
         public void mouseReleased(MouseEvent e) {  
                int d = Integer.parseInt(label.getText());
               // markSelectedDay(d);
                Calendar c = Calendar.getInstance();
                c.set(year, month, d);
                logic.setSelectedDay(c);
                dnevnik.setDate();
         }  
       });   
    }
    
    private void markSelectedDay(int day){
        //System.out.println("selekted " + day);
        Calendar c = Calendar.getInstance();
        c.set(year, month, 1);
        int start = (c.get(Calendar.DAY_OF_WEEK)+5)%7 +1;
        JLabel label = (JLabel)  jPanel1.getComponent(start+day-2);
        JLabel label2 = (JLabel)  jPanel1.getComponent(start+selectedDay-2);
       // label2.setOpaque(true);
        label2.setBorder(null);
        label2.revalidate();
       // label.setOpaque(true);
        label.setBorder(new LineBorder(Color.red,2));
        label.revalidate();
        selectedDay = day;
    }

    private void selectToday(int start){
        Calendar c = Calendar.getInstance();
        int m = c.get(Calendar.MONTH);
        int y = c.get(Calendar.YEAR);
        //System.out.println("mesec trenutni " + month + " selektovani " + m);
        if (m == month && y == year){
            JLabel label = (JLabel)  jPanel1.getComponent(start+day-2);
            label.setBorder(new LineBorder(Color.blue));
        }
    }

    private void setLastWeekVisibilty(int start, int max){
       if ((36-start-max)<0){
           jLabelLastWeek.setVisible(true);
       } else {
           jLabelLastWeek.setVisible(false);
       }
    }
    public void setDate(){
        setSelectedDate(logic.getSelectedDay());
    }
    
    private void setWeeks(Calendar c){
       Calendar cal = Calendar.getInstance();
       cal.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
       int startWeek = cal.get(Calendar.WEEK_OF_YEAR);
       if (cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
           startWeek--;
       }
       jLabelWeek1.setText(""+startWeek);
       jLabelWeek2.setText(""+ ((startWeek%52)+1));
       jLabelWeek3.setText(""+ ((startWeek+1)%52 +1));
       jLabelWeek4.setText(""+ ((startWeek+2)%52 +1));
       jLabelWeek5.setText(""+ ((startWeek+3)%52+1));
       jLabelLastWeek.setText(""+ ((startWeek+4)%52+1));
    }



    public void setSelectedDate(Calendar selectedDate) {
        if (year== selectedDate.get(Calendar.YEAR) && month == selectedDate.get(Calendar.MONTH)){
            markSelectedDay(selectedDate.get(Calendar.DAY_OF_MONTH));
        } else {  //optimization
            year = selectedDate.get(Calendar.YEAR);
            month = selectedDate.get(Calendar.MONTH);
            setUpKalendar(selectedDate);
        }
    }
    
    public String getMonthName(int n){
        return monthNames[n];
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabelLastWeek = new javax.swing.JLabel();
        jLabelWeek5 = new javax.swing.JLabel();
        jLabelWeek2 = new javax.swing.JLabel();
        jLabelWeek1 = new javax.swing.JLabel();
        jLabelWeek3 = new javax.swing.JLabel();
        jLabelWeek4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        godinaSpinner = new javax.swing.JSpinner();
        mesecComboBox = new javax.swing.JComboBox();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel85.setFont(new java.awt.Font("Monaco", 0, 10)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(0, 153, 153));
        jLabel85.setText("Pon");

        jLabel86.setFont(new java.awt.Font("Monaco", 0, 10)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(204, 0, 0));
        jLabel86.setText("Ned");

        jLabelLastWeek.setFont(new java.awt.Font("Lucida Grande", 0, 9)); // NOI18N
        jLabelLastWeek.setForeground(new java.awt.Color(153, 153, 153));
        jLabelLastWeek.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelLastWeek.setText("23");
        jLabelLastWeek.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(245, 240, 240), null));

        jLabelWeek5.setFont(new java.awt.Font("Lucida Grande", 0, 9)); // NOI18N
        jLabelWeek5.setForeground(new java.awt.Color(153, 153, 153));
        jLabelWeek5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelWeek5.setText("23");
        jLabelWeek5.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(245, 240, 240), null));

        jLabelWeek2.setFont(new java.awt.Font("Lucida Grande", 0, 9)); // NOI18N
        jLabelWeek2.setForeground(new java.awt.Color(153, 153, 153));
        jLabelWeek2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelWeek2.setText("23");
        jLabelWeek2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(245, 240, 240), null));

        jLabelWeek1.setFont(new java.awt.Font("Lucida Grande", 0, 9)); // NOI18N
        jLabelWeek1.setForeground(new java.awt.Color(153, 153, 153));
        jLabelWeek1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelWeek1.setText("23");
        jLabelWeek1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(245, 240, 240), null));

        jLabelWeek3.setFont(new java.awt.Font("Lucida Grande", 0, 9)); // NOI18N
        jLabelWeek3.setForeground(new java.awt.Color(153, 153, 153));
        jLabelWeek3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelWeek3.setText("23");
        jLabelWeek3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(245, 240, 240), null));

        jLabelWeek4.setFont(new java.awt.Font("Lucida Grande", 0, 9)); // NOI18N
        jLabelWeek4.setForeground(new java.awt.Color(153, 153, 153));
        jLabelWeek4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelWeek4.setText("23");
        jLabelWeek4.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(245, 240, 240), null));

        jPanel1.setMaximumSize(new java.awt.Dimension(174, 150));
        jPanel1.setOpaque(false);

        jLabel38.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel38.setText("23");
        jLabel38.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel38.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel38.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel38.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel40.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel40.setText("23");
        jLabel40.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel40.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel40.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel40.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel42.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(153, 0, 0));
        jLabel42.setText("23");
        jLabel42.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel42.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel42.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel42.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel36.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel36.setText("23");
        jLabel36.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel36.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel36.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel36.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel37.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel37.setText("23");
        jLabel37.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel37.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel37.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel37.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel39.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel39.setText("23");
        jLabel39.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel39.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel39.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel39.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel12.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(153, 0, 0));
        jLabel12.setText("23");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel12.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel12.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel12.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel41.setBackground(new java.awt.Color(153, 255, 153));
        jLabel41.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel41.setText("23");
        jLabel41.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel41.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel41.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel41.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel66.setBackground(new java.awt.Color(51, 255, 153));
        jLabel66.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel66.setText("23");
        jLabel66.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel66.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel66.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel66.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel67.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel67.setText("23");
        jLabel67.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel67.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel67.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel67.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel6.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel6.setText("23");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel6.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel6.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel6.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel4.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel4.setText("23");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel4.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel4.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel4.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel5.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel5.setText("23");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel5.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel5.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel5.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel14.setBackground(new java.awt.Color(0, 204, 204));
        jLabel14.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(153, 0, 0));
        jLabel14.setText("23");
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel14.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel14.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel14.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel3.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel3.setText("23");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel3.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel3.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel3.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel79.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel79.setText("23");
        jLabel79.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel79.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel79.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel79.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel75.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel75.setText("23");
        jLabel75.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel75.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel75.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel75.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel76.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel76.setText("23");
        jLabel76.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel76.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel76.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel76.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel77.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel77.setText("23");
        jLabel77.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel77.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel77.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel77.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel78.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel78.setText("23");
        jLabel78.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel78.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel78.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel78.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel74.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel74.setText("23");
        jLabel74.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel74.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel74.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel74.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel52.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel52.setText("23");
        jLabel52.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel52.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel52.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel52.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel53.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel53.setText("23");
        jLabel53.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel53.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel53.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel53.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel9.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel9.setText("23");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel9.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel9.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel9.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel8.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel8.setText("23");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel8.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel8.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel8.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel10.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 0, 0));
        jLabel10.setText("23");
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel10.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel10.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel10.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel56.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel56.setText("23");
        jLabel56.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel56.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel56.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel56.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel54.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel54.setText("23");
        jLabel54.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel54.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel54.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel54.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel55.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel55.setText("23");
        jLabel55.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel55.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel55.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel55.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel2.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel2.setText("23");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel2.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel2.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel2.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel1.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel1.setText("23");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel1.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel1.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel1.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel11.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(153, 0, 0));
        jLabel11.setText("23");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel11.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel11.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel11.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel60.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel60.setText("23");
        jLabel60.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel60.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel60.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel60.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel61.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel61.setText("23");
        jLabel61.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel61.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel61.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel61.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel62.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel62.setText("23");
        jLabel62.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel62.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel62.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel62.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel63.setBackground(new java.awt.Color(0, 204, 204));
        jLabel63.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel63.setText("23");
        jLabel63.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel63.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel63.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel63.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel64.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel64.setText("23");
        jLabel64.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel64.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel64.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel64.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel65.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel65.setText("23");
        jLabel65.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel65.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel65.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel65.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel57.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel57.setText("23");
        jLabel57.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel57.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel57.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel57.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel7.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 0, 0));
        jLabel7.setText("23");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel7.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel7.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel7.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel58.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel58.setText("23");
        jLabel58.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel58.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel58.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel58.setPreferredSize(new java.awt.Dimension(20, 14));

        jLabel59.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        jLabel59.setText("23");
        jLabel59.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel59.setMaximumSize(new java.awt.Dimension(20, 14));
        jLabel59.setMinimumSize(new java.awt.Dimension(20, 14));
        jLabel59.setPreferredSize(new java.awt.Dimension(20, 14));

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                .add(jPanel1Layout.createSequentialGroup()
                                    .add(jLabel63, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jLabel62, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jLabel67, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jLabel65, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jLabel66, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jLabel64, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jLabel12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(jLabel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jLabel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jLabel55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jLabel53, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jLabel54, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jLabel52, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jLabel10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jLabel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel59, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel58, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel61, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel57, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel60, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel56, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel74, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel79, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel77, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel78, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel76, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel37, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel38, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel39, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel41, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel42, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .add(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel64, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel66, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel65, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel67, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel62, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel63, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel52, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel54, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel53, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel56, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel60, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel57, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel61, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel58, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel59, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel74, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel79, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel77, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel78, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel76, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel42, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel41, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel39, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel38, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel37, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel87.setFont(new java.awt.Font("Monaco", 0, 10)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(0, 153, 153));
        jLabel87.setText("Sub");

        jLabel88.setFont(new java.awt.Font("Monaco", 0, 10)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(0, 153, 153));
        jLabel88.setText("Uto");

        jLabel89.setFont(new java.awt.Font("Monaco", 0, 10)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(0, 153, 153));
        jLabel89.setText("Сре");

        jLabel90.setFont(new java.awt.Font("Monaco", 0, 10)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(0, 153, 153));
        jLabel90.setText("Čet");

        jLabel91.setFont(new java.awt.Font("Monaco", 0, 10)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(0, 153, 153));
        jLabel91.setText("Pet");

        godinaSpinner.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        godinaSpinner.setModel(new javax.swing.SpinnerListModel(new String[] {"2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"}));
        godinaSpinner.setFocusable(false);
        godinaSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                godinaSpinnerStateChanged(evt);
            }
        });

        mesecComboBox.setBackground(new java.awt.Color(255, 255, 255));
        mesecComboBox.setFont(new java.awt.Font("Monaco", 0, 12)); // NOI18N
        mesecComboBox.setForeground(new java.awt.Color(102, 102, 102));
        mesecComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Januar", "Februar", "Mart", "April", "Maj", "Jun", "Jul", "Avgust", "Septembar", "Oktobar", "Novembar", "Decembar" }));
        mesecComboBox.setOpaque(true);
        mesecComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mesecComboBoxActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabelLastWeek, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabelWeek5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabelWeek4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabelWeek3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(jLabelWeek2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jLabelWeek1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(mesecComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 110, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(godinaSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 62, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .add(layout.createSequentialGroup()
                        .add(4, 4, 4)
                        .add(jLabel85, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel88, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel89, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel91, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel87, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel86, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(godinaSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(mesecComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel86, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel87, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel91, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel85, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel88, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel89, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(5, 5, 5)))
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(6, 6, 6)
                        .add(jLabelWeek1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabelWeek2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabelWeek3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabelWeek4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabelWeek5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabelLastWeek, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    private void mesecComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mesecComboBoxActionPerformed
        if (logic.getMonth()!= mesecComboBox.getSelectedIndex()){
            logic.setSelectedDay(year, mesecComboBox.getSelectedIndex(), 1);
        }
        dnevnik.setDate();
    }//GEN-LAST:event_mesecComboBoxActionPerformed

    private void godinaSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_godinaSpinnerStateChanged
        int y = Integer.parseInt((String)godinaSpinner.getValue());
        if (logic.getYear()!=y){
            day=1;
            logic.setSelectedDay(y, month, day);
        }
        dnevnik.setDate();
    }//GEN-LAST:event_godinaSpinnerStateChanged

    public String selectedDateToString(){
        final String[] daysOfWeek = {"ponedeljak","utorak","sreda","četvrtak","petak","subota","nedelja"};
        int dayOfWeek = (logic.getSelectedDay().get(Calendar.DAY_OF_WEEK) + 5)%7;
        StringBuilder sb = new StringBuilder(daysOfWeek[dayOfWeek]);
        sb.append(", ").append(selectedDay).append(".").append(monthNames[month]);
        sb.append(' ').append(year);
        return sb.toString();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner godinaSpinner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabelLastWeek;
    private javax.swing.JLabel jLabelWeek1;
    private javax.swing.JLabel jLabelWeek2;
    private javax.swing.JLabel jLabelWeek3;
    private javax.swing.JLabel jLabelWeek4;
    private javax.swing.JLabel jLabelWeek5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox mesecComboBox;
    // End of variables declaration//GEN-END:variables

    private void markMonthAndYear(Calendar c) {
        String y = Integer.toString(c.get(Calendar.YEAR));
        mesecComboBox.setSelectedIndex(c.get(Calendar.MONTH));
        godinaSpinner.setValue(y);
    }
}
