package indicators;

/**
 * Interface of HitNotifier.
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     * @param hl HitListener parameter
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl HitListener parameter
     */
    void removeHitListener(HitListener hl);
}
