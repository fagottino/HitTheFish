package hitthefish.GUI;

import hitthefish.Utility.Resources;
import hitthefish.HitTheFish;
import hitthefish.Class.Arm;
import hitthefish.Class.CreateMovingObject;
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
    private Arm arm;
    private CreateMovingObject createMovingObject;
    private MoveObject moveObject;
    private RotateObject rotateObject;
    private SimpleFish simpleFish;
    //endregion
    
    //region Thread
    public Thread threadWaves;
    public Thread threadFish;
    public Thread threadMoveFish;
    //endregion
    
    //region Variabili locali
    private String name;
    private String life;
    private final int timer;
    private int i, k, x, y, XX;
    private int actuallyPoint;
    private int randomTo, randomFrom;
    private Random rand;
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
    //private Ellipse2D circle;
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
        
        //region Istanze classi
        threadWaves = new Thread(new WavesMove());
        arm = new Arm(gun, x, getHeight() - 152);
        createMovingObject = new CreateMovingObject();
        rand = new Random();
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
                this.wait = random(1000, 2800);
                this.x = random(1, 1100);
                if (this.x < 550) {
                    objectFlip = false;
                    simpleFish = new SimpleFish(pathImgSimpleFish, this.x, random(480, 630), imgSimpleFishWidth, imgSimpleFishHeight, random(5, 10), objectFlip);
                } else {
                    objectFlip = true;
                    simpleFish = new SimpleFish(pathImgSimpleFishReverse, this.x, random(480, 630), imgSimpleFishWidth, imgSimpleFishHeight, random(5, 10), objectFlip);
                }
                
                createMovingObject.getArraySimpleFish().add(simpleFish);
                try {
                    //stop = true;
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
                    if (createMovingObject.getArraySimpleFish().get(i).isObjectOut())
                        createMovingObject.getArraySimpleFish().remove(i);
                    else
                        createMovingObject.getArraySimpleFish().get(i).move();
                }
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
    
    private void drawString(Graphics g) {
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.setColor(Color.white);
        g.drawString("PUNTEGGIO", 870, 60);
        
//        Graphics2D g2 = (Graphics2D) g;
//        g2.draw(this.rectangle);
//        if (insideCircle) {
//            //g.drawString("Mouse in Circle at point " + (int)p.getX() + ", " + (int)p.getY(), (int)p.getX(), (int)p.getY());
//            g.drawString("Mouse in Circle at point ", 50, 50);
//        } else {
//            //g.drawString("Mouse outside Circle at point " + (int)p.getX() + ", " + (int)p.getY(), (int)p.getX(), (int)p.getY());
//            g.drawString("Mouse outside Circle at point ", 50, 50);
//        }   
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
//                System.out.println("COORDINATE CLICK X " + me.getX() + " Y " + me.getY());
//                System.out.println("PESCI ---------- X " + rotateObject.getCoordinateX() + " Y " + rotateObject.getCoordinateY());
                me.getClickCount();
            }
        }
    }
    
//    public int random(int _from, int _to) {
//        return _from + (int)(Math.random()*_to);
//    }
    
//    private int random(int _start, int _end, Random _random) {
//    if (_start > _end) {
//      throw new IllegalArgumentException("Start cannot exceed End.");
//    }
//    //get the range, casting to long to avoid overflow problems
//    long range = (long)_end - (long)_start + 1;
//    // compute a fraction of the range, 0 <= frac < range
//    long fraction = (long)(range * _random.nextDouble());
//    int randomNumber =  (int)(fraction + _start); 
//    return randomNumber;
//  }
    
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
    }
        
//        try {
//            this.threadFish.sleep(9000);
//            this.threadWaves.sleep(9000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(PnlGame.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        this.threadFish.interrupt();
//        this.threadWaves.interrupt();
//    }
    
    public void checkShot(MouseEvent _me) {
        int i;
        
        ArrayList<SimpleFish> arraySimpleFish = createMovingObject.getArraySimpleFish();
        
        for (i = 0; i < arraySimpleFish.size(); i++) {
            //Rectangle rectangle = simpleFish.getRectangle();
            //Rectangle2D rectangle = simpleFish.getRectangle();
            Point point = new Point(_me.getPoint());
            rectangle = arraySimpleFish.get(i).getBorderImage();
            
            
            if (this.rectangle.contains(_me.getPoint())) {
                insideCircle = true;
            } else {
                insideCircle = false;
            }
                 p = _me.getPoint();

            //if (rectangle.contains(point)) {
            if (this.rectangle.contains(point)) {
                //System.out.println("PRESO PRESO PRESO PRESO PRESO PRESO");
                arraySimpleFish.remove(i);
            } else {
                System.out.println("MANCATO MANCATO MANCATO MANCATO MANCATO");
            }
        }
    }
    
    public void deleteItemFromArray(int index) {
        createMovingObject.deleteItemFromArray(index);
    }
    
    public BufferedImage getImageSimpleFish() {
        return imgSimpleFish;
    }
}
