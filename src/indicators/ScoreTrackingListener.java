package indicators;

import graphics.Ball;
import graphics.Block;

/**
 * This method represent the score of each block- which mean
 * who many times the block was hatted and if the block should
 * be destroy or not.
 */
public class ScoreTrackingListener implements HitListener {
    private static final int SIMPLE_HIT = 5;
    private static final int DESTROY = 15;
    private Counter currentScore;

    /**
     * The constructor of ScoreTrackingListener.
     *
     * @param scoreCounter counter of sum of hit events
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method checks if the hit event
     * is a simple his or that the block
     * that hitted should be destroy.
     *
     * @param beingHit the block that being hit
     * @param hitter   the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getNumOfHits() == 0) {
            this.currentScore.increase(DESTROY);
        } else {
            this.currentScore.increase(SIMPLE_HIT);
        }
    }
}
