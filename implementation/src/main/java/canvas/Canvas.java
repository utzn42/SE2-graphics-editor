package canvas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import facilitators.Iterator;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import shapes.NonTransformableShapeFactory;
import shapes.Shape;
import shapes.ShapeFactory;
import shapes.ShapeType;
import shapes.ShapeWithTransformAttribute;
import shapes.TransformableShapeFactory;
import shapes.transform.Transformation;

/**
 * The Canvas class is the container in which all layers with shapes are in. It is like the
 * coordinate system for the operations which will take place. It manages all layers and shapes.
 * Every object is "registered" in the canvas.
 *
 * @see Shape
 */
@JsonIgnoreProperties({"shapeFactory"})
public class Canvas implements Serializable {

  private double width;
  private double height;
  private List<CanvasElement> canvasElements;
  private long shapeIDCount;
  private boolean allowTransformAttribute;
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
    shapeIDCount = 0;
    allowTransformAttribute = true;
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
   * Returns a {@link List} of the {@link CanvasElement} which are in the canvas. This gets called when the
   * canvas gets parsed into json to send it to the client.
   *
   * @return a {@link List} of {@link CanvasElement}
   */
  @JsonProperty("elements")
  public List<CanvasElement> getCanvasElements() {
    return canvasElements;
  }

  /**
   * Returns <code>true</code> if the Canvas allows the use of the HTML "transform" attribute for
   * its Shapes.
   *
   * @return <code>true</code> if the Canvas allows the use of the HTML "transform" attribute for
   * its Shapes, <code>false</code> otherwise.
   */
  @JsonProperty("allowTransformAttribute")
  public boolean doesAllowTransformAttribute() {
    return allowTransformAttribute;
  }

  /**
   * Sets whether to allow the use of the HTML "transform" attribute for Shapes on the Canvas.
   * Changing this to <code>false</code> might cause existing transformations to be lost.
   *
   * @param allowTransformAttribute Set to <code>true</code> to allow the use of the HTML "transform"
   * attribute for Shapes on the Canvas, <code>false</code> otherwise.
   */
  public void setAllowTransformAttribute(boolean allowTransformAttribute) {

    if (this.allowTransformAttribute != allowTransformAttribute) {

      this.allowTransformAttribute = allowTransformAttribute;

      if (!allowTransformAttribute) {
        shapeFactory = new NonTransformableShapeFactory();
        canvasElements.forEach(this::makeElementNonTransformable);
      }

      else {
        shapeFactory = new TransformableShapeFactory();
        canvasElements.forEach(this::makeElementTransformable);
      }

    }

  }

  private void makeElementTransformable(CanvasElement element) {
    if (element instanceof CanvasLayer) {
      CanvasLayer layer = (CanvasLayer) element;
      if (!(layer.getShape() instanceof ShapeWithTransformAttribute)) {
        layer.setShape(new ShapeWithTransformAttribute(layer.getShape()));
      }
    }
    else if (element instanceof CanvasElementAggregate) {
      Iterator<CanvasElement> iterator = ((CanvasElementAggregate) element).createIterator();
      while (iterator.hasNext()) {
        CanvasElement currentElement = iterator.next();
        makeElementTransformable(currentElement);
      }
    }
  }

