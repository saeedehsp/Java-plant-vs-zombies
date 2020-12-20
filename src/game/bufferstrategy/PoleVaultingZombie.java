package game.bufferstrategy;

/**
 * a kind of a zombie which jumps on plants
 */
public class PoleVaultingZombie extends Zombie{
    /**
     * PoleVaultingZombie constructor
     * @param x
     * @param y
     * @param state
     */
    public PoleVaultingZombie(int x, int y, GameState state) {

        super(x, y, state, "PoleVaultingZombie2.png" , "PoleVaultingZombie2icy.png");
    }


}
