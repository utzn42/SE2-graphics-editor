package Shape;

import Management.Coordinate;
import Management.RGBColour;

/**
 * Creates a circle out of a center coordinate and a radius.
 * It extends the Shape class.
 *
 * @see Shape
 */
public class Circle extends Shape{

    private Coordinate centerCoord;
    private double radius;

    /**
     * The constructor creates a circle out of the name, color, centerCoord, and the radius.
     * @param name name of the shape in String
     * @param colour color of the shape in RGBColour
     * @param centerCoord coordinates of the center
     * @param radius radius in double
     */
    public Circle(String name, RGBColour colour, Coordinate centerCoord, double radius) {
        super(name, colour);

        this.centerCoord=centerCoord;
        this.radius=radius;
    }


    /**
     * Default constructor with default values for the circle.
     */
    public Circle(){

        super("circle", new RGBColour(new int[]{1, 2, 3}));
        this.centerCoord=new Coordinate(10,10);
        this.radius=10;

    }

    /**
     * Returns the center coordinates.
     * @return returns a Coordinate object
     */
    public Coordinate getCenterCoord() {
        return centerCoord;
    }

    /**
     * Returns the radius of the circle.
     * @return returns the radius in double
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Returns the circumference of the circle
     * @return returns the circumference in double
     */
    public double getCircumference(){
        return Double.parseDouble(null);
    }

    /**
     * Returns the value of the area occupied by the circle.
     * @return returns the area in double
     */
    public double getArea(){
        return Double.parseDouble(null);
    }
}
