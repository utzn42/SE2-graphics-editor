package Shapes;

import Base.Point;

import java.util.List;

/**
 * This interface represents every shape. It defines the basic operations and attributes of a shape.
 * Every shape extends from this interface.
 * It uses the class Point. The relation is an aggregation A graphic Element cannot exist without a Point object.
 *
 * @see Point
 * @see PointBasedElement
 * @see RadialElement
 * @see RectangularElement
 */

public interface GraphicElement {

    /**
     * Moves the element from one point to another.
     * @param point destination point
     */
    void move(Point point);

    /**
     * Returns the HTML-Code of the element.
     * @return returns a String with HTML-Code
     */
    String getHTML();

    /**
     * Returns a list of possible operations.
     * @return returns a list of Strings
     */
    List<String> getModifiers();

    /**
     * Modifies the element.
     * @param modifier parameters of the modification
     */
    void modify(String modifier);

}
