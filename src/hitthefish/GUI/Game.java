package hitthefish.GUI;

import hitthefish.Utility.Resources;
import java.awt.image.BufferedImage;
import hitthefish.HitTheFish;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class Game extends JPanel {

    private final BufferedImage background;
    private BufferedImage bg;
    private final BufferedImage gun;
    public Thread wavesThread;
    public String name;
    private int timer, rand;
    
    public Game() {
        this.setSize(HitTheFish.FRAME_SIZE);
        background = Resources.getImage("../img/bg.png");
        gun = Resources.getImage("../img/gun.png");
        wavesThread = new Thread(new wavesMove());
        bg = Resources.getImage("../img/bg1.png");
        timer = 200;
    }
    
    @Override
    public void paint(Graphics g) {
        update(g);
    }
    
    @Override
    public void update(Graphics g) {
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        sfondo(g);
        g.drawImage(gun, getWidth() - 350, getHeight() - 152, this);
    }
    
    private void sfondo(Graphics g) {
        rand = 1 + (int)(Math.random()*5);
        name = "../img/bg" + rand + ".png";
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
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void startThread() {
        wavesThread.start();
    }
}
