package shapes.transform;

/**
 * Holds attributes of a shape transformation as described in
 * <a href="https://developer.mozilla.org/en-US/docs/Web/SVG/Attribute/transform">SVG transform</a>.
 */
public class ShapeTransformer {

  private Translater translater;
  private Rotator rotator;
  private Scaler scaler;
  private Skewer skewer;

  /**
   * Creates a null transformation
   */
  public ShapeTransformer() {
    translater = null;
    rotator = null;
    scaler = null;
    skewer = null;
  }

  /**
   * Returns <code>true</code> if none of the transformations are set.
   *
   * @return <code>true</code> if none of the transformations are set, <code>false</code> otherwise.
   */
  public boolean isNull() {
    return translater == null && rotator == null && scaler == null && skewer == null;
  }

  /**
   * Returns the translation component of this transformation
   *
   * @return The translation component of this transformation
   */
  public Translater getTranslation() {
    return translater;
  }

  /**
   * Sets the translation component of this transformation
   *
   * @param translater The new translation component for this transformation
   */
  public void setTranslation(Translater translater) {
    this.translater = translater;
  }

  /**
   * Returns the rotation component of this transformation
   *
   * @return The rotation component of this transformation
   */
  public Rotator getRotation() {
    return rotator;
  }

  /**
   * Sets the rotation component of this transformation
   *
   * @param rotator The new rotation component for this transformation
   */
  public void setRotation(Rotator rotator) {
    this.rotator = rotator;
  }

  /**
   * Returns the scale component of this transformation
   *
   * @return The scale component of this transformation
   */
  public Scaler getScale() {
    return scaler;
  }

  /**
   * Sets the scale component of this transformation
   *
   * @param scaler The new scale component for this transformation
   */
  public void setScale(Scaler scaler) {
    this.scaler = scaler;
  }

  /**
   * Returns the skew component of this transformation
   *
   * @return The skew component of this transformation
   */
  public Skewer getSkew() {
    return skewer;
  }

  /**
   * Sets the skew component of this transformation
   *
   * @param skewer The new skew component for this transformation
   */
  public void setSkew(Skewer skewer) {
    this.skewer = skewer;
  }

}
