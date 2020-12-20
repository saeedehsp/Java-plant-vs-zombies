package game.bufferstrategy;

/**
 * A class which is the subclass of the Zombie and its the weakest zombie
 */
class RegularZombie extends Zombie {
    /**
     * RegularZombie constructor
     * @param x 
     * @param y
     * @param state
     */
    RegularZombie(int x, int y, GameState state) {
        super(x, y, state, "Regular_Zombie.png", "Regular_Zombieicy.png");
    }
}
