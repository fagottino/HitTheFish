/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hitthefish.Class;

/**
 *
 * @author Admin
 */
public class BonusFish extends RotateObject {
    
    public int power;
    
    public BonusFish(String pPathImage, int pX, int pY, int pSpeed) {
        super(pPathImage, pX, pY, pSpeed);
        
        power = 2;
    }

    public int getPower() {
        return power;
    }
}
