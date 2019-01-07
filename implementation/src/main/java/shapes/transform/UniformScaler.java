package shapes.transform;

import facilitators.Coordinate;

/**
 * A {@link Scaler} that always scales uniformly in x- and y-direction.
 */
public class UniformScaler extends Scaler {

  /**
   * Creates a uniform scaler given a scale factor.
   *
   * @param scale The factor by which to scale the object by.
   */
  public UniformScaler(double scale) {
    super(scale, scale);
  }

  @Override
  public void setScale(Coordinate scale) {
    super.setScale(scale.getX(), scale.getX());
  }

  @Override
  public void setScale(double x, double y) {
    super.setScale(x, x);
  }

  /**
   * Sets the factor by which to scale the object by.
   *
   * @param x The new scale.
   */
  public void setScale(double x) {
    super.setScale(x, x);
  }
}
