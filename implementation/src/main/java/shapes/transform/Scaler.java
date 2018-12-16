package shapes.transform;

import facilitators.Coordinate;

/**
 * Scale component of a {@link ShapeTransformer}
 */
public class Scaler {

  private Coordinate scale;

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
