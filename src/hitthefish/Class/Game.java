package hitthefish.Class;

import hitthefish.GUI.PnlGame;
import hitthefish.HitTheFish;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.util.TimerTask;

/**
 *
 * @author Admin
 */
public class Game {
    
    private int points, gameSecondTime, delay;
    private int fishStricken, fishMissed;
    private Timer timer;
    private TimerTask ts;
    
    public Game() {
        points = 0;
        fishStricken = 0;
        fishMissed = 0;
        gameSecondTime = 2;
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
                    HitTheFish.pnlGame.setVisible(false);
                    HitTheFish.pnlInfo.setVisible(true);
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
}
