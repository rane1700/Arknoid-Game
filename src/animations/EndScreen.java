package animations;

import biuoop.DrawSurface;
import indicators.Counter;

import java.awt.Color;

/**
 * This class is for the end screen- when the game is ends.
 */
public class EndScreen implements Animation {
    private Counter score;
    private Counter lives;

    /**
     * The constructor of PauseScreen.
     *
     * @param scoreCounter the counter of the user's score
     * @param livesCounter the counter of the user's lives
     */
    public EndScreen(Counter scoreCounter, Counter livesCounter) {
        this.score = scoreCounter;
        this.lives = livesCounter;
    }

    /**
     * This method draw the text on the screen accordingly the reason
     * that the game ands and the user's score.
     *
     * @param d  DrawSurface parameter
     * @param dt the dt parameter of speed
     */
    public void doOneFrame(DrawSurface d, double dt) {
        if (this.lives.getValue() == 0) {
            d.setColor(new Color(139, 0, 0));
            d.drawText(10, d.getHeight() / 2, "Game Over. Your Score is " + this.score.getValue(), 32);
        } else {
            d.setColor(new Color(61, 105, 255));
            d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + this.score.getValue(), 32);
        }

    }

    /**
     * This method is for stops the .
     *
     * @return True or False- stop pr not
     */
    public boolean shouldStop() {
        return false;
    }
}
