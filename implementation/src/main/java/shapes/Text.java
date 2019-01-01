package shapes;

import facilitators.Coordinate;
import shapes.transform.ShapeTransformer;
import shapes.transform.Translatable;
import shapes.transform.Translater;

/**
 * Represents a Text element on the canvas.
 * This class extends {@link Shape} by a center {@link Coordinate}, display text, font and font size.
 *
 * @see Shape
 */
public class Text extends Shape implements Translatable {

  private Coordinate center;
  private String displayText;
  private String font;
  private double fontSize;

  /**
   * Creates a new Text element with default attributes.
   */
  public Text() {
    center = new Coordinate(50, 50);
    displayText = "Hello World!";
    font = "Arial";
    fontSize = 12;
    setFillColour("#000000", 1);
    setStrokeWidth(0);
  }

  /**
   * Creates a new Text element with the specified attributes and default {@link Shape} attributes.
   *
   * @param center The center {@link Coordinate} of the Text element.
   * @param displayText The display text of the Text element.
   * @param font The font of the Text element.
   * @param fontSize The font size of the Text element.
   */
  public Text(Coordinate center, String displayText, String font, double fontSize) {
    this.center = center;
    this.displayText = displayText;
    this.font = font;
    this.fontSize = fontSize;
    setFillColour("#000000", 1);
    setStrokeWidth(0);
  }

  /**
   * Returns the center {@link Coordinate} of the Text element.
   *
   * @return The center {@link Coordinate} of the Text element.
   */
  public Coordinate getCenter() {
    return center;
  }

  /**
   * Sets the center {@link Coordinate} of the Text element.
   *
   * @param center The new center {@link Coordinate} for the Text element.
   */
  public void setCenter(Coordinate center) {
    this.center = center;
  }

  /**
   * Returns the display text of the Text element.
   *
   * @return The display text of the Text element.
   */
  public String getDisplayText() {
    return displayText;
  }

  /**
   * Sets the display text of the Text element.
   *
   * @param displayText The new display text for the Text element.
   */
  public void setDisplayText(String displayText) {
    this.displayText = displayText;
  }

  /**
   * Returns the font of the Text element.
   *
   * @return The font of the Text element.
   */
  public String getFont() {
    return font;
  }

  /**
   * Sets the font of the Text element.
   *
   * @param font The new font for the Text element.
   */
  public void setFont(String font) {
    this.font = font;
  }

  /**
   * Returns the font size of the Text element.
   *
   * @return The font size of the Text element.
   */
  public double getFontSize() {
    return fontSize;
  }

  /**
   * Sets the font size of the Text element.
   *
   * @param fontSize The new font size for the Text element.
   */
  public void setFontSize(double fontSize) {
    this.fontSize = fontSize;
  }

  /**
   * Applies a transformation, given as a {@link ShapeTransformer}, to the Text element.
   * The Text class is non-transformable, so the following transformations will cause an error:
   * rotate, scale, skew
   *
   * @param transformer The transformation to apply to the Text element.
   */
  @Override
  public void applyTransformation(ShapeTransformer transformer) {
    if (transformer.getRotation() != null ||
        transformer.getScale() != null ||
        transformer.getSkew() != null) {
      throw new IllegalArgumentException("Cannot add transform attribute to non-transformable Shape!");
    }
    if (transformer.getTranslation() != null) {
      translate(transformer.getTranslation());
    }
  }

  /**
   * Returns a String representation of the Text element's attributes as chained HTML attributes.
   *
   * @return A String representation of the Text element's attributes as chained HTML attributes.
   *
   * @see Shape#getHTMLAttributes()
   */
  @Override
  protected String getHTMLAttributes() {
    return super.getHTMLAttributes() +
        " x=\"" + center.getX() + "\"" +
        " y=\"" + center.getY() + "\"" +
        " font-family=\"" + font + "\"" +
        " font-size=\"" + fontSize + "\"" +
        " text-anchor=\"middle\" alignment-baseline=\"middle\"";
  }

  /**
   * Returns a String representation of the Text element as an SVG object in HTML.
   *
   * @return A String representation of the Text element as an SVG object in HTML.
   */
  @Override
  public String getHTML() {
    return "<text " + getHTMLAttributes() + ">" + displayText + "</text>";
  }

  /**
   * Translates the Text element using a {@link Translater}.
   *
   * @param translater The translation to apply to the Text element.
   */
  @Override
  public void translate(Translater translater) {
    double newX = center.getX() + translater.getTranslation().getX();
    double newY = center.getY() + translater.getTranslation().getY();

    center = new Coordinate(newX, newY);
  }
}
