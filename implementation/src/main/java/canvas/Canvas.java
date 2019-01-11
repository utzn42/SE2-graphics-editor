package canvas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import shapes.NonTransformableShapeFactory;
import shapes.Shape;
import shapes.ShapeFactory;
import shapes.ShapeType;
import shapes.TransformableShapeFactory;

/**
 * The Canvas class is the container in which all layers with shapes are in. It is like the
 * coordinate system for the operations which will take place. It manages all layers and shapes.
 * Every object is "registered" in the canvas.
 *
 * @see Shape
 * @see Layer
 */
@JsonIgnoreProperties({"shapeFactory"})
public class Canvas implements Serializable {

  private double width;
  private double height;
  private List<CanvasElement> canvasElements;
  private boolean useTransformables;
  private transient ShapeFactory shapeFactory;

  /**
   * This is the default constructor of the Canvas class. It gets called when the user calls the
   * webpage. We chose a default value of 200x200, but the user can change its actual size all the
   * time when needed.
   */
  public Canvas() {
    width = 200;
    height = 200;
    canvasElements = new ArrayList<>();
    useTransformables = true;
    shapeFactory = new TransformableShapeFactory();
  }

  /**
   * This constructor creates a canvas already with layers. We chose a default value of 200x200, but
   * the user can change its actual size all the time when needed.
   *
   * @param canvasElements a {@link List} of {@link Layer}
   */
  public Canvas(List<CanvasElement> canvasElements) {
    width = 200;
    height = 200;
    this.canvasElements = canvasElements;
    useTransformables = true;
    shapeFactory = new TransformableShapeFactory();
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
  public List<CanvasElement> getLayers() {
    return canvasElements;
  }

  /**
   * Returns <code>true</code> if the Canvas allows the use of the HTML "transform" attribute for
   * its Shapes.
   *
   * @return <code>true</code> if the Canvas allows the use of the HTML "transform" attribute for
   * its Shapes, <code>false</code> otherwise.
   */
  public boolean doesUseTransformables() {
    return useTransformables;
  }

  /**
   * Sets whether to allow the use of the HTML "transform" attribute for Shapes on the Canvas.
   * Changing this to <code>false</code> might cause existing transformations to be lost.
   *
   * @param useTransformables Set to <code>true</code> to allow the use of the HTML "transform"
   * attribute for Shapes on the Canvas, <code>false</code> otherwise.
   */
  public void setUseTransformables(boolean useTransformables) {
    this.useTransformables = useTransformables;
    if (!useTransformables) {
      shapeFactory = new NonTransformableShapeFactory();
      //TODO: canvas.Canvas.setUseTransformables: Replace all transformable Shapes with non-transformables.
    }
    else {
      shapeFactory = new TransformableShapeFactory();
      //TODO: canvas.Canvas.setUseTransformables: Replace all non-transformable Shapes with transformables.
    }
  }


  /**
   * Returns the {@link ShapeFactory} this Canvas uses to create {@link Shape Shapes}.
   *
   * @return The ShapeFactory this Canvas uses to create Shapes.
   */
  public ShapeFactory getShapeFactory() {
    return shapeFactory;
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
    for (CanvasElement canvasElement : canvasElements) {
      if (canvasElement.isVisible()) {
        stringBuilder.append(canvasElement.getHTML());
      }
    }
    stringBuilder.append("</svg>");
    return stringBuilder.toString();
  }

  private void readObject(java.io.ObjectInputStream oin)
      throws IOException, ClassNotFoundException {
    oin.defaultReadObject();
    if (useTransformables) {
      shapeFactory = new TransformableShapeFactory();
    } else {
      shapeFactory = new NonTransformableShapeFactory();
    }
  }

  public void addShape(ShapeType shapeType) {
    canvasElements.add(shapeFactory.createShape(shapeType));
  }

  public void addShape(ShapeType shapeType, long ElementID){
    CanvasElement tempElement = findCanvasElementByID(ElementID);
    canvasElements.add(canvasElements.indexOf(tempElement), shapeFactory.createShape(shapeType));
  }

  public CanvasElement findCanvasElementByID(long id){
    for(CanvasElement element : canvasElements){
      if(element.getId() == id){
        return element;
      }
    }
    return null;
  }
}