package game.bufferstrategy;

/**
 * specifing Properties of the level1
 */
class Level1 extends Level {
    /**
     * level1 constructor
     * @param state
     */
    Level1(GameState state) {

        super(state);
    }

    /**
     *  running the level
     */
    @Override
    void init() {
        int grassRows = 1;

        int Delays = 0;
        makeRandomZombiesOfType(ZombieType.Regular, 5, grassRows, Delays);

        super.init();

        drawGrasses(grassRows);

        state.addDrawables(setDefaultPicker(new PeaShooterPicker(60, 10, state)));
        state.addDrawables(new SunFlowerPicker(112, 10, state));
    }
}
