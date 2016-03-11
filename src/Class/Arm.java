/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import GUI.PnlGame;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.SwingUtilities;

/**
 *
 * @author Admin
 */
//public class Arm extends MoveObject {
public class Arm {
    private BufferedImage img;
    private int x, y, width, height, speed, newX;
    public static int limitWidthArm;
    
    public Arm(BufferedImage pImg, int pX, int pY) {
        this.img = pImg;
        this.x = pX;
        this.y = pY;
        this.width = pImg.getWidth();
        this.height = pImg.getHeight();
        limitWidthArm = PnlGame.bgWidth - pImg.getWidth(); 
    }
    
    public void draw(Graphics g) {
        g.drawImage(img, this.x, this.y, this.width, this.height, null);
    }

    public void setX(int pX) {
        this.x = pX;
    }
}
