package Shapes;

import Base.Point;

import java.util.List;

/**
 * Creates a star out of a regular polygon by adding an inner radius. Extends from Regular Polygon.
 *
 * @see Shapes.RadialElement
 * @see Shapes.GraphicElement
 * @see Shapes.RegularPolygon
 */
public class Star extends RegularPolygon {

    private double innerRadius;

    /**
     * Constructor of Star. Creates a star out of a regular polygon by adding an inner radius.
     * @param starList contains inner radius
     */
    public Star(List<String> starList) {
        super(starList);
    }

    /**
     * Moves star from one point to another.
     * @param point destination point
     */
    @Override
    public void move(Point point) {
        super.move(point);
    }

    /**
     * Modifies the star.
     * @param modifier parameters of the modification
     */
    @Override
    public void modify(String modifier) {
        super.modify(modifier);
    }

    /**
     * Returns the HTML-Code.
     * @return returns a String with HTML-Code
     */
    @Override
    public String getHTML() {
        return super.getHTML();
    }

    /**
     * Returns a list of possible operations.
     * @return returns a list of Strings
     */
    @Override
    public List<String> getModifiers() {
        return super.getModifiers();
    }


}
