/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hitthefish.Class;

import hitthefish.Utility.Resources;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleFish {
    
    private BufferedImage imgSimpleFish;
    private Graphics2D g2d;
    private Rectangle borderImage;
    protected int x, y, width, height, speed, rad;
    AffineTransform at;
    public boolean objectOut, objectFlip;
    
    public SimpleFish(String pPathImg, int pX, int pY, int pWidth, int pHeight, int pSpeed, boolean pObjectFlip) {
        this.imgSimpleFish = Resources.getImage(pPathImg);
        this.x = pX;
        this.y = pY;
        this.width = pWidth;
        this.height = pHeight;
        this.speed = pSpeed;
        this.at = AffineTransform.getTranslateInstance(this.x, this.y);
        this.rad = 290;
        this.objectFlip = pObjectFlip;
        this.borderImage = new Rectangle(this.x, this.y, this.width, this.height);
    }
    
    public void drawFish(Graphics g) {
        this.g2d = (Graphics2D) g;
        
        if (this.objectFlip)
            this.at.translate(this.width, this.y);
            
        this.g2d.drawImage(imgSimpleFish, this.at, null);
        
        //this.g2d.drawImage(imgSimpleFish, (int)this.at.getTranslateX(), (int)this.at.getTranslateY(), this.width * -1, this.height, null);
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
            this.rad += 2;
            if (!this.objectFlip) {
                this.x += 5;
            } else {
            this.x -= 5;
            }
        
        this.at = AffineTransform.getTranslateInstance(this.x, this.y);
        this.at.rotate(Math.toRadians(this.rad));
        
        // Se esce dalla scena
        if (this.y >= 792)
            this.objectOut = true;
    }
    
    public boolean isObjectOut() {
        return this.objectOut;
    }
}
