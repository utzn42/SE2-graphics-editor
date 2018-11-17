package Shape;

import facilitators.Coordinate;
import facilitators.RGBColour;

/**
 * Represents a star. Creates a star out of an inner radius and the other parameters of the super classes.
 * It extends the regular polygon class.
 *
 * @see RegularPolygon
 * @see Circle
 */
public class Star extends RegularPolygon {

    private double innerRadius;

    /**
     * Default constructor. Creates a star with default inner radius of 10.
     */
    public Star(){
        super();
        this.innerRadius=10;
    }

    /**
     * Constructor with available parameters.
     * @param name name of the shape
     * @param colour colour of the shape in RGBColour format
     * @param centerCoord anchor coordinate
     * @param radius radius in double
     * @param edgeAmount edgeAmount in int
     * @param innerRadius inner radius in double
     */
    public Star(String name, RGBColour colour, Coordinate centerCoord, double radius, int edgeAmount, double innerRadius){
        super(name, colour, centerCoord, radius, edgeAmount);
        this.innerRadius=innerRadius;
    }

    /**
     * Returns the inner radius of the star.
     * @return innerRadius returns the inner radius in double
     */
    public double getInnerRadius() {
        return innerRadius;
    }

    /**
     * Returns the outter and inner radius.
     * @return returns the outter and inner radius in an double array
     */
    public double[] getRadii(){
        return new double[]{getRadius(), getInnerRadius()};
    }

    /**
     * Lets the user set the inner radius.
     * @param innerRadius inner radius in double
     */
    public void setInnerRadius(double innerRadius) {
        this.innerRadius = innerRadius;
    }
}
