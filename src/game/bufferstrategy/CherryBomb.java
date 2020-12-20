package game.bufferstrategy;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A class of cherry bomb plant which destroys any near zombie
 */
class CherryBomb extends Plant {

    private int explosionradious = 100;

    /**
     * a override method of Drawable class which set the state of a Drawable object
     * @return
     */
    @Override
    int getStateToVisible() {
        return 2;
    }

    /**
     * cherryBomb constructor
     * @param x
     * @param y
     * @param state
     */

    CherryBomb(int x, int y, GameState state) {
        super(x, y, "cherry.gif", 100, 100, state, 0, 100);
    }

    /**
     * overridden method of gameObject which draws the plant in the specific place in ground
     */
    @Override
    void planted() {
        super.planted();
        TimerTask Task = new TimerTask() {
            @Override
            public void run() {

                for (Drawable drawables : gameState.getDrawables()) {
                    if (drawables instanceof Zombie) {
                        int distance = (int) Math.sqrt(Math.pow((drawables.x - x), 2) + Math.pow((drawables.y - y), 2));
                        if (distance <= explosionradious) {
                            ((Zombie) drawables).hurt(Integer.MAX_VALUE);
                        }
                    }
                }

                life = 0;
            }

        };

        Timer timer = new Timer();
        timer.schedule(Task, 2000);
    }
}
