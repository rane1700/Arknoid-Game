package game;

import animations.Animation;
import animations.AnimationRunner;
import animations.PauseScreen;
import animations.KeyPressStoppableAnimation;
import animations.CountdownAnimation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import graphics.Ball;
import graphics.BallRemover;
import graphics.Block;
import graphics.BlockRemover;
import graphics.Paddle;
import graphics.Point;
import graphics.Rectangle;
import indicators.Counter;
import indicators.LivesIndicator;
import indicators.ScoreIndicator;
import indicators.ScoreTrackingListener;
import levels.LevelInformation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * This class create a new game with all of the objects.
 */
public class GameLevel extends GameConst implements Animation {

    private LevelInformation levelInfo;
    private AnimationRunner runner;
    private boolean running;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Block deathBlock;
    private List<Ball> ballsList;
    private Paddle pad;
    private KeyboardSensor keyboard;
    // how many hits were counted on block
    private Integer numOfHits = null;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter score;
    private Counter numberOfLives;

    /**
     * Constructor of game level.
     *
     * @param levelInformation the parameter with the information about the level
     * @param ks               the parameter for the keyboard sensor
     * @param runner           the parameter how run the level
     * @param scoresCounter    the parameter how sum the score of the level
     * @param livesCounter     the parameter of player's lives.
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor ks, AnimationRunner runner,
                     Counter scoresCounter, Counter livesCounter) {
        this.levelInfo = levelInformation;
        this.keyboard = ks;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blockCounter = new Counter(this.levelInfo.numberOfBlocksToRemove());
        this.score = scoresCounter;
        this.numberOfLives = livesCounter;
        this.runner = runner;
        running = true;
        this.ballsList = new ArrayList<Ball>();
        this.deathBlock = new Block(new Rectangle(new Point(0, GUI_HEIGHT), GUI_WIDTH, 10),
                Color.black, null, Color.black);
    }

    /**
     * This method is in charge of stopping condition.
     *
     * @return True or False- stop or not.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Method to add collidable objects to environment.
     *
     * @param c stands for a collidable object
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Method to add sprite.
     *
     * @param s stands for sprite object
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Method to remove collidable objects from environment.
     *
     * @param c stands for a collidable object
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Method to remove sprite.
     *
     * @param s stands for sprite object
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * This method returns the value of the remaining blocks.
     *
     * @return the value eof the remaining blocks.
     */
    public int getRemainingBlocksNum() {
        return this.blockCounter.getValue();
    }

    /**
     * Initialize method. This method initialize a new game: create the Blocks
     * and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        // variable that contains the most left X coordinate of the largest
        // block line (12 blocks)
        this.sprites.addSprite(this.levelInfo.getBackground());
        // determine gui2 borders
        defineGameBorders();
        addIndicators();
    }

    /**
     * Adding borders to game.
     */
    private void defineGameBorders() {
        Block[] borders;
        borders = getBorders();
        // add borders to game
        for (int i = 0; i < borders.length; i++) {
            borders[i].addToGame(this);
        }
        addBlocks();
    }

    /**
     * This method define the border that if the ball cross this border
     * the ball remove from the game.
     *
     * @param deathBlockInLevel the block of the death border
     */
    private void defineDeathBlock(Block deathBlockInLevel) {
        this.deathBlock = deathBlockInLevel;
        BallRemover ballRemove = new BallRemover(this, this.ballCounter, GUI_HEIGHT);
        deathBlock.addHitListener(ballRemove);
        deathBlock.addToGame(this);
    }


