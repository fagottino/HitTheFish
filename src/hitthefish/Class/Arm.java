/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hitthefish.Class;

import hitthefish.GUI.PnlGame;
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
    private int limitWidthArm;
    
    public Arm(BufferedImage _img, int _x, int _y, int _width, int _height) {
        this.img = _img;
        this.x = _x;
        this.y = _y;
        this.width = _width;
        this.height = _height;
        this.limitWidthArm = PnlGame.bgWidth - 85; 
    }
    
    public void draw(Graphics g) {
        g.drawImage(img, this.x, this.y, this.width, this.height, null);
    }
    
    public class MouseEvents extends MouseAdapter {

        @Override
        public void mouseMoved(MouseEvent me) {
        if (x <= limitWidthArm)
            x = me.getX();
        else
            x = limitWidthArm;
        }
    }
}
