package messages.modifiers;

/**
 * An object that can be included in a client request. It can modify a certain type of Object in the
 * Server model.
 *
 * @param <T> The type of Object the Modifier can modify.
 */
public interface Modifier<T> {

  /**
   * Modifies the given Object using the Modifier.
   *
   * @param objectToModify The Object to modify.
   * @return The modified Object.
   */
  T apply(T objectToModify);

}
