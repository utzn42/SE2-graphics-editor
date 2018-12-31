package facilitators;

public class NullIterator implements Iterator {

  @Override
  public Object first() {
    return null;
  }

  @Override
  public Object next() {
    return null;
  }

  @Override
  public boolean isDone() {
    return false;
  }

  @Override
  public Object currentItem() {
    return null;
  }
}
