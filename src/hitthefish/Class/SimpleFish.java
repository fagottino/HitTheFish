/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hitthefish.Class;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class SimpleFish extends RotateObject {
    
    public int shotFish;
    
    public SimpleFish(BufferedImage _img, int _x, int _y, int _width, int _height, int _speed, int _index) {
        super(_img, _x, _y, _width, _height, _speed, _index);
        /*if (_y <= 500)
            _height = (_height - (y - 300));*/
        this.shotFish = 1;
    }
    
    public Rectangle getRectangle() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }    
}
