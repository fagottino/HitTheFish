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
    private int timer;
    private int conta;
    private String[] array;
    private int i;
            
    public Game() {
        this.setSize(HitTheFish.FRAME_SIZE);
        background = Resources.getImage("../img/bg.png");
        gun = Resources.getImage("../img/gun.png");
        wavesThread = new Thread(new wavesMove());
        bg = Resources.getImage("../img/bg1.png");
        conta = 0;
        array = new String[5];
        array[0] = "bg1.png";
        array[1] = "bg2.png";
        array[2] = "bg3.png";
        array[3] = "bg4.png";
        array[4] = "bg5.png";
        i = 0;
    }
    
    @Override
    public void paint(Graphics g) {
        update(g);
    }
    
    @Override
    public void update(Graphics g) {
        
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(gun, getWidth() - 350, getHeight() - 152, this);
        conta++;
        sfondo(g);
    }
    
    private void sfondo(Graphics g){
        if(conta == 5) {
            name = array[i];
            bg = Resources.getImage(name);
            g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            conta = 0;
            i++;
        }
        
//        for(i = 1; i <= 5; i++) {
//            name = "../img/bg" + i + ".png";
//            bg = Resources.getImage(name);
//            g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
//        }
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
