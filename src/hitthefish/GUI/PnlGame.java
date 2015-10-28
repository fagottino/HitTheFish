package hitthefish.GUI;

import hitthefish.Utility.Resources;
import hitthefish.HitTheFish;
import hitthefish.Class.Arm;
import hitthefish.Class.CreateMovingObject;
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
    private int actuallyPoint;
    private int randomTo, randomFrom;
    //endregion
    
    //region Variabili pubbliche
    public static int bgWidth;
    public static int bgHeight;
    public static int imgSimpleFishWidth;
    public static int imgSimpleFishHeight;
    //endregion
    
    //private PnlPause pnlPause;
    
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
        timer = 60;
        
        //region Istanze classi
        threadWaves = new Thread(new WavesMove());
        arm = new Arm(gun, x, getHeight() - 152);
        createMovingObject = new CreateMovingObject();
        //endregion
        
        this.addMouseListener(new MouseEvents());
        this.addMouseMotionListener(new MouseEvents());
        
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
            this.wait = 1000 + (int)(Math.random()*1100);
        }

        @Override
        public void run() {
            while(true) {
                // Attesa casuale tra 5 e 7 secondi
                this.wait = 1000 + (int)(Math.random()*1100);
                createMovingObject.getSimpleFish().add(new MoveObject(imgSimpleFish, 1 + (int)(Math.random()*1100), getHeight() - 152, -1, -1, 1 + (int)(Math.random()*10)));
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
    
    public class WavesMove implements Runnable {

        @Override
        public void run() {
            while (true) {
                //repaint();
                updateUI();
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
            actuallyPoint = (int)me.getPoint().getX();
            if (actuallyPoint >= 0 && actuallyPoint < Arm.limitWidthArm)
                arm.setX(actuallyPoint);
        }

        @Override
        public void mousePressed(MouseEvent me) {
            if(SwingUtilities.isRightMouseButton(me)) {
                //System.out.println("right");
                HitTheFish.pnlPause.setVisible(true);
            } else {
              me.getClickCount();
            }
        }
    }
    
    public void createInstance() {
        threadFish = new Thread(new FishGenerator());
        threadWaves = new Thread(new WavesMove());
    }
    
    public void startThread() {
        this.threadWaves.start();
        this.threadFish.start();
    }
    
    public void stopThread() {
        this.threadFish.interrupt();
        this.threadWaves.interrupt();
    }
    
    public BufferedImage getImageSimpleFish() {
        return imgSimpleFish;
    }
}
