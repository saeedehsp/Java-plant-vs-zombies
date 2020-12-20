package game.bufferstrategy;

/**
 * A class for making the BasketBall of the Catapult zombie
 */
class BasketBall extends GameObject {
    private int originY;
    private int shootStart;
    private int shootEnd;

    /**
     * BasketBall constructor
     * @param catapultBasketBallZombie
     * @param gameState
     * @param y
     * @param startX
     * @param endX
     */
    BasketBall(CatapultBasketBallZombie catapultBasketBallZombie, GameState gameState, int y, int startX, int endX) {
        super(catapultBasketBallZombie.x, catapultBasketBallZombie.y, "ball.png", 20, 20, gameState, 6, Integer.MAX_VALUE);
        originY = y;
        shootStart = startX;
        shootEnd = endX;
    }

    /**
     * a overridden method of the
     */
    @Override
    void move() {
        super.move();
        if (x > shootEnd)
            y = (int) (originY + (0.005 * (x-shootStart) * (x-shootEnd)));
        else if (x <= shootEnd) {
            GameObject collidedPlant = getCollidedPlant();
            if (collidedPlant != null) {
                collidedPlant.hurt(Integer.MAX_VALUE);
                life = 0;
            }
            life = 0;
        }
    }
}
