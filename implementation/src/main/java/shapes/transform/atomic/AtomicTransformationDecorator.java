package shapes.transform.atomic;

/**
 * Provides support for decorating an {@link AtomicTransformation} with extra functionality by
 * extending this class.
 *
 * @see OriginDecorator
 */
public class AtomicTransformationDecorator implements AtomicTransformation {

  private final AtomicTransformation transformation;

  /**
   * Creates a new decorator wrapping the given {@link AtomicTransformation}.
   *
   * @param transformation The transformation to decorate.
   */
  public AtomicTransformationDecorator(AtomicTransformation transformation) {
    this.transformation = transformation;
  }

  /**
   * Returns the decorated transformation.
   *
   * @return The decorated transformation.
   */
  public AtomicTransformation getTransformation() {
    return transformation;
  }

  @Override
  public AtomicTransformationType getType() {
    return transformation.getType();
  }

  @Override
  public String getHTMLAttribute() {
    return transformation.getHTMLAttribute();
  }

}
