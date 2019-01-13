package facilitators;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;


/**
 * This class describes the appearance of a shape. It consists out of an {@link RGBColour} and a value for the extent of the opacity.
 *
 * @see RGBColour
 * @see shapes.Shape
 */
public class Colour implements Serializable {

  private RGBColour rgbColour;
  private float opacity;

  /**
   * Creates a Colour out of {@link RGBColour} and opacity.
   * @param rgbColour The {@link RGBColour}
   * @param opacity The Opacity in {@link Float}
   */
  @JsonCreator
  public Colour(
      @JsonProperty("rgbColour") RGBColour rgbColour,
      @JsonProperty("opacity") float opacity
  ) {
    this.rgbColour = rgbColour;
    this.opacity = opacity;
  }

  /**
   * This constructor gets called whenever a new shape is created. It expects a hex code for the color as well as a value for the opacity. and assigns the color to the shape.
   * @param hex a hex code which represents an {@link RGBColour}
   * @param opacity value of opacity in {@link Float}
   */
  public Colour(String hex, float opacity) {
    this.rgbColour = new RGBColour(hex);
    this.opacity = opacity;
  }

  /**
   * Returns an {@link RGBColour}. Gets called whenever the server sends back the canvas to the client.
   * @return returns an {@link RGBColour}
   */
  public RGBColour getRgbColour() {
    return rgbColour;
  }

  /**
   * Returns the opacity in {@link Float}. Gets called whenever the server sends back the canvas to the client.
   * @return returns a {@link Float} representing the extent of opacity
   */
  public float getOpacity() {
    return opacity;
  }

  /**
   * Lets the user change the color.
   * @param rgbColour the desired {@link RGBColour}
   */
  public void setRgbColour(RGBColour rgbColour) {
    this.rgbColour = rgbColour;
  }

  /**
   * Lets the user change the opacity.
   * @param opacity the desired opacity in {@link Float}
   */
  public void setOpacity(float opacity) {
    this.opacity = opacity;
  }

}
