package shapes;

import facilitators.Coordinate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a regular Polygon on the canvas.
 * It is equilateral and equiangular.
 * This implementation of RegularPolygon is always convex (non-intersecting).
 * Therefore, it only extends {@link Circle} by one additional attribute, the number of edges.
 *
 * @see Circle
 * @see Shape
 * @see Star
 */
public class RegularPolygon extends Circle {

  private int edgeAmount;

  /**
   * Creates a new RegularPolygon with default attributes.
   */
  public RegularPolygon() {
    edgeAmount = 5;
  }

  /**
   * Creates a new RegularPolygon with the specified attributes and default {@link Circle} attributes.
   *
   * @param edgeAmount The number of edges of the RegularPolygon.
   */
  public RegularPolygon(int edgeAmount) {
    this.edgeAmount = edgeAmount;
  }

  /**
   * Returns the number of edges of the RegularPolygon.
   *
   * @return The number of edges of the RegularPolygon.
   */
  public int getEdgeAmount() {
    return edgeAmount;
  }

  /**
   * Sets the number of edges of the RegularPolygon.
   *
   * @param edgeAmount The new number of edges for the RegularPolygon.
   */
  public void setEdgeAmount(int edgeAmount) {
    this.edgeAmount = edgeAmount;
  }

  /**
   * Calculates the {@link Coordinate Coordinates} of the RegularPolygon's vertices and returns them as a {@link List}.
   *
   * @return A {@link List} of the {@link Coordinate Coordinates} of the RegularPolygon's vertices.
   */
  protected List<Coordinate> getCoordinates() {
    List<Coordinate> coordinates = new ArrayList<>();
    for (int i = 0; i < edgeAmount; ++i) {
      float x = (float) (getRadius() * Math.sin((Math.PI + (2 * i * Math.PI)) / edgeAmount)
          + getCenter()
          .getX());
      float y = (float) (getRadius() * Math.cos((Math.PI + (2 * i * Math.PI)) / edgeAmount)
          + getCenter()
          .getY());
      coordinates.add(new Coordinate(x, y));
    }
    return coordinates;
  }

  /**
   * Returns a String representation of the RegularPolygon's attributes as chained HTML attributes.
   *
   * @return A String representation of the RegularPolygon's attributes as chained HTML attributes.
   *
   * @see Shape#getHTMLAttributes()
   */
  @Override
  protected String getHTMLAttributes() {
    StringBuilder stringBuilder = new StringBuilder();
    for (Coordinate coordinate : getCoordinates()) {
      stringBuilder.append(coordinate.getX())
          .append(",")
          .append(coordinate.getY())
          .append(" ");
    }
    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
    String points = stringBuilder.toString();
    return super.getHTMLAttributes() + " points=\"" + points + "\"";
  }

  /**
   * Returns a String representation of the RegularPolygon as an SVG object in HTML.
   *
   * @return A String representation of the RegularPolygon as an SVG object in HTML.
   */
  @Override
  public String getHTML() {
    return "<polygon " + getHTMLAttributes() + "></polygon>";
  }
}
