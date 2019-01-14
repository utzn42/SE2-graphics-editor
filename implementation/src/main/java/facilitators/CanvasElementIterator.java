package facilitators;

import canvas.CanvasElement;

/**
 * An iterator which iterates over all children of an aggregate.
 */

public class CanvasElementIterator implements Iterator<CanvasElement> {

  private Iterator<CanvasElement> childIterator;
  private final Aggregate<CanvasElement> aggregate;
  private int currentIndex;
  private boolean done;
  private boolean invalidated;

  /**
   * Creates a new CanvasElementIterator over the given {@link Aggregate} of {@link CanvasElement CanvasElements}.
   *
   * @param aggregate The Aggregate this CanvasElementIterator should iterate over.
   */
  public CanvasElementIterator(Aggregate<CanvasElement> aggregate) {
    childIterator = null;
    this.aggregate = aggregate;
    currentIndex = -1;
    if (aggregate.size() == 0) {
      done = true;
      invalidated = true;
    } else {
      done = false;
      invalidated = false;
    }
  }

  /**
   * Returns <code>true</code> if the iterator has traversed the whole collection.
   *
   * @return <code>true</code> if the iterator has traversed the whole collection, <code>false</code> otherwise.
   */
  public boolean hasNext() {
    if (currentIndex + 1 >= aggregate.size() && (childIterator == null || !childIterator.hasNext())) {
      done = true;
    }
    return !(invalidated || done);
  }

  /**
   * Returns the next object in the collection.
   *
   * @return The next object in the collection, or <code>null</code> if such an object does not exist.
   */
  @Override
  public CanvasElement next() {

    if (!hasNext()) {
      invalidated = true;
      return null;
    }

    if (childIterator != null) {
      if (!childIterator.hasNext()) {
        childIterator = null;
      } else {
        return childIterator.next();
      }
    }

    ++currentIndex;
    CanvasElement currentTopLevelItem = aggregate.getItem(currentIndex);

    if (currentTopLevelItem instanceof Aggregate && childIterator == null) {
      childIterator = ((Aggregate<CanvasElement>) currentTopLevelItem).createIterator();
    }

    return currentTopLevelItem;

  }

  /**
   * Returns the current object.
   *
   * @return The current object.
   */
  @Override
  public CanvasElement currentItem() {
    if (invalidated || currentIndex == -1) {
      return null;
    }
    if (childIterator != null && childIterator.currentItem() != null) {
      return (childIterator.currentItem());
    }
    return aggregate.getItem(currentIndex);
  }

  /**
   * Removes the current object.
   */
  @Override
  public void remove() {
    if (invalidated || currentIndex == -1) {
      return;
    }
    if (childIterator != null && childIterator.currentItem() != null) {
      childIterator.remove();
    } else {
      aggregate.deleteItem(currentIndex);
    }
    invalidated = true;
  }

  /**
   * Sets the current object to the object given as parameter.
   *
   * @param item The object to set the current object to.
   */
  @Override
  public void set(CanvasElement item) {
    if (invalidated || currentIndex == -1) {
      return;
    }
    if (childIterator != null && childIterator.currentItem() != null) {
      childIterator.set(item);
    } else {
      aggregate.setItem(currentIndex, item);
    }
    invalidated = true;
  }

  /**
   * Adds the given object to the collection before the current object.
   *
   * @param item The object to insert.
   */
  @Override
  public void insert(CanvasElement item) {
    if (invalidated || currentIndex == -1) {
      return;
    }
    if (childIterator != null && childIterator.currentItem() != null) {
      childIterator.insert(item);
    } else {
      aggregate.addItem(item, currentIndex);
    }
    invalidated = true;
  }

}