package Shape;

import Management.Coordinate;
import Management.RGBColour;

/**
 * Represents a line out of 2 points: The start coordinate and the end coordinate.
 * It extends the Shape class.
 *
 * @see Shape
 */
public class Line extends Shape{

    private Coordinate startCoord;
    private Coordinate endCoord;

    /**
     * The Constructor of the Line class. Expects the name, color, start coordinate as well as the end coordinate.
     * It then creates a line out of the 2 coordinates.
     * @param name name of the shape in String
     * @param colour color of the shape in RGBColour
     * @param startCoord coordinate of the start point
     * @param endCoord coordinate of the end point
     */
    public Line(String name, RGBColour colour, Coordinate startCoord, Coordinate endCoord) {
        super(name, colour);

        this.startCoord= startCoord;
        this.endCoord = endCoord;

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
