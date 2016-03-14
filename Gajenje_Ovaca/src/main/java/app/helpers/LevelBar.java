/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.helpers;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.RGBImageFilter;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LevelBar extends JPanel implements MouseListener, MouseMotionListener{
  private final int gap;
  protected final List< ImageIcon > iconList;
  protected final List< JLabel > labelList = Arrays.asList(
    new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel()
  );
  protected final ImageIcon defaultIcon;
  private int clicked = -1;
  public LevelBar(ImageIcon defaultIcon, List< ImageIcon > list, int gap) {
    super(new GridLayout(1, 5, gap*2, gap*2));
    this.defaultIcon = defaultIcon;
    this.iconList = list;
    this.gap = gap;
    for(JLabel l:labelList) {
      l.setIcon(defaultIcon);
      add(l);
    }
    addMouseListener(this);
    addMouseMotionListener(this);
  }
  public void clear() {
    clicked = -1;
    repaintIcon(clicked);
  }
  public int getLevel() {
    return clicked;
  }
  public void setLevel(int l) {
    clicked = l;
    repaintIcon(clicked);
  }
  private int getSelectedIconIndex(Point p) {
    for(int i=0;i < labelList.size();i++) {
      Rectangle r = labelList.get(i).getBounds();
      r.grow(gap, gap);
      if(r.contains(p)) return i;
    }
    return -1;
  }
  protected void repaintIcon(int index) {
    for(int i=0;i < labelList.size();i++) {
      labelList.get(i).setIcon(i <= index?iconList.get(i):defaultIcon);
    }
    repaint();
  }
  public void mouseMoved(MouseEvent e) {
    repaintIcon(getSelectedIconIndex(e.getPoint()));
  }
  public void mouseEntered(MouseEvent e) {
    repaintIcon(getSelectedIconIndex(e.getPoint()));
  }
  public void mouseClicked(MouseEvent e) {
    clicked = getSelectedIconIndex(e.getPoint());
  }
  public void mouseExited(MouseEvent e) {
    repaintIcon(clicked);
  }
  public void mouseDragged(MouseEvent e) {}
  public void mousePressed(MouseEvent e) {}
  public void mouseReleased(MouseEvent e) {}

}
class SelectedImageFilter extends RGBImageFilter {
  private final float[] filter;
  public SelectedImageFilter(float[] filter) {
    this.filter = filter;
    canFilterIndexColorModel = true;
  }


  public int filterRGB(int x, int y, int argb) {
    Color color = new Color(argb, true);
    float[] array = new float[4];
    color.getComponents(array);
    return new Color(array[0]*filter[0],
                     array[1]*filter[1],
                     array[2]*filter[2],
                     array[3]).getRGB();
  }
}
