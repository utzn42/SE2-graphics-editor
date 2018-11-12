package shapes;

import facilitators.Coordinate;

public class Ellipse extends Shape {

    private Coordinate center;
    private double radiusX;
    private double radiusY;

    public Ellipse() {
        this.center = new Coordinate(50, 50);
        this.radiusX = 50;
        this.radiusY = 50;
    }

    public Ellipse(Coordinate center, double radiusX, double radiusY) {
        this.center = center;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }

    public Coordinate getCenter() {
        return center;
    }

    public void setCenter(Coordinate center) {
        this.center = center;
    }

    public double getRadiusX() {
        return radiusX;
    }

    public void setRadiusX(double radiusX) {
        this.radiusX = radiusX;
    }

    public double getRadiusY() {
        return radiusY;
    }

    public void setRadiusY(double radiusY) {
        this.radiusY = radiusY;
    }

    @Override
    public String getHTML() {
        return "<ellipse" +
                " cx=\"" + center.getX() + "\"" +
                " cy=\"" + center.getY() + "\"" +
                " rx=\"" + radiusX + "\"" +
                " ry=\"" + radiusY + "\"" +
                " fill=\"" + getFillColour() + "\"" +
                " stroke=\"" + getStrokeColour() + "\"" +
                " stroke-width=\"" + getStrokeWidth() + "\"" +
                " opacity=\"" + getOpacity() + "\"" +
                "></ellipse>";
    }
}
