package Shape;

import Management.Coordinate;
import Management.RGBColour;

public class Circle extends Shape{

    private Coordinate centerCoord;
    private double radius;

    public Circle(String name, RGBColour colour, Coordinate centerCoord, double radius) {
        super(name, colour);

        this.centerCoord=centerCoord;
        this.radius=radius;
    }

    public Coordinate getCenterCoord() {
        return centerCoord;
    }

    public double getRadius() {
        return radius;
    }

    public double getCircumference(){
        return Double.parseDouble(null);
    }

    public double getArea(){
        return Double.parseDouble(null);
    }
}
