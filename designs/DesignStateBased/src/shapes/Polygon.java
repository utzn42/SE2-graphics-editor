package shapes;

/**
 * Server representation of a polygon.
 *
 * @see GraphicElement
 * @see PointBasedElement
 */
public class Polygon extends PointBasedElement {

    /**
     * Creates a polygon with default attributes.
     */
    public Polygon() {

    }

    /**
     * Creates a polygon with the specified attributes.
     *
     * @param attributes A JSON String containing the attributes
     */
    public Polygon(String attributes) {

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
