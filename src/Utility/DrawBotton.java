package Utility;

import java.awt.Graphics;
import java.awt.Rectangle;

public class DrawBotton extends Rectangle{
    
    private String message;
    
    public DrawBotton(int pX, int pY, int pLarghezza, int pAltezza){
        this.x = pX;
        this.y = pY;
        this.width = pLarghezza;
        this.height = pAltezza;
    }
    
    public void drawRect(Graphics g){
        g.drawRect(this.x, this.y, this.width, this.height);
    }

    public void drawString(Graphics g, String pMessage, int pX, int pY){
        int x,y;
        this.message = pMessage;
        x = pX;
        y = pY;
        g.drawString(this.message, x+20, y+50);
    }
    
}
