package shapes.transform;

import facilitators.Coordinate;

/**
 * Scale component of a {@link ShapeTransformer}.
 * Holds factors in x- and y-direction to scale an object by.
 */
public class Scaler {

  private Coordinate scale;

  /**
   * Creates a Scaler using a {@link Coordinate}.
   *
   * @param scale A {@link Coordinate} specifying the scalars in x- and y-direction.
   */
  public Scaler(Coordinate scale) {
    this.scale = scale;
  }

  /**
   * Creates a Scaler using the given values for x- and y-scaling.
   *
   * @param scaleX The factor by which to scale in x-direction.
   * @param scaleY The factor by which to scale in y-direction.
   */
  public Scaler(double scaleX, double scaleY) {
    this.scale = new Coordinate(scaleX, scaleY);
  }

  /**
   * Returns the scale as a {@link Coordinate}
   *
   * @return The scale as a {@link Coordinate}
   */
  public Coordinate getScale() {
    return scale;
  }

  /**
   * Sets the scale using a {@link Coordinate}
   *
   * @param scale The new scale
   */
  public void setScale(Coordinate scale) {
    this.scale = scale;
  }

  /**
   * Sets the scale using individual values for x and y
   *
   * @param x The new scale in x-direction
   * @param y The new scale in y-direction
   */
  public void setScale(double x, double y) {
    this.scale = new Coordinate(x, y);
  }

}
