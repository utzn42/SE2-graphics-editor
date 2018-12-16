package shapes.transform;

import facilitators.Coordinate;

/**
 * Skew component of a {@link ShapeTransformer}
 */
public class Skewer {

  private Coordinate skew;

  /**
   * Returns the skew as a {@link Coordinate}
   *
   * @return The skew as a {@link Coordinate}
   */
  public Coordinate getSkew() {
    return skew;
  }

  /**
   * Sets the skew using a {@link Coordinate}
   *
   * @param skew The new skew
   */
  public void setSkew(Coordinate skew) {
    this.skew = skew;
  }

  /**
   * Sets the skew using individual values for x and y
   *
   * @param x The new skew in x-direction
   * @param y The new skew in y-direction
   */
  public void setSkew(double x, double y) {
    this.skew = new Coordinate(x, y);
  }

}
