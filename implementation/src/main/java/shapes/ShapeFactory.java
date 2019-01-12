package shapes;

import canvas.CanvasLayer;

/**
 * An interface that defines the basic {@link Shape Shapes} a shape factory must be able to produce.
 */
public interface ShapeFactory {

  /**
   * Creates a {@link Circle} with default attributes.
   *
   * @return The created {@link Circle}.
   */
  CanvasLayer createShape(long id, ShapeType shapeType);

}
