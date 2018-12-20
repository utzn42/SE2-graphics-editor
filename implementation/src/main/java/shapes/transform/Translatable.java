package shapes.transform;

public interface Translatable {

  /**
   * Translates the object using a {@link Translater}
   *
   * @param translater The translation to apply to the object
   */
  void translate(Translater translater);

}
