package canvas;

import facilitators.Iterator;
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
   *
   */

  @Override
  public CanvasElement first() {
    return null;
  }

  /**
   * Sequentially iterates through all children of the node.
   *
   * @return The next leaf or first leaf of the next node, respectively.
   */

  @Override
  public CanvasElement next() {
    if (!isDone()) {
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
  public boolean isDone() {
    if (stack.empty()) {
      return true;
    } else {
      Iterator<CanvasElement> iterator = stack.peek();
      if (iterator.isDone()) {
        stack.pop();
        return isDone();
      } else {
        return false;
      }
    }
  }

  @Override
  public CanvasElement currentItem() {
    return stack.peek().currentItem();
  }
}
