package shapes.transform.atomic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import facilitators.Coordinate;

/**
 * Decorator that adds an origin to an {@link AtomicTransformation}. It modifies the HTML "transform"
 * attribute by translating the Object before and after the transformation, so the transformation
 * is performed in respect to the given origin instead of the default origin (0, 0)
 */
@JsonIgnoreProperties({"beforeTransformation", "afterTransformation", "htmlattribute"})
public class OriginDecorator extends AtomicTransformationDecorator {

  private static final long serialVersionUID = 1L;

  private final Coordinate origin;
  private final TranslationTransformation beforeTransformation;
  private final TranslationTransformation afterTransformation;

  /**
   * Creates a new OriginDecorator decorating the given {@link AtomicTransformation}.
   * The origin is set to (0, 0).
   *
   * @param transformation The transformation to decorate.
   */
  public OriginDecorator(AtomicTransformation transformation) {
    super(transformation);
    origin = new Coordinate(0, 0);
    beforeTransformation = null;
    afterTransformation = null;
  }

  /**
   * Creates a new OriginDecorator decorating the given {@link AtomicTransformation}.
   * The origin is set to the given {@link Coordinate}.
   *
   * @param transformation The transformation to decorate.
   * @param origin The origin for the transformation.
   */
  public OriginDecorator(AtomicTransformation transformation, Coordinate origin) {
    super(transformation);
    this.origin = origin;
    beforeTransformation = new TranslationTransformation(-origin.getX(), -origin.getY());
    afterTransformation = new TranslationTransformation(origin);
  }

  /**
   * Creates a new OriginDecorator decorating the given {@link AtomicTransformation}.
   * The origin is set to the given x- and y-coordinates.
   *
   * @param transformation The transformation to decorate.
   * @param originX The x-coordinate of the origin for the transformation.
   * @param originY The y-coordinate of the origin for the transformation.
   */
  public OriginDecorator(AtomicTransformation transformation, double originX, double originY) {
    super(transformation);
    this.origin = new Coordinate(originX, originY);
    beforeTransformation = new TranslationTransformation(-origin.getX(), -origin.getY());
    afterTransformation = new TranslationTransformation(origin);
  }

  /**
   * Returns the origin for the transformation.
   *
   * @return The origin for the transformation.
   */
  public Coordinate getOrigin() {
    return origin;
  }

  /**
   * Returns the transformation applied before the decorated transformation.
   *
   * @return The transformation applied before the decorated transformation.
   */
  public TranslationTransformation getBeforeTransformation() {
    return beforeTransformation;
  }

  /**
   * Returns the transformation applied after the decorated transformation.
   *
   * @return The transformation applied after the decorated transformation.
   */
  public TranslationTransformation getAfterTransformation() {
    return afterTransformation;
  }

  @Override
  public String getHTMLAttribute() {
    //Note: The transformations are performed from right to left.
    return afterTransformation.getHTMLAttribute() + " "
        + super.getHTMLAttribute() + " "
        + beforeTransformation.getHTMLAttribute();
  }
}
