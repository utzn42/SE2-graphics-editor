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

  /**
   * Creates a {@link CanvasLayer} and assignes an id and a shape.
   *
   * @param id The ID which will get assigned to the Layer.
   * @param shape The {@link Shape} which will get assigned to the Layer.
   */
  public CanvasLayer(long id, Shape shape) {
    super(id);
    this.shape = shape;
  }

  /**
   * Creates a CanvasLayer and assigns an id.
   *
   * @param id The ID which will get assigned to the CanvasLayer.
   */
  public CanvasLayer(long id) {
    super(id);
    this.shape = null;
  }

  /**
   * Creates a CanvasLayer, assigns an id and sets the visibility.
   *
   * @param id The ID which will get assigned to the CanvasLayer.
   * @param visible <code>true</code> if the CanvasLayer should be visible, <code>false</code> otherwise.
   */
  public CanvasLayer(long id, boolean visible) {
    super(id, visible);
  }

  /**
   * Returns the {@link Shape} this CanvasLayer is holding.
   *
   * @return The {@link Shape} this CanvasLayer is holding.
   */
  public Shape getShape() {
    return shape;
  }

  /**
   * Sets a {@link Shape} for this CanvasLayer to hold.
   *
   * @param shape The {@link Shape} this CanvasLayer should hold.
   */
  public void setShape(Shape shape) {
    this.shape = shape;
  }

  /**
   * Returns the HTML Code of the element.
   *
   * @return HTML Code of the element.
   */
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

  @Override
  public void clearTransformations() {
    if (shape instanceof ShapeWithTransformAttribute) {
      ((ShapeWithTransformAttribute) shape).clearTransformation();
    }
  }
}