package hitthefish.Class;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class CreateMovingObject {
    private ArrayList<MoveObject> arraySimpleFish;
    
    public CreateMovingObject() {
        arraySimpleFish = new ArrayList<>();
    }
    
    public ArrayList<MoveObject> getSimpleFish() {
        return arraySimpleFish;
    }
}
