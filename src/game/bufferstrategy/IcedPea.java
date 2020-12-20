package game.bufferstrategy;

/**
 * A class which creates the IcedPeas which are shooted by IcePeaSHooter and it is a subclass of GameObject Class
 */
class IcedPea extends GameObject {
    /**
     * IcedPea constructor
     * @param icedPeaShooter
     * @param gameState
     */
    IcedPea(IcedPeaShooter icedPeaShooter, GameState gameState) {
        super(icedPeaShooter.x + 40, icedPeaShooter.y + 10, "Giant_Pea2.png", 20, 20, gameState, -6, Integer.MAX_VALUE);
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

                ((Zombie) gameObject).getIcy();
                gameObject.hurt(25);
                life = 0;

        }
    }
}
