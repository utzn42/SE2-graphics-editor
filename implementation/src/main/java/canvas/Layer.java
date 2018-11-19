package canvas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import shapes.Shape;

/**
 * A layer is the container of one or more shapes. A layer can lay over or under other layers. It
 * can be toggled visible or invisible.
 *
 * @see Shape
 * @see Canvas
 */
@JsonIgnoreProperties({"state", "html"})
public class Layer implements Serializable {

  private List<Shape> shapes;
  private boolean visible;

  /**
   * Default constructor. This gets called whenever the user adds a new layer. It is set visible,
   * even though the user can't see it because it doesn't have any shape in it.
   */
  public Layer() {
    shapes = new ArrayList<>();
    visible = true;
  }

  /**
   * The constructor of the Layer Class. It expects one or more shapes, which then get assigned to
   * the layer.
   *
   * @param shapes a {@link List} of {@link Shape}
   */
  public Layer(List<Shape> shapes) {
    this.shapes = shapes;
    visible = true;
  }

  /**
   * Returns the {@link List} of {@link Shape} which are in the layer. This gets called whenever the
   * user adds, deletes or edits a shape.
   *
   * @return returns a {@link List} of {@link Layer}
   */
  public List<Shape> getShapes() {
    return shapes;
  }

  /**
   * Returns true or false depending on the visibility of the layer.
   *
   * @return returns a {@link Boolean}
   */
  public boolean isVisible() {
    return visible;
  }

  /**
   * Sets the visibility true or false. This gets called when the user toggles the visibility of the
   * layer.
   *
   * @param visible If <code>true</code>, makes the Layer visible; if <code>false</code> invisible.
   */
  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  /**
   * Returns the containers of each {@link Shape} in the layer. Gets called by {@link Canvas}
   * "getHTML" method.
   *
   * @return returns a {@link Shape} containing all HTML containers of the shapes
   */
  public String getHTML() {
    StringBuilder stringBuilder = new StringBuilder();
    for (Shape shape : shapes) {
      stringBuilder.append(shape.getHTML());
    }
    return stringBuilder.toString();
  }

}
