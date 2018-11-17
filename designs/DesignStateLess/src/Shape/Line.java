package Shape;

import facilitators.Coordinate;
import facilitators.RGBColour;

/**
 * Represents a line out of 2 points: The start coordinate and the end coordinate.
 * It extends the Shape class.
 *
 * @see Shape
 */
public class Line extends Shape{

    private Coordinate[] pCoord;

    /**
     * The Constructor of the Line class. Expects the name, color, start coordinate as well as the end coordinate.
     * It then creates a line out of the 2 coordinates.
     * @param name name of the shape in String
     * @param colour color of the shape in RGBColour
     * @param pCoord coordinates of the points of which the line consists
     */
    public Line(String name, RGBColour colour, Coordinate[] pCoord) {
        super(name, colour);

        this.pCoord=pCoord;

    }

    /**
     * Default constructor with default values for the line.
     */
    public Line(){

        super("line", new RGBColour(new int[]{1, 2, 3}));
        this.pCoord[0]=new Coordinate(10,10);
        this.pCoord[1]= new Coordinate(20,20);

    }

    /**
     * Returns the Length of the line.
     * @return returns the length of the line in double
     */
    public double getLength(){
        return Double.parseDouble(null);
    }

    /**
     * Returns the coordinates of the start and the end point.
     * @return returns an array of coordinates
     */
    public Coordinate[] getCoords(){
        return null;
    }

}
