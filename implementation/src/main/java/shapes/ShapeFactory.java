package shapes;

import canvas.CanvasLayer;

/**
 * An interface that defines the basic {@link Shape Shapes} a shape factory must be able to produce.
 */
public interface ShapeFactory {

  /**
   * Creates a {@link CanvasLayer} which holds a {@link Shape}.
   *
   * @param id The id to assign to the new {@link CanvasLayer}.
   * @param shapeType Decides which shape gets created in the {@link CanvasLayer}.
   * @return The created {@link CanvasLayer}.
   */
  CanvasLayer createShape(long id, ShapeType shapeType);

}
