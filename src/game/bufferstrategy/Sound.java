package game.bufferstrategy;

import java.io.InputStream;
import java.util.TimerTask;
import javazoom.jl.player.Player;

/**
 * a class to play sounds of the game which is extended by the TimerTask class
 */

class Sound extends TimerTask {
    /**
     * an overrided method which plays the file that we want
     */
    @Override
    public void run() {

        try {
            InputStream file = ClassLoader.getSystemResourceAsStream("sounds/level1.mp3");
            Player playMP3 = new Player(file);
            playMP3.play();
        } catch (Exception e) {
            System.out.println("Error while playing sound");
        }

    }
}