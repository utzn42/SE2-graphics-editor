package Shapes;

import Base.Point;

import java.util.List;

/**
 * Creates an ellipse out of a rectangle. Extends RectangularElement.
 *
 * @see Shapes.GraphicElement
 * @see Shapes.RectangularElement
 */
public class Ellipse extends RectangularElement {

    /**
     * Returns HTML-Code.
     * @return returns String with HTML-Code
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
     * Modifies object.
     * @param modifier parameters of the modification
     */
    public void modify(String modifier) {

    }

    /**
     * Constructor which creates an ellipse out of a rectangle.
     * @param ellipseList a list of points which form a rectangle
     */
    public Ellipse(List<String> ellipseList){

    }

    /**
     * Moves ellipse from one point to another. Overrides method from RectangularElement.
     * @param point destination point
     */
    @Override
    public void move(Point point) {
        super.move(point);
    }
}
