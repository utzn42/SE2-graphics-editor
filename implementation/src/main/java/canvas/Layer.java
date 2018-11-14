package canvas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import shapes.Shape;

/**
 * A layer is the container of one or more shapes. One layer can hold up to 50 shapes. A layer can
 * lay over or under other layers.
 *
 * The relation is an aggregation, it cannot exist without a the LayerState.
 *
 * @see LayerState
 * @see Observer
 */
@JsonIgnoreProperties({"state", "html"})
public class Layer implements Observer {

  private LayerState state;
  private List<Shape> shapes;
  private boolean visible;

  /**
   * Default constructor.
   */
  public Layer() {
    state = LayerState.EMPTY;
    shapes = new ArrayList<>();
    visible = true;
  }

  /**
   * The constructor of the Layer Class. It expects one or more shapes, which then get assigned to
   * the layer.
   *
   * @param shapes one ore more shapes
   */
  public Layer(List<Shape> shapes) {
    state = LayerState.EMPTY;
    this.shapes = shapes;
    visible = true;
  }

  public List<Shape> getShapes() {
    return shapes;
  }

  public boolean isVisible() {
    return visible;
  }

  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  public String getHTML() {
    StringBuilder stringBuilder = new StringBuilder();
    for (Shape shape : shapes) {
      stringBuilder.append(shape.getHTML());
    }
    return stringBuilder.toString();
  }

  @Override
  public void update(Observable o, Object arg) {

  }
}
