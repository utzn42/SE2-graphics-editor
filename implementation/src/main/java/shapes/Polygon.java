package shapes;

import facilitators.Coordinate;

/**
 * Represents a Polygon on the canvas.
 * This class is strongly based on {@link Line}, since they both share the same attributes.
 *
 * @see Line
 * @see Shape
 */
public class Polygon extends Line {

  /**
   * Creates a new Polygon with default attributes.
   */
  public Polygon() {
    getCoordinates().add(new Coordinate(0, 100));
  }

  /**
   * Returns a String representation of the Polygon as an SVG object in HTML.
   *
   * @return A String representation of the Polygon as an SVG object in HTML.
   */
  @Override
  public String getHTML() {
    return super.getHTML().replaceAll("polyline", "polygon");
  }

}
