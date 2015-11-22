package hitthefish.GUI;

import hitthefish.HitTheFish;
import hitthefish.Utility.DrawBotton;
import hitthefish.Utility.Resources;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Admin
 */
public class PnlGameEnded extends JPanel {
    
    private BufferedImage background;
    private final DrawBotton restart, menu, quit;
    
    public PnlGameEnded(boolean pWin) {
        this.setSize(HitTheFish.FRAME_SIZE);
        this.background = Resources.getImage("../img/bglose.png");
        restart = new DrawBotton(350, 582, 190, 58);
        menu = new DrawBotton(720, 582, 160, 58);
        quit = new DrawBotton(1100, 685, 55, 35);
        this.addMouseListener(new PnlGameEnded.MouseListener());
        this.addMouseMotionListener(new PnlGameEnded.MouseListener());
    }
    
    @Override
    public void paint(Graphics g) {
        update(g);
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
    
    public void changeBackground() {
        this.background = Resources.getImage("../img/bgwin.png");
        repaint();
    }
    
    public class MouseListener extends MouseAdapter {
        
        @Override
        public void mouseMoved(MouseEvent e) {
            Point p = e.getPoint();
            
            if (restart.contains(p))
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            else if (menu.contains(p))
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            else if (quit.contains(p))
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            else
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            Point p = e.getPoint();
            
            if (restart.contains(p)) {
                HitTheFish.pnlGame.setVisible(true);
                HitTheFish.pnlGameEnded.setVisible(false);
                HitTheFish.pnlGame.createInstance();
                HitTheFish.pnlGame.startThread();
            } 
            if (menu.contains(p)) {
                HitTheFish.pnlMenu.setVisible(true);
                HitTheFish.pnlGameEnded.setVisible(false);
            }
            if (quit.contains(p))
                System.exit(0);
        }
    }
}
