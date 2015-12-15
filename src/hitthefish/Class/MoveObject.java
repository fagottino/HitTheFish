package hitthefish.Class;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class MoveObject extends Thread {
    
    private BufferedImage img;
    protected int width, height;
    protected int x, y, speed, index;
    protected boolean interruptThread;
    
    public MoveObject(BufferedImage pImg, int pX, int pY, int pWidth, int pHeight, int pSpeed, int PIndex) {
        this.img = pImg;
        this.x = pX;
        this.y = pY;
        this.width = pWidth;
        this.height = pHeight;
        this.speed = pSpeed;
        this.index = PIndex;
        this.interruptThread = false;
        this.start();
    }
    
    public void move() {
        this.y -= this.speed;
    }
    
    public void draw(Graphics g) {
        //if (x > )
        g.drawImage(img, this.x, this.y, this.width, this.height, null);
    }

    @Override
    public void run() {
        while (true) {
            move();
            if (interruptThread) {
                this.interrupt();
                //Thread.join();
                this.interruptThread = false;
            }
            
            if (!Thread.interrupted())
                try {
                    Thread.sleep(60);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MoveObject.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
    
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
}
