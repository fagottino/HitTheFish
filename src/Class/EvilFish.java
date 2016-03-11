/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

/**
 *
 * @author Admin
 */
public class EvilFish extends RotateObject {
    
    private int power;
    
    public EvilFish(String pPathImage, int pX, int pY, int pSpeed) {
        super(pPathImage, pX, pY, pSpeed);
        
        power = -3;
    }

    public int getPower() {
        return power;
    }
}
