package shapes;

/**
 * Server representation of an ellipse.
 *
 * @see GraphicElement
 * @see RectangularElement
 */
public class Ellipse extends RectangularElement {

    /**
     * Creates an ellipse with default attributes.
     */
    public Ellipse() {

    }

    /**
     * Creates an ellipse with the specified attributes.
     *
     * @param attributes A JSON String containing the attributes
     */
    public Ellipse(String attributes) {

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
