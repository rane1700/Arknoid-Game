package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * This class make the stop of specific animation
 * one specific key was pressed.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private String endKey;
    private Animation runAnimation;
    private boolean stop, skip;
    private boolean isAlreadyPressed;

    /**
     * The constructor of KeyPressStoppableAnimation.
     *
     * @param sensor    the keyboard sensor
     * @param key       the key that stop the animation
     * @param animation the animation to stop
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard = sensor;
        this.endKey = key;
        this.runAnimation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
        this.skip = false;
    }

    /**
     * This method checks if the stoppable key was pressed
     * and to the actions (change the values of the stop and
     * skip variables) accordingly the result.
     *
     * @param d  DrawSurface parameter
     * @param dt the dt parameter of speed
     */
    public void doOneFrame(DrawSurface d, double dt) {
        if (this.isAlreadyPressed) {
            if (this.keyboard.isPressed(this.endKey)) {
                this.skip = true;
            }
            this.isAlreadyPressed = false;
        }
        this.runAnimation.doOneFrame(d, dt);
        if (this.keyboard.isPressed(this.endKey)) {
            if (!this.skip) {
                this.stop = true;
            }
        } else {
            this.skip = false;
        }
    }

    /**
     * This method is for stops the specific screen(animation).
     *
     * @return True or False- stop or not
     */
    public boolean shouldStop() {
        return this.stop;

    }
}

