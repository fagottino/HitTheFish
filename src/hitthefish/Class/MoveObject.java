package hitthefish.Class;

import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class MoveObject extends Thread {
    
    private int x, y, speed;
    
    public MoveObject(int _x, int _y, int _speed) {
        this.x = _x;
        this.y = _y;
        this.speed = _speed;
        this.start();
    }
    
    public void move() {
            this.y += this.speed;
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
}
