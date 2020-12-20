package game.bufferstrategy;

import java.awt.*;

/**
 * A class which is extended from GameObject that controlls the lawnMovers that kill the zombies when they first come near the house
 */
class LawnMover extends GameObject {
    /**
     * LawnMover constructor
     * @param x
     * @param y
     * @param state
     */


    LawnMover(int x, int y, GameState state) {
        super(x, y, "Lawn_Mower.png", 100, 80, state, 0, 700000000);
    }

    /**
     * overriden method of the Drawable class which draws the LawnMover Graphically
     * @param g2d
     * @throws InterruptedException
     */
    @Override
    public void draw(Graphics2D g2d) throws InterruptedException {
        super.draw(g2d);
    }

    /**
     * overriden method of the GameObject class which detects the collision  of lawnMover and zombie
     */
    @Override
    void update() {
        super.update();
        GameObject collidedZombie = getCollidedZombie();
        if (collidedZombie != null && collidedZombie instanceof Zombie){
            collidedZombie.hurt(Integer.MAX_VALUE);
            this.changeSpeed(-2);
        }
    }
}

