package graphics;

import biuoop.DrawSurface;
import game.CollisionInfo;
import game.GameEnvironment;
import game.GameLevel;
import game.Sprite;
import game.Velocity;

import java.awt.Color;

/**
 * This class defines and creates new ball, draw the ball and makes the ball
 * movement.
 */


public class Ball implements Sprite {
    // variable for maintain ball's center point
    private Point center;
    // variable for maintain ball's radios
    private int radios;
    // variable for maintain ball's color
    private Color color;
    // three variables for gets and maintain the ball's velocity
    private double dxVeloc;
    private double dyVeloc;
    private Velocity veloc;
    // variable for maintain ball's environment
    private GameEnvironment environment;

    /**
     * Defines the constructor of ball.
     *
     * @param center      center point
     * @param r           radios value of the ball
     * @param givenColor  color of the ball
     * @param environment gui environment
     **/
    public Ball(Point center, int r, java.awt.Color givenColor, GameEnvironment environment) {
        this.center = center;
        radios = r;
        this.color = givenColor;
        this.environment = environment;
    }

    /**
     * This method receives two coordinates and make them into center point. The
     * method receives also the radios of the ball and his color.
     *
     * @param x           x value of the center point
     * @param y           y value of the center point
     * @param r           radios value of the ball
     * @param givenColor  color of the ball
     * @param environment game environment
     */
    public Ball(double x, double y, int r, java.awt.Color givenColor, GameEnvironment environment) {
        this.center = new Point(x, y);
        this.radios = r;
        color = givenColor;
        this.environment = environment;
    }

    /**
     * This method return the x value of center point.
     *
     * @return x the value of x
     */
    public double getX() {
        return this.center.getX();
    }

    /**
     * This method return the y value of center point.
     *
     * @return y the value of y
     */
    public double getY() {
        return this.center.getY();
    }

    /**
     * This method return the value of the ball's radios.
     *
     * @return radios value of the ball's radios
     */
    public int getRadios() {
        return radios;
    }

    /**
     * . This method return the value of the ball's color
     *
     * @return color ball's color
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * This method draws the ball.
     *
     * @param surface given DrawSurface
     **/
    public void drawOn(DrawSurface surface) {
        // getting the color of the ball
        surface.setColor(color);
        // getting and casting the values of the ball's center point
        surface.fillCircle((int) center.getX(), (int) center.getY(), radios);
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) center.getX(), (int) center.getY(), radios);


    }

    /**
     * This method set the dx and dy values of the ball movement.
     *
     * @param dx Stands for change of X value
     * @param dy Stands for change of Y value
     */
    public void setVelocity(double dx, double dy) {
        this.dxVeloc = dx;
        this.dyVeloc = dy;
    }

    /**
     * This method will return out current velocity.
     *
     * @return veloc velocity
     */
    public Velocity getVelocity() {

        Velocity v = new Velocity(dxVeloc, dyVeloc);
        return v;
    }

    /**
     * This method This method gets a velocity type variable and updates the
     * dx and dy values from the given Velocity type variable.
     *
     * @param v v param is the velocity type variable
     */
    public void setVelocity(Velocity v) {
        this.veloc = v;
        this.dxVeloc = v.getDx();
        this.dyVeloc = v.getDy();
    }

    /**
     * This method will add sprite to game.
     *
     * @param g game environment
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }


    /**
     * This method makes the movement of the ball and confirms that the ball.
     * won't get out of the borders.
     * @param dt the dt parameter of speed
     */
    public void moveOneStep(double dt) {
        // variable from type collision info for maintain the new information of
        // the collision
        // point that maintain the next location of the ball
        Point nextLocation = this.getVelocity().applyToPoint(this.center, dt);
        // line that maintain the trajectory (line between the current location
        // of the ball to the next location)
        Line trajectory = new Line(
                Math.round(this.center.getX()), Math.round(this.center.getY()),
                Math.round(nextLocation.getX()), Math.round(nextLocation.getY()));
        // getting the closest collision of the trajectory
        CollisionInfo info = this.environment.getClosestCollision(trajectory);
        // checking if the collision exist
        if (info == null) {
            this.center = this.getVelocity().applyToPoint(this.center, dt);
            // move the ball accordingly the collision to avoid the collision
        } else {
            this.setVelocity(info.collisionObject().hit(this, info.collisionPoint(), this.veloc));
            this.center = this.getVelocity().slowApplyToPoint(this.center, this.veloc, dt);
            this.center = this.getVelocity().applyToPoint(this.center, dt);
        }
    }

    /**
     * This method calls to the moveOneStep function.
     *
     * @param dt the dt parameter of speed
     */
    public void timePassed(double dt) {
        this.moveOneStep(dt);
    }

    /**
     * This method removes the ball from the game.
     *
     * @param g GameLevel parameter.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}
