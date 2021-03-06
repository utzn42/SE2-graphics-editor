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

  private static final long serialVersionUID = 1L;

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
   * Returns a {@link List} of the {@link CanvasElement} which are in the canvas. This gets called
   * when the canvas gets parsed into json to send it to the client.
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
   * @param allowTransformAttribute Set to <code>true</code> to allow the use of the HTML
   * "transform" attribute for Shapes on the Canvas, <code>false</code> otherwise.
   */
  public void setAllowTransformAttribute(boolean allowTransformAttribute) {

    if (this.allowTransformAttribute != allowTransformAttribute) {

      this.allowTransformAttribute = allowTransformAttribute;

      if (!allowTransformAttribute) {
        shapeFactory = new NonTransformableShapeFactory();
        canvasElements.forEach(this::makeElementNonTransformable);
      } else {
        shapeFactory = new TransformableShapeFactory();
        canvasElements.forEach(this::makeElementTransformable);
      }

    }

  }

  /**
   * Searches for the element in the parameter and sets it transformable.
   *
   * @param element The element which will get set transformable.
   */
  private void makeElementTransformable(CanvasElement element) {
    if (element instanceof CanvasLayer) {
      CanvasLayer layer = (CanvasLayer) element;
      if (!(layer.getShape() instanceof ShapeWithTransformAttribute)) {
        layer.setShape(new ShapeWithTransformAttribute(layer.getShape()));
      }
    } else if (element instanceof CanvasElementAggregate) {
      Iterator<CanvasElement> iterator = ((CanvasElementAggregate) element).createIterator();
      while (iterator.hasNext()) {
        CanvasElement currentElement = iterator.next();
        makeElementTransformable(currentElement);
      }
    }
  }

  /**
   * Searches for element in the parameter and sets it to not transformable.
   *
   * @param element The element which will get set non transformable.
   */
  private void makeElementNonTransformable(CanvasElement element) {
    if (element instanceof CanvasLayer) {
      CanvasLayer layer = (CanvasLayer) element;
      if (layer.getShape() instanceof ShapeWithTransformAttribute) {
        ShapeWithTransformAttribute decorator = (ShapeWithTransformAttribute) layer.getShape();
        layer.setShape(decorator.getShape());
      }
    } else if (element instanceof CanvasElementAggregate) {
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


  /**
   * Deserializes {@link java.io.ObjectInputStream}.
   * @param oin The InputStream
   * @throws IOException throws IOException
   * @throws ClassNotFoundException throws ClassNotFoundException
   */
  private void readObject(java.io.ObjectInputStream oin)
      throws IOException, ClassNotFoundException {
    oin.defaultReadObject();
    if (allowTransformAttribute) {
      shapeFactory = new TransformableShapeFactory();
    } else {
      shapeFactory = new NonTransformableShapeFactory();
    }
  }

  /**
   * Adds a new element to the elements of the canvas.
   *
   * @param element The element which will get added.
   */
  public void addElement(CanvasElement element) {
    canvasElements.add(element);
  }

  /**
   * Adds a new element to the elements of the canvas before another element.
   *
   * @param element The element which will get added.
   * @param addBeforeElementID The ID of the element where the new element gets added before.
   */
  public void addElement(CanvasElement element, long addBeforeElementID) {
    for (int i = 0; i < canvasElements.size(); ++i) {
      if (canvasElements.get(i).getId() == addBeforeElementID) {
        canvasElements.add(i, element);
        return;
      }
      if (canvasElements.get(i) instanceof CanvasElementAggregate) {
        Iterator<CanvasElement> iterator = ((CanvasElementAggregate) canvasElements.get(i))
            .createIterator();
        while (iterator.hasNext()) {
          CanvasElement currentElement = iterator.next();
          if (currentElement.getId() == addBeforeElementID) {
            iterator.insert(element);
            return;
          }
        }
      }
    }
    throw new IllegalArgumentException(
        "Cannot add before element: " + addBeforeElementID + "; Element does not exist!");
  }

  /**
   * Adds an element beneath another element if it's possible. If it's not possible it throws an {@link IllegalArgumentException}
   * @param element Element which should get nested.
   * @param addIntoElementID The ID of the element where the other element should get added.
   */
  public void addElementIntoElement(CanvasElement element, long addIntoElementID) {
    for (CanvasElement canvasElement : canvasElements) {
      if (canvasElement.getId() == addIntoElementID) {
        if (canvasElement instanceof CanvasElementAggregate) {
          canvasElement.addItem(element);
          return;
        } else {
          throw new IllegalArgumentException(
              "Cannot add into element " + addIntoElementID + "; Not a layer group!");
        }
      }
      if (canvasElement instanceof CanvasElementAggregate) {
        Iterator<CanvasElement> iterator = ((CanvasElementAggregate) canvasElement)
            .createIterator();
        while (iterator.hasNext()) {
          CanvasElement currentElement = iterator.next();
          if (currentElement.getId() == addIntoElementID) {
            if (currentElement instanceof CanvasElementAggregate) {
              currentElement.addItem(element);
              return;
            } else {
              throw new IllegalArgumentException(
                  "Cannot add into element " + addIntoElementID + "; Not a layer group!");
            }
          }
        }
      }
    }
    throw new IllegalArgumentException(
        "Cannot add into element " + addIntoElementID + "; Element does not exist!");
  }

  /**
   * Adds a new Shape to the canvas.
   *
   * @param shapeType The type of the shape which gets added.
   */
  public void addShape(ShapeType shapeType) {
    addElement(shapeFactory.createShape(shapeIDCount, shapeType));
    shapeIDCount++;
  }

  /**
   * Adds a new shape to the canvas before another element.
   *
   * @param shapeType The type of the shape which gets added.
   * @param addBeforeElementID the ID of the element where the new shape gets added before.
   */
  public void addShape(ShapeType shapeType, long addBeforeElementID) {
    addElement(shapeFactory.createShape(shapeIDCount, shapeType), addBeforeElementID);
    shapeIDCount++;
  }

  /**
   * Adds a shape beneath another element if it's possible. If it's not possible it throws an {@link IllegalArgumentException}
   * @param shapeType type of the shape which should get nested.
   * @param addIntoElementID The ID of the element where the other element should get added.
   */
  public void addShapeIntoElement(ShapeType shapeType, long addIntoElementID) {
    addElementIntoElement(shapeFactory.createShape(shapeIDCount, shapeType), addIntoElementID);
    shapeIDCount++;
  }

  /**
   * Adds a new group Layer to the canvas.
   */
  public void addGroupLayer() {
    addElement(new CanvasElementAggregate(shapeIDCount));
    shapeIDCount++;
  }

  /**
   * Adds a new group layer to the canvas before the elementID in the parameter.
   *
   * @param addBeforeElementID The ID of the element where the new layerGroup should get added.
   */
  public void addGroupLayer(long addBeforeElementID) {
    addElement(new CanvasElementAggregate(shapeIDCount), addBeforeElementID);
    shapeIDCount++;
  }

  /**
   * Adds a layerGroup beneath another element if it's possible. If it's not possible it throws an {@link IllegalArgumentException}.
   *
   * @param addIntoElementID The ID of the element where the other element should get added.
   * @throws IllegalArgumentException If an element with the specified ID does not exist or if the element to add into is not a {@link CanvasElementAggregate}.
   */
  public void addGroupLayerIntoElement(long addIntoElementID) {
    addElementIntoElement(new CanvasElementAggregate(shapeIDCount), addIntoElementID);
    shapeIDCount++;
  }

  /**
   * Returns the {@link CanvasElement} with the ID in the parameter.
   *
   * @param id The ID of the CanvasElement.
   * @return The {@link CanvasElement} with the given ID.
   */
  public CanvasElement findElementByID(long id) {
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

  /**
   * Updates the {@link CanvasElement} with the given parameter to the new {@link CanvasElement}
   * given in the parameter.
   *
   * @param id The ID of the {@link CanvasElement}.
   * @param canvasElement The new {@link CanvasElement}.
   */
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

  /**
   * Removes the Element with the given ID.
   *
   * @param id The ID of the element which should get removed.
   */
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

  /**
   * Applies a transformation to the wanted element.
   *
   * @param id The ID of the element which should get transformed.
   * @param transformation The transformation
   */
  public void transformElementByID(long id, Transformation transformation) {
    findElementByID(id).transform(transformation);
  }

  /**
   * Clears all transformations from the wanted element.
   *
   * @param id The ID of the element to clear all transformations for.
   */
  public void clearTransformationsOnElement(long id) {
    findElementByID(id).clearTransformations();
  }

}