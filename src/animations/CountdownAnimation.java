package animations;

import biuoop.DrawSurface;
import game.SpriteCollection;

import java.awt.Color;


/**
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1.
 */
public class CountdownAnimation implements Animation {
    private boolean status;
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private biuoop.Sleeper sleeper;
    private int counter;
    private int frameCounter;

    /**
     * The constructor of PauseScreen.
     *
     * @param numOfSeconds the number of the seconds
     * @param countFrom    the number to count from
     * @param gameScreen   the screen to show the count down on it
     */
    public CountdownAnimation(double numOfSeconds,
                              int countFrom,
                              SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        sleeper = new biuoop.Sleeper();
        this.status = false;
        this.counter = countFrom;
        frameCounter = 1;
    }

    /**
     * This method draw the count down on the screen.
     *
     * @param d  DrawSurface parameter
     * @param dt the dt parameter of speed
     */
    public void doOneFrame(DrawSurface d, double dt) {
        gameScreen.drawAllOn(d);
        frameCounter++;
        d.setColor(Color.RED);
        d.drawText(350, 350, "" + counter, 50);
        if (frameCounter % (60 * this.numOfSeconds / this.countFrom) == 0) {
            counter--;
        } else if (counter == 0) {
            status = true;
        }
    }

    /**
     * This method is for stops the game.
     *
     * @return True or False- stop pr not
     */
    public boolean shouldStop() {
        return status;
    }
}
