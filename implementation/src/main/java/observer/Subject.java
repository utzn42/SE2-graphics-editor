package observer;

/**
 * Subject class for use in the observer pattern. Can be observed by any number of {@link Observer Observers}.
 *
 * @see Observer
 */
public interface Subject {

  /**
   * Registers a new {@link Observer} to observe this Subject.
   *
   * @param o The new {@link Observer} to observe this Subject.
   */
  void registerObserver(Observer o);

  /**
   * Removes an {@link Observer} from the list of Observers observing this Subject.
   *
   * @param o The {@link Observer} to be removed.
   */
  void removeObserver(Observer o);

  /**
   * Notifies all {@link Observer Observers} that the Subject has changed.
   * Should call {@link Observer#update(Object)} on the Observers.
   *
   * @param obj The Object that has changed (does not need to be the Subject itself).
   */
  void notifyObservers(Object obj);

}
