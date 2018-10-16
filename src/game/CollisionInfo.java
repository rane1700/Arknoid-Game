package game;

import graphics.Point;

/**
 * This class gives the information about the collision of the ball with an object.
 */
public class CollisionInfo {
    // this variable maintains the point at which the collision occurs.
    private Point collPoint;
    // this variable maintain the collidable object involved in the collision.
    private Collidable collidableObject;

    /**
     * Define the constructor of the CollisionInfo.
     *
     * @param pointOfCollision gets the point of the collision.
     * @param objectToCollide  gets the collidable object involved in the collision.
     */
    public CollisionInfo(Point pointOfCollision, Collidable objectToCollide) {
        this.collPoint = pointOfCollision;
        this.collidableObject = objectToCollide;
    }

    /**
     * This function gets and returns the point at which the collision occurs.
     *
     * @return the collision point
     */
    public Point collisionPoint() {
        return this.collPoint;
    }

    /**
     * This function gets and returns the collidable object involved in the
     * collision.
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collidableObject;
    }
}
