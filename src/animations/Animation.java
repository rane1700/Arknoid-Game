package animations;

import biuoop.DrawSurface;

/**
 * Interface of animation.
 */
public interface Animation {
    /**
     * draw the texts that should be on the screen.
     *
     * @param d  DrawSurface parameter
     * @param dt the dt parameter of speed
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * This method is for stops the specific screen.
     *
     * @return True or False- stop or not
     */
    boolean shouldStop();
}
