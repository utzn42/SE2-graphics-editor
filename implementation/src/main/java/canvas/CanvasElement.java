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

  public CanvasElement(long id) {
    this.id = id;
    this.visible = true;
  }

  public CanvasElement(long id, boolean visible) {
    this.id = id;
    this.visible = visible;
  }

  public long getId() {
    return id;
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

  public abstract void setShape(Shape shape);

  /**
   * Transforms the CanvasElement using a {@link Transformation}.
   *
   * @param transformation The transformation to be applied to the CanvasElement.
   */
  public abstract void transform(Transformation transformation);

  public abstract String getHTML();

}
