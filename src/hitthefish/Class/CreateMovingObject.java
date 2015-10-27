package hitthefish.Class;

import hitthefish.HitTheFish;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class CreateMovingObject {
    private ArrayList<MoveObject> arraySimpleFish;
    private int i;
    
    public CreateMovingObject() {
        arraySimpleFish = new ArrayList<>();
    }
    
    public void drawMovingObject(Graphics g) {
        for (i = 0; i < arraySimpleFish.size(); i++) {
            MoveObject simpleFish = arraySimpleFish.get(i);
            g.drawImage(HitTheFish.pnlGame.getImageSimpleFish(), simpleFish.getX(), simpleFish.getY(), simpleFish.getWidth(), simpleFish.getHeight(), null);
        }
    }
    
    public ArrayList<MoveObject> getSimpleFish() {
        return arraySimpleFish;
    }
}