  private void makeElementNonTransformable(CanvasElement element) {
    if (element instanceof CanvasLayer) {
      CanvasLayer layer = (CanvasLayer) element;
      if (layer.getShape() instanceof ShapeWithTransformAttribute) {
        ShapeWithTransformAttribute decorator = (ShapeWithTransformAttribute) layer.getShape();
        layer.setShape(decorator.getShape());
      }
    }
    else if (element instanceof CanvasElementAggregate) {
      Iterator<CanvasElement> iterator = ((CanvasElementAggregate) element).createIterator();
      while (iterator.hasNext()) {
        CanvasElement currentElement = iterator.next();
        makeElementNonTransformable(currentElement);
      }
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
    if (allowTransformAttribute) {
      shapeFactory = new TransformableShapeFactory();
    } else {
      shapeFactory = new NonTransformableShapeFactory();
    }
  }

  public void addElement(CanvasElement element) {
    canvasElements.add(element);
  }

  public void addElement(CanvasElement element, long addBeforeElementID) {
    for (int i = 0; i < canvasElements.size(); ++i) {
      if (canvasElements.get(i).getId() == addBeforeElementID) {
        canvasElements.add(i, element);
        return;
      }
      if (canvasElements.get(i) instanceof CanvasElementAggregate) {
        Iterator<CanvasElement> iterator = ((CanvasElementAggregate) canvasElements.get(i)).createIterator();
        while (iterator.hasNext()) {
          CanvasElement currentElement = iterator.next();
          if (currentElement.getId() == addBeforeElementID) {
            iterator.insert(element);
            return;
          }
        }
      }
    }
    throw new IllegalArgumentException("Cannot add before element: " + addBeforeElementID + "; Element does not exist!");
  }

  public void addElementIntoElement(CanvasElement element, long addIntoElementID) {
    for (CanvasElement canvasElement : canvasElements) {
      if (canvasElement.getId() == addIntoElementID) {
        if (canvasElement instanceof CanvasElementAggregate) {
          ((CanvasElementAggregate) canvasElement)
              .addItem(element);
          return;
        } else {
          throw new IllegalArgumentException("Cannot add into element " + addIntoElementID + "; Not a layer group!");
        }
      }
      if (canvasElement instanceof CanvasElementAggregate) {
        Iterator<CanvasElement> iterator = ((CanvasElementAggregate) canvasElement)
            .createIterator();
        while (iterator.hasNext()) {
          CanvasElement currentElement = iterator.next();
          if (currentElement.getId() == addIntoElementID) {
            if (currentElement instanceof CanvasElementAggregate) {
              ((CanvasElementAggregate) currentElement)
                  .addItem(element);
              return;
            } else {
              throw new IllegalArgumentException("Cannot add into element " + addIntoElementID + "; Not a layer group!");
            }
          }
        }
      }
    }
    throw new IllegalArgumentException("Cannot add into element " + addIntoElementID + "; Element does not exist!");
  }

  public void addShape(ShapeType shapeType) {
    addElement(shapeFactory.createShape(shapeIDCount, shapeType));
    shapeIDCount++;
  }

  public void addShape(ShapeType shapeType, long addBeforeElementID) {
    addElement(shapeFactory.createShape(shapeIDCount, shapeType), addBeforeElementID);
    shapeIDCount++;
  }

  public void addShapeIntoElement(ShapeType shapeType, long addIntoElementID) {
    addElementIntoElement(shapeFactory.createShape(shapeIDCount, shapeType), addIntoElementID);
    shapeIDCount++;
  }

  public void addGroupLayer() {
    addElement(new CanvasElementAggregate(shapeIDCount));
    shapeIDCount++;
  }

  public void addGroupLayer(long addBeforeElementID) {
    addElement(new CanvasElementAggregate(shapeIDCount), addBeforeElementID);
    shapeIDCount++;
  }

  public void addGroupLayerIntoElement(long addIntoElementID) {
    addElementIntoElement(new CanvasElementAggregate(shapeIDCount), addIntoElementID);
    shapeIDCount++;
  }

  public CanvasElement findElementByID(long id){
    for (CanvasElement element : canvasElements) {
      if (element.getId() == id) {
        return element;
      } else if (element instanceof CanvasElementAggregate) {
        Iterator<CanvasElement> iterator = ((CanvasElementAggregate) element).createIterator();
        while (iterator.hasNext()) {
          CanvasElement currentElement = iterator.next();
          if (currentElement.getId() == id) {
            return currentElement;
          }
        }
      }
    }
    return null;
  }

  public void updateElementByID(long id, CanvasElement canvasElement) {
    for (int i = 0; i < canvasElements.size(); ++i) {
      if (canvasElements.get(i).getId() == id) {
        canvasElements.set(i, canvasElement);
      } else if (canvasElements.get(i) instanceof CanvasElementAggregate) {
        Iterator<CanvasElement> iterator = ((CanvasElementAggregate) canvasElements.get(i))
            .createIterator();
        while (iterator.hasNext()) {
          CanvasElement currentElement = iterator.next();
          if (currentElement.getId() == id) {
            iterator.set(canvasElement);
            return;
          }
        }
      }
    }
  }

  public void removeElementByID(long id) {
    for (int i = 0; i < canvasElements.size(); ++i) {
      if (canvasElements.get(i).getId() == id) {
        canvasElements.remove(i);
        return;
      }
      if (canvasElements.get(i) instanceof CanvasElementAggregate) {
        Iterator<CanvasElement> iterator = ((CanvasElementAggregate) canvasElements.get(i))
            .createIterator();
        while (iterator.hasNext()) {
          CanvasElement currentElement = iterator.next();
          if (currentElement.getId() == id) {
            iterator.remove();
            return;
          }
        }
      }
    }
  }

  public void transformElementByID(long id, Transformation transformation) {
    findElementByID(id).transform(transformation);
  }

}