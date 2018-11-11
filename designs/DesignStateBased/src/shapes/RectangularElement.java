package shapes;

/**
 * Represents a rectangular object on the canvas.
 * A shape should extend this class if it can be characterized by its height and width.
 *
 * @see GraphicElement
 * @see Ellipse
 */

public abstract class RectangularElement extends GraphicElement{

    private Point offset;
    private Point size;

    /**
     * Returns the RectangularElement's offset.
     *
     * @return The RectangularElement's offset
     */
    public Point getOffset() {
        return offset;
    }

    /**
     * Sets the RectangularElement's offset.
     *
     * @param offset The RectangularElement's new offset
     */
    public void setOffset(Point offset) {

    }

    /**
     * Returns the RectangularElement's size.
     *
     * @return The RectangularElement's size
     */
    public Point getSize() {
        return size;
    }

    /**
     * Sets the RectangularElement's size.
     *
     * @param size The RectangularElement's new size
     */
    public void setSize(Point size) {

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
