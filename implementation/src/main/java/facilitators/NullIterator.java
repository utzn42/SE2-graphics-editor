package facilitators;

public class NullIterator<CanvasElement> implements Iterator<CanvasElement> {

  @Override
  public CanvasElement next() {
    return null;
  }

  @Override
  public boolean hasNext() {
    return false;
  }

  @Override
  public CanvasElement get() {
    return null;
  }

  @Override
  public void set(CanvasElement item) {
    throw new UnsupportedOperationException("Can't set a NullIterator!");
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException("Can't remove a NullIterator!");
  }
}
