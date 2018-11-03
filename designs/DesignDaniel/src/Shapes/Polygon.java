package Shapes;

import Base.Point;

import java.util.List;

/**
 * Shape with multiple points. It extends PointBasedElement.
 *
 * @see Shapes.GraphicElement
 * @see Shapes.PointBasedElement
 */
public class Polygon extends PointBasedElement {

    /**
     * Returns the HTML-Code for the shape.
     * @return returns String with HTML-Code
     */
    public String getHTML() {
        return null;
    }

    /**
     * Returns a list of possible operations.
     * @return returns a list of Strings.
     */
    public List<String> getModifiers() {
        return null;
    }

    /**
     * Modifies the object.
     * @param modifier parameters of the modification
     */
    public void modify(String modifier) {

    }

    /**
     * Constructor of Polygon. Expects multiple points and turns them into a Polygon.
     * @param polygonList list of Strings with attributes of points
     */
    public Polygon(List<String> polygonList){

    }

    /**
     * Moves the object from one point to another. Overrides method from PointBasedElement.
     * @param point destination point
     */
    @Override
    public void move(Point point) {
        super.move(point);
    }
}
