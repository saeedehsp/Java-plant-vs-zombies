package game.bufferstrategy;

/**
 * specifing Properties of the level3
 */
class Level3 extends Level {

    /**
     * level3 constructor
     * @param state
     */
    Level3(GameState state) {
        super(state);
    }

    /**
     *  running the level
     */
    @Override
    void init() {
        int grassRows = 5;

        int Delays = 0;
        Delays = makeRandomZombiesOfType(ZombieType.Regular, 5, grassRows, Delays);
        makeRandomZombiesOfType(ZombieType.BucketHead, 3, grassRows, Delays);

        super.init();

        drawGrasses(grassRows);

        state.addDrawables(setDefaultPicker(new PeaShooterPicker(60, 10, state)));
        state.addDrawables(new SunFlowerPicker(112, 10, state));
        state.addDrawables(new IcedPeaShooterPicker(164, 10, state));
        state.addDrawables(new MushroomPicker(216, 10, state));

    }
}

