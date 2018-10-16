package graphics;

import java.util.ArrayList;
import java.util.List;

/**
 * Rectangle shape class.
 */
public class Rectangle {
    // points to maintain the corners of the rectangle
    private Point upLeft;
    private Point upRight;
    private Point downLeft;
    private Point downRight;
    // variable to maintain the width and height of the rectangle
    private double width;
    private double height;
    // variables from line type to maintain the lines that construct the
    // rectangle
    private Line upperLine;
    private Line leftLine;
    private Line bottomLine;
    private Line rightLine;

    /**
     * Define the constructor of the rectangle.
     *
     * @param upperLeft the upper left point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upLeft = upperLeft;
        this.width = width;
        this.height = height;
        // getting the values of the upper left point
        double x = this.upLeft.getX();
        double y = this.upLeft.getY();
        // creatingthe other points of the corners
        this.upRight = new Point(x + this.width, y);
        this.downLeft = new Point(x, y + this.height);
        this.downRight = new Point(x + this.width, y + this.height);
        // creating the lines from corner to corner
        upperLine = new Line(this.upLeft, this.upRight);
        leftLine = new Line(this.upLeft, this.downLeft);
        bottomLine = new Line(this.downLeft, this.downRight);
        rightLine = new Line(this.upRight, this.downRight);
    }

    /**
     * This method returns a (possibly empty) List of intersection points with
     * the specified line.
     *
     * @param line line for checks if there is an intersection with points
     * @return interList list of intersection points with the specified line.
     */
    public List intersectionPoints(Line line) {
        // maintain the points of the intersection
        List<Point> interList = new ArrayList<Point>();
        // checking intersection with the upper line
        // adding the points of the intersection to the list
        if (upperLine.isIntersecting(line)) {
            if (!interList.contains(upperLine.intersectionWith(line))) {
                interList.add(upperLine.intersectionWith(line));
            }
        }
        // checking intersection with the bottom line
        // adding the points of the intersection to the list
        if (bottomLine.isIntersecting(line)) {
            if (!interList.contains(bottomLine.intersectionWith(line))) {
                interList.add(bottomLine.intersectionWith(line));
            }
        }
        // checking intersection with the let line
        // adding the points of the intersection to the list
        if (leftLine.isIntersecting(line)) {
            if (!interList.contains(leftLine.intersectionWith(line))) {
                interList.add(leftLine.intersectionWith(line));
            }
        }
        // checking intersection with the right line
        // adding the points of the intersection to the list
        if (rightLine.isIntersecting(line)) {
            if (!interList.contains(rightLine.intersectionWith(line))) {
                interList.add((Point) rightLine.intersectionWith(line));
            }
        }
        return interList;
    }

    /**
     * This method returns the width of the rectangle.
     *
     * @return width the value of the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * This method returns the height of the rectangle.
     *
     * @return height the value of the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    // Returns the upper-left point of the rectangle.

    /**
     * This method returns the upper-left point of the rectangle.
     *
     * @return point the rectangle's upper-left point
     */
    public Point getUpperLeft() {
        return this.upLeft;
    }

    /**
     * This method returns the upper-right point of the rectangle.
     *
     * @return point the rectangle's upper-right point
     */
    public Point getUpperRight() {
        return this.upRight;
    }

    /**
     * This method returns the lower-left point of the rectangle.
     *
     * @return point the rectangle's lower-left point
     */
    public Point getLowerLeft() {
        return this.downLeft;
    }

    /**
     * This method returns the lower-right point of the rectangle.
     *
     * @return point the rectangle's lower-right point
     */
    public Point getLowerRight() {
        return this.downRight;
    }

    /**
     * This method returns the upper line of the rectangle.
     *
     * @return line the rectangle's upper line
     */
    public Line getUpperLine() {
        return this.upperLine;
    }

    /**
     * This method returns the bottom line of the rectangle.
     *
     * @return line the rectangle's bottom line
     */
    public Line getBottomLine() {
        return this.bottomLine;
    }

    /**
     * This method returns the left line of the rectangle.
     *
     * @return line the rectangle's left line
     */
    public Line getLeftLine() {
        return this.leftLine;
    }

    /**
     * This method returns the right line of the rectangle.
     *
     * @return line the rectangle's right line
     */
    public Line getRightLine() {
        return this.rightLine;
    }

}
