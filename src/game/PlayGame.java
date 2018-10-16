package game;

import animations.HighScoresAnimation;
import animations.KeyPressStoppableAnimation;
import animations.GameFlow;
import animations.AnimationRunner;
import animations.MenuAnimation;
import biuoop.KeyboardSensor;
import levels.LevelSet;
import levels.LevelInformation;
import levels.ArrangeLevelsSets;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.List;


/**
 * Class to gather all necessary information from text files and turn them into a game.
 */
public class PlayGame {
    private String pathToLevelSet;

    /**
     * Constructor.
     * @param pathParm path to level-set
     */
    public PlayGame(String pathParm) {
        this.pathToLevelSet =  pathParm;
    }

    /**
     * generate a game through the different readers.
     */
    public void startGame() {
        List<LevelSet> listOfSets;
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(pathToLevelSet);
        listOfSets = new ArrangeLevelsSets().fromReader(new InputStreamReader(inputStream));
        final AnimationRunner runner = new AnimationRunner();
        final KeyboardSensor keyborad = runner.getGui().getKeyboardSensor();
        final File file = new File("highscores.txt");
        HighScoresTable scores;
        if (file.exists()) {
            scores = HighScoresTable.loadFromFile(file);
        } else {
            scores = new HighScoresTable(5);
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final HighScoresTable scoresTable = scores;
        Menu<Task<Void>> subMenu = new MenuAnimation<>("Level-Sets", keyborad, runner);
        //Add level sets to sub menu
        for (int i = 0; i < listOfSets.size(); i++) {
            final List<LevelInformation> currentLevelList = listOfSets.get(i).getLevelsForSet();
            subMenu.addSelection(listOfSets.get(i).getSetSymbol(), listOfSets.get(i).getSetName(),
                    new Task<Void>() {
                        @Override
                        /**
                         * This method is for runs the task.
                         */
                        public Void run() {
                            GameFlow flow = new GameFlow(runner, keyborad, currentLevelList, scoresTable, file);
                            flow.runLevels(currentLevelList);
                            return null;
                        }
                    });
        }
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Arknoid", keyborad, runner);
        //Start game option
        menu.addSubMenu("s", "Start Game", subMenu);
        menu.addSelection("h", "High Scores", new Task<Void>() {
            @Override
            /**
             * This method is for runs the task.
             */
            public Void run() {
                runner.run(new KeyPressStoppableAnimation(keyborad, KeyboardSensor.SPACE_KEY,
                        new HighScoresAnimation(scoresTable)));
                return null;
            }
        });
        //Quit game option
        menu.addSelection("q", "Quit", new Task<Void>() {
            @Override
            /**
             * This method is for runs the task.
             */
            public Void run() {
                System.exit(0);
                return null;
            }
        });
        while (true) {
            runner.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
            menu.setStop(false);
        }
    }
}
