package graphics;


import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.Collidable;
import game.GameLevel;
import game.Sprite;
import game.Velocity;
import java.awt.Color;

/**
 * This class defines and creates new paddle, draw the paddle and makes the ball
 * movement.
 */
public class Paddle implements Sprite, Collidable {
    // Keyboard sensor
    private KeyboardSensor keyboard;
    // Rectangle shape of the paddle
    private Rectangle paddleRect;
    // paddle color
    private Color color;
    // gui size parameters
    private int guiBorderL;
    private int guiBorderR;
    private int paddleSpeed;

    /**
     * Constructor of the paddle.
     * @param paddleRect paddle shape
     * @param keyboard   keyboard sensor
     * @param color      paddle color
     * @param guiBorderL gui border
     * @param guiBorderR gui border
     * @param paddleSpeed speed of the paddle
     */

    public Paddle(Rectangle paddleRect, KeyboardSensor keyboard, Color color,
                  int guiBorderL, int guiBorderR, int paddleSpeed) {
        this.paddleRect = paddleRect;
        this.keyboard = keyboard;
        this.color = color;
        this.guiBorderL = guiBorderL;
        this.guiBorderR = guiBorderR;
        this.paddleSpeed = paddleSpeed;
    }

    /**
     * Moving the paddle to the left.
     * @param dt the dt parameter of speed
     */
    public void moveLeft(double dt) {
        Point newPoint;
        Rectangle newRect;
        // keyboard left button was pressed
        if (paddleRect.getUpperLeft().getX() > guiBorderL + this.paddleSpeed * dt) {
            newPoint = new Point((this.paddleRect.getUpperLeft().getX() - (dt * this.paddleSpeed)),
                    this.paddleRect.getUpperLeft().getY());
            newRect = new Rectangle(newPoint, paddleRect.getWidth(), paddleRect.getHeight());
            this.paddleRect = newRect;
        }
    }

    /**
     * This method moves the paddle right when keyboard right button was pressed.
     * @param dt the dt parameter of speed
     */
    public void moveRight(double dt) {
        Point newPoint;
        Rectangle newRect;
        // keyboard right button was pressed
        if (paddleRect.getUpperLeft().getX() < (this.guiBorderR - paddleRect.getWidth() - this.paddleSpeed * dt)) {
            newPoint = new Point((this.paddleRect.getUpperLeft().getX() + dt * this.paddleSpeed),
                    this.paddleRect.getUpperLeft().getY());
            newRect = new Rectangle(newPoint, paddleRect.getWidth(), paddleRect.getHeight());
            this.paddleRect = newRect;
        }
    }

    /**
     * This method makes the paddle move to the right or left.
     * @param dt the dt parameter of speed
     */
    public void timePassed(double dt) {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft(dt);
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight(dt);
        }

    }

    /**
     * This method will draw the paddle.
     *
     * @param d parameter to draw the shapes on gui
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());

    }

    /**
     * This method return the rectangle of the paddle.
     *
     * @return paddle rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.paddleRect;
    }

    /**
     * Method to change velocity of hitting ball.
     *
     * @param hitter  bla
     * @param collisionPoint  where the ball collides with the paddle
     * @param currentVelocity velocity of colliding ball
     * @return new velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVeloc = currentVelocity;
        // this variable shows in which part paddle will be hit
        double relativeXValOnRect;
        // dividing paddle to 5 equal parts.
        int[] paddleParts = new int[5];
        for (int i = 0; i < paddleParts.length; i++) {
            paddleParts[i] += this.paddleRect.getWidth() / 5 * (i + 1);
        }
        relativeXValOnRect = collisionPoint.getX() - this.paddleRect.getUpperLeft().getX();
        if (collisionPoint.getY() == this.paddleRect.getUpperLeft().getY()) {
            // paddle is hit in the first part
            if (relativeXValOnRect <= paddleParts[0]) {
                newVeloc = newVeloc.fromAngleAndSpeed(300, currentVelocity.getSpeed());
                // paddle is hit in the second part
            } else if ((relativeXValOnRect > paddleParts[0]) && (relativeXValOnRect <= paddleParts[1])) {
                newVeloc = newVeloc.fromAngleAndSpeed(330, currentVelocity.getSpeed());
                // paddle is hit in the third part
            } else if ((relativeXValOnRect > paddleParts[1]) && (relativeXValOnRect <= paddleParts[2])) {
                newVeloc = new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
                // paddle is hit in the forth part
            } else if ((relativeXValOnRect > paddleParts[2]) && (relativeXValOnRect <= paddleParts[3])) {
                newVeloc = newVeloc.fromAngleAndSpeed(30, currentVelocity.getSpeed());
                // paddle is hit in the fifth part
            } else if ((relativeXValOnRect > paddleParts[3]) && (relativeXValOnRect <= paddleParts[4])) {
                newVeloc = newVeloc.fromAngleAndSpeed(60, currentVelocity.getSpeed());
            }
        } else {
            newVeloc = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        return newVeloc;
    }

    /**
     * Add this paddle to the game.
     *
     * @param g stands for the game created
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
