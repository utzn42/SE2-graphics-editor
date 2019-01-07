package shapes.transform;

import facilitators.Coordinate;

/**
 * Skew component of a {@link Transformation}.
 * Holds skew values in x- and y-direction to skew an object with.
 */
public class SkewTransformation {

  private Coordinate skew;

  /**
   * Creates a SkewTransformation given skew values for the x- and y-direction as a {@link Coordinate}.
   *
   * @param skew The skew in x- and y-direction.
   */
  public SkewTransformation(Coordinate skew) {
    this.skew = skew;
  }

  /**
   * Creates a SkewTransformation given skew values for the x- and y-direction.
   *
   * @param skewX The skew in x-direction.
   * @param skewY The skew in y-direction.
   */
  public SkewTransformation(double skewX, double skewY) {
    this.skew = new Coordinate(skewX, skewY);
  }

  /**
   * Returns the skew as a {@link Coordinate}.
   *
   * @return The skew as a {@link Coordinate}.
   */
  public Coordinate getSkew() {
    return skew;
  }

  /**
   * Sets the skew using a {@link Coordinate}.
   *
   * @param skew The new skew.
   */
  public void setSkew(Coordinate skew) {
    this.skew = skew;
  }

  /**
   * Sets the skew using individual values for x and y.
   *
   * @param x The new skew in x-direction.
   * @param y The new skew in y-direction.
   */
  public void setSkew(double x, double y) {
    this.skew = new Coordinate(x, y);
  }

  String getHTMLAttribute() {
    return "skewX=(" + skew.getX() + ") skewY=(" + skew.getY() + ")";
  }

}
