package facilitators;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class Colour implements Serializable {

  private RGBColour rgbColour;
  private float opacity;

  @JsonCreator
  public Colour(
      @JsonProperty("rgbColour") RGBColour rgbColour,
      @JsonProperty("opacity") float opacity
  ) {
    this.rgbColour = rgbColour;
    this.opacity = opacity;
  }

  public Colour(String hex, float opacity) {
    this.rgbColour = new RGBColour(hex);
    this.opacity = opacity;
  }

  public RGBColour getRgbColour() {
    return rgbColour;
  }

  public float getOpacity() {
    return opacity;
  }

  public void setRgbColour(RGBColour rgbColour) {
    this.rgbColour = rgbColour;
  }

  public void setOpacity(float opacity) {
    this.opacity = opacity;
  }

}
