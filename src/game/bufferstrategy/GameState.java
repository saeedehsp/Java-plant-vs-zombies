
package game.bufferstrategy;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.awt.Cursor;

/**
 * This class holds the state of the game and all of its elements.
 * This class also handles user inputs, which affect the game state.
 *
 * @author Masih Yeganeh
 */
public class GameState {

    private enum Direction{
        Up,
        Down,
        Left,
        Right
    }

    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;
    public int money = 0;
    int states = 1;
    Cursor cursor = null;
    Plant selectedItem = null;
    private  ArrayList<Drawable> drawables = new ArrayList<Drawable>();
    ArrayList<Selectable> selectables = new ArrayList<Selectable>();
    int selectedItemValue = 0;
    Boolean gameOver = false;
    int level = 1;
    String massage = "";
    int killedZombie = 0;
    Level currentLevel;
    private Selectable pointedSelectable;
    PlantsPicker pointedPicker;
    boolean pointingToPicker = true;
    boolean digISOn = false;


    /**
     * Gamestate Constructor
     */
    public GameState() {
        //
        // Initialize the game state and all elements ...
        //
        keyHandler = new KeyHandler();
        mouseHandler = new MouseHandler();
    }

    /**
     * The method which updates the game state.
     */
    void update() {
        for (Drawable drawable : getDrawables()) {
            if (drawable instanceof GameObject) {
                GameObject gameObject = (GameObject) drawable;
                gameObject.update();
                if (gameObject.isDeletable()) {
                    gameObject.deleteObject();
                    deleteDrawable(drawable);
                }
            }
        }

        for (Selectable selectable : selectables) {
            if (!selectable.isEmpty()) {
                GameObject gameObject = selectable.currentPlant;
                gameObject.update();
                if (gameObject.isDeletable()) {
                    gameObject.deleteObject();
                    selectable.dig();
                }
            }
        }
    }


    KeyListener getKeyListener() {
        return keyHandler;
    }

    MouseListener getMouseListener() {
        return mouseHandler;
    }

    MouseMotionListener getMouseMotionListener() {
        return mouseHandler;
    }


    /**
     * The keyboard handler.
     */
    private class KeyHandler implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_KP_RIGHT:
                    moveSelector(Direction.Right);
                    break;

                case KeyEvent.VK_UP:
                case KeyEvent.VK_KP_UP:
                    moveSelector(Direction.Up);
                    break;

                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_KP_LEFT:
                    moveSelector(Direction.Left);
                    break;

                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_KP_DOWN:
                    moveSelector(Direction.Down);
                    break;

                case KeyEvent.VK_ENTER:
                    if (pointingToPicker) {
                        pointedPicker.onClick();
                        pointingToPicker = false;
                        if (pointedSelectable == null) {
                            for (Selectable selectable : selectables) {
                                if (selectable.isPlantable()) {
                                    pointedSelectable = selectable;
                                    pointedSelectable.setPointing(true);
                                    break;
                                }
                            }
                        }
                    } else {
                        if (pointedSelectable.isEmpty()) {
                            pointedSelectable.plant();
                            pointingToPicker = true;
                        }
                    }
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }

    private void moveSelector(Direction direction) {
        if (pointingToPicker)
            moveSelectorInPickers(direction);
        else
            moveSelectorInSelectables(direction);
    }

    private void moveSelectorInPickers(Direction direction) {
        PlantsPicker previouslyPointedPicker = pointedPicker;
        PlantsPicker currentlyPointingPicker = null;
        int minimumDistance = Integer.MAX_VALUE;
        for (Drawable drawable : getDrawables()) {
            if (drawable instanceof PlantsPicker) {
                if (
                        (
                                (direction == Direction.Down && drawable.y > previouslyPointedPicker.y) ||
                                (direction == Direction.Up && drawable.y < previouslyPointedPicker.y)
                        ) &&
                        drawable.x == previouslyPointedPicker.x
                ) {
                    int distance = Math.abs(drawable.y - previouslyPointedPicker.y);
                    if (distance < minimumDistance) {
                        minimumDistance = distance;
                        currentlyPointingPicker = (PlantsPicker) drawable;
                    }
                } else if (
                        (
                                (direction == Direction.Right && drawable.x > previouslyPointedPicker.x) ||
                                (direction == Direction.Left && drawable.x < previouslyPointedPicker.x)
                        ) &&
                                drawable.y == previouslyPointedPicker.y
                ) {
                    int distance = Math.abs(drawable.x - previouslyPointedPicker.x);
                    if (distance < minimumDistance) {
                        minimumDistance = distance;
                        currentlyPointingPicker = (PlantsPicker) drawable;
                    }
                }
            }
        }
        if (currentlyPointingPicker != null) {
            previouslyPointedPicker.setPointing(false);
            currentlyPointingPicker.setPointing(true);
            pointedPicker = currentlyPointingPicker;
        }
    }

    private void moveSelectorInSelectables(Direction direction) {
        Selectable previouslyPointedSelectable = pointedSelectable;
        Selectable currentlyPointingSelectable = null;
        int minimumDistance = Integer.MAX_VALUE;
        for (Selectable selectable : selectables) {
            if (selectable.isPlantable() && selectable.isEmpty()) {
                if (
                        (
                                (direction == Direction.Down && selectable.y > previouslyPointedSelectable.y) ||
                                (direction == Direction.Up && selectable.y < previouslyPointedSelectable.y)
                        ) &&
                        selectable.x == previouslyPointedSelectable.x
                ) {
                    int distance = Math.abs(selectable.y - previouslyPointedSelectable.y);
                    if (distance < minimumDistance) {
                        minimumDistance = distance;
                        currentlyPointingSelectable = selectable;
                    }
                } else if (
                        (
                                (direction == Direction.Right && selectable.x > previouslyPointedSelectable.x) ||
                                (direction == Direction.Left && selectable.x < previouslyPointedSelectable.x)
                        ) &&
                                selectable.y == previouslyPointedSelectable.y
                ) {
                    int distance = Math.abs(selectable.x - previouslyPointedSelectable.x);
                    if (distance < minimumDistance) {
                        minimumDistance = distance;
                        currentlyPointingSelectable = selectable;
                    }
                }
            }
        }
        if (currentlyPointingSelectable != null) {
            previouslyPointedSelectable.setPointing(false);
            currentlyPointingSelectable.setPointing(true);
            pointedSelectable = currentlyPointingSelectable;
        }
    }

    /**
     * The mouse handler.
     */
    private class MouseHandler implements MouseListener, MouseMotionListener {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }

    }

    /**
     * getter
     * @return
     */
    synchronized ArrayList<Drawable> getDrawables(){
        return new ArrayList<Drawable>(drawables) ;
    }

    /**
     * delete the array
     * @param drawable
     */
    void deleteDrawable(Drawable drawable){
        drawables.remove(drawable);
    }

    /**
     * make the array clear
     */
    void clearDrawables(){
        for (Drawable drawable: drawables) {
            if(drawable instanceof GameObject){
                ((GameObject) drawable).deleteObject();
            }
        }
        drawables.clear();
    }

    /**
     * add objects to the array
     * @param drawable
     */
    void addDrawables(Drawable drawable){
        drawables.add(drawable);
    }

}

