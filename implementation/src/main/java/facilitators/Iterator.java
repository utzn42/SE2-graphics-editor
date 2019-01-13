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
   * @return The next object in the collection, or <code>null</code> if such an object does not exist
   */
  T next();

  /**
   * Returns <code>true</code> if the iterator has traversed the whole collection
   *
   * @return <code>true</code> if the iterator has traversed the whole collection, <code>false</code> otherwise
   */
  boolean hasNext();

  /**
   * Returns the current object
   *
   * @return The current object
   */
  T currentItem();

  /**
   * Removes the current object.
   */
  void remove();

  /**
   * Sets the current object to the object given as parameter.
   *
   * @param item The object to set the current object to.
   */
  void set(T item);

  /**
   * Adds the given object to the collection before the current object.
   *
   * @param item The object to insert.
   */
  void insert(T item);

}
