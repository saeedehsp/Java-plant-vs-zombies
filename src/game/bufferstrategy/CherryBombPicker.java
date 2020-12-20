package game.bufferstrategy;

/**
 * A class for the picker menus which are places in the top left of the GameFrame
 */
class CherryBombPicker extends PlantsPicker {
    /**
     * CherryBombPicker constructor
     * @param x
     * @param y
     * @param state
     */

    CherryBombPicker(int x, int y, GameState state) {
        super(x, y, "CherryBombPicker.png", "CherryBomb.png", state);
    }

    /**
     * overridden method of plantPicker class to set the sun which a plant should collect before being unlocked
     * @return
     */
    @Override
    int getValue() {
        return 150;
    }

    /**
     * overridden method of plantPicker class to do works after being clicked
     * initializing a cherryBomb
     */

    @Override
    void onClick() {
        super.onClick();
        if (gameState.money >= getValue())
            gameState.selectedItem = new CherryBomb(0, 0, gameState);
    }
}
