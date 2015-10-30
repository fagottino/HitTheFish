/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hitthefish.Class;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 *
 * @author Admin
 */
public class RotateObject extends MoveObject {
    
    private int i, speed;
    AffineTransform at;

    public RotateObject(BufferedImage _img, int _x, int _y, int _width, int _height, int _speed) {
        super(_img, _x, _y, _width, _height, _speed);
        at = AffineTransform.getTranslateInstance(super.x, this.y);
        i = 290;
        this.speed = _speed;
    }

    @Override
    public void move() {
        if (i < 360) {
            super.y -= super.speed;
            at = AffineTransform.getTranslateInstance(super.x, super.y);
            i = i + 2;
            at.rotate(Math.toRadians(i));
        } else {
            super.y += super.speed;
            
            at = AffineTransform.getTranslateInstance(super.x, super.y);
            i = i + 2;
            at.rotate(Math.toRadians(i));            
        }
    }
    
    public AffineTransform getAt() {
        return this.at;
    }
}
