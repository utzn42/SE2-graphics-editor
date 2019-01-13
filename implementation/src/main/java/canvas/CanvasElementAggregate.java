package canvas;

import com.fasterxml.jackson.annotation.JsonProperty;
import facilitators.Aggregate;
import facilitators.CanvasElementIterator;
import facilitators.Iterator;
import java.util.ArrayList;
import java.util.List;
import shapes.Shape;
import shapes.transform.Transformation;

/**
 * An iterable aggregate that can hold any number of {@link CanvasElement CanvasElements}.
 */
public class CanvasElementAggregate extends CanvasElement implements Aggregate<CanvasElement> {

  private static final long serialVersionUID = 1L;

  private List<CanvasElement> elements;

  public CanvasElementAggregate(long id) {
    super(id);
    elements = new ArrayList<>();
  }

  @Override
  public void setShape(Shape shape) {
    throw new UnsupportedOperationException("Can't set shape in a CanvasElementAggregate!");
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
  @JsonProperty("elements")
  public List<CanvasElement> asList() {
    return new ArrayList<>(elements);
  }

  @Override
  public void setItem(int index, CanvasElement item) throws IndexOutOfBoundsException {
    elements.set(index, item);
  }

  @Override
  public boolean deleteItem(int index) {
    if ((index >= 0) && (index < elements.size())) {
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
    return new CanvasElementIterator(this);
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
    elements.forEach(element -> element.transform(transformation));
  }

}
