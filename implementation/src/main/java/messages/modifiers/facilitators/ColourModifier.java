package messages.modifiers.facilitators;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import facilitators.Colour;
import facilitators.RGBColour;
import java.util.Optional;
import messages.modifiers.Modifier;

/**
 * Holds all parameters that may be changed for a {@link facilitators.Colour}.
 */
public class ColourModifier implements Modifier<Colour> {

  private final Optional<String> hex;
  private final Optional<Integer> red;
  private final Optional<Integer> green;
  private final Optional<Integer> blue;
  private final Optional<Float> opacity;

  /**
   * Creates a Colour object with the given values.
   *
   * @param hex (Optional) Hex string of the new Colour value.
   * Should take precedence over individual RGB values if given.
   * @param red (Optional) The red value. Range: 0-255.
   * @param green (Optional) The green value. Range: 0-255.
   * @param blue (Optional) The blue value. Range: 0-255.
   * @param opacity (Optional) The opacity. Range: 0-1.
   */
  @JsonCreator
  public ColourModifier(
      @JsonProperty("hex") Optional<String> hex,
      @JsonProperty("red") Optional<Integer> red,
      @JsonProperty("green") Optional<Integer> green,
      @JsonProperty("blue") Optional<Integer> blue,
      @JsonProperty("opacity") Optional<Float> opacity
  ) {
    this.hex = hex;
    this.red = red.map(integer -> Math.min(Math.max(0, integer), 255));
    this.green = green.map(integer -> Math.min(Math.max(0, integer), 255));
    this.blue = blue.map(integer -> Math.min(Math.max(0, integer), 255));
    this.opacity = opacity;
  }

  /**
   * (Optional) Returns the hex string of the new Colour value.
   *
   * @return (Optional) The hex string of the new Colour value.
   */
  public Optional<String> getHex() {
    return hex;
  }

  /**
   * (Optional) Returns the red value. Range: 0-255.
   *
   * @return (Optional) The red value. Range: 0-255.
   */
  public Optional<Integer> getRed() {
    return red;
  }

  /**
   * (Optional) Returns the green value. Range: 0-255.
   *
   * @return (Optional) The green value. Range: 0-255.
   */
  public Optional<Integer> getGreen() {
    return green;
  }

  /**
   * (Optional) Returns the blue value. Range: 0-255.
   *
   * @return (Optional) The blue value. Range: 0-255.
   */
  public Optional<Integer> getBlue() {
    return blue;
  }

  /**
   * (Optional) Returns the opacity. Range: 0-1.
   *
   * @return (Optional) The opacity. Range: 0-1.
   */
  public Optional<Float> getOpacity() {
    return opacity;
  }

  @Override
  public Colour apply(Colour objectToModify) {
    getOpacity().ifPresent(objectToModify::setOpacity);
    if (getHex().isPresent()) {
      objectToModify.setRgbColour(new RGBColour(getHex().get()));
    } else {
      byte red = objectToModify.getRgbColour().getRed();
      if (getRed().isPresent()) {
        red = getRed().get().byteValue();
      }
      byte green = objectToModify.getRgbColour().getGreen();
      if (getGreen().isPresent()) {
        green = getGreen().get().byteValue();
      }
      byte blue = objectToModify.getRgbColour().getBlue();
      if (getBlue().isPresent()) {
        blue = getBlue().get().byteValue();
      }
      objectToModify.setRgbColour(new RGBColour(red, green, blue));
    }
    return objectToModify;
  }
}
