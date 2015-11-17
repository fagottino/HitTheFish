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
    
    public MoveObject(BufferedImage _img, int _x, int _y, int _width, int _height, int _speed, int _index) {
        this.img = _img;
        this.x = _x;
        this.y = _y;
        this.width = _width;
        this.height = _height;
        this.speed = _speed;
        this.index = _index;
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
