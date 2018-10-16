package levels;

import game.Sprite;
import game.Velocity;
import graphics.Block;
import java.util.List;
import java.awt.Color;
import java.awt.Image;

/**
 * Created by Ran on 05/06/2016.
 */
public class LevelInformationGenerator implements LevelInformation {
      private int numOfBalls;
      private List<Velocity> intiallBallVeloc;
      private String levelName;
      private int paddleWidth;
      private int blocksToRemove;
      private int paddleSpeed;
      private List<Block> blockList;
      private Image image;
      private Color color;

    /**
     * Constructor.
     * @param numBalls number of balls in level
     * @param veloc List of balls velocities
     * @param paddleSpeedParm paddle speed
     * @param paddleWidthParm paddle width
     * @param lName level name
     * @param bList list of blocks
     * @param numOfBlockToRemove number of blocks to remove from game
     */
    public LevelInformationGenerator(int numBalls, List<Velocity> veloc, int paddleSpeedParm, int paddleWidthParm,
                                     String lName, List<Block> bList, int numOfBlockToRemove) {
        this.numOfBalls = numBalls;
        this.intiallBallVeloc = veloc;
        this.paddleSpeed = paddleSpeedParm;
        this.paddleWidth = paddleWidthParm;
        this.levelName = lName;
        this.blockList = bList;
        this.blocksToRemove = numOfBlockToRemove;
    }

    /**
     * Set the GUI background to be an image.
     * @param img Background image
     */
    public void setLevelBackground(Image img) {
        this.image = img;
        this.color = null;
    }

    /**
     * Set the GUI background to be a color.
     * @param clr Background color
     */
    public void setLevelBackground(Color clr) {
        this.color = clr;
        this.image = null;
    }
    /**
     * Number Of balls in game.
     *
     * @return number of balls
     */
    public int numberOfBalls() {
        return this.numOfBalls;
    }


    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == NUMBER_OF_BALLS().
     *
     * @return List of velocities for level balls.
     */
    public List<Velocity> initialBallVelocities() {
        return this.intiallBallVeloc;
    }

    /**
     * Paddle Speed.
     *
     * @return paddle speed
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * Paddle Width.
     *
     * @return paddle width
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * Level Name.
     *
     * @return String name of the level
     */
    public String levelName() {
        return this.levelName;
    }

    /**
     * Returns a sprite with the background of the level.
     * @return sprite with the background
     */
    public Sprite getBackground() {
        if (this.color == null) {
            return new BackGround(this.image);
        } else {
            return new BackGround(this.color);
        }
    }

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return list of the blocks
     */
    public List<Block> blocks() {
        return this.blockList;
    }

    /**
     * Number of blocks that should be removed.
     *
     * @return the sum of the blocks
     */
    public int numberOfBlocksToRemove() {
        return this.blocksToRemove;
    }
}
