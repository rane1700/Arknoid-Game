package animations;

import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import game.GameLevel;
import game.ScoreInfo;
import indicators.Counter;
import levels.LevelInformation;

import java.io.File;
import java.io.IOException;
import java.util.List;

import game.HighScoresTable;

/**
 * This class is in charge of creating the different levels,
 * and moving from one level to the next.
 */
public class GameFlow {
    private List levelsOrder;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboad;
    private Counter livesCounter;
    private Counter scoreCounter;
    private HighScoresTable highScoresTab;
    private File file;

    /**
     * The constructor of GameFlow.
     *
     * @param ar            animation runner object for running each level
     * @param ks            KeyboardSensor object for knowing the user acts
     * @param levelOrder    the list of the order of the levels
     * @param highScoresTab the high scores table object
     * @param file          the file that save the scores table
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, List levelOrder, HighScoresTable highScoresTab, File file) {
        this.animationRunner = ar;
        this.keyboad = ks;
        this.livesCounter = new Counter(7);
        this.scoreCounter = new Counter(0);
        this.levelsOrder = levelOrder;
        this.highScoresTab = highScoresTab;
        this.file = file;
    }

    /**
     * This method runs the levels one by one and the
     * end screen in the end of the game.
     *
     * @param levels list of the levels to run
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo,
                    this.keyboad,
                    this.animationRunner, this.scoreCounter, this.livesCounter);
            level.initialize();
            while (level.getRemainingBlocksNum() > 0 && this.livesCounter.getValue() > 0) {
                level.playOneTurn();
            }
            if (this.livesCounter.getValue() == 0) {
                break;
            }
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.animationRunner.getGui().getKeyboardSensor(),
                KeyboardSensor.SPACE_KEY, new EndScreen(this.scoreCounter, this.livesCounter)));
        if (highScoresTab.getRank(this.scoreCounter.getValue()) <= this.highScoresTab.size()) {
            DialogManager dialog = this.animationRunner.getGui().getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            ScoreInfo newOne = new ScoreInfo(name, this.scoreCounter.getValue());
            this.highScoresTab.add(newOne);
            try {
                this.highScoresTab.save(this.file);
            } catch (IOException e) {
                System.out.print("wrong file!!!");
            }
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.animationRunner.getGui().getKeyboardSensor(),
                KeyboardSensor.SPACE_KEY, (new HighScoresAnimation(this.highScoresTab))));
        return;
    }
}
