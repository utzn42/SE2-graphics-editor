package shapes.transform;

import facilitators.Coordinate;

/**
 * Scale component of a {@link Transformation}.
 * Holds factors in x- and y-direction to scale an object by.
 */
public class ScaleTransformation {

  private Coordinate scale;
  //TODO: shapes.transform.ScaleTransformation: Add scale origin

  /**
   * Creates a ScaleTransformation using a {@link Coordinate}.
   *
   * @param scale A {@link Coordinate} specifying the scalars in x- and y-direction.
   */
  public ScaleTransformation(Coordinate scale) {
    this.scale = scale;
  }

  /**
   * Creates a ScaleTransformation using the given values for x- and y-scaling.
   *
   * @param scaleX The factor by which to scale in x-direction.
   * @param scaleY The factor by which to scale in y-direction.
   */
  public ScaleTransformation(double scaleX, double scaleY) {
    this.scale = new Coordinate(scaleX, scaleY);
  }

  /**
   * Creates a ScaleTransformation using a uniform value for both x- and y-scaling.
   *
   * @param scale The factor by which to scale in both directions.
   */
  public ScaleTransformation(double scale) {
    this.scale = new Coordinate(scale, scale);
  }

  /**
   * Returns the scale as a {@link Coordinate}.
   *
   * @return The scale as a {@link Coordinate}.
   */
  public Coordinate getScale() {
    return scale;
  }

  /**
   * Sets the scale using a {@link Coordinate}.
   *
   * @param scale The new scale.
   */
  public void setScale(Coordinate scale) {
    this.scale = scale;
  }

  /**
   * Sets the scale using individual values for x and y.
   *
   * @param x The new scale in x-direction.
   * @param y The new scale in y-direction.
   */
  public void setScale(double x, double y) {
    this.scale = new Coordinate(x, y);
  }

  /**
   * Sets the scale for x and y uniformly using the provided value.
   *
   * @param scale The new scale in x- and y-direction.
   */
  public void setScale(double scale) {
    this.scale = new Coordinate(scale, scale);
  }

  String getHTMLAttribute() {
    return "scale=(" + scale.getX() + " " + scale.getY() + ")";
  }

}
