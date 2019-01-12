package canvas;

import facilitators.Iterator;
import shapes.transform.Transformation;

/**
 * Represents an element on a {@link Canvas}. Defines basic functionality any element on a canvas
 * needs to have.
 */
public abstract class CanvasElement {

  private long id;
  private boolean visible;

  public void addItem(CanvasElement item) {
    throw new UnsupportedOperationException(
        "addItem() cannot be called directly from CanvasElement!");
  }

  public CanvasElement getItem(int index) {
    throw new UnsupportedOperationException(
        "getItem() cannot be called directly from CanvasElement!");
  }

  public boolean deleteItem(int index) {
    throw new UnsupportedOperationException(
        "deleteItem() cannot be called directly from CanvasElement!");
  }

  public boolean deleteItem(CanvasElement item) {
    throw new UnsupportedOperationException(
        "addItem() cannot be called directly from CanvasElement!");
  }

  public void addItem() {
    throw new UnsupportedOperationException(
        "addItem() cannot be called directly from CanvasElement!");
  }

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
   * Transforms the CanvasElement using a {@link Transformation}.
   *
   * @param transformation The transformation to be applied to the CanvasElement.
   */
  public abstract void transform(Transformation transformation);

  public abstract Iterator<CanvasElement> createIterator();

  public abstract String getHTML();

  public long getId() {
    return id;
  }


}
