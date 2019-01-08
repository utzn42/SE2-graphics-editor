package shapes.transform.atomic;

import shapes.transform.Transformation;

/**
 * Skew component of a {@link Transformation}.
 * Holds a skew angle and axis to skew the object with.
 */
public class SkewTransformation implements AtomicTransformation {

  private double skewAngle;
  private String skewAxis;
  private static final AtomicTransformationType type = AtomicTransformationType.SKEW;

  /**
   * Creates a SkewTransformation given the skew angle and axis.
   *
   * @param skewAngle The skew angle. Can not be a right angle.
   * @param skewAxis The skew axis. Can be <code>"x"</code> or <code>"y"</code>.
   * @throws IllegalArgumentException If the parameter restrictions are violated.
   */
  public SkewTransformation(double skewAngle, String skewAxis) {
    if (isIllegalAngle(skewAngle)) {
      throw new IllegalArgumentException("Skew angle can not be a right angle!");
    }
    if (isIllegalAxis(skewAxis)) {
      throw new IllegalArgumentException("Skew axis must be x or y!");
    }
    this.skewAngle = skewAngle;
    this.skewAxis = skewAxis;
  }

  /**
   * Returns the skew angle
   *
   * @return The skew angle.
   */
  public double getSkewAngle() {
    return skewAngle;
  }

  /**
   * Sets the skew angle. New angle can not be a right angle.
   *
   * @param skewAngle The new skew angle. Can not be a right angle.
   * @throws IllegalArgumentException If the parameter restrictions are violated.
   */
  public void setSkewAngle(double skewAngle) {
    if (isIllegalAngle(skewAngle)) {
      throw new IllegalArgumentException("Skew angle can not be a right angle!");
    }
    this.skewAngle = skewAngle;
  }

  /**
   * Returns the skew axis. Can be <code>"x"</code> or <code>"y"</code>.
   *
   * @return The skew axis. Can be <code>"x"</code> or <code>"y"</code>.
   */
  public String getSkewAxis() {
    return skewAxis;
  }

  /**
   * Sets the skew axis. Can be <code>"x"</code> or <code>"y"</code>.
   *
   * @param skewAxis The new skew axis. Can be <code>"x"</code> or <code>"y"</code>.
   * @throws IllegalArgumentException If the parameter restrictions are violated.
   */
  public void setSkewAxis(String skewAxis) {
    if (isIllegalAxis(skewAxis)) {
      throw new IllegalArgumentException("Skew axis must be x or y!");
    }
    this.skewAxis = skewAxis;
  }

  private boolean isIllegalAngle(double skewAngle) {
    return (skewAngle + 90) % 180 == 0;
  }

  private boolean isIllegalAxis(String skewAxis) {
    return !skewAxis.toUpperCase().equals("X") && !skewAxis.toUpperCase().equals("Y");
  }

  @Override
  public AtomicTransformationType getType() {
    return type;
  }

  @Override
  public String getHTMLAttribute() {
    return "skew" + skewAxis.toUpperCase() + "(" + skewAngle + ")";
  }

}
