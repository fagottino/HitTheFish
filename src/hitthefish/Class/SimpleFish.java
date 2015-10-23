/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hitthefish.Class;
import java.awt.image.BufferedImage;

public class SimpleFish extends MoveObject {

    public SimpleFish(BufferedImage _img, int _x, int _y, int _width, int _height, int _speed) {
        super(_img, _x, _y, _width, _height, _speed);
    }

    @Override
    public void move() {
        this.y -= this.speed;
    }
    
    
}
