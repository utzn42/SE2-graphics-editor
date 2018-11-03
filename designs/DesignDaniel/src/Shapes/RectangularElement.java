package Shapes;

import Base.Point;

/**
 * Superclass of rectangular elements. It implements GraphicElement.
 *
 * @see GraphicElement
 * @see Ellipse
 */

public abstract class RectangularElement implements GraphicElement{

    private Point offset;
    private Point size;


    /**
     * Moves the object from one point to another.
     * @param point destination point
     */
    public void move(Point point) {

    }
}
