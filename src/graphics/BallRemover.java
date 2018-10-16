package graphics;

import game.GameLevel;
import indicators.Counter;
import indicators.HitListener;

/**
 * This class removes the ball from the game.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;
    private int deathBlockHieght;

    /**
     * Constructor of BallRemover.
     *
     * @param gameLevel                   GameLevel parameter
     * @param removedBalls                counter the balls how removed
     * @param deathBlockHieghtYCoordinate the point that exit the ball from game
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls, int deathBlockHieghtYCoordinate) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
        this.deathBlockHieght = deathBlockHieghtYCoordinate;
    }


    /**
     * This method check if the ball hits the point the take him out of the game.
     *
     * @param beingHit the point of "death"
     * @param hitter   the ball the hit the "death" point on the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getCollisionRectangle().getUpperLine().getStart().getY() == this.deathBlockHieght) {
            this.remainingBalls.decrease(1);
            hitter.removeFromGame(this.gameLevel);
        }
    }
}
