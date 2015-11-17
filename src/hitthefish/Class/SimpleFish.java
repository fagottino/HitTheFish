/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hitthefish.Class;

import hitthefish.Utility.Resources;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class SimpleFish {
    
    private BufferedImage imgSimpleFish;
    private Graphics2D g2d;
    protected int x, y, width, height, speed, rad;
    AffineTransform at;
    public int shotFish;
    
    public SimpleFish(String pPathImg, int pX, int pY, int pWidth, int pHeight, int pSpeed) {
        this.imgSimpleFish = Resources.getImage(pPathImg);
        this.x = pX;
        this.y = pY;
        this.width = pWidth;
        this.height = pHeight;
        this.speed = pSpeed;
        this.at = AffineTransform.getTranslateInstance(this.x, this.y);
        this.rad = 290;
        this.shotFish = 0;
    }
    
    public void drawFish(Graphics g) {
        this.g2d = (Graphics2D) g;
        
        this.g2d.drawImage(imgSimpleFish, this.at, null);
    }
    
    public void move() {
        // Se sta salendo
        if (this.rad < 360) {
            this.y -= this.speed;
        // Se è arrivato a fine salita decremento piano
        } else if (this.rad >= 360 && this.rad <= 370) {
            this.y--;
        // Discesa
        } else {
            this.y += this.speed + 2;   
        }
        //this.rad = this.rad + 2;
        this.rad += 2;
        this.x += 5;
        this.at = AffineTransform.getTranslateInstance(this.x, this.y);
        this.at.rotate(Math.toRadians(this.rad));
        
        // Se esce dalla scena
        //if (this.y >= 792)
    }
}
