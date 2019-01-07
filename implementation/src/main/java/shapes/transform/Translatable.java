package shapes.transform;

/**
 * Specifies a class that can be transformed using a {@link TranslationTransformation}.
 */
public interface Translatable {

  /**
   * Translates the object using a {@link TranslationTransformation}.
   *
   * @param translationTransformation The transformation to apply to the object.
   */
  void translate(TranslationTransformation translationTransformation);

}
