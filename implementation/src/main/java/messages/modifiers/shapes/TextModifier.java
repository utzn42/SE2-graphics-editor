package messages.modifiers.shapes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import messages.modifiers.facilitators.ColourModifier;
import messages.modifiers.facilitators.CoordinateModifier;
import shapes.Shape;
import shapes.ShapeDecorator;
import shapes.Text;

/**
 * Holds all parameters that may be changed for a {@link shapes.Text}.
 */
public class TextModifier extends ShapeModifier {

  private final Optional<CoordinateModifier> center;
  private final Optional<String> displayText;
  private final Optional<String> font;
  private final Optional<Double> fontSize;

  /**
   * Creates a Text object with the given values.
   *
   * @param fillColour (Optional) The fill colour of the Text.
   * @param strokeColour (Optional) The stroke colour of the Text.
   * @param strokeWidth (Optional) The stroke width of the Text.
   * @param opacity (Optional) The opacity of the Text. Range: 0-1.
   * @param center (Optional) The center of the Text.
   * @param displayText (Optional) The display text.
   * @param font (Optional) The font of the Text.
   * @param fontSize (Optional) The font size of the Text.
   */
  @JsonCreator
  public TextModifier(
      @JsonProperty("fillColour") Optional<ColourModifier> fillColour,
      @JsonProperty("strokeColour") Optional<ColourModifier> strokeColour,
      @JsonProperty("strokeWidth") Optional<Double> strokeWidth,
      @JsonProperty("opacity") Optional<Double> opacity,
      @JsonProperty("center") Optional<CoordinateModifier> center,
      @JsonProperty("displayText") Optional<String> displayText,
      @JsonProperty("font") Optional<String> font,
      @JsonProperty("fontSize") Optional<Double> fontSize
  ) {
    super(fillColour, strokeColour, strokeWidth, opacity);
    this.center = center;
    this.displayText = displayText;
    this.font = font;
    this.fontSize = fontSize;
  }

  /**
   * (Optional) Returns the center of the Text.
   *
   * @return (Optional) The center of the Text.
   */
  public Optional<CoordinateModifier> getCenter() {
    return center;
  }

  /**
   * (Optional) Returns the display text.
   *
   * @return (Optional) The display text.
   */
  public Optional<String> getDisplayText() {
    return displayText;
  }

  /**
   * (Optional) Returns the font of the Text.
   *
   * @return (Optional) The font of the Text.
   */
  public Optional<String> getFont() {
    return font;
  }

  /**
   * (Optional) Returns the font size of the Text.
   *
   * @return (Optional) The font size of the Text.
   */
  public Optional<Double> getFontSize() {
    return fontSize;
  }

  @Override
  public Shape apply(Shape objectToModify) {

    objectToModify = super.apply(objectToModify);

    Shape unpackedObject = objectToModify;
    while (unpackedObject instanceof ShapeDecorator) {
      unpackedObject = ((ShapeDecorator) unpackedObject).getShape();
    }

    if (unpackedObject instanceof Text) {
      Text text = (Text) unpackedObject;
      center.ifPresent(newCenter -> text.setCenter(newCenter.apply(text.getCenter())));
      displayText.ifPresent(text::setDisplayText);
      font.ifPresent(text::setFont);
      fontSize.ifPresent(text::setFontSize);
    }

    return objectToModify;
  }
}
