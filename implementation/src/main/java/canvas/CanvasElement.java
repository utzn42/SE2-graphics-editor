package canvas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import facilitators.Iterator;
import java.io.Serializable;
import shapes.transform.Transformation;

/**
 * Represents an element on a {@link Canvas}. Defines basic functionality any element on a canvas
 * needs to have.
 */
@JsonIgnoreProperties({"html"})
public abstract class CanvasElement implements Serializable {

  private static final long serialVersionUID = 1L;

  private final long id;
  private boolean visible;

  public CanvasElement(long id) {
    this.id = id;
  }

  public CanvasElement(long id, boolean visible) {
    this.id = id;
    this.visible = visible;
  }

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
