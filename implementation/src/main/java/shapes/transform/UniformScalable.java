package shapes.transform;

/**
 * Specifies a class that can be transformed using a {@link UniformScaleTransformation}.
 */
public interface UniformScalable {

  /**
   * Uniformly scales the object using a {@link UniformScaleTransformation}.
   *
   * @param transformation The transformation to apply to the object.
   */
  void scale(UniformScaleTransformation transformation);

}
