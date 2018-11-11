package shapes;

/**
 * Server representation of a star.
 * Note that this shape uses a radius to determine its size, so it is not stretchable.
 *
 * @see GraphicElement
 * @see RadialElement
 * @see RegularPolygon
 */
public class Star extends RegularPolygon {

    private double innerRadius;

    /**
     * Creates a star with default attributes.
     */
    public Star() {

    }

    /**
     * Creates a star with the specified attributes.
     *
     * @param attributes A JSON String containing the attributes
     */
    public Star(String attributes) {

    }

    /**
     * Returns the Star's innerRadius.
     *
     * @return The Star's innerRadius
     */
    public double getInnerRadius() {
        return innerRadius;
    }

    /**
     * Sets the Star's innerRadius.
     *
     * @param innerRadius The Star's new innerRadius
     */
    public void setInnerRadius(double innerRadius) {

    }

    /**
     * Returns the HTML code for the shape.
     *
     * @return A String containing HTML code for the shape
     */
    public String getHTML() {
        return null;
    }

    /**
     * Returns a JSON representation of the shape.
     * Modifications to the shape should use the same attribute names.
     *
     * @return A String containing a JSON representation of the shape
     */
    public String getJSON() {
        return null;
    }

    /**
     * Modifies the shape using the specified attributes.
     *
     * @param attributes A JSON String containing the attributes to be modified
     * @return <code>true</code> if the shape had one or more of its attributes modified, <code>false</code> otherwise
     */
    public boolean modify(String attributes) {
        return false;
    }

}
