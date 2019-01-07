package shapes.transform;

import facilitators.Coordinate;

/**
 * A {@link ScaleTransformation} that always scales uniformly in x- and y-direction.
 * This class ensures the transformation stays uniform.
 */
public class UniformScaleTransformation extends ScaleTransformation {

  /**
   * Creates a UniformScaleTransformation given a scale factor.
   *
   * @param scale The factor by which to scale the object by.
   */
  public UniformScaleTransformation(double scale) {
    super(scale);
  }

  @Override
  public void setScale(Coordinate scale) {
    super.setScale(scale.getX());
  }

  @Override
  public void setScale(double x, double y) {
    super.setScale(x);
  }

  @Override
  public void setScale(double x) {
    super.setScale(x);
  }

}
