package Shapes;

import Base.Point;

import java.util.List;

/**
 * Creates a circle out of a point and the radius. Extends from RadialElement.
 *
 * @see Shapes.GraphicElement
 * @see Shapes.RadialElement
 */

public class Circle extends RadialElement {

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
     * Modifies the Circle.
     * @param modifier parameters of the modification
     */
    public void modify(String modifier) {

    }

    /**
     * Constructor of Circle. Creates the circle out of the center point and the radius.
     * @param circleList contains center point and radius
     */
    public Circle(List<String> circleList){

    }

    /**
     * Moves the circle from one point to another. Overrides method from RadialElement.
     * @param point destination point
     */
    @Override
    public void move(Point point) {
        super.move(point);
    }

}
