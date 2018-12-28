package canvas;

import facilitators.Aggregate;
import facilitators.Iterator;
import java.util.List;
import shapes.transform.ShapeTransformer;

/**
 * An iterable aggregate that can hold any number of {@link CanvasElement CanvasElements}.
 */
public class CanvasElementAggregate extends CanvasElement implements Aggregate<CanvasElement> {

  private List<CanvasElement> elements;

  @Override
  public void addItem(CanvasElement item) {
    //TODO: Implement canvas.CanvasElementAggregate#addItem(CanvasElement)
  }

  @Override
  public CanvasElement getItem(int index) throws IndexOutOfBoundsException {
    //TODO: Implement canvas.CanvasElementAggregate#getItem(int)
    return null;
  }

  @Override
  public boolean deleteItem(int index) {
    //TODO: Implement canvas.CanvasElementAggregate#deleteItem(int)
    return false;
  }

  @Override
  public boolean deleteItem(CanvasElement item) {
    //TODO: Implement canvas.CanvasElementAggregate#deleteItem(CanvasElement)
    return false;
  }

  @Override
  public Iterator<CanvasElement> createIterator() {
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