    /**
     * This method adds the indicators to the level.
     */
    private void addIndicators() {
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);
        LivesIndicator livesIndicator = new LivesIndicator(this.numberOfLives);
        livesIndicator.addToGame(this);
    }

    /**
     * This method creates the balls for each level.
     */
    private void createBalls() {
        Point pos;
        int xPos = 400, yPos = 500;
        for (int i = 0; i < this.levelInfo.numberOfBalls(); i++) {
            pos = new Point(xPos, yPos);
            Ball ball = new Ball(pos, 4, Color.WHITE, this.environment);
            ball.addToGame(this);
            ball.setVelocity(Velocity.fromAngleAndSpeed(this.levelInfo.initialBallVelocities().get(i).getDx(),
                    this.levelInfo.initialBallVelocities().get(i).getDy()));
            this.ballsList.add(ball);
        }
        this.ballCounter = new Counter(this.ballsList.size());
    }

    /**
     * This method creates the paddle for each level.
     */
    private void createPaddle() {
        int paddleHeight = 15;
        Point upperLeftPad = new Point((400 - this.levelInfo.paddleWidth() / 2), GUI_HEIGHT - paddleHeight - 10);
        Rectangle padRec = new Rectangle(upperLeftPad, this.levelInfo.paddleWidth(), paddleHeight);
        this.pad = new Paddle(padRec, keyboard, new Color(205, 133, 0), BORDER_WIDTH,
                GUI_WIDTH - BORDER_WIDTH, this.levelInfo.paddleSpeed());
        this.pad.addToGame(this);
    }

    /**
     * Method to add blocks to gui2.
     */
    private void addBlocks() {
        BlockRemover blockRemove;
        ScoreTrackingListener stl;
        stl = new ScoreTrackingListener(this.score);
        blockRemove = new BlockRemover(this, this.blockCounter);
        for (int i = 0; i < this.levelInfo.blocks().size(); i++) {
            Block block = new Block(this.levelInfo.blocks().get(i));
            block.addToGame(this);
            block.addHitListener(blockRemove);
            block.addHitListener(stl);
        }
    }

    /**
     * Method to create array of colors.
     *
     * @return arr of colors
     */


    /**
     * Method to determine borders for gui2.
     *
     * @return borders array for gui2
     */
    private Block[] getBorders() {
        // Upper border left point
        Point upperLeft1 = new Point(0, 0);
        // Left border left point
        Point upperLeft2 = new Point(0, 0);
        // Right border border
        Point upperLeft3 = new Point(775, 0);
        // Point for block on top of upper block
        Point upperLeft4 = new Point(0, 0);
        // Upper border rectangle
        Rectangle rect1 = new Rectangle(upperLeft1, 800, 25);
        // Left border rectangle
        Rectangle rect2 = new Rectangle(upperLeft2, 25, 600);
        // Right border rectangle
        Rectangle rect3 = new Rectangle(upperLeft3, 25, 600);
        // Rectangle for block on top of upper block
        Rectangle rect4 = new Rectangle(upperLeft4, 800, 18);
        Block[] borders = new Block[4];
        borders[0] = new Block(rect1, Color.gray, null, Color.black);
        borders[1] = new Block(rect2, Color.gray, null, Color.black);
        borders[2] = new Block(rect3, Color.gray, null, Color.black);
        borders[3] = new Block(rect4, Color.lightGray, null, null);
        return borders;
    }

    /**
     * This method removes the balls from the game.
     */
    private void removeBalls() {
        for (Ball ball : this.ballsList) {
            ball.removeFromGame(this);

        }
        this.ballsList.clear();
    }

    /**
     * This method removes the paddle from the game.
     */
    private void removePaddle() {
        this.removeSprite(this.pad);
        this.removeCollidable(this.pad);
    }

    /**
     * This method is in charge of the game-specific logic.
     *
     * @param d  DrawSurface parameter.
     * @param dt the dt parameter of speed
     */
    public void doOneFrame(DrawSurface d, double dt) {
        deathBlock.drawOn(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
        d.setColor(Color.BLACK);
        d.drawText(550, 15, "Level Name: " + this.levelInfo.levelName(), 15);
        if (this.runner.getGui().getKeyboardSensor().isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.runner.getGui().getKeyboardSensor(),
                    KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
        if (this.blockCounter.getValue() == 0) {
            this.score.increase(100);
            removePaddle();
            removeBalls();
            this.running = false;
        }
        if (this.ballCounter.getValue() == 0) {
            removePaddle();
            removeBalls();
            this.numberOfLives.decrease(1);
            this.running = false;
        }
    }

    /**
     * This method runs the game- play one turn.
     */
    public void playOneTurn() {
        createBalls();
        defineDeathBlock(this.deathBlock);
        createPaddle();
        this.running = true;
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.runner.run(this);

    }
}