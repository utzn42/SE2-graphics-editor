package canvas;

import shapes.Shape;
import shapes.transform.ShapeTransformer;

/**
 * A layer is a container for a single {@link Shape}.
 *
 * @see Shape
 * @see CanvasElement
 */
public class CanvasLayer extends CanvasElement {

  private Shape shape;

  /**
   * Returns the {@link Shape} this CanvasLayer is holding
   *
   * @return The {@link Shape} this CanvasLayer is holding
   */
  public Shape getShape() {
    return shape;
  }

  /**
   * Sets a {@link Shape} for this CanvasLayer to hold
   *
   * @param shape The {@link Shape} this CanvasLayer should hold
   */
  public void setShape(Shape shape) {
    this.shape = shape;
  }

  /**
   * Applies transformation, given as a {@link ShapeTransformer}, to the contained {@link Shape}.
   *
   * @param shapeTransformer the transformation to be applied to the {@link Shape}.
   */
  @Override
  public void transform(ShapeTransformer shapeTransformer) {
    //TODO: Apply transformation
  }

}
