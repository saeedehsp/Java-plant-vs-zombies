package game.bufferstrategy;

/**
 * Almost every Object in the game
 */
abstract class GameObject extends Drawable {

    int speed;
    int life;

    /**
     * GameObject constructor
     * @param x
     * @param y
     * @param file
     * @param width
     * @param height
     * @param gameState
     * @param speed
     * @param life
     */

    GameObject(int x, int y, String file, int width, int height, GameState gameState, int speed, int life) {
        super(x, y, file, width, height, gameState);
        this.speed = speed;
        this.life = life;
    }

    /**
     * the speed of every object
     */
    void move() {
        x -= speed;
    }

    /**
     * the change in speed of every object
     * @param speed
     */
    void changeSpeed(int speed) {

        this.speed = speed;
    }

    /**
     * when being shooted by a bullet (pea or icedpea)
     * @param strength
     */

    void hurt(int strength) {
        life -= strength;
        if (life < 0)
            life = 0;
    }

    /**
     * when being destroyed by Cherrybomb
     * @return
     */
    boolean isDeletable() {
        return life == 0;
    }

    /**
     * delete the object
     */
    void deleteObject() {

    }

    /**
     * update the movement of a object
     */
    void update() {
        if (gameState.states > 1)
            move();
    }

    /**
     * plant a object (plants)
     */
    void planted() {

    }
    GameObject getCollidedZombie() {
        for (Drawable drawable : gameState.getDrawables()) {
            if (drawable instanceof Zombie) {
                if (
                        (
                                (x < drawable.x + drawable.width && x >= drawable.x) ||
                                (x + width >= drawable.x && x + width <= drawable.x + drawable.width)
                        ) &&
                        (
                                (y > drawable.y && y <= drawable.y + drawable.height) ||
                                (y + height >= drawable.y && (y + height <= drawable.height + drawable.y))
                        )
                ) {
                    return (GameObject) drawable;
                }
            }
        }
        return null;
    }

    /**
     * come across a plant
     * @return
     */
    GameObject getCollidedPlant() {
        for (Selectable selectable : gameState.selectables) {
            if (
                    !selectable.isEmpty() &&
                    (
                            (x < selectable.x + selectable.currentPlant.width && x > selectable.currentPlant.x) ||
                            (x + width > selectable.currentPlant.x && x + width < selectable.currentPlant.x + selectable.currentPlant.width)
                    ) &&
                    (
                            (y > selectable.currentPlant.y && y < selectable.currentPlant.y + selectable.currentPlant.height) ||
                            (y + height > selectable.currentPlant.y && (y + height < selectable.currentPlant.height + selectable.currentPlant.y))
                    )
                    ) {
                return selectable.currentPlant;
            }
        }
        return null;
    }
}
