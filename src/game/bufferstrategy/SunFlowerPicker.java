package game.bufferstrategy;

/**
 *  A class for the picker menus which are places in the top left of the GameFrame
 */
class SunFlowerPicker extends PlantsPicker {
    /**
     * SunFlowerPicker constructor
     * @param x
     * @param y
     * @param state
     */
    SunFlowerPicker(int x, int y, GameState state) {
        super(x, y, "sunflowerpicker.png", "Sunflower.png", state);
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
     * initializing a sunFlower
     */
    @Override
    void onClick() {
        super.onClick();
        if (gameState.money >= getValue())
            gameState.selectedItem = new SunFlower(0, 0, gameState);
    }
}


