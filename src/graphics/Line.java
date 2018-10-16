package graphics;

import java.util.ArrayList;

/**
 * This class defines and creates new line. The class calculate the line's
 * length, slope, and if it's has an intersection with another line. the method
 * check if two lines are equals.
 **/
public class Line {
    // variable to maintain the point of the line's start
    private Point start;
    // variable to maintain the point of the line's end
    private Point end;
    // array list for maintain the points of intersection
    private ArrayList<Point> interList;

    /**
     * define the constructors of the line.
     *
     * @param start the values of the start point
     * @param end   the values of the end point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * define the points that construct the line.
     *
     * @param x1 value of x for the start point
     * @param y1 value of y for the start point
     * @param x2 value of x for the end point
     * @param y2 value of y for the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
    }

    /**
     * This method calculate and return the slope of the line.
     *
     * @param start values of the line's start point
     * @param end   values of the line's end point
     * @return slope variable from double type that maintain the slope the
     * received after the calculation
     */
    public static double slope(Point start, Point end) {
        double slope = (start.getY() - end.getY()) / (start.getX() - end.getX());
        return -1 * slope;
    }

    /**
     * This method symbolizes the b for the y=mx+b.
     *
     * @param line line arg.
     * @return b1 variable from double type that symbolizes b
     */
    public static double getYCord(Line line) {
        double b1;
        double functionSlope = slope(line.start, line.end);
        b1 = (-1 * functionSlope * line.start.getX() + line.start.getY());
        return b1;
    }

    /**
     * This method checks if two lines are intersect by use their slopes. if
     * they intersect the method returns the point of the intersection and if
     * not the method returns null.
     *
     * @param firstLine  one of the line to check
     * @param secondLine the second line to check
     * @return intersection or null the values of the intersection point if it's
     * exist or null if the lines aren't intersect.
     * Note: this code is
     * based upon the following article
     * https://www.topcoder.com/community/data-science/
     * data-science-tutorials/geometry-concepts-line-intersection-and-
     * its-applications/
     */
    public static Point getIntersection(Line firstLine, Line secondLine) {
        double a1, a2;
        double b1, b2;
        double c1, c2;
        double determinante;
        // variables for maintain first line values
        double minXF, maxXF, minYF, maxYF;
        // variables for maintain second line values
        double minXS, maxXS, minYS, maxYS;
        // Calculate first line

        minXF = Math.min(firstLine.start.getX(), firstLine.end.getX());
        maxXF = Math.max(firstLine.start.getX(), firstLine.end.getX());
        minYF = Math.min(firstLine.start.getY(), firstLine.end.getY());
        maxYF = Math.max(firstLine.start.getY(), firstLine.end.getY());
        minXS = Math.min(secondLine.start.getX(), secondLine.end.getX());
        maxXS = Math.max(secondLine.start.getX(), secondLine.end.getX());
        minYS = Math.min(secondLine.start.getY(), secondLine.end.getY());
        maxYS = Math.max(secondLine.start.getY(), secondLine.end.getY());
        Point intersection;
        a1 = firstLine.end.getY() - firstLine.start.getY();
        a2 = secondLine.end.getY() - secondLine.start.getY();
        b1 = firstLine.start.getX() - firstLine.end.getX();
        b2 = secondLine.start.getX() - secondLine.end.getX();
        c1 = a1 * firstLine.start.getX() + b1 * firstLine.start.getY();
        c2 = a2 * secondLine.start.getX() + b2 * secondLine.start.getY();
        determinante = a1 * b2 - a2 * b1;
        if (determinante == 0) {
            return null;
        }
        intersection = new Point(((b2 * c1 - b1 * c2) / determinante),
                ((a1 * c2 - a2 * c1) / determinante));
        if (((intersection.getX() >= minXF) && (intersection.getX() <= maxXF))
                && (((intersection.getY() >= minYF) && (intersection.getY() <= maxYF))
                && ((intersection.getX() >= minXS) && (intersection.getX() <= maxXS))
                && (intersection.getY() >= minYS) && (intersection.getY() <= maxYS))) {
            return intersection;
        } else {
            return null;
        }

    }

    /**
     * This method returns the line's length.
     *
     * @return distance the value of the length of the line.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * This method returns the middle point of the line.
     *
     * @return middle the values of the middle point.
     */
    public Point middle() {
        // calculate the x value of the middle point
        double midX = (start.getX() + end.getX()) / 2;
        // calculate the y value of the middle point
        double midY = (start.getY() + end.getY()) / 2;
        // assigning the values the received to the variable
        Point middle = new Point(midX, midY);
        return middle;
    }

    /**
     * This method returns the start point of the line.
     *
     * @return start values of the line's start point
     **/
    public Point getStart() {
        return start;
    }

    /**
     * This method returns the end point of the line.
     *
     * @return start values of the line's end point
     **/
    public Point getEnd() {
        return end;
    }

    /**
     * This method checks if the line has point of intersection with anther line
     * by use the others methods. The method returns true if the lines
     * intersect, false otherwise.
     *
     * @param other the line for checking
     * @return boolean true or false
     */
    public boolean isIntersecting(Line other) {
        // getting the other line for checking if they are intersect
        Line line = new Line(this.start, this.end);
        // getting the point of the intersection
        Point intersection = getIntersection(line, other);
        // checking if the point exist or not
        return intersection != null;
    }

    /**
     * This method returns the intersection point if the lines intersect, and
     * null otherwise. The method checks this by calls to other methods.
     *
     * @param other the line that receives to check the intersection with the
     *              other lines
     * @return intersection point or null. returns the intersection point if the
     * lines intersect,and null otherwise.
     **/
    public Point intersectionWith(Line other) {
        if (this.isIntersecting(other)) {
            return getIntersection(this, other);
        } else {
            return null;
        }

    }

    /**
     * This method checks if two lines are equals (in their length)' by calls to
     * anther method. The method return true is the lines are equal, false
     * otherwise.
     *
     * @param other the other line to check if it's equal
     * @return boolean true or false
     */
    public boolean equals(Line other) {
        return this.start.distance(this.end) == other.start.distance(other.end);

    }

    /**
     * This method checks which point of intersection is closest to the line's.
     * start.
     *
     * @param rect the object that the involved in the collision with the ball
     * @return Point the closest point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // checking if there is an intersection point
        if (rect.intersectionPoints(this).isEmpty()) {
            return null;
            // case that there are two point of collision
            // checking which one of the point closest to the line's start and
            // returning this point
        } else if (rect.intersectionPoints(this).size() > 1) {
            if (this.start.distance((Point) rect.intersectionPoints(this).get(0)) > this.start
                    .distance((Point) rect.intersectionPoints(this).get(1))) {
                return (Point) rect.intersectionPoints(this).get(1);
            } else {
                return (Point) rect.intersectionPoints(this).get(0);
            }
            // case that there is oe point of collision - returning this point
        } else {
            return (Point) rect.intersectionPoints(this).get(0);
        }


    }
    /*public boolean ifPointOnLine(Point point) {
        double dist1 = this.start.distance(point); // Calculating the distance.
        double dist2 = point.distance(this.end); // Calculating the distance.
        if ((float) this.length() == (float) (dist1 + dist2)) {
            return true;
        }
        return false;
    }*/
}
