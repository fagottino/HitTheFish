package hitthefish.GUI;

import hitthefish.Utility.Resources;
import hitthefish.HitTheFish;
import hitthefish.Class.Arm;
import hitthefish.Class.CreateMovingObject;
import hitthefish.Class.MoveObject;
import hitthefish.Class.SimpleFish;
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

    //region Immagini
    private final BufferedImage background;
    private BufferedImage bg;
    private final BufferedImage gun;
    private BufferedImage imgSimpleFish;
    // endregion
    
    //region Classi
    private Arm arm;
    private CreateMovingObject createMovingObject;
    private MoveObject moveObject;
    //endregion
    
    //region Thread
    public Thread threadWaves;
    public Thread threadFish;
    //endregion
    
    //region Variabili locali
    private String name;
    private final int timer;
    private int i, x, y;
    //endregion
    
    //region Variabili pubbliche
    public static int bgWidth;
    public static int bgHeight;
    public static int imgSimpleFishWidth;
    public static int imgSimpleFishHeight;
    //endregion
    
    public PnlGame() {
        this.setSize(HitTheFish.FRAME_SIZE);
        
        //region Immagini del gioco
        background = Resources.getImage("../img/bg.png");
        gun = Resources.getImage("../img/gun.png");
        imgSimpleFish = Resources.getImage("../img/simplefish.png");
        //endregion
        
        bgWidth = background.getWidth();
        bgHeight = background.getHeight();
        imgSimpleFishWidth = imgSimpleFish.getWidth();
        imgSimpleFishHeight = imgSimpleFish.getHeight();
        timer = 20;
        
        //region Istanze classi
        threadWaves = new Thread(new wavesMove());
        arm = new Arm(gun, x, getHeight() - 152, 81, 124);
        createMovingObject = new CreateMovingObject();
        moveObject = new MoveObject(imgSimpleFish, 300, getHeight() - 100, imgSimpleFishWidth, imgSimpleFishHeight, 20);
        //simpleFish = new SimpleFish(imgSimpleFish, 300, getHeight() - 100, imgSimpleFishWidth, imgSimpleFishHeight, 20);
        //endregion
        
        this.addMouseListener(arm.new MouseEvents());
        this.addMouseMotionListener(arm.new MouseEvents());
        
        // region Nascondo il cursore del mouse
        Toolkit toolkit = Toolkit.getDefaultToolkit();
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
        createMovingObject.drawMovingObject(g);
    }
    
    public class FishGenerator implements Runnable {
        private int wait;
        
        public FishGenerator() {
            
        }

        @Override
        public void run() {
            while(true) {
                // Attesa casuale tra 5 e 7 secondi
                this.wait = 3 + (int)(Math.random()*10);
                createMovingObject.getSimpleFish().add(new MoveObject(imgSimpleFish, 25, 25, 3 + (int)(Math.random()*10), -10, 3 + (int)(Math.random()*10)));
                try {
                    Thread.sleep(this.wait);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PnlGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
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
        public void mousePressed(MouseEvent me) {
            if(SwingUtilities.isRightMouseButton(me)) {
                System.out.println("right");
            } else {
              me.getClickCount();
            }
        }
    }
    
    public void createInstance() {
        threadFish = new Thread(new FishGenerator());
    }
    
    public void startThread() {
        this.threadWaves.start();
        this.threadFish.start();
    }
    
    public BufferedImage getImageSimpleFish() {
        return imgSimpleFish;
    }
}
