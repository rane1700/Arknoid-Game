package animations;

import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * This class is the animations runner.
 */
public class AnimationRunner {
    // static variable for maintain the game screen height
    private static final int GUI_HEIGHT = 600;
    // static variable for maintain the game screen width
    private static final int GUI_WIDTH = 800;
    private GUI gui;
    private int framesPerSecond;
    private biuoop.Sleeper sleeper;
    private double dt;

    /**
     * The constructor of AnimationRunner.
     */
    public AnimationRunner() {
        framesPerSecond = 60;
        this.gui = new GUI("Arknoid", GUI_WIDTH, GUI_HEIGHT);
        sleeper = new biuoop.Sleeper();
        this.dt = ((double) 1 / (double) 60);
    }

    /**
     * This method returns the GUI.
     *
     * @return gui object
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * This method makes the animations run
     * and the performance on the screen.
     *
     * @param animation that animation to run
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d, this.dt);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
