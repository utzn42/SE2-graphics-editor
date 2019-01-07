package shapes.transform;

import facilitators.Coordinate;

/**
 * Rotation component of a {@link Transformation}.
 * Holds values for rotating an object (rotation angle and rotation center).
 */
public class RotationTransformation {

  private double rotationAngle;
  private Coordinate rotationCenter;

  /**
   * Creates a RotationTransformation given a rotation angle and center.
   * Rotates around the specified center coordinate.
   *
   * @param rotationAngle The rotation angle.
   * @param rotationCenter The rotation center.
   */
  public RotationTransformation(double rotationAngle, Coordinate rotationCenter) {
    this.rotationAngle = rotationAngle;
    this.rotationCenter = rotationCenter;
  }

  /**
   * Creates a RotationTransformation given a rotation angle and center.
   * Rotates around the specified center coordinate.
   *
   * @param rotationAngle The rotation angle.
   * @param rotationCenterX The rotation center's x-coordinate.
   * @param rotationCenterY The rotation center's y-coordinate.
   */
  public RotationTransformation(double rotationAngle, double rotationCenterX, double rotationCenterY) {
    this.rotationAngle = rotationAngle;
    this.rotationCenter = new Coordinate(rotationCenterX, rotationCenterY);
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

  /**
   * Returns the rotation center as a {@link Coordinate}.
   *
   * @return The rotation center as a {@link Coordinate}.
   */
  public Coordinate getRotationCenter() {
    return rotationCenter;
  }

  /**
   * Sets the rotation center using a {@link Coordinate}.
   *
   * @param rotationCenter The new rotation center.
   */
  public void setRotationCenter(Coordinate rotationCenter) {
    this.rotationCenter = rotationCenter;
  }

  /**
   * Sets the rotation center using individual values for x and y.
   *
   * @param x The new rotation center in x-direction.
   * @param y The new rotation center in y-direction.
   */
  public void setRotationCenter(double x, double y) {
    this.rotationCenter = new Coordinate(x, y);
  }

  String getHTMLAttribute() {
    return "rotate(" + rotationAngle + " " + rotationCenter.getX() + " " + rotationCenter.getY() + ")";
  }

}
