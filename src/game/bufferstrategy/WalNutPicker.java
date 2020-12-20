package game.bufferstrategy;

/**
 *  A class for the picker menus which are places in the top left of the GameFrame
 */
class WalNutPicker extends PlantsPicker {
    /**
     * walnut picker constructor
     * @param x
     * @param y
     * @param state
     */
    WalNutPicker(int x, int y, GameState state) {
        super(x, y, "walnutpicker.png", "WalNut.png", state);
    }

    /**
     * overridden method of plantPicker class to set the sun which a plant should collect before being unlocked
     * @return
     */
    @Override
    int getValue() {
        return 50;
    }

    /**
     * overridden method of plantPicker class to do works after being clicked
     * initializing a walnut
     */
    @Override
    void onClick() {
        super.onClick();
        if (gameState.money >= getValue())
            gameState.selectedItem = new WalNut(0, 0, gameState);
    }
}
