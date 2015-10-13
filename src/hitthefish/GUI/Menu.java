package hitthefish.GUI;

import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import hitthefish.Utility.Resources;
import hitthefish.Utility.DrawBotton;
import hitthefish.HitTheFish;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Admin
 */
public class Menu extends JPanel {
    
    private final BufferedImage background;
    private final DrawBotton info, selectLevel, play, quit;
    
    public Menu() {
        //this.setSize(hitthefish.HitTheFish.FRAME_SIZE);
        background = Resources.getImage("../img/menu.png");
        info = new DrawBotton(844, 223, 104, 20);
        selectLevel = new DrawBotton(786, 275, 225, 27);
        play = new DrawBotton(767, 352, 260, 64);
        quit = new DrawBotton(876, 500, 43, 23);
        this.addMouseListener(new MouseListener());
        this.addMouseMotionListener(new MouseListener());
    }
    
    @Override
    public void paint(Graphics g) {
        update(g);
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
    
    public class MouseListener extends MouseAdapter {

        @Override
        public void mouseMoved(MouseEvent e) {
            Point p = e.getPoint();
            
            if(info.contains(p) || selectLevel.contains(p) || play.contains(p) || quit.contains(p))
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            else
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            Point p = e.getPoint();
            
            if (info.contains(p)) {
                HitTheFish.pnlInfo.setVisible(true);
                HitTheFish.pnlMenu.setVisible(false);
            }
            else if (play.contains(p)) {
                HitTheFish.pnlGame.setVisible(true);
                HitTheFish.pnlMenu.setVisible(false);
                HitTheFish.pnlGame.startThread();
            }
            if (quit.contains(p))
                System.exit(0);
        }
    }
}
