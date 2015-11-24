package hitthefish.GUI;

import hitthefish.Utility.Resources;
import hitthefish.HitTheFish;
import hitthefish.Class.Arm;
import hitthefish.Class.BonusFish;
import hitthefish.Class.CreateMovingObject;
import hitthefish.Class.EvilFish;
import hitthefish.Class.File;
import hitthefish.Class.Game;
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
import java.io.IOException;
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
    private final BufferedImage imgViewFinder;
    private final String pathImgSimpleFish;
    private final String pathImgBonusFish;
    private final String pathImgEvilFish;
    // endregion
    
    //region Classi
    private Game game;
    private final Arm arm;
    private CreateMovingObject createMovingObject;
    private SimpleFish simpleFish;
    private BonusFish bonusFish;
    private EvilFish evilFish;
    private File file;
    //endregion
    
    //region Thread
    public static Thread threadWaves;
    public static Thread threadSimpleFish;
    public static Thread threadBonusFish;
    public static Thread threadEvilFish;
    public static Thread threadMoveFish;
    //endregion
    
    //region Variabili locali
    private String name;
    private final int timer;
    private int i, x, XX;
    private int actuallyPoint, time;
    private Random rand;
    private ArrayList<SimpleFish> arraySimpleFish;
    private ArrayList<BonusFish> arrayBonusFish;
    private ArrayList<EvilFish> arrayEvilFish;
    private ArrayList<Integer> arrayData;
    //endregion
    
    //region Variabili pubbliche
    public static int bgWidth;
    public static int bgHeight;
    //endregion
    
    //private PnlPause pnlPause;
    private Rectangle2D rectangle;
    private boolean insideCircle = false;
    
    public PnlGame() {
        this.setSize(HitTheFish.FRAME_SIZE);
        
        //region Immagini del gioco
        background = Resources.getImage("../img/bg.png");
        gun = Resources.getImage("../img/gun.png");
        imgViewFinder = Resources.getImage("../img/viewfinder.png");
        pathImgSimpleFish = "../img/simplefish.png";
        pathImgBonusFish = "../img/bonusfish.png";
        pathImgEvilFish = "../img/evilfish.png";
        //endregion
        
        timer = 60;
        bgWidth = background.getWidth();
        bgHeight = background.getHeight();
        
        //region Istanze classi
        arm = new Arm(gun, x, getHeight() - 152);
        createMovingObject = new CreateMovingObject();
        rand = new Random();
        //endregion
        
        this.addMouseListener(new MouseEvents());
        this.addMouseMotionListener(new MouseEvents());
        
        arraySimpleFish = createMovingObject.getArraySimpleFish();
        arrayBonusFish = createMovingObject.getArrayBonusFish();
        arrayEvilFish = createMovingObject.getArrayEvilFish();
        //arrayData = file.getData();

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
        for (i = 0; i < arraySimpleFish.size(); i++) {
            arraySimpleFish.get(i).drawFish(g);
        }
        for (i = 0; i < arrayBonusFish.size(); i++) {
            arrayBonusFish.get(i).drawFish(g);
        }
        for (i = 0; i < arrayEvilFish.size(); i++) {
            arrayEvilFish.get(i).drawFish(g);
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
        
//        if (arrayData.size() > 0) {
//            g.setFont(new Font("Arial", Font.BOLD, 30));
//            g.setColor(Color.white);
//            g.drawString("PUNTEGGIO", 870, 60);
//        }
    }
    
    public class SimpleFishGenerator implements Runnable {
        private int wait;
        private boolean stop;
        
        public SimpleFishGenerator() {
            stop = false;
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
    
    public class BonusFishGenerator implements Runnable {
        private int wait;
        private boolean stop;
        
        public BonusFishGenerator() {
            stop = false;
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
                
                bonusFish = new BonusFish(pathImgBonusFish, random(1, 1100), random(480, 630), random(5, 10));
                arrayBonusFish.add(bonusFish);
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
    
    public class EvilFishGenerator implements Runnable {
        private int wait;
        private boolean stop;
        
        public EvilFishGenerator() {
            stop = false;
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
                
                evilFish = new EvilFish(pathImgEvilFish, random(1, 1100), random(480, 630), random(5, 10));
                arrayEvilFish.add(evilFish);
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
                for (i = 0; i < arraySimpleFish.size(); i++) {
                    if (arraySimpleFish.get(i).isObjectOut()) {
                        arraySimpleFish.remove(i);
                        game.setMissedFish(game.getMissedFish() + 1);
                    } else {
                        arraySimpleFish.get(i).move();
                    }
                }
                for (i = 0; i < arrayBonusFish.size(); i++) {
                    if (arrayBonusFish.get(i).isObjectOut()) {
                        arrayBonusFish.remove(i);
                        game.setMissedFish(game.getMissedFish() + 1);
                    } else {
                        arrayBonusFish.get(i).move();
                    }
                }
                for (i = 0; i < arrayEvilFish.size(); i++) {
                    if (arrayEvilFish.get(i) != null)
                        if (arrayEvilFish.get(i).isObjectOut()) {
                            arrayEvilFish.remove(i);
                            game.setMissedFish(game.getMissedFish() + 1);
                        } else {
                            if (i < arrayEvilFish.size())
                                arrayEvilFish.get(i).move();
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
                HitTheFish.pnlGameEnded.setVisible(true);
            } else {
                checkShot(me);
            }
        }
    }
    
    public void checkShot(MouseEvent _me) {
        int i = 0;
        boolean blankShoot = true;
        
        if (this.arraySimpleFish.size() > 0)
            while (i < this.arraySimpleFish.size() && blankShoot) {
                Point point = new Point(_me.getPoint());
                if (i < arraySimpleFish.size())
                    this.rectangle = this.arraySimpleFish.get(i).getBorderImage();

                if (this.rectangle.contains(point)) {
                    this.arraySimpleFish.remove(i);
                    game.setStrickenFish(game.getStrickenFish() + simpleFish.getPower());
                    blankShoot = false;
                }
                i++;
            }
        
        i = 0;
        if (this.arrayBonusFish.size() > 0 && blankShoot)
            while (i < this.arrayBonusFish.size() && blankShoot) {
                Point point = new Point(_me.getPoint());
                if (i < arrayBonusFish.size())
                    this.rectangle = this.arrayBonusFish.get(i).getBorderImage();

                if (this.rectangle.contains(point)) {
                    this.arrayBonusFish.remove(i);
                    game.setStrickenFish(game.getStrickenFish() + bonusFish.getPower());
                    blankShoot = false;
                }
                i++;
            }
        
        i = 0;
        if (this.arrayEvilFish.size() > 0 && blankShoot)
            while (i < this.arrayEvilFish.size() && blankShoot) {
                Point point = new Point(_me.getPoint());
                if (i < arrayEvilFish.size())
                    this.rectangle = this.arrayEvilFish.get(i).getBorderImage();

                if (this.rectangle.contains(point)) {
                    this.arrayEvilFish.remove(i);
                    game.setStrickenFish(game.getStrickenFish() + evilFish.getPower());
                    blankShoot = false;
                }
                i++;
            }
    }
    
    private int random(int pStart, int pEnd) {
        int differenza = pEnd - pStart;
        XX = rand.nextInt(differenza);
        differenza = pEnd - XX;
    return differenza;
    }
    
    public void createInstance() {
        threadSimpleFish = new Thread(new SimpleFishGenerator());
        threadWaves = new Thread(new WavesMove());
        threadMoveFish = new Thread(new MoveFish());
        threadBonusFish = new Thread(new BonusFishGenerator());
        threadEvilFish = new Thread(new EvilFishGenerator());
        game = new Game();
        this.createMovingObject = new CreateMovingObject();
    }
    
    public void startThread() {
        threadSimpleFish.start();
        threadWaves.start();
        threadMoveFish.start();
        threadBonusFish.start();
        threadEvilFish.start();
        game.setStartTimer();
        arraySimpleFish = createMovingObject.getArraySimpleFish();
        arrayBonusFish = createMovingObject.getArrayBonusFish();
        arrayEvilFish = createMovingObject.getArrayEvilFish();
    }
        
    public static void stopThread() {
//        try {
//            //PnlGame.threadSimpleFish.join();
////            PnlGame.threadBonusFish.join();
////            PnlGame.threadMoveFish.join();
////            PnlGame.threadWaves.join();
////            PnlGame.threadEvilFish.join();
//        } catch (InterruptedException ex) {
//            Logger.getLogger(PnlGame.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        PnlGame.threadSimpleFish.interrupt();
//        PnlGame.threadMoveFish.interrupt();
//        PnlGame.threadWaves.interrupt();
        PnlGame.threadSimpleFish.stop();
        PnlGame.threadBonusFish.stop();
        PnlGame.threadEvilFish.stop();
        PnlGame.threadMoveFish.stop();
        PnlGame.threadWaves.stop();
    }
}
