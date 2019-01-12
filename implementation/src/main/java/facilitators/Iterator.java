package facilitators;

/**
 * An iterator over a collection of objects
 *
 * @param <T> The type of objects contained in the collection
 */
public interface Iterator<T> {

  /**
   * Returns the next object in the collection
   *
   * @return The next object in the collection, or <code>null</code> if such an object does not
   * exist
   */
  T next();

  /**
   * Returns <code>true</code> if the iterator has traversed the whole collection
   *
   * @return <code>true</code> if the iterator has traversed the whole collection,
   * <code>false</code> otherwise
   */
  boolean hasNext();


  void set(T item);

  void remove();
}
