package facilitators;

import java.util.List;

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
   * Adds an item at the specified index to the Aggregate. Pushes the original item with that
   * index and all following items back an index.
   *
   * @param item The item to add to the Aggregate.
   * @param index The index at which to insert the item.
   */
  void addItem(T item, int index);

  /**
   * Adds all items in the given Aggregate to this Aggregate.
   *
   * @param aggregate The Aggregate to add all items from.
   */
  void addAll(Aggregate<T> aggregate);

  /**
   * Returns the item at the specified index
   *
   * @param index The index of the item to get
   * @return The item at the specified index
   * @throws IndexOutOfBoundsException If the specified index does not exist
   */
  T getItem(int index) throws IndexOutOfBoundsException;

  /**
   * Returns all items as a {@link List}.
   *
   * @return All items as a {@link List}.
   */
  List<T> asList();

  /**
   * Sets the item at the specified index to the specified item.
   *
   * @param index The index of the item to set.
   * @param item The new item to replace the old one.
   * @throws IndexOutOfBoundsException If the specified index does not exist.
   */
  void setItem(int index, T item) throws IndexOutOfBoundsException;


  /**
   * Removes the item at the specified index
   *
   * @param index The index of the item to be removed
   * @return <code>true</code> if a deletion occurred, <code>false</code> otherwise
   */
  boolean deleteItem(int index);

  /**
   * Returns the number of items in the Aggregate.
   *
   * @return The number of items in the Aggregate.
   */
  int size();


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
