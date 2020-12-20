package game.bufferstrategy;

/**
 * a class for every plant in the game
 */
abstract class Plant extends GameObject {
    /**
     * plant constructor
     * @param x
     * @param y
     * @param file
     * @param width
     * @param height
     * @param gameState
     * @param speed
     * @param life
     */
    Plant(int x, int y, String file, int width, int height, GameState gameState, int speed, int life) {
        super(x, y, file, width, height, gameState, speed, life);
    }

    /**
     * making the movement of the objects
     */
    @Override
    void move() {
        super.move();

        if (x > 1100) {
            this.life = 0;
        }
    }
}
