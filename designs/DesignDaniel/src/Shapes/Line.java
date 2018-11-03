package Shapes;

import Base.Point;

import java.util.List;

/**
 * Creates a line out of two points. Extends PointBasedElement.
 *
 * @see Shapes.PointBasedElement
 * @see Shapes.GraphicElement
 */
public class Line extends PointBasedElement {

    /**
     * Returns the HTML-Code.
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
     * Modifies the Line.
     * @param modifier parameters of the modification
     */
    public void modify(String modifier) {

    }

    /**
     * Constructor which creates a line out of two points.
     * @param lineList contains the list of the two points
     */
    public Line(List<String> lineList){

    }

    /**
     * Moves object from one point to another. Overrides method from PointBasedElement.
     * @param point destination point
     */
    @Override
    public void move(Point point) {
        super.move(point);
    }
}
