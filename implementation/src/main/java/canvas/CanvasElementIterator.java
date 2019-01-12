package canvas;

import facilitators.Aggregate;
import facilitators.Iterator;

/**
 * An iterator which iterates over all children of an aggregate.
 */

public class CanvasElementIterator implements Iterator<CanvasElement> {

  private Iterator<CanvasElement> childIterator;
  private final Aggregate<CanvasElement> aggregate;
  private int currentIndex;
  private boolean done;
  private boolean invalidated;

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

  public boolean isDone() {
    if (currentIndex + 1 >= aggregate.size() && (childIterator == null || childIterator.isDone())) {
      done = true;
    }
    return invalidated || done;
  }

  @Override
  public CanvasElement next() {

    if (isDone()) {
      invalidated = true;
      return null;
    }

    if (childIterator != null) {
      if (childIterator.isDone()) {
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

  @Override
  public void remove() {
    aggregate.deleteItem(currentIndex);
    invalidated = true;
  }

  @Override
  public void set(CanvasElement item) {
    aggregate.setItem(currentIndex, item);
  }

}