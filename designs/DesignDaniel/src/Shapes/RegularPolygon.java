package Shapes;

import Base.Point;

import java.util.List;

/**
 * Creates a regular polygon out of the number of edges and the radius to these from the center. Extends RadialElement.
 *
 * @see Shapes.RadialElement
 * @see Shapes.GraphicElement
 */
public abstract class RegularPolygon extends RadialElement {

    private int numberOfEdges;

    /**
     * Returns the HTML-Code.
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
     * Modifies the regular polygon.
     * @param modifier parameters of the modification
     */
    public void modify(String modifier) {

    }

    /**
     * Constructor of the regular polygon. Creates the polygon out of the number of edges, the center and the radius to the edges from the center.
     * @param regularPolygonList contains nr of edges, center point, radius
     */
    RegularPolygon(List<String> regularPolygonList){

    }

    /**
     * Moves the regular polygon from one point to another.
     * @param point destination point
     */
    @Override
    public void move(Point point) {
        super.move(point);
    }
}
