/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hitthefish.Class;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 *
 * @author Admin
 */
public class RotateObject extends MoveObject {
    
    private BufferedImage img;
    protected int width, height;
    protected int x, y, rad, speed, index;
    AffineTransform at;

    public RotateObject(BufferedImage _img, int _x, int _y, int _width, int _height, int _speed, int _index) {
        super(_img, _x, _y, _width, _height, _speed, _index);
        this.img = _img;
        this.x = _x;
        this.y = _y;
//        if (_width == -1 && _height == -1) {
//            this.width = _img.getWidth();
//            this.height = _img.getHeight();
//        } else {
            this.width = _width;
            this.height = _height;
//        }
        this.speed = _speed;
        this.index = _index;
//        this.start();
        at = AffineTransform.getTranslateInstance(this.x, this.y);
        rad = 290;
        this.speed = _speed;
    }
    
    @Override
    public void move() {
        // Se sta salendo
        if (rad < 360) {
            this.y -= this.speed;
        // Se è arrivato a fine salita decremento piano
        } else if (rad >= 360 && rad <= 370) {
            this.y--;
        // Discesa
        } else {
            this.y += this.speed + 2;   
        }
        rad = rad + 2;
        this.x += 5;
        at = AffineTransform.getTranslateInstance(this.x, this.y);
        at.rotate(Math.toRadians(rad));
        if (this.y >= 792)
            super.interruptThread = true;
    }
    
    /**
     *
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(img, this.x, this.y, this.width, this.height, null);
    }

//    @Override
//    public void run() {
//        while (true) {
//            move();
//            if (this.y >= 792) {
//                hitthefish.HitTheFish.pnlGame.removeMovingObject(this.index);
//                //this.interrupt();
//            }
//            if (!Thread.interrupted())
//                try {
//                    Thread.sleep(60);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(MoveObject.class.getName()).log(Level.SEVERE, null, ex);
//                }
//        }
//    }
    
    public AffineTransform getAt() {
        return this.at;
    }
    
    public int getCoordinateX() {
        return this.x;
    }
    
    public int getCoordinateY() {
        return this.y;
    }
}
