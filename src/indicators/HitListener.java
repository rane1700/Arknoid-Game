package indicators;

import graphics.Ball;
import graphics.Block;

/**
 * Interface of HitListener.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit the block that being hit
     * @param hitter the ball the hit the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
