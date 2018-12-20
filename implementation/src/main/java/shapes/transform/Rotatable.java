package shapes.transform;

public interface Rotatable {

  /**
   * Rotates the object using a {@link Rotator}
   *
   * @param rotator The rotation to apply to the object
   */
  void rotate(Rotator rotator);

}
