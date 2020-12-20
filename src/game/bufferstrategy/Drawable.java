package game.bufferstrategy;

import java.awt.*;

/**
 * A class which every drawable  object is made from it
 */
public class Drawable {

    Image img;
    int x;
    int y;
    int width;
    int height;
    GameState gameState;

    /**
     *   method which set the state of a Drawable object
     * @return
     */
    int getStateToVisible() {
        return 1;
    }

    /**
     *  Drawable constructor
     * @param x
     * @param y
     * @param file
     * @param width
     * @param height
     * @param gameState
     */
    public Drawable(int x, int y, String file, int width, int height, GameState gameState) {
        this.x = x;
        this.y = y;
        this.gameState = gameState;
        this.height = height;
        this.width = width;
        img = Main.loadImage(file);
    }

    /**
     * method which draws with graphic
     * @param g2d
     * @throws InterruptedException
     */
    public void draw(Graphics2D g2d) throws InterruptedException {
        g2d.drawImage(img, x, y, width, height, null);
    }

    /**
     *  a method  to do works after being clicked
     */
    void onClick() {
    }

    /**
     * setLocation of the drawable object
     * @param x
     * @param y
     */
    void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * delete the object
     */
    void selfDestruction() {
        gameState.deleteDrawable(this);
    }


    //TODO: placing ImageIcon


}
