package facilitators;

public class NullIterator<T> implements Iterator<T> {

  @Override
  public T next() {
    return null;
  }

  @Override
  public boolean hasNext() {
    return false;
  }

  @Override
  public void set(T item) {
    throw new UnsupportedOperationException("Can't set a NullIterator!");
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException("Can't remove a NullIterator!");
  }
}
