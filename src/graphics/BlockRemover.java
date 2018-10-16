package graphics;

import game.GameLevel;
import indicators.Counter;
import indicators.HitListener;

/**
 * This class removes the block from the game.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * This method removes the block from the game.
     *
     * @param gameLevel     GameLevel parameter
     * @param removedBlocks the counter of the removed blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }


    /**
     * This method checks if the value of the block is already 0
     * and calls to another method to removes it's from the game.
     *
     * @param beingHit the block that being hit
     * @param hitter   the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getNumOfHits() == 0) {
            this.remainingBlocks.decrease(1);
            beingHit.removeFromGame(this.gameLevel);
            beingHit.removeHitListener(this);
        }
    }
}
