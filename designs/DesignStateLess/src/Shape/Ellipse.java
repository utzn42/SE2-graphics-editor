package Shape;

import management.Coordinate;
import management.RGBColour;

/**
 * Creates an ellipse out of two coordinates and a radius. It extends the Shape class.
 *
 * @see Shape
 */
public class Ellipse extends Shape{

    private Coordinate f1Coord;
    private Coordinate f2Coord;
    private double radius;

    /**
     * The constructor of the Ellipse class takes the name, colour, f1coordinate and f2coordinate and creates the ellipse.
     *
     * @param name name of the shape in String
     * @param colour color of the shape in RGBColour
     * @param f1Coord coordinates of f1
     * @param f2Coord coordinates of f2
     * @param radius radius in double
     */
    public Ellipse(String name, RGBColour colour, Coordinate f1Coord, Coordinate f2Coord, double radius) {
        super(name, colour);

        this.f1Coord=f1Coord;
        this.f2Coord=f2Coord;
        this.radius=radius;
    }

    /**
     * Default constructor with default values for the ellipse.
     */
    public Ellipse(){

        super("ellipse", new RGBColour(new int[]{1, 2, 3}));
        this.f1Coord=new Coordinate(10,10);
        this.f2Coord=new Coordinate(20,20);
        this.radius=10;

    }

    /**
     * Returns the coordinates of f1 and f2.
     * @return returns a coordinate
     */
    public Coordinate getF1F2(){
        return null;
    }

    /**
     * Returns the circumference of the ellipse.
     * @return returns the circumference in double
     */
    public double getCircumference(){
        return Double.parseDouble(null);
    }

    /**
     * Returns the area occupied by the ellipse.
     * @return returns the area in double
     */
    public double getArea(){
        return Double.parseDouble(null);
    }

}
