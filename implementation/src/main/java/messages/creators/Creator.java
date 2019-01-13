package messages.creators;

/**
 * An object that can be included in a client request. It can create a certain type of Object in the
 * Server model.
 *
 * @param <T> The type of Object to create.
 */
public interface Creator<T> {

  /**
   * Creates an Object of the Creator's type from its internal parameters.
   *
   * @return The created Object.
   */
  T create();

}
