package shapes.transform;

import shapes.transform.atomic.SkewTransformation;

/**
 * Specifies a class that can be transformed using a {@link SkewTransformation}.
 */
public interface Skewable {

  /**
   * Skews the object using a {@link SkewTransformation}.
   *
   * @param transformation The transformation to apply to the object.
   */
  void skew(SkewTransformation transformation);

}
