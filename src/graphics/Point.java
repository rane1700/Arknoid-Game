package graphics;

/**
 * This class defines and creates new point. The class calculate the distance
 * from the main axes to the point and checks if two points are equals.
 */
public class Point {
    // variables for point's values
    private double x;
    private double y;
    private Point point;

    /**
     * define the constructor of the point.
     *
     * @param x value of the x's point
     * @param y value of the y's point
     **/
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor.
     *
     * @param p point
     */
    public Point(Point p) {
        this.point = p;
    }

    /**
     * this method return null.
     *
     * @return null
     */
    public static Point pointIsNull() {
        return null;
    }

    /**
     * This method calculate the distance of the point from the main axes.
     *
     * @param other receives point
     * @return distance the distance that got after the calculation
     */
    public double distance(Point other) {
        return Math.sqrt((other.x - x) * (other.x - x) + (other.y - y) * (other.y - y));
    }

    /**
     * This method checks if two points are equals or not.
     *
     * @param other receives point
     * @return boolean return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return other.x == x && other.y == y;
    }

    /**
     * This method return the x value.
     *
     * @return x the value of x
     */
    public double getX() {
        return x;
    }

    /**
     * This method return the y value.
     *
     * @return y the value of y
     */
    public double getY() {
        return y;
    }

}
