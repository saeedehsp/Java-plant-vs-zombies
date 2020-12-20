package game.bufferstrategy;

/**
 * creating the button of the menu in the gameFrame
 */

class MenuButton extends Drawable {
    /**
     * menuButton constructor
     * @param x
     * @param y
     * @param file
     * @param width
     * @param height
     * @param gameState
     */

    MenuButton(int x, int y, String file, int width, int height, GameState gameState) {
        super(x, y, file, width, height, gameState);
    }

    /**
     * opens the select menu by being clicked
     */
    @Override
    void onClick() {
        super.onClick();
        new SelectMenu();
    }
}
