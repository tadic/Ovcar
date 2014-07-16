/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.gajenje_ovaca.gui.dnevnik;

import app.gajenje_ovaca.gui.dnevnik.belezenjeAktivnosti.BelezenjeAktivnosti;
import app.gajenje_ovaca.gui.dnevnik.belezenjeAktivnosti.NabavkaOvacaPanel;
import app.logic.Logic;
import app.model.Aktivnost;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders;

/**
 *
 * @author ivantadic
 */
public class RoundedPanel extends JPanel {

    /** Stroke size. it is recommended to set it to 1 for better view */
    protected int strokeSize = 1;
    /** Color of shadow */
    protected Color shadowColor = Color.black;
    /** Sets if it drops shadow */
    protected boolean shady = true;
    /** Sets if it has an High Quality view */
    protected boolean highQuality = true;
    /** Double values for Horizontal and Vertical radius of corner arcs */
    protected Dimension arcs = new Dimension(20, 20);
    /** Distance between shadow border and opaque panel border */
    protected int shadowGap = 5;
    
    private JLabel actionNameLabel;
    private JLabel lokacijaLabel;
    private JLabel troskoviLabel;
    private JLabel napomenaLabel;
    private JButton deleteButton, editButton;
    private Aktivnost aktivnost;
    private Color color;
    private Logic logic;
    private final JPanel mainPanel;
    //private Dnevnik dnevnik;


public RoundedPanel(Aktivnost aktivnost, JPanel mainPanel, Logic logic) {
        super();
       // this.dnevnik = dnevnik;
        this.mainPanel = mainPanel;
        this.logic = logic;
        this.aktivnost = aktivnost;
        initComponents();
}

private void initComponents(){
        this.color = new Color(aktivnost.getVrstaAktivnosti().getColor());
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(400,aktivnost.getHight()));
        this.setLayout(null);
        
