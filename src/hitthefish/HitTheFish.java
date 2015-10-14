package hitthefish;

import javax.swing.JFrame;
import hitthefish.GUI.Menu;
import hitthefish.GUI.Info;
import hitthefish.GUI.PnlGame;
import java.awt.Container;
import java.awt.Dimension;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author Admin
 */
public class HitTheFish extends JFrame{
    
    public static final String GAME_NAME = "Hit the Fish";
    
    public static final int WIDTH = 1190;
    public static final int HEIGHT = 780;
    public static final int MENU = 0;
    public static final int INFO = 1;
    public static final int SELECT_LEVEL = 2;
    public static final int PLAY = 3;
    
    public Container _container;
    
    public static final Dimension FRAME_SIZE = new Dimension(WIDTH, HEIGHT);
    
    public static Menu pnlMenu;
    public static Info pnlInfo;
    public static PnlGame pnlGame;
    
    public HitTheFish() {
        this.setSize(FRAME_SIZE);
        this.setTitle(GAME_NAME);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        _container = this.getContentPane();
        
        pnlMenu = new Menu();
        pnlInfo = new Info();
        pnlGame = new PnlGame();
        
        pnlMenu.setVisible(true);
        pnlInfo.setVisible(false);
        pnlGame.setVisible(false);
        _container.add(pnlInfo);
        _container.add(pnlGame);
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
