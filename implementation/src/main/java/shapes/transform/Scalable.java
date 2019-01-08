package shapes.transform;

import shapes.transform.atomic.ScaleTransformation;

/**
 * Specifies a class that can be transformed using a {@link ScaleTransformation}.
 */
public interface Scalable {

  /**
   * Scales the object using a {@link ScaleTransformation}.
   *
   * @param transformation The transformation to apply to the object.
   */
  void scale(ScaleTransformation transformation);

}
