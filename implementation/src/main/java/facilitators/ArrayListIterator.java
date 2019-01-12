package facilitators;

import canvas.CanvasElement;
import java.util.ArrayList;

public class ArrayListIterator implements Iterator {

  ArrayList<CanvasElement> elements;
  int index = 0;

  public ArrayListIterator(ArrayList<CanvasElement> elements) {
    this.elements = elements;
  }

  @Override
  public Object first() {
    return elements.get(0);
  }

  @Override
  public Object next() {
    return elements.get(++index);
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
