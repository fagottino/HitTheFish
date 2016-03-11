/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

public class SimpleFish extends RotateObject {
    
    private int power;
        
    public SimpleFish(String pPathImg, int pX, int pY, int pSpeed) {
        super(pPathImg, pX, pY, pSpeed);
    
        power = 1;
    }
    
    public int getPower() {
        return power;
    }
}
