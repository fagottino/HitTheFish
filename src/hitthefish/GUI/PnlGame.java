package hitthefish.GUI;

import hitthefish.Utility.Resources;
import java.awt.image.BufferedImage;
import hitthefish.HitTheFish;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

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
    private int timer, i;
    
    public PnlGame() {
        this.setSize(HitTheFish.FRAME_SIZE);
        background = Resources.getImage("../img/bg.png");
        gun = Resources.getImage("../img/gun.png");
        wavesThread = new Thread(new wavesMove());
        this.addMouseListener(new mouseEvents());
        this.addMouseMotionListener(new mouseEvents());
        bg = Resources.getImage("../img/bg1.png");
        timer = 20;
    }
    
    @Override
    public void paint(Graphics g) {
        update(g);
    }
    
    @Override
    public void update(Graphics g) {
        
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        i = 1 + (int)(Math.random()*5);
        sfondo(g, i);
        g.drawImage(gun, getWidth() - 350, getHeight() - 152, this);
    }
    
    private void sfondo(Graphics g, int i) {
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
    
    public class mouseEvents extends MouseAdapter {

        @Override
        public void mouseMoved(MouseEvent me) {
            //super.mouseMoved(me); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mousePressed(MouseEvent me) {
            //super.mousePressed(me); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    public void startThread() {
        wavesThread.start();
    }
}
