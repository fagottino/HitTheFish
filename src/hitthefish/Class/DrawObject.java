package hitthefish.Class;

import java.awt.image.BufferedImage;

/**
 *
 * @author Admin
 */
public class DrawObject extends Thread {
    
    private int x, y, width, height;
    private BufferedImage img;
    
    public DrawObject(BufferedImage _img, int _x, int _y, int _width, int _height) {
        this.x = _x;
        this.y = _y;
        this.width = _width;
        this.height = _height;
        this.start();
    }

    @Override
    public void run() {
       
    }
    
    
}
