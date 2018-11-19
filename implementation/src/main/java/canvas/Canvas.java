package canvas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import shapes.Shape;

/**
 * The Canvas class is the container in which all layers with shapes are in. It is like the
 * coordinate system for the operations which will take place. It manages all layers and shapes.
 * Every object is "registered" in the canvas.
 *
 * @see Shape
 * @see Layer
 */
@JsonIgnoreProperties({"state"})
public class Canvas implements Serializable {

  private double width;
  private double height;
  private List<Layer> layers;

  /**
   * This is the default constructor of the Canvas class. It gets called when the user calls the
   * webpage. We chose a default value of 200x200, but the user can change its actual size all the
   * time when needed.
   */
  public Canvas() {
    width = 200;
    height = 200;
    layers = new ArrayList<>();
  }

  /**
   * This constructor creates a canvas already with layers. We chose a default value of 200x200, but
   * the user can change its actual size all the time when needed.
   *
   * @param layers a {@link List} of {@link Layer}
   */
  public Canvas(List<Layer> layers) {
    width = 200;
    height = 200;
    this.layers = layers;
  }

  /**
   * Returns the width of the canvas. Gets called when the canvas gets parsed into json to send it
   * to the client.
   *
   * @return returns width in {@link Double}
   */
  public double getWidth() {
    return width;
  }

  /**
   * Lets you set the width of the canvas. This gets called when the user edits the canvas.
   *
   * @param width the width of the canvas in {@link Double}
   */
  public void setWidth(double width) {
    this.width = width;
  }

  /**
   * Returns the height of the canvas. Gets called when the canvas gets parsed into json to send it
   * to the client.
   *
   * @return returns height in {@link Double}
   */
  public double getHeight() {
    return height;
  }

  /**
   * Lets you set the height of the canvas. This gets called when the user edits the canvas.
   *
   * @param height the height of the canvas in {@link Double}
   */
  public void setHeight(double height) {
    this.height = height;
  }

  /**
   * Returns a {@link List} of the {@link Layer} which are in the canvas. This gets called when the
   * canvas gets parsed into json to send it to the client.
   *
   * @return a {@link List} of {@link Layer}
   */
  public List<Layer> getLayers() {
    return layers;
  }

  /**
   * Returns the SVG container for the HTML file.
   *
   * @return returns a {@link String} which contains the SVG container
   */
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

}