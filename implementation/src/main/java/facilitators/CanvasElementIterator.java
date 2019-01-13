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

  public boolean hasNext() {
    if (currentIndex + 1 >= aggregate.size() && (childIterator == null || !childIterator.hasNext())) {
      done = true;
    }
    return !(invalidated || done);
  }

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