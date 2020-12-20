package game.bufferstrategy;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A class which is extended from Drawable class and draws the garss on the ground of the main frame of the game
 */
class Grass extends Drawable {
    /**
     * 2  grass states
     */
    enum GrassState {
        Initial,
        Growing
    }

    GrassState state = GrassState.Initial;

    private int cropWidth = 1;
    private BufferedImage fullImage;

    /**
     * Grass Constuctor
     * @param x
     * @param y
     * @param state
     */

    Grass(int x, int y, GameState state) {
        super(x, y, "gr.png", 0, 110, state);
        fullImage = (BufferedImage) img;
    }

    /**
     * overriden method of Drawable class which draw the Grass graphically growing step by step
     * @param g2d
     */
    @Override
    public void draw(Graphics2D g2d) {
        try {
            super.draw(g2d);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (state == GrassState.Growing) {
            if (cropWidth < 700) {
                img = fullImage.getSubimage(0, 0, cropWidth, height);
                cropWidth += 6;
                width += 6;
            } else if (gameState.states == 1) gameState.states = 2;
        }
    }
}

