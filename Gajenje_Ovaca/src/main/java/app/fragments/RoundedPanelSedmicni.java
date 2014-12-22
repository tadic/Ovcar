/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.fragments;

import app.logic.Logic;
import app.model.Aktivnost;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders;

/**
 *
 * @author ivantadic
 */
public class RoundedPanelSedmicni extends JPanel {

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
    
    private JLabel actionNameLabel, actionTimeLabel;
    private JButton deleteButton, editButton;
    private Aktivnost aktivnost;
    private Color color;
    private Logic logic;

public RoundedPanelSedmicni() {
        super();
        setOpaque(false);
    }
public RoundedPanelSedmicni(Aktivnost aktivnost, Logic logic) {
        super();
        this.logic = logic;
        this.aktivnost = aktivnost;
        initComponents();
}

private void initComponents(){
        this.color = new Color(aktivnost.getVrstaAktivnosti().getColor());
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(350,32));
        this.setLayout(null);
                actionTimeLabel = new JLabel(aktivnost.vremeToString());
                actionNameLabel = new JLabel(aktivnost.getVrstaAktivnosti().getName());
               
                actionTimeLabel.setFont(new Font("Ariel", 1,13));
                actionTimeLabel.setBounds((23 + this.getInsets().left), (this.getInsets().top+2), 
                    200, actionNameLabel.getPreferredSize().height);
                actionTimeLabel.setForeground(color);
                
                actionNameLabel.setFont(new Font("Ariel", 1,13));
                actionNameLabel.setBounds((125 + this.getInsets().left), (this.getInsets().top+2), 
                    200, actionNameLabel.getPreferredSize().height);
                actionNameLabel.setForeground(color.darker());

    
    this.add(actionTimeLabel);
    this.add(actionNameLabel);

    this.setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue(), 200));
    repaint();
    //addMouseCapability();
        
}

public Aktivnost getAktivnost(){
    return aktivnost;
}

public void setUnfocused(){
    setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue(), 90));
    deleteButton.setVisible(false);
    editButton.setVisible(false);
    revalidate();
    repaint();
}
/*
 public void addMouseCapability(){  
        this.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                   
                }
                @Override
                public void mouseEntered(MouseEvent e)
                {
                   setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue(), 50));
//                   deleteButton.setVisible(true);
 //                  editButton.setVisible(true);
                   revalidate();
                   repaint();
                }
        }); 
}
 */
 
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
       // graphics.fillRoundRect(0, 0, width - shadowGap, 
		//height - shadowGap, arcs.width, arcs.height);
        graphics.fillRect(5, 3, 12, 12);
        graphics.setColor(color.darker());
        graphics.drawRect(5, 3, 12, 12);
        //graphics.drawRoundRect(0, 0, width - shadowGap, 
	//	height - shadowGap, arcs.width, arcs.height);
        graphics.setStroke(new BasicStroke());
        Border border = new BasicBorders.MarginBorder();
        
        this.setBorder(border);
    }

    public void refresh() {
     
        aktivnost = logic.getActivityWithId(aktivnost.getId());
        color = new Color(aktivnost.getVrstaAktivnosti().getColor());
        actionNameLabel.setText(aktivnost.getVrstaAktivnosti().getName());
       // setPreferredSize(new Dimension(400,100));
        Rectangle rec =this.getBounds();
        this.setBounds(aktivnost.getXPosition(), aktivnost.getYPosition(), aktivnost.getWidth(), aktivnost.getHight());
        this.setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue(), 90));
        revalidate();
        repaint();
    }


} 

