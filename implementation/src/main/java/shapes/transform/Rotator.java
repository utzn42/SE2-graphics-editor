package shapes.transform;

import facilitators.Coordinate;

/**
 * Rotation component of a {@link ShapeTransformer}
 */
public class Rotator {

  private double rotationAngle;
  private Coordinate rotationCenter;

  /**
   * Returns the rotation angle
   *
   * @return The rotation angle
   */
  public double getRotationAngle() {
    return rotationAngle;
  }

  /**
   * Sets the rotation angle
   *
   * @param rotationAngle The new rotation angle
   */
  public void setRotationAngle(double rotationAngle) {
    this.rotationAngle = rotationAngle;
  }

  /**
   * Returns the rotation center as a {@link Coordinate}
   *
   * @return The rotation center as a {@link Coordinate}
   */
  public Coordinate getRotationCenter() {
    return rotationCenter;
  }

  /**
   * Sets the rotation center using a {@link Coordinate}
   *
   * @param rotationCenter The new rotation center
   */
  public void setRotationCenter(Coordinate rotationCenter) {
    this.rotationCenter = rotationCenter;
  }

  /**
   * Sets the rotation center using individual values for x and y
   *
   * @param x The new rotation center in x-direction
   * @param y The new rotation center in y-direction
   */
  public void setRotationCenter(double x, double y) {
    this.rotationCenter = new Coordinate(x, y);
  }

}
