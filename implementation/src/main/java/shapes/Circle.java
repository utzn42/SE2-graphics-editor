package shapes;

import main.facilitators.Coordinate;

public class Circle extends Shape {

    private Coordinate center;
    private double radius;

    public Circle() {
        this.center = new Coordinate(50, 50);
        this.radius = 50;
    }

    public Circle(Coordinate center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Coordinate getCenter() {
        return center;
    }

    public void setCenter(Coordinate center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public String getHTML() {
        return "<circle" +
                " cx=\"" + center.getX() + "\"" +
                " cy=\"" + center.getY() + "\"" +
                " r=\"" + radius + "\"" +
                " fill=\"" + getFillColour() + "\"" +
                " stroke=\"" + getStrokeColour() + "\"" +
                " stroke-width=\"" + getStrokeWidth() + "\"" +
                " opacity=\"" + getOpacity() + "\"" +
                "></circle>";
    }

}
