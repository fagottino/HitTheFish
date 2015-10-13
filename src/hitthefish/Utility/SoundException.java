package hitthefish.Utility;

/**
 *
 * @author Andrea D'Ottavio
 */
public class SoundException extends Exception {

    SoundException(String string) {
        super(string);
    }

    public SoundException(String fileName, Throwable cause) {
        super("Cannot read/play " + fileName + ": " + cause.getMessage());
    }

    public SoundException(Throwable cause) {
        super(cause);
    }
}