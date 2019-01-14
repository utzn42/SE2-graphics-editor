package facilitators;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.io.Serializable;

/**
 * Represents a Colour in form of the RGB Code. Every shape has a {@link Colour} which consists out of RGBColour and opacity.
 *
 * @see shapes.Shape
 * @see Colour
 */
public class RGBColour implements Serializable {

  private static final long serialVersionUID = 1L;

  private final byte red;
  private final byte green;
  private final byte blue;

  /**
   * This constructor takes the 3 values in byte.
   * @param red value for red in {@link Byte}
   * @param green value for green in {@link Byte}
   * @param blue value for blue in {@link Byte}
   */
  public RGBColour(byte red, byte green, byte blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Constructor which expects RGB code in hexadecimal in {@link String} because HTML sends it in Hex.
   * @param hex RGBCode in hex in {@link String}
   */
  @JsonCreator()
  public RGBColour(String hex) {
    hex = hex.replace("#", "");
    this.red = (byte) Integer.parseInt(hex.substring(0, 2), 16);
    this.green = (byte) Integer.parseInt(hex.substring(2, 4), 16);
    this.blue = (byte) Integer.parseInt(hex.substring(4, 6), 16);
  }

  /**
   * Returns red in {@link Byte}.
   * @return returns red in {@link Byte}
   */
  public byte getRed() {
    return red;
  }

  /**
   * Returns green in {@link Byte}.
   * @return returns green in {@link Byte}
   */
  public byte getGreen() {
    return green;
  }

  /**
   * Returns blue in {@link Byte}.
   * @return returns blue in {@link Byte}
   */
  public byte getBlue() {
    return blue;
  }

  /**
   * Returns RGBColor in Hexadecimal in {@link String}.
   * @return returns a {@link String} with the hexadecimal RGB Code
   */
  @JsonValue
  public String getHexColour() {
    return "#" + String.format("%02X", red) + String.format("%02X", green) + String
        .format("%02X", blue);
  }

  @Override
  public String toString() {
    return getHexColour();
  }

}
