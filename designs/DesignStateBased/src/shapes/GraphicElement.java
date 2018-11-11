package shapes;

/**
 * Represents an object on the canvas. It defines the basic operations and attributes of a shape.
 * Every shape should extend this class or one of its subclasses.
 * This class holds attributes that are available to any shape: fillColor, strokeColor and strokeWidth
 *
 * @see Point
 * @see PointBasedElement
 * @see RadialElement
 * @see RectangularElement
 */

public abstract class GraphicElement {

    private String fillColor;
    private String strokeColor;
    private int strokeWidth;

    /**
     * Returns the GraphicElement's fillColor.
     *
     * @return The GraphicElement's fillColor
     */
    public String getFillColor() {
        return fillColor;
    }

    /**
     * Sets the GraphicElement's fillColor.
     *
     * @param fillColor The GraphicElement's new fillColor
     */
    public void setFillColor(String fillColor) {

    }

    /**
     * Returns the GraphicElement's strokeColor.
     *
     * @return The GraphicElement's strokeColor
     */
    public String getStrokeColor() {
        return strokeColor;
    }

    /**
     * Sets the GraphicElement's strokeColor.
     *
     * @param strokeColor The GraphicElement's new strokeColor
     */
    public void setStrokeColor(String strokeColor) {

    }

    /**
     * Returns the GraphicElement's strokeWidth.
     *
     * @return The GraphicElement's strokeWidth
     */
    public int getStrokeWidth() {
        return strokeWidth;
    }

    /**
     * Sets the GraphicElement's strokeWidth.
     *
     * @param strokeWidth The GraphicElement's new strokeWidth
     */
    public void setStrokeWidth(int strokeWidth) {

    }

    /**
     * Should return the HTML code for the GraphicElement.
     *
     * @return A String containing HTML code for the GraphicElement
     */
    public abstract String getHTML();

    /**
     * Should return a JSON representation of the GraphicElement.
     * Modifications to the GraphicElement should use the same attribute names.
     *
     * @return A String containing a JSON representation of the GraphicElement
     */
    public abstract String getJSON();

    /**
     * Should modify the GraphicElement using the specified attributes.
     *
     * @param attributes A JSON String containing the attributes to be modified
     * @return <code>true</code> if the shape had one or more of its attributes modified, <code>false</code> otherwise
     */
    public abstract boolean modify(String attributes);

    /**
     * Should move the GraphicElement by the specified vector.
     *
     * @param vector The move vector
     * @see Point
     */
    public abstract void move(Point vector);

}
