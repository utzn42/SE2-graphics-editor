package shapes.transform;

import facilitators.Coordinate;

/**
 * Translation component of a {@link ShapeTransformer}
 */
public class Translater {

  private Coordinate translation;

  /**
   * Returns the translation as a {@link Coordinate}
   *
   * @return The translation as a {@link Coordinate}
   */
  public Coordinate getTranslation() {
    return translation;
  }

  /**
   * Sets the translation using a {@link Coordinate}
   *
   * @param translation The new translation
   */
  public void setTranslation(Coordinate translation) {
    this.translation = translation;
  }

  /**
   * Sets the translation using individual values for x and y
   *
   * @param x The new translation in x-direction
   * @param y The new translation in y-direction
   */
  public void setTranslation(double x, double y) {
    this.translation = new Coordinate(x, y);
  }

}
