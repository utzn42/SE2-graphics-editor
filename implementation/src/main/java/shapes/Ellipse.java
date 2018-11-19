package shapes;

import facilitators.Coordinate;

/**
 * Represents an Ellipse on the canvas.
 * This class extends {@link Shape} by a center {@link Coordinate}, an x- and a y-radius.
 *
 * @see Shape
 */
public class Ellipse extends Shape {

  private Coordinate center;
  private double radiusX;
  private double radiusY;

  /**
   * Creates a new Ellipse with default attributes.
   */
  public Ellipse() {
    this.center = new Coordinate(50, 50);
    this.radiusX = 50;
    this.radiusY = 50;
  }

  /**
   * Creates a new Ellipse with the specified attributes and default {@link Shape} attributes.
   *
   * @param center The center {@link Coordinate} of the Ellipse.
   * @param radiusX The Ellipse's radius in the x-direction.
   * @param radiusY The Ellipse's radius in the y-direction.
   */
  public Ellipse(Coordinate center, double radiusX, double radiusY) {
    this.center = center;
    this.radiusX = radiusX;
    this.radiusY = radiusY;
  }

  /**
   * Returns the center {@link Coordinate} of the Ellipse.
   *
   * @return The center {@link Coordinate} of the Ellipse.
   */
  public Coordinate getCenter() {
    return center;
  }

  /**
   * Sets the center of the Ellipse.
   *
   * @param center The new center {@link Coordinate} for the Ellipse.
   */
  public void setCenter(Coordinate center) {
    this.center = center;
  }

  /**
   * Returns the Ellipse's radius in the x-direction.
   *
   * @return The Ellipse's radius in the x-direction.
   */
  public double getRadiusX() {
    return radiusX;
  }

  /**
   * Sets the Ellipse's radius in the x-direction.
   *
   * @param radiusX The Ellipse's new radius in the x-direction.
   */
  public void setRadiusX(double radiusX) {
    this.radiusX = radiusX;
  }

  /**
   * Returns the Ellipse's radius in the y-direction.
   *
   * @return The Ellipse's radius in the y-direction.
   */
  public double getRadiusY() {
    return radiusY;
  }

  /**
   * Sets the Ellipse's radius in the y-direction.
   *
   * @param radiusY The Ellipse's new radius in the y-direction.
   */
  public void setRadiusY(double radiusY) {
    this.radiusY = radiusY;
  }

  /**
   * Returns a String representation of the Ellipse's attributes as chained HTML attributes.
   *
   * @return A String representation of the Ellipse's attributes as chained HTML attributes.
   *
   * @see Shape#getHTMLAttributes()
   */
  @Override
  protected String getHTMLAttributes() {
    return super.getHTMLAttributes() +
        " cx=\"" + center.getX() + "\"" +
        " cy=\"" + center.getY() + "\"" +
        " rx=\"" + radiusX + "\"" +
        " ry=\"" + radiusY + "\"";
  }

  /**
   * Returns a String representation of the Ellipse as an SVG object in HTML.
   *
   * @return A String representation of the Ellipse as an SVG object in HTML.
   */
  @Override
  public String getHTML() {
    return "<ellipse " + getHTMLAttributes() + "></ellipse>";
  }
}
