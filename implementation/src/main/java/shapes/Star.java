package shapes;

import main.facilitators.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Star extends RegularPolygon {

    private double innerRadius;

    public Star() {
        innerRadius = 25;
    }

    public Star(double innerRadius) {
        this.innerRadius = innerRadius;
    }

    public double getInnerRadius() {
        return innerRadius;
    }

    public void setInnerRadius(double innerRadius) {
        this.innerRadius = innerRadius;
    }

    public List<Coordinate> getStarCoordinates() {
        List<Coordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < getEdgeAmount(); ++i) {
            float xOuter = (float) (getRadius() * Math.sin((2 * i * Math.PI)/getEdgeAmount()) + getCenter().getX());
            float yOuter = (float) (getRadius() * Math.cos((2 * i * Math.PI)/getEdgeAmount()) + getCenter().getY());
            coordinates.add(new Coordinate(xOuter, yOuter));
            float xInner = (float) (innerRadius * Math.sin((2 * (i + 0.5) * Math.PI)/getEdgeAmount()) + getCenter().getX());
            float yInner = (float) (innerRadius * Math.cos((2 * (i + 0.5) * Math.PI)/getEdgeAmount()) + getCenter().getY());
            coordinates.add(new Coordinate(xInner, yInner));
        }
        return coordinates;
    }

    @Override
    public String getHTML() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Coordinate coordinate : getStarCoordinates()) {
            stringBuilder.append(coordinate.getX())
                    .append(",")
                    .append(coordinate.getY())
                    .append(" ");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        String points = stringBuilder.toString();
        return "<polygon" +
                " points=\"" + points + "\"" +
                " fill=\"" + getFillColour() + "\"" +
                " stroke=\"" + getStrokeColour() + "\"" +
                " stroke-width=\"" + getStrokeWidth() + "\"" +
                " opacity=\"" + getOpacity() + "\"" +
                "></polygon>";
    }
}
