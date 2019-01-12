package shapes;

import canvas.CanvasLayer;

/**
 * An interface that defines the basic {@link Shape Shapes} a shape factory must be able to produce.
 */
public interface ShapeFactory {

  /**
   * Creates a {@link CanvasLayer} which holds a shape.
   *
   * @param shapeType decides which shape gets created in {@link CanvasLayer}
   * @return The created {@link CanvasLayer}.
   */
  CanvasLayer createShape(ShapeType shapeType);

}
