package shapes.transform;

import java.util.StringJoiner;

/**
 * Holds attributes of a shape transformation as described in
 * <a href="https://developer.mozilla.org/en-US/docs/Web/SVG/Attribute/transform">SVG transform</a>.
 */
public class Transformation {

  private TranslationTransformation translationTransformation;
  private RotationTransformation rotationTransformation;
  private ScaleTransformation scaleTransformation;
  private SkewTransformation skewTransformation;

  /**
   * Creates a null transformation
   */
  public Transformation() {
    translationTransformation = null;
    rotationTransformation = null;
    scaleTransformation = null;
    skewTransformation = null;
  }

  /**
   * Returns <code>true</code> if none of the transformations are set.
   *
   * @return <code>true</code> if none of the transformations are set, <code>false</code> otherwise.
   */
  public boolean isNull() {
    return translationTransformation == null && rotationTransformation == null && scaleTransformation == null && skewTransformation
        == null;
  }

  /**
   * Returns the translation component of this transformation
   *
   * @return The translation component of this transformation
   */
  public TranslationTransformation getTranslation() {
    return translationTransformation;
  }

  /**
   * Sets the translation component of this transformation
   *
   * @param translationTransformation The new translation component for this transformation
   */
  public void setTranslation(TranslationTransformation translationTransformation) {
    this.translationTransformation = translationTransformation;
  }

  /**
   * Returns the rotation component of this transformation
   *
   * @return The rotation component of this transformation
   */
  public RotationTransformation getRotation() {
    return rotationTransformation;
  }

  /**
   * Sets the rotation component of this transformation
   *
   * @param rotationTransformation The new rotation component for this transformation
   */
  public void setRotation(RotationTransformation rotationTransformation) {
    this.rotationTransformation = rotationTransformation;
  }

  /**
   * Returns the scale component of this transformation
   *
   * @return The scale component of this transformation
   */
  public ScaleTransformation getScale() {
    return scaleTransformation;
  }

  /**
   * Sets the scale component of this transformation
   *
   * @param scaleTransformation The new scale component for this transformation
   */
  public void setScale(ScaleTransformation scaleTransformation) {
    this.scaleTransformation = scaleTransformation;
  }

  /**
   * Returns the skew component of this transformation
   *
   * @return The skew component of this transformation
   */
  public SkewTransformation getSkew() {
    return skewTransformation;
  }

  /**
   * Sets the skew component of this transformation
   *
   * @param skewTransformation The new skew component for this transformation
   */
  public void setSkew(SkewTransformation skewTransformation) {
    this.skewTransformation = skewTransformation;
  }

  /**
   * Returns an HTML "transform" attribute representing the transformation.
   *
   * @return An HTML "transform" attribute representing the transformation.
   */
  public String getHTMLAttribute() {
    StringJoiner stringJoiner = new StringJoiner(" ", "transform=\"", "\"");
    if (translationTransformation != null) {
      stringJoiner.add(translationTransformation.getHTMLAttribute());
    }
    if (rotationTransformation != null) {
      stringJoiner.add(rotationTransformation.getHTMLAttribute());
    }
    if (scaleTransformation != null) {
      stringJoiner.add(scaleTransformation.getHTMLAttribute());
    }
    if (skewTransformation != null) {
      stringJoiner.add(skewTransformation.getHTMLAttribute());
    }
    return stringJoiner.toString();
  }

}
