package canvas;

import facilitators.Aggregate;
import facilitators.Iterator;
import java.util.ArrayList;
import java.util.List;
import shapes.transform.Transformation;

/**
 * An iterable aggregate that can hold any number of {@link CanvasElement CanvasElements}.
 */
public class CanvasElementAggregate extends CanvasElement implements Aggregate<CanvasElement> {

  Iterator<CanvasElement> iterator;
  private List<CanvasElement> elements;

  public CanvasElementAggregate() {
    iterator = null;
    elements = new ArrayList<>();
  }

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
      iterator = new CanvasElementIterator(this.createIterator());
    }
    return new CanvasElementIterator(this.iterator);
  }

  @Override
  public String getHTML() {
    StringBuilder stringBuilder = new StringBuilder();
    for(CanvasElement element : elements){
      stringBuilder.append(element.getHTML());
    }
    return stringBuilder.toString();
  }


  /**
   * Transforms each {@link CanvasElement} within the aggregate using a {@link Transformation}
   *
   * @param transformation the transformation to be applied to each {@link CanvasElement}
   */
  @Override
  public void transform(Transformation transformation) {
    //TODO: Implement canvas.CanvasElementAggregate#transform(Transformation) - xandi
  }

}
