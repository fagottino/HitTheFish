/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hitthefish.Class;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 *
 * @author Admin
 */
public class RotateObject extends MoveObject {
    
    private BufferedImage img;

    public RotateObject(BufferedImage _img, int _x, int _y, int _width, int _height, int _speed) {
        super(_img, _x, _y, _width, _height, _speed);
        this.img = _img;
    }
       
    public void paintContent(Graphics g) {
        AffineTransform at = AffineTransform.getTranslateInstance(100, 100);
        at.rotate(Math.toRadians(90));

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(img, at, null);
    }

    public BufferedImage getImg() {
        return img;
    }
    
    
}
