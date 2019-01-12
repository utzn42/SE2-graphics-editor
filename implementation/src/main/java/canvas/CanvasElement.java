package canvas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import facilitators.Iterator;
import shapes.transform.Transformation;

/**
 * Represents an element on a {@link Canvas}. Defines basic functionality any element on a canvas
 * needs to have.
 */
@JsonIgnoreProperties({"html"})
public abstract class CanvasElement {

  private final long id;
  private boolean visible;

  public CanvasElement(long id) {
    this.id = id;
  }

  public CanvasElement(long id, boolean visible) {
    this.id = id;
    this.visible = visible;
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
