package hitthefish.Class;

import hitthefish.HitTheFish;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class CreateMovingObject {
    private ArrayList<SimpleFish> arraySimpleFish;
    private int i;
    Graphics2D g2d;
    
    public CreateMovingObject() {
        arraySimpleFish = new ArrayList<>();
    }
    
    public void drawMovingObject(Graphics g) {
        g2d = (Graphics2D) g;
        
        for (i = 0; i < arraySimpleFish.size(); i++) {
            SimpleFish simpleFish = arraySimpleFish.get(i);
            g2d.drawImage(HitTheFish.pnlGame.getImageSimpleFish(), simpleFish.getAt(), null);
            //g.drawImage(HitTheFish.pnlGame.getImageSimpleFish(), simpleFish.getX(), simpleFish.getY(), simpleFish.getWidth(), simpleFish.getHeight(), null);
        }
    }
    
    public ArrayList<SimpleFish> getSimpleFish() {
        return arraySimpleFish;
    }
    
    public void deleteItemFromArray(int index) {
        
    }
}
