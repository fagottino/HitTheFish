package Class;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class CreateMovingObject {
    private ArrayList<SimpleFish> arraySimpleFish;
    private ArrayList<BonusFish> arrayBonusFish;
    private ArrayList<EvilFish> arrayEvilFish;
    
    public CreateMovingObject() {
        arraySimpleFish = new ArrayList<>();
        arrayBonusFish = new ArrayList<>();
        arrayEvilFish = new ArrayList<>();
    }
    
    public ArrayList<SimpleFish> getArraySimpleFish() {
        return arraySimpleFish;
    }

    public ArrayList<BonusFish> getArrayBonusFish() {
        return arrayBonusFish;
    }

    public ArrayList<EvilFish> getArrayEvilFish() {
        return arrayEvilFish;
    }
}
