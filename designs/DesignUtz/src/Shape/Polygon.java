package Shape;

import Management.Coordinate;
import Management.RGBColour;

public class Polygon extends Shape{

    private Coordinate v1Coord;
    private int[] vertices;
    private boolean regular;

    public Polygon(String name, RGBColour colour, Coordinate[] coordinates) {
        super(name, colour);

    }

    public int getVertexAmount(){
        return Integer.parseInt(null);
    }

    public int[] getVertexCoords(){
        return null;
    }

    public double getCircumference(){
        return Double.parseDouble(null);
    }

    public double getArea(){
        return Double.parseDouble(null);
    }

    public boolean isRegular(){
        return regular;
    }

}
