package Shapes;

import Base.Point;

import java.util.List;

/**
 * Superclass of all point based elements. Implements GraphicElement.
 * Point based elements describe shapes where 2 or more points are connected in a row, and the last one with the first.
 *
 * @see Polygon
 * @see Line
 * @see GraphicElement
 */
public abstract class PointBasedElement implements GraphicElement {

    private List<Point> points;

    /**
     * Moves object from one point to another.
     * @param point destination point
     */
    public void move(Point point) {

    }
}
