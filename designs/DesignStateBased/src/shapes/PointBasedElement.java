package shapes;

import java.util.List;

/**
 * Represents a point-based object on the canvas.
 * A shape should extend this class if it is made up of any number of points that should be independently modifiable.
 *
 * @see GraphicElement
 * @see Line
 * @see Polygon
 */
public abstract class PointBasedElement extends GraphicElement {

    private List<Point> points;

    /**
     * Returns the PointBasedElement's points.
     *
     * @return The PointBasedElement's points
     */
    public List<Point> getPoints() {
        return points;
    }

    /**
     * Sets the PointBasedElement's points.
     *
     * @param points The PointBasedElement's new points
     */
    public void setPoints(List<Point> points) {

    }

    /**
     * Moves the shape by the specified vector.
     *
     * @param vector The move vector
     * @see Point
     */
    public void move(Point vector) {

    }

}
