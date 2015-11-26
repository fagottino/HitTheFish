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
        gameSecondTime = 10;
        delay = 1000;
    }

    public int getTimer() {
        return gameSecondTime;
    }

    public int getStrickenFish() {
        return fishStricken;
    }

    public int getMissedFish() {
        return fishMissed;
    }

    public void setStrickenFish(int pFishStricken) {
        fishStricken = pFishStricken;
    }

    public void setMissedFish(int pFishMissed) {
        fishMissed = pFishMissed;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setStartTimer() {
        
        ActionListener al = new ActionListener() {
        
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (gameSecondTime <= 0) {
                    timer.stop();
                    //saveResult();
                    PnlGame.stopThread();
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
    
    public void saveResult() {
        file.writeFile(fishStricken, fishMissed);
    }
}
