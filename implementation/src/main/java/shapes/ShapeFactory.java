package shapes;

/**
 * An interface that defines the basic {@link Shape Shapes} a shape factory must be able to produce.
 */
public interface ShapeFactory {

  /**
   * Creates a {@link Circle} with default attributes.
   *
   * @return The created {@link Circle}.
   */
  Circle createCircle();

  /**
   * Creates an {@link Ellipse} with default attributes.
   *
   * @return The created {@link Ellipse}.
   */
  Ellipse createEllipse();

  /**
   * Creates a {@link Line} with default attributes.
   *
   * @return The created {@link Line}.
   */
  Line createLine();

  /**
   * Creates a {@link Polygon} with default attributes.
   *
   * @return The created {@link Polygon}.
   */
  Polygon createPolygon();

  /**
   * Creates a {@link RegularPolygon} with default attributes.
   *
   * @return The created {@link RegularPolygon}.
   */
  RegularPolygon createRegularPolygon();

  /**
   * Creates a {@link Star} with default attributes.
   *
   * @return The created {@link Star}.
   */
  Star createStar();

  /**
   * Creates a {@link Text} element with default attributes.
   *
   * @return The created {@link Text} element.
   */
  Text createText();

}
