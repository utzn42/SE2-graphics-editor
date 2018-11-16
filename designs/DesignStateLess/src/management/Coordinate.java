package management;

/**
 * Every shape needs at least one coordinate. It represents a point in a two-dimensional graph.
 * A point consists out of a x and y coordinate.
 *
 * @see Shape.Shape
 */
public class Coordinate {

    private int xCoord;
    private int yCoord;

    /**
     * Default constructor of the Coordinate class.
     */
    public Coordinate(){

    }

    /**
     * Constructor which expects the x and y coordinate values.
     *
     * @param xCoord x value
     * @param yCoord y value
     */
    public Coordinate(int xCoord, int yCoord){
        this.xCoord=xCoord;
        this.yCoord=yCoord;
    }

    /**
     * Returns the value of the x coordinate.
     * @return returns the value of the x coordinate in integer
     */
    public int getxCoord() {
        return xCoord;
    }

    /**
     * Returns the value of the y coordinate.
     * @return returns the value of the y coordinate in integer
     */
    public int getyCoord() {
        return yCoord;
    }

    /**
     * Lets you set the x coordinate to the value received from the parameter.
     * @param xCoord value of the x coordinate in integer
     */
    private void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    /**
     * Lets you set the y coordinate to the value received from the parameter.
     * @param yCoord value of the y coordinate in integer
     */
    private void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    /**
     * Lets you set the x and y coordinates to the values received from the parameter.
     * @param xy value of the x and y coordinates in integer array
     */
    public void setXY(int[] xy){
        setxCoord(xy[0]);
        setyCoord(xy[1]);
    }
}
