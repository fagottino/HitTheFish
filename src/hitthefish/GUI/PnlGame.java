package hitthefish.GUI;

import hitthefish.Utility.Resources;
import java.awt.image.BufferedImage;
import hitthefish.HitTheFish;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Cursor;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Admin
 */
public class PnlGame extends JPanel {

    private final BufferedImage background;
    private BufferedImage bg;
    private final BufferedImage gun;
    public Thread wavesThread;
    
    public String name;
    private int timer, i, x, y;
    
    public PnlGame() {
        this.setSize(HitTheFish.FRAME_SIZE);
        background = Resources.getImage("../img/bg.png");
        gun = Resources.getImage("../img/gun.png");
        bg = Resources.getImage("../img/bg1.png");
        wavesThread = new Thread(new wavesMove());
        timer = 200;
        this.addMouseListener(new MouseEvents());
        this.addMouseMotionListener(new MouseEvents());
        
        // region Nascondo il cursore del mouse
        Toolkit toolkit = Toolkit.getDefaultToolkit ();
        Image image = new BufferedImage (32, 32, BufferedImage.TYPE_INT_ARGB);
        Cursor c = toolkit.createCustomCursor (image, new Point (0,0), "");
        setCursor(c);
        // endregion
    }
    
    @Override
    public void paint(Graphics g) {
        update(g);
    }
    
    @Override
    public void update(Graphics g) {
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        i = 1 + (int)(Math.random()*5);
        drawWaves(g, i);
        drawGun(g, this.x);
    }
    
    private void drawWaves(Graphics g, int i) {
        name = "../img/bg" + i + ".png";
        bg = Resources.getImage(name);
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
    
    private void drawGun (Graphics g, int _x) {
        g.drawImage(gun, _x, getHeight() - 152, this);
    }
    
    public class wavesMove implements Runnable {

        @Override
        public void run() {

            while (true) {
                repaint();
                try {
                    Thread.sleep(timer);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PnlGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public class MouseEvents extends MouseAdapter {

        @Override
        public void mouseMoved(MouseEvent me) {
            x = me.getX();
            repaint();
        }

        @Override
        public void mousePressed(MouseEvent me) {
            if(SwingUtilities.isRightMouseButton(me)) {
                System.out.println("right");
            } else {
              me.getClickCount();
            }
        }
    }
    
    public void startThread() {
        wavesThread.start();
    }
}
