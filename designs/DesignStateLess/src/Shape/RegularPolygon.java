package Shape;


import facilitators.Coordinate;
import facilitators.RGBColour;

/**
 * Represents a regular polygon. Creates a polygon out of the nr of edges.
 * It extends the circle class.
 *
 * @see Circle
 */
public class RegularPolygon extends Circle {

    private int edgeAmount;

    /**
     * Default constructor. Creates regular polygon with default edgeAmount of 4.
     */
    public RegularPolygon(){
        super();
        this.edgeAmount=4;
    }

    /**
     * Constructor with available parameters.
     * @param name name of the shape
     * @param colour colour of the shape in RGBColour format
     * @param centerCoord anchor coordinate
     * @param radius radius in double
     * @param edgeAmount edgeAmount in int
     */
    public RegularPolygon(String name, RGBColour colour, Coordinate centerCoord, double radius, int edgeAmount){
        super(name, colour, centerCoord, radius);
        this.edgeAmount=edgeAmount;
    }

    /**
     * Returns the edge amount of the regular polygon.
     * @return amount of edges in int
     */
    public int getEdgeAmount() {
        return edgeAmount;
    }

    /**
     * Lets the user change the amount of edges for the regular polygon.
     * @param edgeAmount nr of edges in int
     */
    public void setEdgeAmount(int edgeAmount) {
        this.edgeAmount = edgeAmount;
    }
}
