package Shape;

import facilitators.Coordinate;
import facilitators.RGBColour;

/**
 * Represents a polygon. Creates a polygon out of the nr of vertices and coordinates.
 * It extends the Shape class.
 *
 * @see Shape
 */
public class Polygon extends Shape{

    private Coordinate[] v1Coord;

    /**
     * The constructor expects the name, color and coordinates.
     * @param name name of the shape in String
     * @param colour color of the shape in RGBColour
     * @param coordinates coordinates of the polygon
     */
    public Polygon(String name, RGBColour colour, Coordinate[] coordinates) {
        super(name, colour);

    }

    /**
     * Default constructor with default values for the polygon.
     */
    public Polygon(){

        super("polygon", new RGBColour(new int[]{1, 2, 3}));
        this.v1Coord[0]=new Coordinate(10,10);
        this.v1Coord[1]= new Coordinate(20,5);
        this.v1Coord[0]=new Coordinate(30,10);
        this.v1Coord[1]= new Coordinate(40,20);

    }

    /**
     * Returns the amount of vertexes.
     * @return returns amount of vertexes in integer
     */
    public int getVertexAmount(){
        return Integer.parseInt(null);
    }

    /**
     * Returns the coordinates of the vertexes.
     * @return returns the coordinates of the vertexes in an array of integer
     */
    public int[] getVertexCoords(){
        return null;
    }

    /**
     * Returns the circumference of the polygon.
     * @return returns the circumference in double
     */
    public double getCircumference(){
        return Double.parseDouble(null);
    }

    /**
     * Returns the area which is occupied by the polygon.
     * @return returns the area in double
     */
    public double getArea(){
        return Double.parseDouble(null);
    }

    /**
     * Returns true or false whether the polygon is regular or not.
     * @return returns T/F in boolean
     */

}
