package shapes.transform;

import shapes.transform.atomic.UniformScaleTransformation;

/**
 * Specifies a class that can be transformed using a {@link UniformScaleTransformation}.
 */
public interface UniformScalable {

  /**
   * Uniformly scales the object using a {@link UniformScaleTransformation}.
   *
   * @param transformation The transformation to apply to the object.
   */
  void uniformScale(UniformScaleTransformation transformation);

}
