package shapes;

/**
 * Represents a radial object on the canvas.
 * A shape should extend this class if it can be characterized by a center point and a radius.
 *
 * @see GraphicElement
 * @see Circle
 * @see RegularPolygon
 * @see Star
 */
public abstract class RadialElement extends GraphicElement{

    private Point center;
    private double radius;

    /**
     * Returns the RadialElement's center.
     *
     * @return The RadialElement's center
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Sets the RadialElement's center.
     *
     * @param center The RadialElement's new center
     */
    public void setCenter(Point center) {

    }

    /**
     * Returns the RadialElement's radius.
     *
     * @return The RadialElement's radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Sets the RadialElement's radius.
     *
     * @param radius The RadialElement's new radius
     */
    public void setRadius(double radius) {

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
