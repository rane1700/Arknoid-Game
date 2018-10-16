package game;

import graphics.Ball;
import graphics.Point;
import graphics.Rectangle;

/**
 * interface of collidable.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     *
     * @return the rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with.
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param hitter          the ball that hit the block
     * @param collisionPoint  point of collision
     * @param currentVelocity velocity of ball
     * @return the new velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
