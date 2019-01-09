package shapes;

import facilitators.Coordinate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Star on the canvas.
 * It extends {@link RegularPolygon} by an inner radius (making the inherited radius the outer radius).
 * The inner and outer radius determine the distance of the inner and outer vertices to the center respectively.
 * This implementation of Star is equilateral and non-intersecting, but not equiangular.
 *
 * @see RegularPolygon
 * @see Circle
 * @see Shape
 */
public class Star extends RegularPolygon {

  private static final long serialVersionUID = 1L;
  private static final ShapeType shapeType = ShapeType.STAR;

  private double innerRadius;

  /**
   * Creates a new Star with default attributes.
   */
  public Star() {
    innerRadius = 25;
  }

  /**
   * Creates a new Star with the specified attributes and default {@link RegularPolygon} attributes.
   *
   * @param innerRadius The radius of the inner vertices of the Star.
   */
  public Star(double innerRadius) {
    this.innerRadius = innerRadius;
  }

  /**
   * Returns the radius of the inner vertices of the Star.
   *
   * @return The radius of the inner vertices of the Star.
   */
  public double getInnerRadius() {
    return innerRadius;
  }

  /**
   * Sets the radius of the inner vertices of the Star.
   *
   * @param innerRadius The new radius of the inner vertices of the Star.
   */
  public void setInnerRadius(double innerRadius) {
    this.innerRadius = innerRadius;
  }

  /**
   * Calculates the {@link Coordinate Coordinates} of the Star's vertices and returns them as a {@link List}.
   *
   * @return A {@link List} of the {@link Coordinate Coordinates} of the Star's vertices.
   */
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

  @Override
  public ShapeType getShapeType() {
    return shapeType;
  }

}
