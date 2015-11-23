package hitthefish;

import javax.swing.JFrame;
import hitthefish.GUI.PnlMenu;
import hitthefish.GUI.PnlInfo;
import hitthefish.GUI.PnlGame;
import hitthefish.GUI.PnlGameEnded;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author Admin
 */
public class HitTheFish extends JFrame{
    
    public static final String GAME_NAME = "Hit the Fish";
    
    public static final int WIDTH = 1190;
    public static final int HEIGHT = 791;
    public static final int MENU = 0;
    public static final int INFO = 1;
    public static final int SELECT_LEVEL = 2;
    public static final int PLAY = 3;
    
    public static Container _container;
    
    public static final Dimension FRAME_SIZE = new Dimension(WIDTH, HEIGHT);
    
    public static PnlMenu pnlMenu;
    public static PnlInfo pnlInfo;
    public static PnlGame pnlGame;
    public static PnlGameEnded pnlGameEnded;
    
    public HitTheFish() {
        this.setSize(FRAME_SIZE);
        this.setTitle(GAME_NAME);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        _container = this.getContentPane();
        
        pnlMenu = new PnlMenu();
        pnlInfo = new PnlInfo();
        pnlGame = new PnlGame();
        pnlGameEnded = new PnlGameEnded(false);
        
        pnlMenu.setVisible(true);
        pnlInfo.setVisible(false);
        pnlGame.setVisible(false);
        pnlGameEnded.setVisible(false);
        
        _container.add(pnlInfo);
        _container.add(pnlGame);
        _container.add(pnlGameEnded);
        _container.add(pnlMenu);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HitTheFish frameMain = new HitTheFish();
        frameMain.setVisible(true);
    }
}