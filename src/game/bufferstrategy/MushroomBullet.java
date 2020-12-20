package game.bufferstrategy;

/**
 * a class for creating the bullet of the mushroom
 */
public class MushroomBullet extends GameObject  {
    /**
     * MushroomBullet constructor
     * @param mushroom
     * @param gameState
     */
    MushroomBullet(Mushroom mushroom, GameState gameState) {
    super(mushroom.x + 40, mushroom.y + 35, "tuff.png", 20, 20, gameState, -6, Integer.MAX_VALUE);
}

    /**
     * a override method of Drawable class which set the sate of a Drawable object
     * @return
     */
    @Override
    int getStateToVisible() {
        return 2;
    }

    /**
     * a override method of GameObject class which controls the damage of this bullet and its movement
     */
    @Override
    void update() {
        super.update();
        GameObject gameObject = getCollidedZombie();
        if (gameObject != null && gameObject instanceof Zombie) {
            gameObject.hurt(50);
            life = 0;
        }
    }
}
