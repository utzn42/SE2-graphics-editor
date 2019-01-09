package shapes.transform.atomic;

import java.io.Serializable;
import shapes.transform.Transformation;

/**
 * Uniform scale component of a {@link Transformation}.
 * Holds a factor by which to uniformly scale an object by.
 */
public class UniformScaleTransformation implements AtomicTransformation, Serializable {

  private static final long serialVersionUID = 1L;

  private double scale;
  private static final AtomicTransformationType type = AtomicTransformationType.UNIFORM_SCALE;

  /**
   * Creates a UniformScaleTransformation given the scaling factor.
   *
   * @param scale The scaling factor for the transformation.
   */
  public UniformScaleTransformation(double scale) {
    this.scale = scale;
  }

  /**
   * Returns the scaling factor of the transformation.
   *
   * @return The scaling factor of the transformation.
   */
  public double getScale() {
    return scale;
  }

  /**
   * Sets the scaling factor for the transformation.
   *
   * @param scale The new scaling factor for the transformation.
   */
  public void setScale(double scale) {
    this.scale = scale;
  }

  @Override
  public AtomicTransformationType getType() {
    return type;
  }

  @Override
  public String getHTMLAttribute() {
    return "scale(" + scale + ")";
  }

}
