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
  public Object next() {
    return elements.get(index++);
  }

  @Override
  public boolean hasNext() {
    if (index == elements.size()) {
      return false;
    }
    return true;
  }

  public void set(CanvasElement canvasElement) {
    elements.set(index, canvasElement);
  }

  @Override
  public void remove() {
    elements.remove(index);
  }
}
