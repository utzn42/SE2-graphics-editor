package facilitators;

public class NullIterator<T> implements Iterator<T> {

  @Override
  public T first() {
    return null;
  }

  @Override
  public T next() {
    return null;
  }

  @Override
  public boolean isDone() {
    return true;
  }

  @Override
  public T currentItem() {
    return null;
  }

  @Override
  public void removeItem() {
    throw new UnsupportedOperationException("NullIterator doesn't support removeItem().");
  }
}
