package game.bufferstrategy;
/**
 * A class which creates the Peas which are shooted by PeaSHooter and  it is a subclass of GameObject Class
 */
class Pea extends GameObject {
    /**
     * Pea constructor
     * @param peaShooter
     * @param gameState
     */
    Pea(PeaShooter peaShooter, GameState gameState) {
        super(peaShooter.x + 40, peaShooter.y + 10, "Pea.png", 20, 20, gameState, -6, Integer.MAX_VALUE);
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





