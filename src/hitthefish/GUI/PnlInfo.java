/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hitthefish.GUI;

import hitthefish.Utility.DrawBotton;
import hitthefish.Utility.Resources;
import hitthefish.HitTheFish;
import java.awt.Cursor;
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
public class PnlInfo extends JPanel {
    
    private final BufferedImage background;
    private final DrawBotton back;

    public PnlInfo() {
        this.setSize(HitTheFish.FRAME_SIZE);
        background = Resources.getImage("../img/info.png");
        back = new DrawBotton(1000, 687, 138, 49);
        this.addMouseListener(new PnlInfo.MouseListener());
        this.addMouseMotionListener(new PnlInfo.MouseListener());
    }
    
    @Override
    public void paint(Graphics g) {
        update(g);
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
    
    public class MouseListener extends MouseAdapter {
        
        @Override
        public void mouseMoved(MouseEvent e) {
            Point p = e.getPoint();
            
            if (back.contains(p))
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            else
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            Point p = e.getPoint();
            
            if (back.contains(p)) {
                HitTheFish.pnlInfo.setVisible(false);
                HitTheFish.pnlMenu.setVisible(true);
            }
        }
    }
}
