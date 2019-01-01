package canvas;

import facilitators.Iterator;
import shapes.transform.ShapeTransformer;

/**
 * Represents an element on a {@link Canvas}. Defines basic functionality any element on a canvas
 * needs to have.
 */
public abstract class CanvasElement {

  private long id;
  private boolean visible;

  public boolean isVisible() {
    return visible;
  }

  /**
   * Sets the visibility of the CanvasElement.
   *
   * @param visible If <code>true</code>, makes the CanvasElement visible; invisible otherwise.
   */
  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  /**
   * Transforms the CanvasElement using a {@link ShapeTransformer}.
   *
   * @param shapeTransformer the transformation to be applied to the CanvasElement.
   */
  public abstract void transform(ShapeTransformer shapeTransformer);

  public Iterator<CanvasElement> createIterator() {                                                 //TODO: Fix multiple inheritance issues.
    return null;
  }

}
