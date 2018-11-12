package shapes;

import facilitators.RGBColour;

public abstract class Shape {

    private RGBColour fillColour;
    private RGBColour strokeColour;
    private double strokeWidth;
    private double opacity;

    // TODO: Another idea? (read plz)
    // What if we add an anchor Coordinate here? (e.g. center for Circle, RegularPolygon, Star, Ellipse and Text (using text-anchor="middle" alignment-baseline="middle"), first point for Line & Polygon)
    // -> Could then implement move(vector) directly here

    public Shape() {
        this.fillColour = new RGBColour("#FFFFFF");
        this.strokeColour = new RGBColour("#000000");
        this.strokeWidth = 1;
        this.opacity = 1;
    }

    public Shape(RGBColour fillColour, RGBColour strokeColour, double strokeWidth, double opacity) {
        this.fillColour = fillColour;
        this.strokeColour = strokeColour;
        this.strokeWidth = strokeWidth;
        this.opacity = 1;
    }

    public RGBColour getFillColour() {
        return fillColour;
    }

    public void setFillColour(RGBColour fillColour) {
        this.fillColour = fillColour;
    }

    public void setFillColour(String fillColour) {
        this.fillColour = new RGBColour(fillColour);
    }

    public RGBColour getStrokeColour() {
        return strokeColour;
    }

    public void setStrokeColour(RGBColour strokeColour) {
        this.strokeColour = strokeColour;
    }

    public void setStrokeColour(String strokeColour) {
        this.strokeColour = new RGBColour(strokeColour);
    }

    public double getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(double strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public double getOpacity() {
        return opacity;
    }

    public void setOpacity(double opacity) {
        this.opacity = opacity;
    }

    public abstract String getHTML();

}
