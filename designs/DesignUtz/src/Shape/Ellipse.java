package Shape;

import Management.Coordinate;
import Management.RGBColour;

public class Ellipse extends Shape{

    private Coordinate f1Coord;
    private Coordinate f2Coord;
    private double radius;

    public Ellipse(String name, RGBColour colour, Coordinate f1Coord, Coordinate f2Coord, double radius) {
        super(name, colour);

        this.f1Coord=f1Coord;
        this.f2Coord=f2Coord;
        this.radius=radius;
    }

    public Coordinate getF1F2(){
        return null;
    }

    public double getCircumference(){
        return Double.parseDouble(null);
    }

    public double getArea(){
        return Double.parseDouble(null);
    }

}
