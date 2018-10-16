package levels;

import game.Sprite;
import game.Velocity;
import graphics.Block;

import java.util.List;

/**
 * Interface of LevelInformation.
 */
public interface LevelInformation {
    /**
     * Number Of balls in game.
     *
     * @return number of balls
     */
    int numberOfBalls();


    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == NUMBER_OF_BALLS().
     *
     * @return List of velocities for level balls.
     */
    List<Velocity> initialBallVelocities();

    /**
     * Paddle Speed.
     *
     * @return paddle speed
     */
    int paddleSpeed();

    /**
     * Paddle Width.
     *
     * @return paddle width
     */
    int paddleWidth();

    /**
     * Level Name.
     *
     * @return String name of the level
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return sprite with the background
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return list of the blocks
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed.
     *
     * @return the sum of the blocks
     */
    int numberOfBlocksToRemove();
}
