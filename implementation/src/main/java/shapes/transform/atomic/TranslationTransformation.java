package shapes.transform.atomic;

import facilitators.Coordinate;
import java.io.Serializable;
import shapes.transform.Transformation;

/**
 * Translation component of a {@link Transformation}.
 * Holds values for moving an object in x- and y-direction.
 */
public class TranslationTransformation implements AtomicTransformation, Serializable {

  private static final long serialVersionUID = 1L;

  private Coordinate translation;
  private static final AtomicTransformationType type = AtomicTransformationType.TRANSLATION;

  /**
   * Creates a TranslationTransformation given values for x- and y-translation as a {@link Coordinate}.
   *
   * @param translation The values by which to translate.
   */
  public TranslationTransformation(Coordinate translation) {
    this.translation = translation;
  }

  /**
   * Creates a TranslationTransformation given values for x- and y-translation.
   *
   * @param translationX The value by which to translate in x-direction.
   * @param translationY The value by which to translate in y-direction.
   */
  public TranslationTransformation(double translationX, double translationY) {
    this.translation = new Coordinate(translationX, translationY);
  }

  /**
   * Returns the translation as a {@link Coordinate}.
   *
   * @return The translation as a {@link Coordinate}.
   */
  public Coordinate getTranslation() {
    return translation;
  }

  /**
   * Sets the translation using a {@link Coordinate}.
   *
   * @param translation The new translation.
   */
  public void setTranslation(Coordinate translation) {
    this.translation = translation;
  }

  /**
   * Sets the translation using individual values for x and y.
   *
   * @param x The new translation in x-direction.
   * @param y The new translation in y-direction.
   */
  public void setTranslation(double x, double y) {
    this.translation = new Coordinate(x, y);
  }

  @Override
  public AtomicTransformationType getType() {
    return type;
  }

  @Override
  public String getHTMLAttribute() {
    return "translate(" + translation.getX() + " " + translation.getY() + ")";
  }

}
