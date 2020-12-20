package game.bufferstrategy;


/**
 * A class Wallnut  which stands still to be eated by a zombie
 */
class WalNut extends Plant {

    /**
     * a override method of Drawable class which set the state of a Drawable object
     * @return
     */
    @Override
    int getStateToVisible() {
        return 2;
    }

    /**
     *Walnut constructor
     * @param x
     * @param y
     * @param state
     */

    WalNut(int x, int y, GameState state) {
        super(x, y, "wallnut.gif", 100, 100, state, 0, 300);
    }
}
