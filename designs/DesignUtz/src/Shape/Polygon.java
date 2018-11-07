package Shape;

import Management.Coordinate;
import Management.RGBColour;

/**
 * Represents a polygon. Creates a polygon out of the nr of vertices and coordinates.
 * It extends the Shape class.
 *
 * @see Shape
 */
public class Polygon extends Shape{

    private Coordinate v1Coord;
    private int[] vertices;
    private boolean regular;

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
    public boolean isRegular(){
        return regular;
    }

}
