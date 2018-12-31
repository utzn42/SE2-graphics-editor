package canvas;

import facilitators.Aggregate;
import facilitators.AggregateIterator;
import facilitators.Iterator;
import java.util.ArrayList;
import shapes.transform.ShapeTransformer;

/**
 * An iterable aggregate that can hold any number of {@link CanvasElement CanvasElements}.
 */
public class CanvasElementAggregate extends CanvasElement implements Aggregate<CanvasElement> {

  Iterator<CanvasElement> iterator = null;
  private ArrayList<CanvasElement> elements;

  @Override
  public void addItem(CanvasElement item) {
    elements.add(item);
  }

  @Override
  public CanvasElement getItem(int index) throws IndexOutOfBoundsException {
    return elements.get(index);
  }

  @Override
  public boolean deleteItem(int index) {
    if ((index > 0) && (index < elements.size())) {
      elements.remove(index);
      return true;
    }
    return false;
  }

  @Override
  public boolean deleteItem(CanvasElement item) {
    if (elements.contains(item)) {
      elements.remove(item);
      return true;
    }
    return false;
  }

  @Override
  public Iterator<CanvasElement> createIterator() {
    if (iterator == null) {
      iterator = new AggregateIterator(this.createIterator());
    }
    return new CanvasElementIterator(this);
  }

  /**
   * Transforms each {@link CanvasElement} within the aggregate using a {@link ShapeTransformer}
   *
   * @param shapeTransformer the transformation to be applied to each {@link CanvasElement}
   */
  @Override
  public void transform(ShapeTransformer shapeTransformer) {
    //TODO: Implement canvas.CanvasElementAggregate#transform(ShapeTransformer)
  }

}
