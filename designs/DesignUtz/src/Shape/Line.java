package Shape;

import Management.Coordinate;
import Management.RGBColour;

public class Line extends Shape{

    private Coordinate startCoord;
    private Coordinate endCoord;

    public Line(String name, RGBColour colour, Coordinate startCoord, Coordinate endCoord) {
        super(name, colour);

        this.startCoord= startCoord;
        this.endCoord = endCoord;

    }

    public double getLength(){
        return Double.parseDouble(null);
    }

    public Coordinate[] getCoords(){
        return null;
    }

}
