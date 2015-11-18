package hitthefish.Class;

import hitthefish.HitTheFish;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class CreateMovingObject {
    private ArrayList<SimpleFish> arraySimpleFish;
    private BufferedImage img;
    protected int width, height;
    protected int x, y, rad, speed, index;
    Graphics2D g2d;
    
    public CreateMovingObject() {
        arraySimpleFish = new ArrayList<>();
    }
    
    public ArrayList<SimpleFish> getArraySimpleFish() {
        return arraySimpleFish;
    }
    
    public void deleteItemFromArray(int index) {
        this.arraySimpleFish.remove(index);
    }
}
