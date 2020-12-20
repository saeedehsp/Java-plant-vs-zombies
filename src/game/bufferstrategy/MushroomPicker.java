package game.bufferstrategy;

/**
 *  A class for the picker menus which are places in the top left of the GameFrame
 */
public class MushroomPicker extends PlantsPicker {
    /**
     *  MushroomPicker constructor
     * @param x
     * @param y
     * @param state
     */
    MushroomPicker(int x, int y, GameState state) {
        super(x, y, "mushroompicker .png", "mushroom.png", state);
    }

    /**
     * overridden method of plantPicker class to set the sun which a plant should collect before being unlocked
     * @return
     */
    @Override
    int getValue() {
        return 0;
    }

    /**
     * overridden method of plantPicker class to do works after being clicked
     * initializing a Mushroom
     */
    @Override
    void onClick() {
        super.onClick();
        if (gameState.money >= getValue())
            gameState.selectedItem = new Mushroom(0, 0, gameState);
    }
}
