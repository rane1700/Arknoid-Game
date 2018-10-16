package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.Menu;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;


/**
 * This class is for the menus screens- when the game is begins.
 *
 * @param <T> the task to do
 */
public class MenuAnimation<T> implements Menu<T> {
    private String nameOfMenu;
    private List<String> itemKeys;
    private List<String> itemMessages;
    private List<T> returnValues;
    private List<Menu<T>> listOfSub;
    private List<Boolean> checkForSubMenu;
    private T status;
    private KeyboardSensor keyboard;
    private boolean stop;
    private AnimationRunner ar;

    /**
     * The constructor of MenuAnimation.
     *
     * @param nameOfMenu      the name of the current menu
     * @param keyboard        the keyboard sensor
     * @param animationRunner the AnimationRunner object
     */
    public MenuAnimation(String nameOfMenu, KeyboardSensor keyboard, AnimationRunner animationRunner) {
        this.nameOfMenu = nameOfMenu;
        this.keyboard = keyboard;
        this.returnValues = new ArrayList<T>();
        this.itemKeys = new ArrayList<String>();
        this.itemMessages = new ArrayList<String>();
        this.listOfSub = new ArrayList<Menu<T>>();
        this.checkForSubMenu = new ArrayList<>();
        this.stop = false;
        this.ar = animationRunner;
    }


    /**
     * This method is for draws the menus screens, and for make them work.
     *
     * @param d  DrawSurface
     * @param dt the dt parameter of speed
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(new Color(58, 95, 205));
        d.drawText(320, 300, nameOfMenu, 50);
        int j = 350;
        d.setColor(new Color(100, 149, 237));
        for (int i = 0; i < this.returnValues.size(); i++) {
            d.drawText(320, j, ("(" + this.itemKeys.get(i) + ")"
                    + " " + this.itemMessages.get(i)), 30);
            j += 40;
        }
        d.setColor(new Color(131, 139, 139));
        d.fillRectangle(300, 575, 200, 20);
        d.setColor(Color.black);
        d.drawRectangle(300, 575, 200, 20);
        d.drawCircle(400, 550, 9);
        d.setColor(Color.white);
        d.fillCircle(400, 550, 8);

        int clrIndex = 0;
        Color[] clr = new Color[4];
        clr[0] = (new Color(0, 104, 139));
        clr[1] = (new Color(51, 161, 201));
        clr[2] = (new Color(173, 216, 230));
        clr[3] = (new Color(191, 239, 255));
        for (int i = 20; i <= 170; i += 50) {
            for (int k = 10; k <= 660; k += 130) {
                d.setColor(clr[clrIndex]);
                d.fillRectangle(k, i, 130, 50);
                d.setColor(Color.black);
                d.drawRectangle(k, i, 130, 50);


            }
            clrIndex++;
        }
        for (int i = 0; i < this.returnValues.size(); i++) {
            if (this.keyboard.isPressed(this.itemKeys.get(i))) {
                //This is not the sub menu
                if (!checkForSubMenu.get(i)) {
                    this.status = this.returnValues.get(i);
                } else {
                    Menu<T> sub = this.listOfSub.get(0);
                    ar.run(sub);
                    this.status = sub.getStatus();
                    sub.setStop(false);
                }
                this.stop = true;
                break;
            }
        }

    }

    /**
     * This method is for stops the specific screen.
     *
     * @return True or False- stop or not
     */
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * This method is for add a select option to the menu.
     *
     * @param key       the key for the specific option
     * @param message   the name of the option
     * @param returnVal the task itself
     */
    public void addSelection(String key, String message, T returnVal) {
        this.itemKeys.add(key);
        this.itemMessages.add(message);
        this.returnValues.add(returnVal);
        this.checkForSubMenu.add(false);
    }

    /**
     * This method returns the status, which mean the task to do.
     *
     * @return the task to do
     */
    public T getStatus() {
        return status;
    }

    /**
     * This method change the value of the stop variable.
     *
     * @param newStatus the desirable value
     */
    public void setStop(boolean newStatus) {
        this.stop = newStatus;
    }

    /**
     * This menu add  a select option to the sub menu of the game.
     *
     * @param key     the key for the specific option
     * @param message the name of the option
     * @param subMenu the task itself
     */
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        this.itemKeys.add(key);
        this.itemMessages.add(message);
        this.listOfSub.add(subMenu);
        this.returnValues.add(null);
        this.checkForSubMenu.add(true);
    }
}



