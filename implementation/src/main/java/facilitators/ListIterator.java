package facilitators;

import canvas.CanvasElement;
import java.util.List;


public class ListIterator implements Iterator<CanvasElement> {

  List<CanvasElement> elements;
  int index = 0;

  public ListIterator(List<CanvasElement> elements) {
    this.elements = elements;
  }

  @Override
  public CanvasElement next() {
    return elements.get(index++);
  }

  @Override
  public boolean hasNext() {
    if (index == elements.size()) {
      return false;
    }
    return true;
  }

  @Override
  public CanvasElement get() {
    return elements.get(index);
  }

  @Override
  public void set(CanvasElement item) {
    elements.set(index, item);
  }

  @Override
  public void remove() {
    elements.remove(index);
  }
}
