package game.bufferstrategy;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A class IcedPeaShooter plant which shoots  any near zombie and ice them (make them walk slowly )
 */
class IcedPeaShooter extends Plant {
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
     * IcedPeaShooter constructor
     * @param x
     * @param y
     * @param state
     */

    IcedPeaShooter(int x, int y, GameState state) {
        super(x, y, "Snow-Pea2.gif", 100, 100, state, 0, 100);
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
        final IcedPeaShooter me = this;
        TimerTask Task = new TimerTask() {
            @Override
            public void run() {
                for (Drawable drawable : gameState.getDrawables()) {
                    if (drawable.y == me.y && drawable instanceof Zombie)  {
                        IcedPea icedPea = new IcedPea(me, gameState);
                        gameState.addDrawables((icedPea));
                    }
                }

            }

        };

        timer.schedule(Task, 0L, 4000L);
    }
}
