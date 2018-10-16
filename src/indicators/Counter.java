package indicators;

/**
 * This class is for counting some things in the game.
 */
public class Counter {
    private int counter;

    /**
     * Constructor of counter.
     *
     * @param cou value of counter
     */
    public Counter(int cou) {
        this.counter = cou;
    }

    /**
     * add number to current count.
     *
     * @param number the value of the number to add
     */
    public void increase(int number) {
        this.counter = this.counter + number;
    }

    /**
     * subtract number from current count.
     *
     * @param number subtract number
     */
    public void decrease(int number) {
        this.counter = this.counter - number;
    }

    /**
     * get current count.
     *
     * @return the current count
     */
    public int getValue() {
        return this.counter;
    }
}
