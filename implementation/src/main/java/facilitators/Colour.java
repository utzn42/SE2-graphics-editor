package facilitators;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Colour {

  private RGBColour rgbColour;
  private boolean transparent;

  @JsonCreator
  public Colour(
      @JsonProperty("rgbColour") RGBColour rgbColour,
      @JsonProperty("transparent") boolean transparent
  ) {
    this.rgbColour = rgbColour;
    this.transparent = transparent;
  }

  public Colour(String colour) {
    if (colour.replaceAll(" ", "").toLowerCase().equals("transparent")) {
      this.rgbColour = new RGBColour("#ffffff");
      this.transparent = true;
    } else {
      this.rgbColour = new RGBColour(colour);
      this.transparent = false;
    }
  }

  public RGBColour getRgbColour() {
    return rgbColour;
  }

  public boolean isTransparent() {
    return transparent;
  }

  public void setRgbColour(RGBColour rgbColour) {
    this.rgbColour = rgbColour;
  }

  public void setTransparent(boolean transparent) {
    this.transparent = transparent;
  }

  @Override
  public String toString() {
    if (transparent) {
      return "transparent";
    } else {
      return rgbColour.toString();
    }
  }
}
