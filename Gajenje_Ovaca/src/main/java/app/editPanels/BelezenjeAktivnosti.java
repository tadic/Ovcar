/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.editPanels;

import app.logic.Logic;
import app.model.Aktivnost;
import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author ivantadic
 */
public class BelezenjeAktivnosti{

private Logic logic;
private Aktivnost aktivnost;
private JPanel mainpanel;


    public BelezenjeAktivnosti(JPanel mainPanel, Logic logic, Aktivnost aktivnost) {
       // initComponents();
        this.mainpanel = mainPanel;
        this.aktivnost = aktivnost;
        this.logic = logic;
       // initComponents();
    }
    
    public void showEditPanel(){
        if (aktivnost.getVrstaAktivnosti().getName().equals("Nabavka ovaca")){
            setPanel(new NabavkaOvacaPanel(mainpanel, logic, aktivnost));
        }   else if (aktivnost.getVrstaAktivnosti().getName().equals("Jagnjenje")){
            setPanel(new JagnjenjaPanel(mainpanel, logic, aktivnost));
        }   else if (aktivnost.getVrstaAktivnosti().getName().equals("Uginuce")){
            setPanel(new UginucaPanel(mainpanel, logic, aktivnost));
        } else if (aktivnost.getVrstaAktivnosti().getName().equals("Prodaja")){
            setPanel(new ProdajaPanel(mainpanel, logic, aktivnost));
        } else if (aktivnost.getVrstaAktivnosti().getName().equals("Vakcinacija/Lečenje")){
            setPanel(new VakcinacijaPanel(mainpanel, logic, aktivnost));
        } else if (aktivnost.getVrstaAktivnosti().getName().equals("Merenje")){
            setPanel(new MerenjePanel(mainpanel, logic, aktivnost));
        } else {
            setPanel(new RadoviPanel(mainpanel, logic, aktivnost));
        }
        
    }
    
    private void setPanel(JPanel editPanel){
            editPanel.setVisible(true);
            mainpanel.removeAll();
            mainpanel.add(editPanel, BorderLayout.CENTER);  
            mainpanel.repaint();
            mainpanel.revalidate(); 
    }
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    /*
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
*/
   

