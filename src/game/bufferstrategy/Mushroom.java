package game.bufferstrategy;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A class mushroom  plant which shoots  any near zombie
 */
public class Mushroom extends Plant {
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
     * Mushroom constructor
     * @param x
     * @param y
     * @param state
     */
    Mushroom(int x, int y, GameState state) {
        super(x, y, "mushroom.png", 100, 100, state, 0, 100);

    }


    /**
     * to delete the plant
     */
    @Override
    void deleteObject() {
        super.deleteObject();
        timer.cancel();
    }

    /**
     * to plant it on the ground
     */
    @Override
    void planted() {
        super.planted();
        final Mushroom me = this;
        TimerTask Task = new TimerTask() {

            @Override
            public void run() {
                MushroomBullet mushroomBullet = new MushroomBullet(me, gameState);
                gameState.addDrawables((mushroomBullet));
            }

        };

        timer.schedule(Task, 0L, 4000L);
    }
}