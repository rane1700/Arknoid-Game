package indicators;

import biuoop.DrawSurface;
import game.GameLevel;
import game.Sprite;
import java.awt.Color;

/**
 * This method represent the score that the user achieve in the game.
 */


public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * The constructor of ScoreIndicator..
     *
     * @param scoreOfGame counter of the score.
     */
    public ScoreIndicator(Counter scoreOfGame) {
        this.score = scoreOfGame;
    }

    /**
     * This method draw the text about on the screen
     * who much score the user achieve.
     *
     * @param d DrawSurface on gui
     */
    public void drawOn(DrawSurface d) {
        // getting the color of the ball
        d.setColor(Color.BLACK);
        // getting and casting the values of the ball's center point
        d.drawText(390, 15, "Score: " + this.score.getValue(), 15);
    }

    /**
     * empty method.
     * @param dt the dt parameter of speed
     */
    public void timePassed(double dt) {

    }

    /**
     * This method add the text to the screen.
     *
     * @param g GameLevel parameter
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