                deleteButton = new JButton(new ImageIcon(getClass().getResource("/images/delete.png")));
                deleteButton.setPreferredSize(new Dimension(25,25));
                deleteButton.setOpaque(false);
                deleteButton.setContentAreaFilled(false);
                deleteButton.setBorderPainted(false);
                deleteButton.setVisible(false);
                deleteButton.setBounds((this.getPreferredSize().width-28 + this.getInsets().left), (this.getInsets().top-3),
                deleteButton.getPreferredSize().width, deleteButton.getPreferredSize().height);
                deleteButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent evn){
                        destroyThisAction();
                    }
                });


                editButton = new JButton(new ImageIcon(getClass().getResource("/images/pencil.png")));
                editButton.setPreferredSize(new Dimension(25,25));
                editButton.setOpaque(false);
                editButton.setContentAreaFilled(false);
                editButton.setBorderPainted(false);
                editButton.setVisible(false);
                editButton.setBounds((this.getPreferredSize().width-50 + this.getInsets().left), (this.getInsets().top-3),
                        editButton.getPreferredSize().width, editButton.getPreferredSize().height);
                editButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent evn){
                       
                        NabavkaOvacaPanel editPanel = new NabavkaOvacaPanel(mainPanel, logic, aktivnost);
            editPanel.setVisible(true);
            mainPanel.removeAll();
            mainPanel.add(editPanel, BorderLayout.CENTER);  
            mainPanel.repaint();
            mainPanel.revalidate();
                    }
                });
                actionNameLabel = new JLabel(aktivnost.getVrstaAktivnosti().getName());
                actionNameLabel.setFont(new Font("Ariel", 1,13));
                actionNameLabel.setBounds((10 + this.getInsets().left), (this.getInsets().top+2), 
                    200, actionNameLabel.getPreferredSize().height);
                actionNameLabel.setForeground(color.darker());

                lokacijaLabel = new JLabel("Lokacija:       "+aktivnost.getLokacija());
                lokacijaLabel.setFont(new Font("Ariel", Font.PLAIN,13));
                lokacijaLabel.setBounds((10 + this.getInsets().left), (this.getInsets().top+40), 
                    200, lokacijaLabel.getPreferredSize().height);
                lokacijaLabel.setForeground(color.darker());
                
                troskoviLabel = new JLabel("Troskovi:      "+Aktivnost.round(aktivnost.getTroskovi(),1)+" €");
                troskoviLabel.setFont(new Font("Ariel", Font.PLAIN,13));
                troskoviLabel.setBounds((10 + this.getInsets().left), (this.getInsets().top+60), 
                    200, troskoviLabel.getPreferredSize().height);
                troskoviLabel.setForeground(color.darker());
                
                napomenaLabel = new JLabel("Napomena:   "+aktivnost.getNapomena());
                napomenaLabel.setFont(new Font("Ariel", Font.PLAIN,13));
                napomenaLabel.setBounds((10 + this.getInsets().left), (this.getInsets().top+80), 
                    200, napomenaLabel.getPreferredSize().height);
                napomenaLabel.setForeground(color.darker());
                
                JPopupMenu pop = new JPopupMenu();
                JMenuItem info = new JMenuItem("Vidi sve informacije");
                JMenuItem cut = new JMenuItem("Seci");
                JMenuItem copy = new JMenuItem("Kopiraj");
                JMenuItem paste = new JMenuItem("Spusti");
                JMenuItem edit = new JMenuItem("Promeni");  
                edit.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent evn){
                        new BelezenjeAktivnosti(mainPanel, logic, aktivnost).showEditPanel();
                    }
                });
                JMenuItem delete = new JMenuItem("Izbrisi");
                delete.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent evn){
                        destroyThisAction();
                    }
                });

                pop.add(info);
                pop.add(cut);
                pop.add(copy);
                pop.add(paste);
                pop.add(edit);
                pop.add(delete);

    this.add(actionNameLabel);
    this.add(lokacijaLabel);
    this.add(troskoviLabel);
    this.add(napomenaLabel);
    this.add(deleteButton);
    this.add(editButton);
    this.setComponentPopupMenu(pop);
    this.setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue(), 50));
    repaint();
    addMouseCapability();
        
}

    public Aktivnost getAktivnost(){
        return aktivnost;
    }

    
    private void destroyThisAction(){
        DnevniPanel dnevni = (DnevniPanel) getParent();
        dnevni.deleteActivity(this);
    }

    public void setUnfocused(){
        setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue(), 90));
        deleteButton.setVisible(false);
        editButton.setVisible(false);
        revalidate();
        repaint();
    }

    public void addMouseCapability(){  
           this.addMouseListener(new MouseAdapter() {
                   @Override
                   public void mousePressed(MouseEvent e) {

                   }
                   @Override
                   public void mouseEntered(MouseEvent e)
                   {
                      focusSet();
                   }
           }); 
   }
 
    private void focusSet(){
        DnevniPanel dp = (DnevniPanel) this.getParent();
        dp.unfocusAll();
                      setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue(), 180));
                      deleteButton.setVisible(true);
                      editButton.setVisible(true);
                      revalidate();
                      repaint();
    }
   public void setPlace(Insets insets, int x, int y){
       Dimension size = this.getPreferredSize();
       this.setBounds(x + insets.left, y + insets.top,
                size.width, size.height);
   }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;

        if (highQuality) {
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
			RenderingHints.VALUE_ANTIALIAS_ON);
        }
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width - shadowGap, 
		height - shadowGap, arcs.width, arcs.height);
        graphics.setColor(color.darker());
        graphics.drawRoundRect(0, 0, width - shadowGap, 
		height - shadowGap, arcs.width, arcs.height);
        graphics.setStroke(new BasicStroke());
        Border border = new BasicBorders.MarginBorder();
        
        this.setBorder(border);
    }

    public void refresh() {
        System.err.println("Refreshaaa" + aktivnost.getVremeZavrsetka());
       //
        aktivnost = logic.getActivityWithId(aktivnost.getId());
       //aktivnost = logic.getServer().find(Aktivnost.class, aktivnost.getId());
         System.err.println("Refreshaaa" + aktivnost.getVremeZavrsetka());
        color = new Color(aktivnost.getVrstaAktivnosti().getColor());
        actionNameLabel.setText(aktivnost.getVrstaAktivnosti().getName());
       // setPreferredSize(new Dimension(400,100));
        this.setBounds(aktivnost.getXPosition(), aktivnost.getYPosition(), aktivnost.getWidth(), aktivnost.getHight());
        this.setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue(), 90));
        revalidate();
        repaint();
    }

    void setActivity(Aktivnost act) {
        aktivnost = act;
        color = new Color(aktivnost.getVrstaAktivnosti().getColor());
        actionNameLabel.setText(aktivnost.getVrstaAktivnosti().getName());
        this.setBounds(aktivnost.getXPosition(), aktivnost.getYPosition(), aktivnost.getWidth(), aktivnost.getHight());
        this.setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue(), 90));
        revalidate();
        repaint();
    }


} 

