package game.bufferstrategy;

import java.util.Timer;
import java.util.TimerTask;


/**
 * A class sunflower  plant which produce sun
 */
class SunFlower extends Plant {
    private Timer timer = new Timer();
    /**
     * a override method of Drawable class which set the state of a Drawable object
     * @return
     */
    @Override
    int getStateToVisible() {
        return 2;
    }

    /**
     *sunFlower constructor
     * @param x
     * @param y
     * @param state
     */

    SunFlower(int x, int y, GameState state) {
        super(x, y, "sunflower.gif", 100, 100, state, 0, 100);
    }

    /**
     * making sun if the sunflower is planted
     */
    @Override
    void planted() {
        super.planted();
        makeSun();
    }

    /**
     * method of producing sun
     */
    private void makeSun() {
        final SunFlower me = this;
        TimerTask Task = new TimerTask() {
            @Override
            public void run() {
                Sun sun = new Sun(me, gameState);
                gameState.addDrawables(sun);
            }

        };

        timer.schedule(Task, 2000L, 7000L);
    }

    /**
     * delete the object
     */
    @Override
    void deleteObject() {
        timer.cancel();
        super.deleteObject();
    }
}
