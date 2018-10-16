package game;

import graphics.Line;
import graphics.Point;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.MAX_VALUE;

/**
 * Class for gui environment.
 * This class checks if collision is going to happen and returns an information
 * about the collision
 */
public class GameEnvironment {
    // list of collidable Objects
    private List<Collidable> collidableObjects;

    /**
     * Define the constructor of GameEnvironment.
     */
    public GameEnvironment() {
        this.collidableObjects = new ArrayList<Collidable>();
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c collidable object
     */
    public void addCollidable(Collidable c) {
        this.collidableObjects.add(c);
    }

    /**
     * This method removes the collidable object from the environment.
     *
     * @param c bla
     */
    public void removeCollidable(Collidable c) {
        this.collidableObjects.remove(c);
    }

    /**
     * This method assume an object moving from line.start() to line.end(). if
     * this object will not collide with any of the collidables in this
     * collection, return null. else, return the information about the closest
     * collision that is going to occur.
     *
     * @param trajectory line of trajectory
     * @return null or CollisionInfo null or closest collision that is going to
     * occur and object
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // maintain the size of collidable objects list
        int size = this.collidableObjects.size();
        // variable that maintain the highest value of integer
        // which mean that highest distance that could be
        double distance = MAX_VALUE;
        // maintain the closest point
        Point newPoint = null;
        // maintain the collidable object
        Collidable object = null;
        // find the closest intersection point
        for (int i = 0; i < size; i++) {
            // if there is a section point
            if (trajectory.closestIntersectionToStartOfLine(
                    ((Collidable) this.collidableObjects.get(i)).getCollisionRectangle()) != null) {
                // if the distance is smaller
                if (trajectory.getStart().distance(trajectory.closestIntersectionToStartOfLine(
                        ((Collidable) this.collidableObjects.get(i)).getCollisionRectangle())) < distance) {
                    distance = trajectory.getStart().distance(trajectory.closestIntersectionToStartOfLine(
                            ((Collidable) this.collidableObjects.get(i)).getCollisionRectangle()));
                    newPoint = trajectory.closestIntersectionToStartOfLine(
                            ((Collidable) this.collidableObjects.get(i)).getCollisionRectangle());
                    object = (Collidable) this.collidableObjects.get(i);
                }
            }
        }
        // if it found collide with any of the collidables
        if (newPoint != null) {
            CollisionInfo collisInfo = new CollisionInfo(newPoint, object);
            return collisInfo;
        }
        // else
        return null;
    }
}
