package indicators;

import biuoop.DrawSurface;
import game.GameLevel;
import game.Sprite;
import java.awt.Color;


/**
 * This class represent the levels that
 * the player has in the game.
 */
public class LivesIndicator implements Sprite {
    private Counter lives;

    /**
     * The constructor of LivesIndicator.
     *
     * @param numberOfLives counter of lives
     */
    public LivesIndicator(Counter numberOfLives) {
        this.lives = numberOfLives;
    }

    /**
     * This method draw the text about on the screen
     * who much lives the user has.
     *
     * @param d DrawSurface on gui
     */
    public void drawOn(DrawSurface d) {
        // getting the color of the ball
        d.setColor(Color.BLACK);
        // getting and casting the values of the ball's center point
        d.drawText(190, 15, "Lives: " + this.lives.getValue(), 15);
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
