package facilitators;

import canvas.CanvasElement;
import java.util.List;

public class ListIterator implements Iterator {

  List<CanvasElement> elements;
  int index = 0;

  public ListIterator(List<CanvasElement> elements) {
    this.elements = elements;
  }

  @Override
  public Object first() {
    return elements.get(0);
  }

  @Override
  public Object next() {
    return elements.get(index++);
  }

  @Override
  public boolean isDone() {
    if (index == elements.size()) {
      return true;
    }
    return false;
  }

  @Override
  public Object currentItem() {
    return elements.get(index);
  }

  @Override
  public void removeItem() {
    elements.remove(index);
  }
}
