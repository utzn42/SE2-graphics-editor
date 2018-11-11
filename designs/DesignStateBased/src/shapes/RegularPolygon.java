package shapes;

/**
 * Server representation of a regular polygon.
 * Note that this shape uses a radius to determine its size, so it is not stretchable.
 *
 * @see GraphicElement
 * @see RadialElement
 */
public class RegularPolygon extends RadialElement {

    private int numberOfEdges;

    /**
     * Creates a regular polygon with default attributes.
     */
    public RegularPolygon() {

    }

    /**
     * Creates a regular polygon with the specified attributes.
     *
     * @param attributes A JSON String containing the attributes
     */
    public RegularPolygon(String attributes) {

    }

    /**
     * Returns the RegularPolygon's number of edges.
     *
     * @return The RegularPolygon's number of edges
     */
    public int getNumberOfEdges() {
        return numberOfEdges;
    }

    /**
     * Sets the RegularPolygon's number of edges.
     *
     * @param numberOfEdges the RegularPolygon's new number of edges
     */
    public void setNumberOfEdges(int numberOfEdges) {

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
