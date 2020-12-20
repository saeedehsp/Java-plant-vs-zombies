package game.bufferstrategy;

/**
 * undoing the  planting work
 */
public class Shovel extends PlantsPicker {
    /**
     * shovel constructor
     * @param x
     * @param y
     * @param state
     */
    Shovel(int x, int y, GameState state) {
        super(x, y, "dig.png", "dig.png", state);
    }

    /**
     * undo by clicking
     */
    @Override
    void onClick() {
        super.onClick();
        gameState.digISOn = true;
    }

}
