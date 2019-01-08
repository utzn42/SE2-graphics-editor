package shapes.transform.atomic;

/**
 * An atomic transformation that can be applied to an object and represented in an HTML "transform"
 * attribute.
 */
public interface AtomicTransformation {

  /**
   * Returns the type of transformation.
   *
   * @return The type of transformation.
   * @see AtomicTransformationType
   */
  AtomicTransformationType getType();

  /**
   * Returns the transformation as it would be represented in an HTML "transform" attribute.
   *
   * @return The transformation as it would be represented in an HTML "transform" attribute.
   */
  String getHTMLAttribute();

}
