/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.helpers;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.util.Arrays;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StarsPanel extends JPanel {
    private LevelBar levelBar;
    public StarsPanel() {
        super(new GridLayout(2, 2, 4, 4));
        //PI Diagona Icons Pack 1.0 - Download Royalty Free Icons and Stock Images For Web & Graphics Design
        //http://www.freeiconsdownload.com/Free_Downloads.asp?id=60
        ImageIcon defaultIcon = new ImageIcon(getClass().getResource("/31g.png"));
        ImageProducer ip = defaultIcon.getImage().getSource();

        // 1
//        List<ImageIcon> list = Arrays.asList(
//            makeStarImageIcon(ip,  1f, .5f, .5f),
//            makeStarImageIcon(ip, .5f,  1f, .5f),
//            makeStarImageIcon(ip,  1f, .5f,  1f),
//            makeStarImageIcon(ip, .5f, .5f,  1f),
//            makeStarImageIcon(ip,  1f,  1f, .5f));
//        add(makeStarRatingPanel("gap=0", new LevelBar(defaultIcon, list, 0)));

        // 2
//        list = Arrays.asList(
//            makeStarImageIcon(ip, .2f, .5f, .5f),
//            makeStarImageIcon(ip,  0f,  1f, .2f),
//            makeStarImageIcon(ip,  1f,  1f, .2f),
//            makeStarImageIcon(ip, .8f, .4f, .2f),
//            makeStarImageIcon(ip,  1f, .1f, .1f));
//        add(makeStarRatingPanel("gap=1+1", new LevelBar(defaultIcon, list, 1) {
//            @Override protected void repaintIcon(int index) {
//                for (int i = 0; i < labelList.size(); i++) {
//                    labelList.get(i).setIcon(i <= index ? iconList.get(index) : defaultIcon);
//                }
//                repaint();
//            }
//        }));

        // 3
//        list = Arrays.asList(
//            makeStarImageIcon(ip, .6f, .6f, 0f),
//            makeStarImageIcon(ip, .7f, .7f, 0f),
//            makeStarImageIcon(ip, .8f, .8f, 0f),
//            makeStarImageIcon(ip, .9f, .9f, 0f),
//            makeStarImageIcon(ip,  1f,  1f, 0f));
//        add(makeStarRatingPanel("gap=2+2", new LevelBar(defaultIcon, list, 2)));

        // 4
        ImageIcon yStar = makeStarImageIcon(ip, 1f, 1f, 0f);
         List<ImageIcon> list = Arrays.asList(yStar, yStar, yStar, yStar, yStar);
         levelBar = new LevelBar(defaultIcon, list, 1);
        add(levelBar);
        setPreferredSize(new Dimension(150, 60));
    }
    public int getLevel(){
        return levelBar.getLevel();
    }
    public void setLevel(int n){
        if (n>0 && n<6){
            levelBar.setLevel(n);
        }
    }
    
    private JPanel makeStarRatingPanel(final LevelBar label) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        p.add(label);
        return p;
    }
    private static ImageIcon makeStarImageIcon(ImageProducer ip, float rf, float gf, float bf) {
        return new ImageIcon(Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(ip, new SelectedImageFilter(rf, gf, bf))));
    }
    
static class SelectedImageFilter extends RGBImageFilter {
    private final float rf;
    private final float gf;
    private final float bf;
    protected SelectedImageFilter(float rf, float gf, float bf) {
        super();
        this.rf = Math.min(1f, rf);
        this.gf = Math.min(1f, gf);
        this.bf = Math.min(1f, bf);
        canFilterIndexColorModel = false;
    }
//     @Override public int filterRGB(int x, int y, int argb) {
//         Color color = new Color(argb, true);
//         float[] array = new float[4];
//         color.getComponents(array);
//         return new Color(array[0] * filter[0], array[1] * filter[1], array[2] * filter[2], array[3]).getRGB();
//     }
    @Override public int filterRGB(int x, int y, int argb) {
        int r = (int) (((argb >> 16) & 0xFF) * rf);
        int g = (int) (((argb >>  8) & 0xFF) * gf);
        int b = (int) (((argb)       & 0xFF) * bf);
        return (argb & 0xFF000000) | (r << 16) | (g << 8) | (b);
    }
}
 
//    public static void createAndShowGUI() {
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (ClassNotFoundException | InstantiationException
//               | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//            ex.printStackTrace();
//        }
//        JFrame frame = new JFrame("@title@");
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.getContentPane().add(new StarsPanel());
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//    }
}