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
    private int width, height;
    protected int x, y, speed;
    
    //public MoveObject(BufferedImage _img, int _x, int _y, int _width, int _height, int _speed) {
    public MoveObject(BufferedImage _img, int _x, int _y, int _width, int _height, int _speed) {
        this.img = _img;
        this.x = _x;
        this.y = _y;
        if (_width == -1 && _height == -1) {
            this.width = _img.getWidth();
            this.height = _img.getHeight();
        } else {
            this.width = _width;
            this.height = _height;
        }
        this.speed = _speed;
        this.start();
    }
    
    public void move() {
        this.y -= this.speed;
    }
    
    public void draw(Graphics g) {
        g.drawImage(img, this.x, this.y, this.width, this.height, null);
    }

    @Override
    public void run() {
        while (true) {
            move();
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
