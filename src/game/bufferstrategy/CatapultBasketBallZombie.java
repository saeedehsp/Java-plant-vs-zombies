package game.bufferstrategy;

import java.util.Timer;
import java.util.TimerTask;

/**
 * a kind of a zombie that throw basketballs to the plants and after finishing his balls , he starts to move forward
 */
class CatapultBasketBallZombie extends Zombie {


    ZombieState state = ZombieState.Walking;
    private int basketball;
    Timer timer = new Timer();

    /**
     * CatapultBasketBallZombie constructor
     * @param x
     * @param y
     * @param gameState
     */
    CatapultBasketBallZombie(int x, int y, GameState gameState) {
        super(x, y, gameState, "Catapult-Zombie.gif", "Catapult-Zombie.gif");
    }

    /**
     * overriden method of Zombie class to set the state
     * @param state
     */
    @Override
    void setState(ZombieState state) {
        this.state = state;
        super.setState(state);
    }

    /**
     * overriden method of Zombie class to delete zombie
     */

    @Override
    void deleteObject() {
        timer.cancel();
        super.deleteObject();
    }

    /**
     * overriden method of Zombie class to move the zombie and toss the balls
     */
    @Override
    void update() {
        super.update();
        if (state == ZombieState.Walking) {

            move();

            if (x >= 800 && x <= 810) {
                setState(ZombieState.Shooting);
                final CatapultBasketBallZombie me = this;
                TimerTask ballTask = new TimerTask() {
                    @Override
                    public void run() {
                        if (basketball >= 10) {
                            setState(ZombieState.Walking);
                            this.cancel();
                            return;
                        }

                        for (Selectable selectable : gameState.selectables) {
                            if (selectable.y == me.y && !selectable.isEmpty()) {
                                gameState.addDrawables(new BasketBall(me, gameState, y, x, selectable.x));
                                basketball++;
                                return;
                            }
                        }
                        setState(ZombieState.Walking);
                    }
                };

                timer.schedule(ballTask, 0, 1000);
            }
        }
    }
}