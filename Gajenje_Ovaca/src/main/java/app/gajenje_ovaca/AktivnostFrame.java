/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.gajenje_ovaca;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders;

/**
 *
 * @author ivantadic
 */
public class AktivnostFrame extends JFrame{
    
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
    /** The offset of shadow.  */
    protected int shadowOffset = 4;
    /** The transparency value of shadow. ( 0 - 255) */
    protected int shadowAlpha = 150;
    private JLabel eventNameLabel;
    private int vreme;

public AktivnostFrame() {
        super();
    }
public AktivnostFrame(Color c, int vreme) {
        super();
        this.vreme = vreme;
        //setOpaque(false);
        setPreferredSize(new Dimension(400,68));
        
        setBackground(new Color(c.getRed(), c.getGreen(), c.getBlue(), 50));
        addMouseCapability();
        eventNameLabel = new JLabel("Nova Aktivnost");
        this.add(eventNameLabel);
        
        
        
        
}
 public void addMouseCapability(){
        final Insets insets = this.getInsets();
        this.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                     final JLabel timeLabel = new JLabel(Integer.toString(vreme));
                     add(timeLabel);
                     repaint();
                }
        }); 
    }
public void setPlace(Insets insets, int x, int y){
    Dimension size = this.getPreferredSize();
    this.setBounds(x + insets.left, y + insets.top,
             size.width, size.height);
}

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        int width = getWidth();
        int height = getHeight();
        int shadowGap = this.shadowGap;
        Color shadowColorA = new Color(shadowColor.getRed(), 
	shadowColor.getGreen(), shadowColor.getBlue(), shadowAlpha);
        Graphics2D graphics = (Graphics2D) g;

        //Sets antialiasing if HQ.
        if (highQuality) {
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
			RenderingHints.VALUE_ANTIALIAS_ON);
        }

        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width - shadowGap, 
		height - shadowGap, arcs.width, arcs.height);
        graphics.setStroke(new BasicStroke());
        Border border = new BasicBorders.MarginBorder();
    }


} 

