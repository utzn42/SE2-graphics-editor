package Shapes;

import Base.Point;

import java.util.List;

/**
 * This class creates a basic text field object. It implements GraphicElement.
 *
 * @see Shapes.GraphicElement
 */
public class TextElement implements GraphicElement{

    private Point offset;
    private String content;

    /**
     * Moves the text element from one point to another.
     * @param point destination point
     */
    public void move(Point point) {

    }

    /**
     * Returns HTML-Code.
     * @return returns a String with HTML-Code
     */
    public String getHTML() {
        return null;
    }

    /**
     * Returns a list of possible operations.
     * @return returns a list of Strings
     */
    public List<String> getModifiers() {
        return null;
    }

    /**
     * Modifies the text element.
     * @param modifier parameters of the modification
     */
    public void modify(String modifier) {

    }
}
