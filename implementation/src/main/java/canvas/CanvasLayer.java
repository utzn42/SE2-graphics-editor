package canvas;

import shapes.Shape;
import shapes.ShapeWithTransformAttribute;
import shapes.transform.Transformation;

/**
 * A layer is a container for a single {@link Shape}.
 *
 * @see Shape
 * @see CanvasElement
 */
public class CanvasLayer extends CanvasElement {

  private static final long serialVersionUID = 1L;

  private Shape shape;

  public CanvasLayer(long id, Shape shape) {
    super(id);
    this.shape = shape;
  }

  public CanvasLayer(long id, boolean visible) {
    super(id, visible);
    this.shape = null;
  }

  public CanvasLayer(long id) {
    super(id);
    this.shape = null;
  }

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

  @Override
  public String getHTML() {
    return shape.getHTML();
  }


  /**
   * Applies transformation, given as a {@link Transformation}, to the contained {@link Shape}.
   *
   * @param transformation the transformation to be applied to the {@link Shape}.
   */

  @Override
  public void transform(Transformation transformation) {
    if (shape instanceof ShapeWithTransformAttribute) {
      Transformation existingTransformations = ((ShapeWithTransformAttribute) shape).getTransformation();
      existingTransformations.addTransformations(transformation);
      shape.applyTransformation(existingTransformations);
    } else {
      shape.applyTransformation(transformation);
    }
  }

}