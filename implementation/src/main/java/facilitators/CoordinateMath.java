package facilitators;

/**
 * Provides mathematical helper functions to deal with Coordinate transformations.
 */
public class CoordinateMath {

  /**
   * Translates a {@link Coordinate} by a vector (given as a Coordinate).
   *
   * @param coordinateToTranslate The Coordinate to be translated.
   * @param translation The translation vector.
   * @return The translated Coordinate.
   */
  public static Coordinate translateByCoordinate(Coordinate coordinateToTranslate,
      Coordinate translation) {

    double translatedX = coordinateToTranslate.getX() + translation.getX();
    double translatedY = coordinateToTranslate.getY() + translation.getY();

    return new Coordinate(translatedX, translatedY);

  }

  /**
   * Rotates a {@link Coordinate} around a pivot point (given as a Coordinate).
   *
   * @param coordinateToRotate The Coordinate to be rotated.
   * @param angleInDegrees The rotation angle.
   * @param rotationCenter The center of rotation.
   * @return The rotated Coordinate.
   */
  public static Coordinate rotateAroundCoordinate(Coordinate coordinateToRotate,
      double angleInDegrees, Coordinate rotationCenter) {

    double angleInRadians = angleInDegrees * Math.PI / 180;
    double rotatedX = (coordinateToRotate.getX() - rotationCenter.getX()) * Math.cos(angleInRadians)
        - (coordinateToRotate.getY() - rotationCenter.getY()) * Math.sin(angleInRadians)
        + rotationCenter.getX();
    double rotatedY = (coordinateToRotate.getX() - rotationCenter.getX()) * Math.sin(angleInRadians)
        + (coordinateToRotate.getY() - rotationCenter.getY()) * Math.cos(angleInRadians)
        + rotationCenter.getY();

    return new Coordinate(rotatedX, rotatedY);

  }

  /**
   * Scales a {@link Coordinate} in respect to an origin (given as a Coordinate), so that the
   * distance grows or shrinks depending on the given scaling factors. The scaling factors can be
   * different for x and y.
   *
   * @param coordinateToScale The Coordinate to be scaled.
   * @param scalingFactors The factors by which to scale the Coordinate.
   * @param origin The origin to scale the Coordinate in respect to.
   * @return The scaled Coordinate.
   */
  public static Coordinate scaleVector(Coordinate coordinateToScale, Coordinate scalingFactors, Coordinate origin) {

    double scaledX = (coordinateToScale.getX() - origin.getX()) * scalingFactors.getX() + origin.getX();
    double scaledY = (coordinateToScale.getY() - origin.getY()) * scalingFactors.getY() + origin.getY();

    return new Coordinate(scaledX, scaledY);

  }

  /**
   * Skews a {@link Coordinate} along the x-axis using the given angle. The origin (given as a
   * Coordinate) specifies the position of the x-axis.
   *
   * @param coordinateToSkew The Coordinate to be skewed.
   * @param angleInDegrees The skew angle.
   * @param origin The origin, which specifies the position of the x-axis.
   * @return The skewed Coordinate.
   */
  public static Coordinate skewX(Coordinate coordinateToSkew, double angleInDegrees, Coordinate origin) {

    // Orthogonal angles (90°, 270° etc.) and coordinateToSkew.y == origin.y cause division by zero
    // This is unavoidable because of how skew works
    if ((angleInDegrees + 90) % 180 == 0 || coordinateToSkew.getY() == origin.getY()) {
      return coordinateToSkew;
    }

    // Take the point vertically aligned to the origin and horizontally aligned to the coordinate
    Coordinate originVertical = new Coordinate(origin.getX(), coordinateToSkew.getY());
    // See how rotation affects that point
    Coordinate rotatedOriginVertical = rotateAroundCoordinate(originVertical, angleInDegrees, origin);
    // Follow the line through the origin and the rotated point back to the initial y-position
    // The result is the total translation on the x-axis for any point with that y-position
    // (which includes the given coordinate)
    double xTranslation = (originVertical.getY() - rotatedOriginVertical.getY())
        * (rotatedOriginVertical.getX() - origin.getX()) / (rotatedOriginVertical.getY() - origin.getY())
        + rotatedOriginVertical.getX() - origin.getX();
    double skewedX = coordinateToSkew.getX() + xTranslation;
    double skewedY = coordinateToSkew.getY();

    return new Coordinate(skewedX, skewedY);

  }

  /**
   * Skews a {@link Coordinate} along the y-axis using the given angle. The origin (given as a
   * Coordinate) specifies the position of the y-axis.
   *
   * @param coordinateToSkew The Coordinate to be skewed.
   * @param angleInDegrees The skew angle.
   * @param origin The origin, which specifies the position of the y-axis.
   * @return The skewed Coordinate.
   */
  public static Coordinate skewY(Coordinate coordinateToSkew, double angleInDegrees, Coordinate origin) {

    // Orthogonal angles (90°, 270° etc.) and coordinateToSkew.x == origin.x cause division by zero
    // This is unavoicable because of how skew works
    if ((angleInDegrees + 90) % 180 == 0 || coordinateToSkew.getX() == origin.getX()) {
      return coordinateToSkew;
    }

    // Take the point horizontally aligned to the origin and vertically aligned to the coordinate
    Coordinate originHorizontal = new Coordinate(coordinateToSkew.getX(), origin.getY());
    // See how rotation affects that point
    Coordinate rotatedOriginHorizontal = rotateAroundCoordinate(originHorizontal, angleInDegrees, origin);
    // Follow the line through the origin and the rotated point back to the initial x-position
    // The result is the total translation on the y-axis for any point with that x-position
    // (which includes the given coordinate)
    double yTranslation = (originHorizontal.getX() - rotatedOriginHorizontal.getX())
        * (rotatedOriginHorizontal.getY() - origin.getY()) / (rotatedOriginHorizontal.getX() - origin.getX())
        + rotatedOriginHorizontal.getY() - origin.getY();
    double skewedX = coordinateToSkew.getX();
    double skewedY = coordinateToSkew.getY() + yTranslation;

    return new Coordinate(skewedX, skewedY);

  }

}
