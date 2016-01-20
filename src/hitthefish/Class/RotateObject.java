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
import java.awt.image.BufferedImage;

/**
 *
 * @author Admin
 */
public class RotateObject {
    
    private BufferedImage imageObject;
    private Graphics2D g2d;
    private Rectangle borderImage;
    protected int x, y, adjustX, adjustY, speed, rad;
    public boolean objectOut, objectFlip;
    AffineTransform at;
    
    public RotateObject(String pPathImg, int pX, int pY, int pSpeed) {
        this.x = pX;
        this.y = pY;
        this.speed = pSpeed;
        this.at = AffineTransform.getTranslateInstance(this.x, this.y);
        if (pX > 550) {
            pPathImg = pPathImg.substring(0, pPathImg.length() - 4) + "reverse.png";
            this.objectFlip = true;
            this.at.rotate(Math.toRadians(110));
            this.rad = 80;
        } else {
            this.at.rotate(Math.toRadians(290));
            this.rad = 290;
            this.objectFlip = false;
        }
        this.imageObject = Resources.getImage(pPathImg);
        this.borderImage = new Rectangle(this.x, this.y, imageObject.getWidth(), imageObject.getHeight());
    }
    
    public void move() {
        if (!this.objectFlip) {
            // Se sta salendo
            if (this.rad < 360) {
                this.adjustX = -8;
                this.adjustY = 15;
                this.y -= this.speed;
            // Se è arrivato a fine salita decremento piano
            } else if (this.rad >= 360 && this.rad <= 370) {
                this.adjustX = 0;
                this.adjustY = 0;
                this.y--;
            // Discesa
            } else {
                this.adjustX = +45;
                this.adjustY = -30;
                this.y += this.speed + 2;
            }
            this.rad += 2;
            this.x += 5;
        } else {
            // Se sta salendo
            if (this.rad < 80 && this.rad > 10) {
                this.adjustX = +28;
                this.adjustY = -20;
                this.y -= this.speed;
            // Se è arrivato a fine salita decremento piano
            } else if (this.rad <= 10 && this.rad >= 0) {
                this.adjustX = 0;
                this.adjustY = 0;
                this.y--;
            // Discesa
            } else {
                this.adjustX = -10;
                this.adjustY = +40;
                this.y += this.speed + 2;   
            }
            this.rad -= 2;
            this.x -= 5;
        }
        
        borderImage.x = this.x - adjustX;
        borderImage.y = this.y - adjustY;
        this.at = AffineTransform.getTranslateInstance(this.x, this.y);
        this.at.rotate(Math.toRadians(this.rad));
        
        // Se esce dalla scena
        if (this.y >= 792)
            this.objectOut = true;
    }
    
    public void drawFish(Graphics g) {
        this.g2d = (Graphics2D) g;
        
        this.g2d.drawImage(imageObject, this.at, null);
        //g2d.draw(borderImage);
    }
    
    public boolean isObjectOut() {
        return this.objectOut;
    }

    public Rectangle getBorderImage() {
        return borderImage;
    }
}
