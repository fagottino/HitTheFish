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
        this.objectFlip = pObjectFlip;
        this.borderImage = new Rectangle(this.x, this.y, this.width, this.height);
        this.at = AffineTransform.getTranslateInstance(this.x, this.y);
        if (!this.objectFlip) {
            this.at.rotate(Math.toRadians(290));
            this.rad = 290;
        } else {
            this.at.rotate(Math.toRadians(110));
            this.rad = 80;
        }
    }
    
    public void drawFish(Graphics g) {
        this.g2d = (Graphics2D) g;
        
        this.g2d.drawImage(imgSimpleFish, this.at, null);
    }
    
    public void move() {
        if (!this.objectFlip) {
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
            this.x += 5;
        } else {
            // Se sta salendo
            if (this.rad < 80 && this.rad > 10) {
                this.y -= this.speed;
            // Se è arrivato a fine salita decremento piano
            } else if (this.rad <= 10 && this.rad >= 0) {
                this.y--;
            // Discesa
            } else {
                this.y += this.speed + 2;   
            }
            this.rad -= 2;
            this.x -= 5;
        }
        
        borderImage.x = this.x;
        borderImage.y = this.y;
        this.at = AffineTransform.getTranslateInstance(this.x, this.y);
        this.at.rotate(Math.toRadians(this.rad));
        
        // Se esce dalla scena
        if (this.y >= 792)
            this.objectOut = true;
    }
    
    public boolean isObjectOut() {
        return this.objectOut;
    }

    public Rectangle getBorderImage() {
        return borderImage;
    }
}
