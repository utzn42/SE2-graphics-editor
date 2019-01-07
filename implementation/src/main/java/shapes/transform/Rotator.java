package shapes.transform;

import facilitators.Coordinate;
import org.springframework.lang.Nullable;

/**
 * Rotation component of a {@link ShapeTransformer}.
 * Holds values for rotating an object (rotation angle and optionally rotation center).
 */
public class Rotator {

  private double rotationAngle;
  private boolean rotateAroundShapeCenter;
  private Coordinate rotationCenter;

  /**
   * Creates a Rotator given a rotation angle.
   * Rotates around the Shape's center.
   *
   * @param rotationAngle The rotation angle.
   */
  public Rotator(double rotationAngle) {
    this.rotationAngle = rotationAngle;
    this.rotateAroundShapeCenter = true;
    this.rotationCenter = null;
  }

  /**
   * Creates a Rotator given a rotation angle and center.
   * Rotates around the specified center coordinate.
   *
   * @param rotationAngle The rotation angle.
   * @param rotationCenter The rotation center.
   */
  public Rotator(double rotationAngle, Coordinate rotationCenter) {
    this.rotationAngle = rotationAngle;
    this.rotateAroundShapeCenter = false;
    this.rotationCenter = rotationCenter;
  }

  /**
   * Creates a Rotator given a rotation angle and center.
   * Rotates around the specified center coordinate.
   *
   * @param rotationAngle The rotation angle.
   * @param rotationCenterX The rotation center's x-coordinate.
   * @param rotationCenterY The rotation center's y-coordinate.
   */
  public Rotator(double rotationAngle, double rotationCenterX, double rotationCenterY) {
    this.rotationAngle = rotationAngle;
    this.rotateAroundShapeCenter = false;
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
   * Returns the rotation center as a {@link Coordinate} or <code>null</code> if the Rotator rotates around the Shape's center.
   *
   * @return The rotation center as a {@link Coordinate} or <code>null</code> if the Rotator rotates around the Shape's center.
   */
  @Nullable
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
    this.rotateAroundShapeCenter = false;
  }

  /**
   * Sets the rotation center using individual values for x and y.
   *
   * @param x The new rotation center in x-direction.
   * @param y The new rotation center in y-direction.
   */
  public void setRotationCenter(double x, double y) {
    this.rotationCenter = new Coordinate(x, y);
    this.rotateAroundShapeCenter = false;
  }

  /**
   * Returns <code>true</code> if the Rotator should rotate around the Shape's center.
   *
   * @return <code>true</code> if the Rotator should rotate around the Shape's center, <code>false</code> otherwise.
   */
  public boolean doesRotateAroundShapeCenter() {
    return rotateAroundShapeCenter;
  }

  /**
   * Sets the Rotator to rotate around the Shape's center.
   * Doing so resets the rotation center to <code>null</code>.
   */
  public void setRotateAroundShapeCenter() {
    this.rotateAroundShapeCenter = true;
    this.rotationCenter = null;
  }

}
