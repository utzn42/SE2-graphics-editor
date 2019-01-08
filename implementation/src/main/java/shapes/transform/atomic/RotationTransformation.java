package shapes.transform.atomic;

import facilitators.Coordinate;
import shapes.transform.Transformation;

/**
 * Rotation component of a {@link Transformation}.
 * Holds the rotation angle.
 */
public class RotationTransformation implements AtomicTransformation {

  private double rotationAngle;
  private static final AtomicTransformationType type = AtomicTransformationType.ROTATION;

  /**
   * Creates a RotationTransformation given a rotation angle.
   *
   * @param rotationAngle The rotation angle.
   */
  public RotationTransformation(double rotationAngle) {
    this.rotationAngle = rotationAngle;
  }

  /**
   * Returns the rotation angle.
   *
   * @return The rotation angle.
   */
  public double getRotationAngle() {
    return rotationAngle;
  }

  /**
   * Sets the rotation angle.
   *
   * @param rotationAngle The new rotation angle.
   */
  public void setRotationAngle(double rotationAngle) {
    this.rotationAngle = rotationAngle;
  }

  @Override
  public AtomicTransformationType getType() {
    return type;
  }

  @Override
  public String getHTMLAttribute() {
    return "rotate(" + rotationAngle + ")";
  }

}
