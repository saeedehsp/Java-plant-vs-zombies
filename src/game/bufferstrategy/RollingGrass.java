package game.bufferstrategy;

import java.awt.*;

/**
 * A class which is extended from Drawable which shows the roll of grass
 */
class RollingGrass extends Drawable {
    /**
     * 2 RollingGrass states
     */
    enum RollingGrassState {
        Initial,
        Rolling
    }

    RollingGrassState state = RollingGrassState.Initial;

    /**
     * RollingGrass Constructor
     * @param x
     * @param y
     * @param state
     */

    RollingGrass(int x, int y, GameState state) {
        super(x, y, "roll.jpg", 35, 100, state);
    }

    /**
     * overriden method of Drawable class which draw the RollingGrass graphically rolling step by step
     * @param g2d
     */

    @Override
    public void draw(Graphics2D g2d) {
        try {
            super.draw(g2d);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (state == RollingGrassState.Rolling)
            x += 6;

        for (Selectable selectable : gameState.selectables) {
            if (
                    (
                            (y >= selectable.y && y <= selectable.y + 100) ||
                            (y + height >= selectable.y && (y + height <= 100 + selectable.y))
                    )
                    && this.x >= selectable.x
            ) {
                selectable.setPlantable();
            }
        }

        if (x > 1100)
            selfDestruction();
    }
}

