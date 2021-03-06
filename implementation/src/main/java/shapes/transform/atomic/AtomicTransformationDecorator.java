package shapes.transform.atomic;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import java.io.Serializable;

/**
 * Provides support for decorating an {@link AtomicTransformation} with extra functionality by
 * extending this class.
 *
 * @see OriginDecorator
 */
public class AtomicTransformationDecorator implements AtomicTransformation, Serializable {

  private static final long serialVersionUID = 1L;

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
  @JsonUnwrapped
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
