package canvas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import java.io.Serializable;
import shapes.Shape;
import shapes.transform.Transformation;

/**
 * Represents an element on a {@link Canvas}. Defines basic functionality any element on a canvas
 * needs to have.
 */
@JsonIgnoreProperties({"html"})
@JsonTypeInfo(
    use = Id.NAME,
    include = As.PROPERTY,
    property = "elementType"
)
@JsonSubTypes({
    @Type(value = CanvasLayer.class, name = "LAYER"),
    @Type(value = CanvasElementAggregate.class, name = "LAYER_GROUP")
})
public abstract class CanvasElement implements Serializable {

  private static final long serialVersionUID = 1L;

  private final long id;
  private boolean visible;

  /**
   * Creates a visible {@link CanvasElement}.
   * @param id The ID of the new element.
   */
  public CanvasElement(long id) {
    this.id = id;
    this.visible = true;
  }

  /**
   * Creates a {@link CanvasElement} visible or not visible.
   * @param id The ID of the new element.
   * @param visible Indicates whether the element should be visible or not.
   */
  public CanvasElement(long id, boolean visible) {
    this.id = id;
    this.visible = visible;
  }

  /**
   * Returns the ID of the element.
   * @return The ID of the element in {@link Long}
   */
  public long getId() {
    return id;
  }

  /**
   * Returns whether the element is visible or not.
   * @return true if the element is visible, false if the element is not visible.
   */
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

  public void setShape(Shape shape) {
    throw new UnsupportedOperationException("Can't set shape in a CanvasElementAggregate!");
  }

  public void addItem(CanvasElement canvasElement) {
    throw new UnsupportedOperationException("Cant add an item to a Canvas!");
  }

  /**
   * Transforms the CanvasElement using a {@link Transformation}.
   *
   * @param transformation The transformation to be applied to the CanvasElement.
   */
  public abstract void transform(Transformation transformation);

  /**
   * Clears all transformations from the CanvasElement.
   */
  public abstract void clearTransformations();

  /**
   * Returns the HTML Code of the element.
   * @return HTML Code of the element.
   */
  public abstract String getHTML();

}
