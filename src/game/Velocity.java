package game;

import graphics.Point;

/**
 * This class calculate the dx and dy values.
 */
public class Velocity {
    // variables to maintain the dx and dy values
    private double dxVal;
    private double dyVal;

    /**
     * Define the constructor of velocity.
     *
     * @param dx value of dx
     * @param dy value of dx
     */
    public Velocity(double dx, double dy) {
        dxVal = dx;
        dyVal = dy;
    }

    /**
     * Takes the angel and the speed and returns dx and dy.
     *
     * @param angle object that means the angle of the ball
     * @param speed object that means the speed of the ball
     * @return newVeloc the new speed and angel of the ball.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        Velocity newVeloc;
        double dxValTmp;
        double dyValTmp;
        dxValTmp = speed * Math.sin(Math.toRadians(angle));
        dyValTmp = -speed * Math.cos(Math.toRadians(angle));
        newVeloc = new Velocity(dxValTmp, dyValTmp);
        return newVeloc;
    }

    /**
     * This method return the value of dx.
     *
     * @return setDx dx value
     */
    public double getDx() {
        return dxVal;

    }

    /**
     * This method return the value of dy.
     *
     * @return setDy dy value
     */
    public double getDy() {
        return dyVal;
    }

    /**
     * This method calculate the speed and returns it.
     *
     * @return returns the value of the ball speed
     */
    public double getSpeed() {
        return Math.sqrt(this.dxVal * this.dxVal + this.dyVal * this.dyVal);
    }

    /**
     * This method take a point with position (x,y).
     * return a new point with
     * position (x+dx, y+dy)
     *
     * @param p  point
     * @param dt the dt parameter of speed
     * @return pointApplied the new point
     */
    public Point applyToPoint(Point p, double dt) {
        return new Point(p.getX() + this.getDx() * dt, p.getY() + this.getDy() * dt);
    }

    /**
     * This method makes the ball movement, but slowly.
     *
     * @param ballCenter current center of ball
     * @param veloc      velocity of ball
     * @param dt         the dt parameter of speed
     * @return new ball center after minor progress
     */
    public Point slowApplyToPoint(Point ballCenter, Velocity veloc, double dt) {
        Point pointApplied;
        // moderate ball velocity
        pointApplied = new Point(ballCenter.getX() + veloc.getDx() * 0.1 * dt,
                ballCenter.getY() + veloc.getDy() * 0.1 * dt);
        return pointApplied;
    }
}
