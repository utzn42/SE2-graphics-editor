package shapes.transform;

/**
 * Specifies a class that can be transformed using a {@link RotationTransformation}.
 */
public interface Rotatable {

  /**
   * Rotates the object using a {@link RotationTransformation}.
   *
   * @param transformation The rotation to apply to the object.
   */
  void rotate(RotationTransformation transformation);

}
