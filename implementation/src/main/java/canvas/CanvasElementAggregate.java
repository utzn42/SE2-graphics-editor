package canvas;

import com.fasterxml.jackson.annotation.JsonProperty;
import facilitators.Aggregate;
import facilitators.Iterator;
import java.util.ArrayList;
import java.util.List;
import shapes.transform.Transformation;

/**
 * An iterable aggregate that can hold any number of {@link CanvasElement CanvasElements}.
 */
public class CanvasElementAggregate extends CanvasElement implements Aggregate<CanvasElement> {

  private static final long serialVersionUID = 1L;

  private List<CanvasElement> elements;

  /**
   * Creates a {@link CanvasElementAggregate}
   *
   * @param id The ID which gets assigned to the {@link CanvasElementAggregate}
   */
  public CanvasElementAggregate(long id) {
    super(id);
    elements = new ArrayList<>();
  }

  /**
   * Adds an item to the Aggregate
   *
   * @param item The item to add to the Aggregate
   */
  @Override
  public void addItem(CanvasElement item) {
    elements.add(item);
  }

  /**
   * Adds an item at the specified index to the Aggregate. Pushes the original item with that index
   * and all following items back an index.
   *
   * @param item The item to add to the Aggregate.
   * @param index The index at which to insert the item.
   */
  @Override
  public void addItem(CanvasElement item, int index) {
    elements.add(index, item);
  }

  /**
   * Adds all items in the given Aggregate to this Aggregate.
   *
   * @param aggregate The Aggregate to add all items from.
   */
  @Override
  public void addAll(Aggregate<CanvasElement> aggregate) {
    elements.addAll(aggregate.asList());
  }

  /**
   * Returns the item at the specified index
   *
   * @param index The index of the item to get
   * @return The item at the specified index
   * @throws IndexOutOfBoundsException If the specified index does not exist
   */
  @Override
  public CanvasElement getItem(int index) throws IndexOutOfBoundsException {
    return elements.get(index);
  }

  /**
   * Returns all items as a {@link List}.
   *
   * @return All items as a {@link List}.
   */
  @Override
  @JsonProperty("elements")
  public List<CanvasElement> asList() {
    return new ArrayList<>(elements);
  }

  /**
   * Sets the item at the specified index to the specified item.
   *
   * @param index The index of the item to set.
   * @param item The new item to replace the old one.
   * @throws IndexOutOfBoundsException If the specified index does not exist.
   */
  @Override
  public void setItem(int index, CanvasElement item) throws IndexOutOfBoundsException {
    elements.set(index, item);
  }

  /**
   * Removes the item at the specified index
   *
   * @param index The index of the item to be removed
   * @return <code>true</code> if a deletion occurred, <code>false</code> otherwise
   */
  @Override
  public boolean deleteItem(int index) {
    if ((index >= 0) && (index < elements.size())) {
      elements.remove(index);
      return true;
    }
    return false;
  }

  /**
   * Removes the given item from the Aggregate
   *
   * @param item The item to be removed
   * @return <code>true</code> if a deletion occurred, <code>false</code> otherwise
   */
  @Override
  public boolean deleteItem(CanvasElement item) {
    if (elements.contains(item)) {
      elements.remove(item);
      return true;
    }
    return false;
  }

  /**
   * Returns the number of items in the Aggregate.
   *
   * @return The number of items in the Aggregate.
   */
  @Override
  public int size() {
    return elements.size();
  }

  /**
   * Returns an {@link Iterator} over the Aggregate
   *
   * @return An {@link Iterator} over the Aggregate
   */
  @Override
  public Iterator<CanvasElement> createIterator() {
    return new CanvasElementIterator(this);
  }

  /**
   * Returns the HTML Code of the element.
   *
   * @return HTML Code of the element.
   */
  @Override
  public String getHTML() {
    StringBuilder stringBuilder = new StringBuilder();
    for (CanvasElement element : elements) {
      if (element.isVisible()) {
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
