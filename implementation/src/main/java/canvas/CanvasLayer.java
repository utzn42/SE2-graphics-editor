package canvas;

import facilitators.Iterator;
import facilitators.NullIterator;
import java.util.List;
import shapes.Shape;
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

  /**
   * Creates an Iterator which returns null/false as default operation, since CanvasLayer represents
   * a leaf element in the tree structure and therefore, can't be iterated over.
   *
   * @return A default NullIterator
   */

  @Override
  public Iterator<CanvasElement> createIterator() {
    return new NullIterator<>();
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
    shape.applyTransformation(transformation);
  }

}