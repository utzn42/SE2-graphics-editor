package Shapes;

import Base.Point;

/**
 * Superclass of all radial elements. Implements GraphicElement.
 *
 * @see GraphicElement
 * @see Circle
 * @see Star
 * @see RegularPolygon
 */
public abstract class RadialElement implements GraphicElement{

    private Point center;
    private double radius;

    /**
     * Moves object from one point to another.
     * @param point destination point
     */
    public void move(Point point) {

    }
}
