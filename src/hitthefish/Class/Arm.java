/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hitthefish.Class;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Admin
 */
public class Arm {
    private int x, y, width, height;
    private BufferedImage img;
    
    public Arm(BufferedImage _img, int _x, int _y, int _width, int _height) {
        this.x = _x;
        this.y = _y;
        this.width = _width;
        this.height = _height;
        this.img = _img;
    }
    
    public void draw(Graphics g) {
        g.drawImage(img, this.x, this.y, this.width, this.height, null);
        System.out.println("sdvsbvsdv");
    }
}
