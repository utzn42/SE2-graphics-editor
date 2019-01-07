package shapes.transform;

public interface UniformScalable {

  /**
   * Scales the object using a {@link UniformScaler}
   *
   * @param scaler The scaler to apply to the object
   */
  void scale(UniformScaler scaler);

}
