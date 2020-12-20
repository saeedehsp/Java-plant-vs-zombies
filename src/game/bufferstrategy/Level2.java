package game.bufferstrategy;

/**
 * specifing Properties of the level2
 */
class Level2 extends Level {
    /**
     * level2 constructor
     * @param state
     */

    Level2(GameState state) {
        super(state);
    }

    /**
     *  running the level
     */
    @Override
    void init() {
        int grassRows = 3;

        int Delays = 0;
        makeRandomZombiesOfType(ZombieType.Regular, 10, grassRows, Delays);

        super.init();

        drawGrasses(grassRows);

        state.addDrawables(setDefaultPicker(new PeaShooterPicker(60, 10, state)));
        state.addDrawables(new SunFlowerPicker(112, 10, state));
        state.addDrawables(new IcedPeaShooterPicker(164, 10, state));

    }
}