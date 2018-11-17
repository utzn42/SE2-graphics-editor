package shapes;

import facilitators.Coordinate;
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

  @Override
  protected List<Coordinate> getCoordinates() {
    List<Coordinate> coordinates = new ArrayList<>();
    for (int i = 0; i < getEdgeAmount(); ++i) {
      float xOuter = (float) (
          getRadius() * Math.sin((Math.PI + (2 * i * Math.PI)) / getEdgeAmount())
              + getCenter().getX());
      float yOuter = (float) (
          getRadius() * Math.cos((Math.PI + (2 * i * Math.PI)) / getEdgeAmount())
              + getCenter().getY());
      coordinates.add(new Coordinate(xOuter, yOuter));
      float xInner = (float) (
          innerRadius * Math.sin((Math.PI + (2 * (i + 0.5) * Math.PI)) / getEdgeAmount())
              + getCenter().getX());
      float yInner = (float) (
          innerRadius * Math.cos((Math.PI + (2 * (i + 0.5) * Math.PI)) / getEdgeAmount())
              + getCenter().getY());
      coordinates.add(new Coordinate(xInner, yInner));
    }
    return coordinates;
  }

}
