package animations;

import biuoop.DrawSurface;
import game.HighScoresTable;


/**
 * This class makes the performance of the high scores table.
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable highScores;


    /**
     * The constructor of HighScoresAnimation.
     *
     * @param scores the high scores table to perform.
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.highScores = scores;
    }


    /**
     * This method is for draws the high scores table.
     *
     * @param d  DrawSurface parameter
     * @param dt the dt parameter of speed
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText(50, d.getHeight() / 4, "player name", 32);
        d.drawText(500, d.getHeight() / 4, "score", 32);
        d.drawLine(10, (d.getHeight() / 4 + 20), d.getWidth() - 40, (d.getHeight() / 4 + 20));
        d.drawText(150, d.getHeight() - 100, "paused -- press space to continue", 32);
        int j = 200;
        for (int i = 0; i < this.highScores.getHighScores().size(); i++) {
            Integer score = this.highScores.getHighScores().get(i).getScore();
            d.drawText(50, j, this.highScores.getHighScores().get(i).getName(), 30);
            d.drawText(500, j, score.toString(), 30);
            j += 35;
        }
    }

    /**
     * This method is for stops the specific screen.
     *
     * @return True or False- stop or not
     */
    public boolean shouldStop() {
        return false;
    }

}
