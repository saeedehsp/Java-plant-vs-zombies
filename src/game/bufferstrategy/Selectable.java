package game.bufferstrategy;

import java.awt.*;

/**
 * a class which shows that what part of the ground is occupied by zombies or plants
 */
class Selectable {
    int x;
    int y;
    private boolean plantable = false;
    private boolean isPointing;

    Plant currentPlant = null;
    GameState gameState;

    /**
     * Selectable constructor
     * @param x
     * @param y
     * @param gameState
     */
    Selectable(int x, int y, GameState gameState) {
        this.gameState = gameState;
        this.x = x;
        this.y = y;
    }

    /**
     * check whether is empty or not
     * @return
     */
    boolean isEmpty() {
        return currentPlant == null;
    }

    /**
     * plant on the ground
     * @return
     */
    boolean plant() {
        if (!plantable || !isEmpty())
            return false;
        if (gameState.selectedItem != null && gameState.states >= gameState.selectedItem.getStateToVisible()) {
            currentPlant = gameState.selectedItem;
            currentPlant.setLocation(x, y);
            currentPlant.planted();
            gameState.selectedItem = null;
            gameState.cursor = null;
            gameState.money -= gameState.selectedItemValue;
            gameState.selectedItemValue = 0;
            return true;
        }
        return false;
    }

    /**
     * draw the object graphically
     * @param g2d
     * @param state
     * @throws InterruptedException
     */
    void draw(Graphics2D g2d, GameState state) throws InterruptedException {
        if (!isEmpty()) {
            if (currentPlant.getStateToVisible() <= state.states)
                currentPlant.draw(g2d);
        }
        else if (isPointing && !state.pointingToPicker) {
            Stroke previousStroke = g2d.getStroke();
            Paint previousPaint = g2d.getPaint();

            g2d.setStroke(new BasicStroke(2));
            g2d.setPaint(Color.red);
            g2d.drawRect(x, y, 70, 100);

            g2d.setStroke(previousStroke);
            g2d.setPaint(previousPaint);
        }
    }

    /**
     * undo the planting work
     */

    void dig() {
        if (!isEmpty()) {
            currentPlant.deleteObject();
            currentPlant = null;
        }
    }
    void setPlantable() {
        plantable = true;
    }
    void setUnPlantable() {
        plantable = false;
    }

    boolean isPlantable() {
        return plantable;
    }

    void setPointing(boolean pointing) {
        this.isPointing = pointing;
    }
}
