package facilitators;

import canvas.CanvasElement;
import java.util.Stack;

/**
 * An iterator which iterates over all children of an aggregate.
 */

public class CanvasElementIterator implements Iterator<CanvasElement> {

  Stack<Iterator<CanvasElement>> stack = new Stack<>();

  /**
   * Constructs an CanvasElementIterator with the iterator of the overlying composite.
   *
   * @param iterator Iterator variable of the overlying composite.
   */

  public CanvasElementIterator(Iterator iterator) {
    stack.push(iterator);
  }

  /**
   * Sequentially iterates through all children of the node.
   *
   * @return The next leaf or first leaf of the next node, respectively.
   */

  @Override
  public CanvasElement next() {
    if (hasNext()) {
      Iterator<CanvasElement> iterator = stack.peek();
      CanvasElement element = iterator.next();

      stack.push(element
          .createIterator());

      return element;
    } else {
      return null;
    }
  }

  /**
   * Checks stack to see whether there are any elements left to iterate over.
   *
   * @return A boolean value which indicates weather the stack is empty (done) or not.
   */

  @Override
  public boolean hasNext() {
    if (stack.empty()) {
      return false;
    } else {
      Iterator<CanvasElement> iterator = stack.peek();
      if (!iterator.hasNext()) {
        stack.pop();
        return hasNext();
      } else {
        return true;
      }
    }
  }

  @Override
  public CanvasElement get() {
    Iterator iterator = stack.peek();
    return (CanvasElement) iterator.get();
  }

  @Override
  public void set(CanvasElement item) {
    Iterator iterator = stack.peek();
    iterator.set(item);
  }

  @Override
  public void remove() {
    while (!(stack.peek() instanceof ListIterator)) {
      stack.pop();
    }
    Iterator iterator = stack.peek();
    iterator.remove();
  }
}
