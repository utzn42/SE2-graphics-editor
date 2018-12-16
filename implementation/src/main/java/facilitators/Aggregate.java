package facilitators;

/**
 * An iterable collection of objects
 *
 * @param <T> The type of object contained in the collection
 */
public interface Aggregate<T> {

  /**
   * Adds an item to the Aggregate
   *
   * @param item The item to add to the Aggregate
   */
  void addItem(T item);

  /**
   * Returns the item at the specified index
   *
   * @param index The index of the item to get
   * @return The item at the specified index
   * @throws IndexOutOfBoundsException If the specified index does not exist
   */
  T getItem(int index) throws IndexOutOfBoundsException;

  /**
   * Removes the item at the specified index
   *
   * @param index The index of the item to be removed
   * @return <code>true</code> if a deletion occurred, <code>false</code> otherwise
   */
  boolean deleteItem(int index);

  /**
   * Removes the given item from the Aggregate
   *
   * @param item The item to be removed
   * @return <code>true</code> if a deletion occurred, <code>false</code> otherwise
   */
  boolean deleteItem(T item);

  /**
   * Returns an {@link Iterator} over the Aggregate
   *
   * @return An {@link Iterator} over the Aggregate
   */
  Iterator<T> createIterator();

}
