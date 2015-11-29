package hitthefish.GUI;

import hitthefish.Class.Game;
import hitthefish.HitTheFish;
import hitthefish.Utility.DrawBotton;
import hitthefish.Utility.Resources;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Admin
 */
public class PnlGameEnded extends JPanel {
    
    private BufferedImage background, simpleFish, bonusFish, evilFish;
    private final DrawBotton restart, menu, quit;
    private int pixelImg, simpleFishX, simpleFishY, bonusFishX, bonusFishY, evilFishX, evilFishY;
    
    public PnlGameEnded(boolean pWin) {
        this.setSize(HitTheFish.FRAME_SIZE);
        this.background = Resources.getImage("../img/bglose.png");
        this.simpleFish = Resources.getImage(HitTheFish.pnlGame.pathImgSimpleFish);
        this.bonusFish = Resources.getImage(HitTheFish.pnlGame.pathImgBonusFish);
        this.evilFish = Resources.getImage(HitTheFish.pnlGame.pathImgEvilFish);
        this.pixelImg = 15;
        this.simpleFishX = 270;
        this.simpleFishY = 200;
        this.bonusFishX = 540;
        this.bonusFishY = 200;
        this.evilFishX = 770;
        this.evilFishY = 185;
        restart = new DrawBotton(350, 582, 190, 58);
        menu = new DrawBotton(720, 582, 160, 58);
        quit = new DrawBotton(1100, 685, 55, 35);
        this.addMouseListener(new PnlGameEnded.MouseListener());
        this.addMouseMotionListener(new PnlGameEnded.MouseListener());
    }
    
    @Override
    public void paint(Graphics g) {
        update(g);
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(simpleFish, simpleFishX, simpleFishY, simpleFish.getWidth() + pixelImg, simpleFish.getHeight() + pixelImg, this);
        
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.setColor(Color.red);
        g.drawString("-" + Game.getMissedFish(HitTheFish.SIMPLEFISH), simpleFishX - 10, simpleFishY + 5);

        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.setColor(Color.green);
        g.drawString("+" + Game.getStrickenFish(HitTheFish.SIMPLEFISH), simpleFishX + 70, simpleFishY + 80);
        
        g.drawImage(bonusFish, bonusFishX, bonusFishY, bonusFish.getWidth() + pixelImg, bonusFish.getHeight() + pixelImg, this);
        
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.setColor(Color.red);
        g.drawString("-" + Game.getMissedFish(HitTheFish.BONUSFISH), bonusFishX - 25, bonusFishY + 5);

        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.setColor(Color.green);
        g.drawString("+" + Game.getStrickenFish(HitTheFish.BONUSFISH), bonusFishX + 60, bonusFishY + 85);

        g.drawImage(evilFish, evilFishX, evilFishY, evilFish.getWidth() + pixelImg, evilFish.getHeight() + pixelImg, this);
        
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.setColor(Color.red);
        g.drawString("-" + Game.getMissedFish(HitTheFish.EVILFISH), evilFishX - 15, evilFishY + 20);

        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.setColor(Color.green);
        g.drawString("+" + Game.getStrickenFish(HitTheFish.EVILFISH), evilFishX + 75, evilFishY + 100);

        g.setFont(new Font("Arial", Font.BOLD, 90));
        g.setColor(Color.white);
        g.drawString("=", 930, 270);

        g.setFont(new Font("Arial", Font.BOLD, 120));
        if (Game.getPoints() >= 0)
            g.setColor(Color.green);
        else
            g.setColor(Color.red);
        g.drawString("" + Game.getPoints(), 1005, 270);
    }
    
    public void changeBackground() {
        this.background = Resources.getImage("../img/bgwin.png");
    }
    
    public class MouseListener extends MouseAdapter {
        
        @Override
        public void mouseMoved(MouseEvent e) {
            Point p = e.getPoint();
            
            if (restart.contains(p))
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            else if (menu.contains(p))
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            else if (quit.contains(p))
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            else
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            Point p = e.getPoint();
            
            if (restart.contains(p)) {
                HitTheFish.pnlGame.setVisible(true);
                HitTheFish.pnlGameEnded.setVisible(false);
                HitTheFish.pnlGame.createInstance();
                HitTheFish.pnlGame.startThread();
            } 
            if (menu.contains(p)) {
                HitTheFish.pnlMenu.setVisible(true);
                HitTheFish.pnlGameEnded.setVisible(false);
            }
            if (quit.contains(p))
                System.exit(0);
        }
    }
    
//    public void Repaint() {
//        repaint();
//    }
}
