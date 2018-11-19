package observer;

/**
 * Observer class for use in the observer pattern. Observes a {@link Subject} for changes.
 *
 * @see Subject
 */
public interface Observer {

  /**
   * Function that should be called by the observed subject when it changes.
   *
   * @param obj The changed object (does not necessarily need to be the subject itself).
   */
  void update(Object obj);

}
