package shapes;

/**
 * Server representation of a text element.
 *
 * @see GraphicElement
 */
public class TextElement extends GraphicElement{

    private Point offset;
    private String content;

    /**
     * Creates an empty text element with default attributes.
     */
    public TextElement() {

    }

    /**
     * Creates a text element with the specified attributes.
     *
     * @param attributes A JSON String containing the attributes
     */
    public TextElement(String attributes) {

    }

    /**
     * Returns the TextElement's offset.
     *
     * @return The TextElement's offset
     */
    public Point getOffset() {
        return offset;
    }

    /**
     * Sets the TextElement's offset.
     *
     * @param offset The TextElement's new offset
     */
    public void setOffset(Point offset) {

    }

    /**
     * Returns the TextElement's content.
     *
     * @return The TextElement's content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the TextElement's content.
     *
     * @param content The TextElement's new content
     */
    public void setContent(String content) {

    }

    /**
     * Returns the HTML code for the text element.
     *
     * @return A String containing HTML code for the text element
     */
    public String getHTML() {
        return null;
    }

    /**
     * Returns a JSON representation of the text element.
     * Modifications to the text element should use the same attribute names.
     *
     * @return A String containing a JSON representation of the text element
     */
    public String getJSON() {
        return null;
    }

    /**
     * Modifies the text element using the specified attributes.
     *
     * @param attributes A JSON String containing the attributes to be modified
     * @return <code>true</code> if the shape had one or more of its attributes modified, <code>false</code> otherwise
     */
    public boolean modify(String attributes) {
        return false;
    }

    /**
     * Moves the text element by the specified vector.
     *
     * @param vector The move vector
     * @see Point
     */
    public void move(Point vector) {

    }

}
