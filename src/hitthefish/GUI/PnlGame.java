package hitthefish.GUI;

import hitthefish.Utility.Resources;
import hitthefish.HitTheFish;
import hitthefish.Class.Arm;
import hitthefish.Class.CreateMovingObject;
import hitthefish.Class.MoveObject;
import hitthefish.Class.RotateObject;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Cursor;
import java.awt.Image;
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
    private final BufferedImage imgSimpleFish;
    private final BufferedImage imgViewFinder;
    // endregion
    
    //region Classi
    private Arm arm;
    private CreateMovingObject createMovingObject;
    private MoveObject moveObject;
    private RotateObject r;
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
    public static int imgViewFinderWidth;
    public static int imgViewFinderHeight;
    //endregion
    
    //private PnlPause pnlPause;
    
    public PnlGame() {
        this.setSize(HitTheFish.FRAME_SIZE);
        
        //region Immagini del gioco
        background = Resources.getImage("../img/bg.png");
        gun = Resources.getImage("../img/gun.png");
        imgSimpleFish = Resources.getImage("../img/simplefish.png");
        imgViewFinder = Resources.getImage("../img/viewfinder.png");
        //endregion
        
        timer = 60;
        bgWidth = background.getWidth();
        bgHeight = background.getHeight();
        imgSimpleFishWidth = imgSimpleFish.getWidth();
        imgSimpleFishHeight = imgSimpleFish.getHeight();
        imgViewFinderWidth = imgViewFinder.getWidth();
        imgViewFinderHeight = imgViewFinder.getHeight();
        
        //region Istanze classi
        threadWaves = new Thread(new WavesMove());
        arm = new Arm(gun, x, getHeight() - 152);
        createMovingObject = new CreateMovingObject();
        //endregion
        
        this.addMouseListener(new MouseEvents());
        this.addMouseMotionListener(new MouseEvents());
        
        // region Cambio il cursore del mouse
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Cursor c = toolkit.createCustomCursor(imgViewFinder, new Point(this.getX(), this.getY()), "cursor");
        setCursor (c);
        // endregion
    }
    
    @Override
    public void paint(Graphics g) {
        update(g);
    }
    
    @Override
    public void update(Graphics g) {
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        i = random(1, 5);
        drawWaves(g, i);
        arm.draw(g);
        createMovingObject.drawMovingObject(g, r);
        
    }
    
    public class FishGenerator implements Runnable {
        private int wait;
        
        public FishGenerator() {
            //this.wait = random(1000, 1100);
        }

        @Override
        public void run() {
            while(true) {
                // Attesa casuale tra 5 e 7 secondi
                this.wait = 1000 + (int)(Math.random()*1100);
                //createMovingObject.getSimpleFish().add(new MoveObject(imgSimpleFish, 1 + (int)(Math.random()*1100), getHeight() - 152, -1, -1, 1 + (int)(Math.random()*10)));
                r = new RotateObject(imgSimpleFish, 1 + (int)(Math.random()*1100), getHeight() - 152, -1, -1, 1 + (int)(Math.random()*10));
                createMovingObject.getSimpleFish().add(r);
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
            actuallyPoint = (int)me.getPoint().getX();
            if (actuallyPoint >= 0 && actuallyPoint < Arm.limitWidthArm)
                arm.setX(actuallyPoint);
        }

        @Override
        public void mousePressed(MouseEvent me) {
            if(SwingUtilities.isRightMouseButton(me)) {
                stopThread();
                HitTheFish.pnlPause.setVisible(true);
            } else {
              me.getClickCount();
            }
        }
    }
    
    public int random(int _from, int _to) {
        return _from + (int)(Math.random()*_to);
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
        if (!this.threadFish.interrupted()) {
            this.threadFish.interrupt();
        }
        
        if (!this.threadWaves.interrupted()) {
            this.threadWaves.interrupt();
        }
        
//        try {
//            this.threadFish.sleep(9000);
//            this.threadWaves.sleep(9000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(PnlGame.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        this.threadFish.interrupt();
//        this.threadWaves.interrupt();
    }
    
    public BufferedImage getImageSimpleFish() {
        return imgSimpleFish;
    }
}
