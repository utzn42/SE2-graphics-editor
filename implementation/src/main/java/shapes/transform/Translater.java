package shapes.transform;

import facilitators.Coordinate;

/**
 * Translation component of a {@link ShapeTransformer}.
 * Holds values for moving an object in x- and y-direction.
 */
public class Translater {

  private Coordinate translation;

  /**
   * Creates a Translater given values for x- and y-translation as a {@link Coordinate}.
   *
   * @param translation The values by which to translate.
   */
  public Translater(Coordinate translation) {
    this.translation = translation;
  }

  /**
   * Creates a Translater given values for x- and y-translation.
   *
   * @param translationX The value by which to translate in x-direction.
   * @param translationY The value by which to translate in y-direction.
   */
  public Translater(double translationX, double translationY) {
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

}
