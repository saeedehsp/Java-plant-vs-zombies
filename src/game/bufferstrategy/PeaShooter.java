package game.bufferstrategy;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A class mushroom  plant which shoots  any near zombie
 */
class PeaShooter extends Plant {
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
     * PeaShooter constructor
     * @param x
     * @param y
     * @param state
     */

    PeaShooter(int x, int y, GameState state) {
        super(x, y, "Peashooter.gif", 100, 100, state, 0, 100);

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
        final PeaShooter me = this;
        TimerTask Task = new TimerTask() {

            @Override
            public void run() {
                for (Drawable drawable : gameState.getDrawables()) {
                    if (drawable.y == me.y && drawable instanceof Zombie) {
                        Pea pea = new Pea(me, gameState);
                        gameState.addDrawables((pea));
                    }
                }

            }

        };

        timer.schedule(Task, 0L, 4000L);
    }
}

