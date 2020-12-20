package game.bufferstrategy;

/**
 *  A class for the picker menus which are places in the top left of the GameFrame
 */
class PeaShooterPicker extends PlantsPicker {

    /**
     * PeaShooterPicker constructor
     * @param x
     * @param y
     * @param state
     */
    PeaShooterPicker(int x, int y, GameState state) {
        super(x, y, "peashooterpicker.jpg", "Peashooter_HD.png", state);
    }
    /**
     * overridden method of plantPicker class to set the sun which a plant should collect before being unlocked
     * @return
     */
    @Override
    int getValue() {
        return 100;
    }

    /**
     * overridden method of plantPicker class to do works after being clicked
     * initializing a peashooter
     */
    @Override
    void onClick() {
        super.onClick();
        if (gameState.money >= getValue())
            gameState.selectedItem = new PeaShooter(0, 0, gameState);
    }
}