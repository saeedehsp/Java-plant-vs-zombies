package game.bufferstrategy;

/**
 * a kind of a zombie which wears a cone as hat
 */
public class BucketHeadZombie extends Zombie{
    /**
     * a override method of Drawable class which set the sate of a Drawable object
     * @return
     */

    @Override

    int getStateToVisible() {
        return 2;
    }

    /**
     * BucketHeadZombie constructor
     * @param x
     * @param y
     * @param state
     */
    BucketHeadZombie(int x, int y, GameState state) {
        super(x, y, state, "conehead.gif", "coneheadicy.png");
    }
}
