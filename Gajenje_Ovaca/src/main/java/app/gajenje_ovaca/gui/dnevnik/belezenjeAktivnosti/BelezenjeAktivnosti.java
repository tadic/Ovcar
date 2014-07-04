/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.gajenje_ovaca.gui.dnevnik.belezenjeAktivnosti;

import app.gajenje_ovaca.gui.dnevnik.Dnevnik;
import app.logic.Logic;
import app.model.Aktivnost;
import app.model.VrsteAktivnosti;
import com.avaje.ebean.EbeanServer;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders;

/**
 *
 * @author ivantadic
 */
public class BelezenjeAktivnosti extends javax.swing.JDialog {

private Logic logic;
private Aktivnost aktivnost;
private Dnevnik dnevnik;


    public BelezenjeAktivnosti(Dnevnik dnevnik, Logic logic, Aktivnost aktivnost, javax.swing.JFrame frame, boolean modal) {
        super(frame);
        setPreferredSize(frame.getSize());
        this.aktivnost = aktivnost;
        this.logic = logic;
        this.dnevnik = dnevnik;
        setUndecorated(true);
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        initComponents();
        showEditPanel();
    }
    private void showEditPanel(){
        this.setLayout(new FlowLayout());
        if (aktivnost.getVrstaAktivnosti().getName().equals("Nabavka ovaca")){
            NabavkaOvacaPanel editPanel = new NabavkaOvacaPanel(this, logic, aktivnost);
            editPanel.setVisible(true);
            this.add(editPanel, BorderLayout.CENTER);
        }      
        getRootPane().setOpaque (false);  
        getContentPane().setBackground (new Color (0, 0, 0, 50));
        setBackground (new Color (0, 0, 0, 0));  
        pack();
        Point loc = new Point(0,20);
        setLocation(loc);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Бележење Активности");
        setBackground(java.awt.Color.white);
        setBounds(new java.awt.Rectangle(50, 50, 0, 0));
        setResizable(false);
        setUndecorated(true);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 369, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 330, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BelezenjeAktivnosti.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BelezenjeAktivnosti.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BelezenjeAktivnosti.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BelezenjeAktivnosti.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                BelezenjeAktivnosti dialog = new BelezenjeAktivnosti(null, null, new Aktivnost(), new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    void close() {
       dnevnik.setDate();
       this.dispose();
    }
}
