package shapes;

import shapes.transform.Rotatable;
import shapes.transform.Scalable;
import shapes.transform.Skewable;
import shapes.transform.Transformation;
import shapes.transform.Translatable;
import shapes.transform.UniformScalable;

/**
 * Defines the functionality of a {@link Shape} that can be transformed in any way using a
 * {@link Transformation}.
 */
public interface TransformableShape extends Translatable, Rotatable, UniformScalable,
    Scalable, Skewable {

  /**
   * Returns all stored transformations that can not be directly applied to the Shape as a
   * {@link Transformation}.
   *
   * @return All stored transformations that can not be directly applied to the Shape.
   */
  Transformation getTransformations();

  /**
   * Applies a transformation, given as a {@link Transformation}, to the shape.
   *
   * @param transformation The transformation to apply to the shape.
   */
  void applyTransformation(Transformation transformation);

}
