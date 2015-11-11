package hitthefish.Class;

/**
 *
 * @author Admin
 */
public class Game {
    
    private int life;
    private int strickenFish;
    private int timer;
    
    public Game() {
        life = 0;
        strickenFish = 0;
    }

    public int getStrickenFish() {
        return strickenFish;
    }

    public void setStrickenFish(int strickenFish) {
        this.strickenFish = strickenFish;
    }
}
