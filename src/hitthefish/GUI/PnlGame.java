package hitthefish.GUI;

import hitthefish.Utility.Resources;
import hitthefish.HitTheFish;
import hitthefish.Class.Arm;
import hitthefish.Class.MoveObject;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private Arm arm;
    private MoveObject moveObject;
    public String name;
    private int timer, i, x, y;
    
    public PnlGame() {
        this.setSize(HitTheFish.FRAME_SIZE);
        background = Resources.getImage("../img/bg.png");
        gun = Resources.getImage("../img/gun.png");
        bg = Resources.getImage("../img/bg1.png");
        wavesThread = new Thread(new wavesMove());
        timer = 20;
        this.addMouseListener(new MouseEvents());
        this.addMouseMotionListener(new MouseEvents());
        arm = new Arm(gun, x, getHeight() - 152, 81, 124);
        moveObject = new MoveObject(150, 300, 3);
        
        // region Nascondo il cursore del mouse
        Toolkit toolkit = Toolkit.getDefaultToolkit ();
        Image image = new BufferedImage (32, 32, BufferedImage.TYPE_INT_ARGB);
        Cursor c = toolkit.createCustomCursor(image, new Point (0,0), "");
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
        arm.draw(g);
    }
    
    private void drawWaves(Graphics g, int i) {
        name = "../img/bg" + i + ".png";
        bg = Resources.getImage(name);
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
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
