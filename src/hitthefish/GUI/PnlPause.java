package hitthefish.GUI;

import hitthefish.HitTheFish;
import hitthefish.Utility.Resources;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class PnlPause extends JPanel {
    
    private BufferedImage background;
    
    public PnlPause() {
        this.setSize(HitTheFish.FRAME_SIZE);
        this.background = Resources.getImage("../img/bgpause.png");
    }
    
    @Override
    public void paint(Graphics g) {
        update(g);
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}
