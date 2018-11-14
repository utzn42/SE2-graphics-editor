package canvas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import shapes.Shape;

/**
 * The Canvas class is the container in which all layers with shapes are in. It is like the
 * coordinate system for the operations which will take place. It manages all layers and shapes.
 * Every object is "registered" in the canvas. A canvas can hold up to 150 shapes.
 *
 * The Canvas class uses the Shape class and the CanvasState class. The relation is a composition.
 * The Canvas class cannot exist without the CanvasState.
 *
 * @see CanvasState
 * @see Shape
 */
@JsonIgnoreProperties({"state"})
public class Canvas implements Observer {

  private CanvasState state;
  private double width;
  private double height;
  private List<Layer> layers;

  /**
   * This is the default constructor of the Canvas class.
   */
  public Canvas() {
    state = CanvasState.EMPTY;
    width = 200;
    height = 200;
    layers = new ArrayList<>();
  }

  public Canvas(List<Layer> layers) {
    state = CanvasState.EMPTY;
    width = 200;
    height = 200;
    this.layers = layers;
  }

  public double getWidth() {
    return width;
  }

  public void setWidth(double width) {
    this.width = width;
  }

  public double getHeight() {
    return height;
  }

  public void setHeight(double height) {
    this.height = height;
  }

  public List<Layer> getLayers() {
    return layers;
  }

  public String getHTML() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("<svg width=\"").append(width)
        .append("\" height=\"").append(height)
        .append("\" baseProfile=\"full\" xmlns=\"http://www.w3.org/2000/svg\">");
    for (Layer layer : layers) {
      if (layer.isVisible()) {
        stringBuilder.append(layer.getHTML());
      }
    }
    stringBuilder.append("</svg>");
    return stringBuilder.toString();
  }

  @Override
  public void update(Observable o, Object arg) {

  }
}