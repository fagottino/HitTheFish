package hitthefish.GUI;

import hitthefish.Utility.Resources;
import hitthefish.HitTheFish;
import hitthefish.Class.Arm;
import hitthefish.Class.CreateMovingObject;
import hitthefish.Class.Game;
import hitthefish.Class.MoveObject;
import hitthefish.Class.RotateObject;
import hitthefish.Class.SimpleFish;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;
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
    private final String pathImgSimpleFish;
    private final String pathImgSimpleFishReverse;
    // endregion
    
    //region Classi
    private Game game;
    private Arm arm;
    private CreateMovingObject createMovingObject;
    private MoveObject moveObject;
    private RotateObject rotateObject;
    private SimpleFish simpleFish;
    //endregion
    
    //region Thread
    public static Thread threadWaves;
    public static Thread threadFish;
    public static Thread threadMoveFish;
    //endregion
    
    //region Variabili locali
    private String name;
    private String life;
    private final int timer;
    private int i, k, x, y, XX;
    private int actuallyPoint, time;
    private Random rand;
    private ArrayList<SimpleFish> arraySimpleFish;
    private boolean stopMouseListener;
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
    private Rectangle2D rectangle;
    private boolean insideCircle = false;
    private Point p;
    
    public PnlGame() {
        this.setSize(HitTheFish.FRAME_SIZE);
        
        //region Immagini del gioco
        background = Resources.getImage("../img/bg.png");
        gun = Resources.getImage("../img/gun.png");
        imgSimpleFish = Resources.getImage("../img/simplefish.png");
        imgViewFinder = Resources.getImage("../img/viewfinder.png");
        
        pathImgSimpleFish = "../img/simplefish.png";
        pathImgSimpleFishReverse = "../img/simplefishreverse.png";
        //endregion
        
        timer = 60;
        bgWidth = background.getWidth();
        bgHeight = background.getHeight();
        imgSimpleFishWidth = imgSimpleFish.getWidth();
        imgSimpleFishHeight = imgSimpleFish.getHeight();
        imgViewFinderWidth = imgViewFinder.getWidth();
        imgViewFinderHeight = imgViewFinder.getHeight();
        this.stopMouseListener = false;
        
        //region Istanze classi
        threadWaves = new Thread(new WavesMove());
        arm = new Arm(gun, x, getHeight() - 152);
        createMovingObject = new CreateMovingObject();
        rand = new Random();
        game = new Game();
        //endregion
        
        this.addMouseListener(new MouseEvents());
        this.addMouseMotionListener(new MouseEvents());
        
        arraySimpleFish = createMovingObject.getArraySimpleFish();
        
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
        drawWaves(g, random(1, 5));
        drawString(g);
        arm.draw(g);
        for (i = 0; i < createMovingObject.getArraySimpleFish().size(); i++) {
            createMovingObject.getArraySimpleFish().get(i).drawFish(g);
        }
    }
    
    public class FishGenerator implements Runnable {
        private int wait, x;
        private boolean stop, objectFlip;
        
        public FishGenerator() {
            stop = false;
            objectFlip = false;
        }

        @Override
        public void run() {
            while(!stop) {
                time = game.getTimer();
                if (time > 50)
                    this.wait = random(1000, 2800);
                else if (time <= 50 && time > 40)
                    this.wait = random(800, 2600);
                else if (time <= 40 && time > 30)
                    this.wait = random(600, 2200);
                else if (time <= 30 && time > 20)
                    this.wait = random(400, 1400);
                else if (time <= 20 && time > 10)
                    this.wait = random(200, 600);
                //else if (time <= 10 && time > 0)
                else
                    this.wait = random(50, 300);
                    
                simpleFish = new SimpleFish(pathImgSimpleFish, random(1, 1100), random(480, 630), random(5, 10));
                createMovingObject.getArraySimpleFish().add(simpleFish);
                    try {
                        //stop = true;
                        if (!Thread.interrupted())
                            Thread.sleep(this.wait);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PnlGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        }
    }
    
    public class MoveFish implements Runnable {
        
        int wait;
        
        public MoveFish() {
        }
       
        @Override
        public void run() {
            while (true) {
                this.wait = random(10, 100);
                for (i = 0; i < createMovingObject.getArraySimpleFish().size(); i++) {
                    if (createMovingObject.getArraySimpleFish().get(i).isObjectOut()) {
                        createMovingObject.getArraySimpleFish().remove(i);
                        game.setMissedFish(game.getMissedFish() + 1);
                    } else {
                        createMovingObject.getArraySimpleFish().get(i).move();
                    }
                }
                try {
                    if (!Thread.interrupted())
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
    
    private void drawString(Graphics g) {
        g.setFont(new Font("Arial", Font.BOLD, 60));
        if (game.getTimer() >= 20)
            g.setColor(Color.white);
        else if (time <= 20 && time > 10)
            g.setColor(Color.orange);
        else
            g.setColor(Color.red);
        g.drawString("" + game.getTimer(), 550, 70);
        
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.setColor(Color.white);
        g.drawString("PUNTEGGIO", 870, 60);

        g.setFont(new Font("Arial", Font.BOLD, 70));
        g.setColor(Color.yellow);
        g.drawString(""+game.getStrickenFish(), 1080, 70);

        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.setColor(Color.red);
        g.drawString("Mancati", 972, 85);

        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.setColor(Color.red);
        g.drawString(""+game.getMissedFish(), 1037, 86);
    }
    
    public class WavesMove implements Runnable {

        @Override
        public void run() {
            while (true) {
                repaint();
                try {
                    if (!Thread.interrupted())
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
            if (actuallyPoint >= 0 && actuallyPoint < Arm.limitWidthArm) {
                arm.setX(actuallyPoint);
                repaint();
            }
        }

        @Override
        public void mousePressed(MouseEvent me) {
            if(SwingUtilities.isRightMouseButton(me)) {
                //stopThread();
                System.out.println("sdvnjsndv");
                HitTheFish.pnlPause.setVisible(true);
            } else {
                checkShot(me);
            }
        }
    }
    
    private int random(int pStart, int pEnd) {
        int differenza = pEnd - pStart;
        XX = rand.nextInt(differenza);
        differenza = pEnd - XX;
    return differenza;
    }
    
    public void createInstance() {
        threadFish = new Thread(new FishGenerator());
        threadWaves = new Thread(new WavesMove());
        threadMoveFish = new Thread(new MoveFish());
    }
    
    public void startThread() {
        this.threadFish.start();
        this.threadWaves.start();
        this.threadMoveFish.start();
        game.setStartTimer();
    }
        
    public static void stopThread() {
//            PnlGame.threadFish.join();
//            PnlGame.threadMoveFish.join();
//            PnlGame.threadWaves.join();
        PnlGame.threadFish.interrupt();
        PnlGame.threadMoveFish.interrupt();
        PnlGame.threadWaves.interrupt();
    }
    
    public void checkShot(MouseEvent _me) {
        int i = 0;
        boolean blankShoot = false;
        
        if (this.arraySimpleFish.size() > 0)
            while (i < this.arraySimpleFish.size() && blankShoot == false) {
                Point point = new Point(_me.getPoint());
                this.rectangle = this.arraySimpleFish.get(i).getBorderImage();

                if (this.rectangle.contains(point)) {
                    this.arraySimpleFish.remove(i);
                    game.setStrickenFish(game.getStrickenFish() + 1);
                    i++;
                } else {
                    blankShoot = true;
                }
            }
    }
}
