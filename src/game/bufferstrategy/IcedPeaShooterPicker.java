package game.bufferstrategy;

/**
 *  A class for the picker menus which are places in the top left of the GameFrame
 */
class IcedPeaShooterPicker extends PlantsPicker {

    /**
     * IcedPeaShooterPicker
     * @param x
     * @param y
     * @param state
     */
    IcedPeaShooterPicker(int x, int y, GameState state) {
        super(x, y, "Icedpeashooter2.png", "SnowPea.gif", state);
    }

    /**
     * overridden method of plantPicker class to set the sun which a plant should collect before being unlocked
     * @return
     */
    @Override
    int getValue() {
        return 175;
    }

    /**
     * overridden method of plantPicker class to do works after being clicked
     * initializing a Ice pea shooter
     */
    @Override
    void onClick() {
        super.onClick();
        if (gameState.money >= getValue())
            gameState.selectedItem = new IcedPeaShooter(0, 0, gameState);
    }
}