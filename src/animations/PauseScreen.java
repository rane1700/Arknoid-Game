package animations;

import biuoop.DrawSurface;


/**
 * This class is the pause screen- when the user want to
 * pause the game.
 */
public class PauseScreen implements Animation {


    /**
     * The constructor of PauseScreen.
     */
    public PauseScreen() {

    }

    /**
     * This method is for draws the pause screen text.
     *
     * @param d  DrawSurface parameter
     * @param dt the dt parameter of speed
     */

    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText(10, d.getHeight() / 2, "press space to continue", 32);
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
