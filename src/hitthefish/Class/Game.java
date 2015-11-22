package hitthefish.Class;

import hitthefish.GUI.PnlGame;
import hitthefish.HitTheFish;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import javax.swing.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Game {
    
    private int points, gameSecondTime, delay;
    private int fishStricken, fishMissed;
    private Timer timer;
    private TimerTask ts;
    private File file;
    
    public Game() {
        points = 0;
        fishStricken = 0;
        fishMissed = 0;
        gameSecondTime = 5;
        delay = 1000;
    }

    public int getTimer() {
        return gameSecondTime;
    }

    public int getStrickenFish() {
        return this.fishStricken;
    }

    public int getMissedFish() {
        return fishMissed;
    }

    public void setStrickenFish(int pFishStricken) {
        this.fishStricken = pFishStricken;
    }

    public void setMissedFish(int pFishMissed) {
        this.fishMissed = pFishMissed;
    }

    public void setStartTimer() {
        
        ActionListener al = new ActionListener() {
        
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (gameSecondTime <= 0) {
                    timer.stop();
                    PnlGame.stopThread();
                    //checkResult();
                    // se vince cambio background
//                    HitTheFish.pnlGameEnded.changeBackground();
                    HitTheFish.pnlGame.setVisible(false);
                    HitTheFish.pnlGameEnded.setVisible(true);
                } else {
                    gameSecondTime--;
                }
            }
          };
        
        timer = new Timer(delay, al);
        timer.start();
    }
    
    public void stopGame() {
        
    }
    
    public void checkResult() {
        try {
            file.writeFile(this.fishStricken, this.fishMissed);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
