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
    
    private int gameSecondTime, delay;
    private static int points, simpleFishStricken, bonusFishStricken, evilFishStricken, simpleFishMissed, bonusFishMissed, evilFishMissed, totalFishStricken, totalFishMissed;
    private Timer timer;
    private TimerTask ts;
    private File file;
    
    public Game() {
        points = 0;
        simpleFishStricken = 0;
        bonusFishStricken = 0;
        evilFishStricken = 0;
        totalFishStricken = 0;
        simpleFishMissed = 0;
        bonusFishMissed = 0;
        evilFishMissed = 0;
        totalFishMissed = 0;
        gameSecondTime = 10;
        delay = 1000;
    }

    public int getTimer() {
        return gameSecondTime;
    }

    public static int getStrickenFish(int pFishType) {
        switch(pFishType) {
            case HitTheFish.SIMPLEFISH:
                pFishType = simpleFishStricken;
            break;
            case HitTheFish.BONUSFISH:
                pFishType = bonusFishStricken;
            break;
            case HitTheFish.EVILFISH:
                pFishType = evilFishStricken;
            break;
            case HitTheFish.ALL:
            default:
                pFishType = simpleFishStricken + bonusFishStricken + evilFishStricken;
            break;
        }
        return pFishType;
    }

    public static int getMissedFish(int pFishType) {
        switch(pFishType) {
            case HitTheFish.SIMPLEFISH:
                pFishType = simpleFishMissed;
            break;
            case HitTheFish.BONUSFISH:
                pFishType = bonusFishMissed;
            break;
            case HitTheFish.EVILFISH:
                pFishType = evilFishMissed;
            break;
            case HitTheFish.ALL:
            default:
                //pFishType = simpleFishMissed + bonusFishMissed + evilFishMissed;
                pFishType = simpleFishMissed + bonusFishMissed;
            break;
        }
        return pFishType;
    }

    public void setStrickenFish(int pFishStricken, int pFishType) {
        switch(pFishType) {
            case HitTheFish.SIMPLEFISH:
                simpleFishStricken = pFishStricken;
            break;
            case HitTheFish.BONUSFISH:
                bonusFishStricken = pFishStricken;
            break;
            case HitTheFish.EVILFISH:
                evilFishStricken = pFishStricken;
            break;
        }
    }

    public void setMissedFish(int pFishMissed, int pFishType) {
        switch(pFishType) {
            case HitTheFish.SIMPLEFISH:
                simpleFishMissed = pFishMissed;
            break;
            case HitTheFish.BONUSFISH:
                bonusFishMissed = pFishMissed;
            break;
            case HitTheFish.EVILFISH:
                evilFishMissed = pFishMissed;
            break;
        }
    }

    public static int getPoints() {
        return points;
    }

    public void setPoints(int pPoints) {
        points = pPoints;
    }

    public void setStartTimer() {
        
        ActionListener al = new ActionListener() {
        
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (gameSecondTime <= 0) {
                    timer.stop();
                    saveResult();
                    PnlGame.stopThread();
                    // se vince cambio background
                    //HitTheFish.pnlGameEnded.changeBackground();
                    HitTheFish.pnlGameEnded.repaint();
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
        timer.stop();
        PnlGame.stopThread();
        HitTheFish.pnlGameEnded.repaint();
        HitTheFish.pnlGame.setVisible(false);
        HitTheFish.pnlGameEnded.setVisible(true);
    }
    
    public void saveResult() {
        file.writeFile(getStrickenFish(HitTheFish.ALL), getMissedFish(HitTheFish.ALL));
    }
}
