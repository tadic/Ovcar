
package app.gajenje_ovaca.gui.dnevnik;

import app.gajenje_ovaca.gui.dnevnik.belezenjeAktivnosti.BelezenjeAktivnosti;
import app.logic.Logic;
import app.model.Aktivnost;
import app.model.Dan;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class DnevniPanel extends JPanel{
    private static final int TOP_MARGIN = 0;
    private Dan dan;
    private JPanel mainPanel;
    private Dnevnik dnevnik;
    

    private Logic logic;
    /**
     * Creates new form DnavniKalendarPanel
     */

    public DnevniPanel(Dnevnik parent, final Logic logic, final JPanel mainPanel){
       this.dnevnik = parent;
       this.mainPanel = mainPanel;
       this.logic = logic;
        setPreferredSize(new Dimension(466, 1532));
        setLayout(null);
        setBackground(Color.WHITE);

        final Insets insets = this.getInsets();
        this.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
                int n = e.getY()/32;
                n=n*32;
                int sati = n/64;
                int minuta = 30*((n/32) - (sati*2));
                Aktivnost aktivnost = new Aktivnost(sati, minuta);
                aktivnost.setVrstaAktivnosti(dnevnik.getChoosenActivityType());
                aktivnost.setDan(dan);

                new BelezenjeAktivnosti(mainPanel, logic, aktivnost).showEditPanel();
             }
             @Override
             public void mouseEntered(MouseEvent e)
             {
                unfocusAll();
             }
       
        });
 
 }
    public void unfocusAll(){
        for (Component c: getComponents()){
               RoundedPanel r = (RoundedPanel) c;
               r.setUnfocused();
           }
    }
   /* private void setUpSedmicnaList(){
        dnevnik.setUpSedmicnaLista();
    }
    */
    
    public void setDate(){
        this.removeAll();
        dan = logic.getDayWithDate(logic.getSelectedDay());
      //  Aktivnost aka = logic.getActivityWithId(1);
         //   System.out.println("nabavljenih grla id: " + aka.getNabavljenaGrla().get(0).getNapomena());
            for (Aktivnost a: dan.getAktivnosti()){

                RoundedPanel eventPanel2 = new RoundedPanel(a,mainPanel, logic);
                eventPanel2.setPlace(this.getInsets(), a.getXPosition(), a.getYPosition());
                add(eventPanel2);

            }

        revalidate();
    }

public void showEvents(){
    for (Aktivnost a: dan.getAktivnosti()){
            this.add(new RoundedPanel(a, mainPanel, logic));
    }
    revalidate();
}
  /*         
public void refreshEvents(){
    for (Component c: getComponents()){
        RoundedPanel rp = (RoundedPanel) c;
        rp.refresh();
    }
    setUpSedmicnaList();
    this.revalidate();
    this.repaint();
}
    */
public void deleteActivity(RoundedPanel rp){
    logic.delete(rp.getAktivnost());
    dan.getAktivnosti().remove(rp.getAktivnost());
    dnevnik.setUpSedmicnaLista();
    this.remove(rp);
    this.revalidate();
    this.repaint();
}

@Override
protected void paintComponent(Graphics g){
    super.paintComponent(g);
    g.setFont(new Font("Arial", Font.BOLD, 14));
    for (int i=0; i<=24; i++){
            g.setColor(new Color(202, 202, 202));
            g.drawString(getTimeString(i), 10, TOP_MARGIN + i*64);
            
            g.drawLine(55, TOP_MARGIN + (i*64)-5, 450, TOP_MARGIN + (i*64)-5);
            g.setColor(new Color(230, 227, 227));
            g.drawLine(55, TOP_MARGIN + (i*64)+27, 450, TOP_MARGIN + (i*64)+27);
    }
    repaint();
}
    
private String getTimeString(int i){
        StringBuilder sb = new StringBuilder();
        if (i<10){
            sb.append('0');
        }
        sb.append(Integer.toString(i));
        sb.append(":00");
        return sb.toString();
    }

}
