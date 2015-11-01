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
    
    private int rad, speed;
    AffineTransform at;

    public RotateObject(BufferedImage _img, int _x, int _y, int _width, int _height, int _speed) {
        super(_img, _x, _y, _width, _height, _speed);
        at = AffineTransform.getTranslateInstance(super.x, this.y);
        rad = 290;
        this.speed = _speed;
    }

    @Override
    public void move() {
        // Se sta salendo
        if (rad < 360) {
            super.y -= super.speed;
        // Se è arrivato a fine salita incremento piano
        } else if (rad >= 360 && rad <= 370) {
            super.y--;
        // Discesa
        } else {
            super.y += super.speed + 2;   
        }
        rad = rad + 2;
        super.x += 5;
        at = AffineTransform.getTranslateInstance(super.x, super.y);
        at.rotate(Math.toRadians(rad));
    }
    
    public AffineTransform getAt() {
        return this.at;
    }
}
