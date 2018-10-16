package game;


/**
 * This is the Task interface.
 * @param <T> General task object
 */
public interface Task<T> {
    /**
     * This method is for runs the task.
     * @return the task itself
     */
    T run();
}