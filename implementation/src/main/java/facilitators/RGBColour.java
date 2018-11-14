package facilitators;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class RGBColour {

  private final byte red;
  private final byte green;
  private final byte blue;

  public RGBColour(byte red, byte green, byte blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  @JsonCreator()
  public RGBColour(String hex) {
    hex = hex.replace("#", "");
    this.red = (byte) Integer.parseInt(hex.substring(0, 2), 16);
    this.green = (byte) Integer.parseInt(hex.substring(2, 4), 16);
    this.blue = (byte) Integer.parseInt(hex.substring(4, 6), 16);
  }

  public byte getRed() {
    return red;
  }

  public byte getGreen() {
    return green;
  }

  public byte getBlue() {
    return blue;
  }

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
