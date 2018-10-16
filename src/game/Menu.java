package game;

import animations.Animation;

/**
 *  This is the Menu interface that extends the Animation interface.
 * @param <T> the task to do
 */
public interface Menu<T> extends Animation {
    /**
     * This method is for add a select option to the menu.
     *
     * @param key       the key for the specific option
     * @param message   the name of the option
     * @param returnVal the task itself
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * This method returns the status, which mean the task to do.
     *
     * @return the task to do
     */
    T getStatus();

    /**
     * This method change the value of the stop variable.
     *
     * @param status the desirable value
     */
    void setStop(boolean status);

    /**
     * This menu add  a select option to the sub menu of the game.
     *
     * @param key     the key for the specific option
     * @param message the name of the option
     * @param subMenu the task itself
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}
