package game.bufferstrategy;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * a class to create suns
 */
class Sun extends Drawable {

    private int destination;

    /**
     * sun states
     */
    private enum SunState {
        falling,
        standing
    }

    SunState state = SunState.falling;
    /**
     * a override method of Drawable class which set the sate of a Drawable object
     * @return
     */
    @Override
    int getStateToVisible() {
        return 2;
    }

    /**
     * sun constructor
     * @param state
     * @param selectable
     */
    Sun(GameState state, Selectable selectable) {
        super(selectable.x, -3, "Sun_pvZ2.png", 70, 70, state);
        this.destination = selectable.y;
    }

    /**
     * sun constructor
     * @param sunFlower
     * @param gameState
     */
    Sun(SunFlower sunFlower, GameState gameState) {
        super(sunFlower.x + 50, sunFlower.y, "Sun_pvZ2.png", 70, 70, gameState);
        this.destination = y;
    }

    /**
     * drawing the suns
     * @param g2d
     */
    @Override
    public void draw(Graphics2D g2d) {
        try {
            super.draw(g2d);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (y < destination)
            y += 1;
        else if (state == SunState.falling) {
            state = SunState.standing;
            Timer selfDestructionTimer = new Timer();
            TimerTask selfDestrutionTask = new TimerTask() {
                @Override
                public void run() {
                    selfDestruction();
                }
            };

            selfDestructionTimer.schedule(selfDestrutionTask, 5000L);
        }
    }

    /**
     * when clicking on the suns , you may collect sun power for the plants
     */
    @Override
    void onClick() {
        super.onClick();
        gameState.money += 25;
    }
}


