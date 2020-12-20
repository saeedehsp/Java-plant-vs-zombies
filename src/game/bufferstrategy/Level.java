package game.bufferstrategy;

import java.util.*;

/**
 * a method which makes everything a level needs in a game
 */
class Level {
    private int[] zombieEntrance = {
            250, 145, 355, 40, 460
    };
    private HashMap<Integer, Zombie> generateZombies = new HashMap<Integer, Zombie>();
    private ArrayList<TimerTask> zombiesTimerTasks = new ArrayList<TimerTask>();
    private Timer zombiesTimer = new Timer();
    GameState state;

    /**
     * level constructor
     * @param state
     */
    Level(GameState state) {
        this.state = state;
    }

    /**
     * running the level
     */
    void init() {
        for (final Integer delay: generateZombies.keySet()) {
            TimerTask spawnTimerTask = new TimerTask() {
                @Override
                public void run() {
                    state.addDrawables(generateZombies.get(delay));
                }
            };

            zombiesTimerTasks.add(spawnTimerTask);
            zombiesTimer.schedule(spawnTimerTask, delay + 15000 /* Level initial delay */);
        }
    }

    /**
     * when a level is finished , it should be go to the next level and start again
     */
    private void levelCompleted() {
        finishLevel();
        state.killedZombie = 0;
        state.money = 0;
        state.clearDrawables();
        for (Selectable selectable : state.selectables) {
            selectable.dig();
            selectable.setUnPlantable();
        }

        state.level++;
        state.massage = "مرحله بعد";
              Timer nextLevelTimer = new Timer();
        TimerTask nextLevelMessage = new TimerTask() {
            @Override
            public void run() {
                state.massage = "";
            }
        };
        nextLevelTimer.schedule(nextLevelMessage, 2000);


        switch (state.level) {
            case 2:
                state.currentLevel = new Level2(state);
                break;
            case 3:
                state.currentLevel = new Level3(state);
                break;
            case 4:
                state.currentLevel = new Level4(state);
                break;
            case 5:
                state.currentLevel = new Level5(state);
                break;
            default:
                state.gameOver = true;
                return;
        }
        state.currentLevel.init();
    }

    /**
     * rolls the grasses convenient  for each level
     * @param grassRows
     */
    void drawGrasses(int grassRows) {
        Timer drawGrassTimer = new Timer();

        final Grass grassRow1 = new Grass(385, 50-5, state);
        final RollingGrass rollingGrass1 = new RollingGrass(385, 50, state);
        final Grass grassRow2 = new Grass(380, 150-5, state);
        final RollingGrass rollingGrass2 = new RollingGrass(380, 150, state);
        final Grass grassRow3 = new Grass(380, 250-5, state);
        final RollingGrass rollingGrass3 = new RollingGrass(380, 250, state);
        final Grass grassRow4 = new Grass(380, 350-5, state);
        final RollingGrass rollingGrass4 = new RollingGrass(380, 350, state);
        final Grass grassRow5 = new Grass(375, 450-5, state);
        final RollingGrass rollingGrass5 = new RollingGrass(375, 450, state);

        if (grassRows > 3) {
            state.addDrawables(grassRow1);
            state.addDrawables(rollingGrass1);
        }
        if (grassRows > 1) {
            state.addDrawables(grassRow2);
            state.addDrawables(rollingGrass2);
        }
        state.addDrawables(grassRow3);
        state.addDrawables(rollingGrass3);
        if (grassRows > 1) {
            state.addDrawables(grassRow4);
            state.addDrawables(rollingGrass4);

            state.addDrawables(new LawnMover(270, 150, state));
            state.addDrawables(new LawnMover(270, 350, state));
        }
        if (grassRows > 3) {
            state.addDrawables(grassRow5);
            state.addDrawables(rollingGrass5);

            state.addDrawables(new LawnMover(270, 50, state));
            state.addDrawables(new LawnMover(270, 450, state));
        }

        grassRow3.state = Grass.GrassState.Growing;
        rollingGrass3.state = RollingGrass.RollingGrassState.Rolling;

        state.addDrawables(new LawnMover(270, 250, state));

        if (grassRows > 1) {
            drawGrassTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    grassRow2.state = Grass.GrassState.Growing;
                    rollingGrass2.state = RollingGrass.RollingGrassState.Rolling;
                    grassRow4.state = Grass.GrassState.Growing;
                    rollingGrass4.state = RollingGrass.RollingGrassState.Rolling;
                }
            }, 2000L);
        }

        if (grassRows > 3) {
            drawGrassTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    grassRow1.state = Grass.GrassState.Growing;
                    rollingGrass1.state = RollingGrass.RollingGrassState.Rolling;
                    grassRow5.state = Grass.GrassState.Growing;
                    rollingGrass5.state = RollingGrass.RollingGrassState.Rolling;
                }
            }, 3000L);
        }
    }

    /**
     * check whether a level is compeleted or not
     */
    void update() {
        if (state.killedZombie >= generateZombies.size()) {
            levelCompleted();
        }
    }

    /**
     * finishes a level
     */
    private void finishLevel(){
        for (TimerTask timerTask : zombiesTimerTasks) {
            timerTask.cancel();
        }
        zombiesTimer.purge();
        zombiesTimer.cancel();
    }

    enum ZombieType {
        Regular,
        BucketHead,
        PoleVaulting,
        CatapultBasketBall
    }

    /**
     * make random zombies to attack the house
     * @param zombieType
     * @param count
     * @param grassRows
     * @param Delays
     * @return
     */
    int makeRandomZombiesOfType(ZombieType zombieType, int count, int grassRows, int Delays) {
        final Random rand = new Random();

        for (int i = 0; i < count; i++) {
            int randomDelay = rand.nextInt(5000);
            randomDelay += 8000;

            Delays += randomDelay;

            Zombie zombie;

            switch (zombieType) {
                case Regular:
                    zombie = new RegularZombie(1100, zombieEntrance[rand.nextInt(grassRows)], state);
                    break;

                case BucketHead:
                    zombie = new BucketHeadZombie(1100, zombieEntrance[rand.nextInt(grassRows)], state);
                    break;

                case PoleVaulting:
                    zombie = new PoleVaultingZombie(1100, zombieEntrance[rand.nextInt(grassRows)], state);
                    break;

                case CatapultBasketBall:
                    zombie = new CatapultBasketBallZombie(1100, zombieEntrance[rand.nextInt(grassRows)], state);
                    break;
                default:
                    continue;
            }

            generateZombies.put(Delays, zombie);
        }

        return Delays;
    }

    /**
     * set a default picker for the keyboard
     * @param plantsPicker
     * @return
     */

    PlantsPicker setDefaultPicker(PlantsPicker plantsPicker) {
        state.pointedPicker = plantsPicker;
        plantsPicker.setPointing(true);
        return plantsPicker;
    }

}
