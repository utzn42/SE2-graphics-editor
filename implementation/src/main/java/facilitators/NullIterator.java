package facilitators;

public class NullIterator<T> implements Iterator<T> {

  @Override
  public T next() {
    return null;
  }

  @Override
  public boolean hasNext() {
    return true;
  }
}
