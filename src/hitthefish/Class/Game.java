package hitthefish.Class;

/**
 *
 * @author Admin
 */
public class Game {
    
    private int points;
    private int strickenFish;
    private int timer;
    
    public Game() {
        points = 0;
        strickenFish = 0;
    }

    public int getStrickenFish() {
        return strickenFish;
    }

    public void setStrickenFish(int strickenFish) {
        this.strickenFish = strickenFish;
    }
}
