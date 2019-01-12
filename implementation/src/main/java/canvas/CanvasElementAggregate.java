package canvas;

import facilitators.Aggregate;
import facilitators.Iterator;
import facilitators.ListIterator;
import java.util.ArrayList;
import java.util.List;
import shapes.transform.Transformation;

/**
 * An iterable aggregate that can hold any number of {@link CanvasElement CanvasElements}.
 */
public class CanvasElementAggregate extends CanvasElement implements Aggregate<CanvasElement> {

  private static final long serialVersionUID = 1L;

  transient Iterator<CanvasElement> iterator;
  private List<CanvasElement> elements;

  public CanvasElementAggregate(long id) {
    super(id);
    iterator = null;
    elements = new ArrayList<>();
  }

  @Override
  public void addItem(CanvasElement item) {
    elements.add(item);
  }

  @Override
  public void addItem(CanvasElement item, int index) {
    elements.add(index, item);
  }

  @Override
  public void addAll(Aggregate<CanvasElement> aggregate) {
    elements.addAll(aggregate.asList());
  }


  @Override
  public CanvasElement getItem(int index) throws IndexOutOfBoundsException {
    return elements.get(index);
  }

  @Override
  public List<CanvasElement> asList() {
    return new ArrayList<>(elements);
  }

  @Override
  public void setItem(int index, CanvasElement item) throws IndexOutOfBoundsException {
    elements.set(index, item);
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
  public int size() {
    return elements.size();
  }


  @Override
  public Iterator<CanvasElement> createIterator() {
    if (iterator == null) {
      iterator = new CanvasElementIterator(new ListIterator(elements));
    }
    return iterator;
  }

  @Override
  public String getHTML() {
    StringBuilder stringBuilder = new StringBuilder();
    for(CanvasElement element : elements){
      if(element.isVisible()) {
        stringBuilder.append(element.getHTML());
      }
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
