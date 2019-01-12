package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;

/**
 * This class is used to store data of the request from the client if editCanvas in {@link main.input.RESTHandler} gets called and the user wants to edit the {@link canvas.Canvas}.
 * It is necessary to access the data of the JSON object/body which the server receives from the client.
 *
 * @see main.input.RESTHandler
 * @see main.input.ProjectService
 */
public class RequestEditCanvas {

  private final Optional<Double> width;
  private final Optional<Double> height;
  private final Optional<Boolean> allowTransformAttribute;

  /**
   * The constructor assigns the JSON Property to the class attributes width and height, which indicate the new desired values of the canvas.
   * Also allows setting whether to allow the HTML "transform" attribute.
   *
   * @param width Optional width in {@link Double}.
   * @param height Optional height in {@link Double}.
   * @param allowTransformAttribute Optional boolean value that determines whether the "transform"
   * attribute should be allowed or not.
   * @see Optional
   */
  @JsonCreator()
  public RequestEditCanvas(
      @JsonProperty("width") Optional<Double> width,
      @JsonProperty("height") Optional<Double> height,
      @JsonProperty("allowTransformAttribute") Optional<Boolean> allowTransformAttribute
  ) {
    this.width = width;
    this.height = height;
    this.allowTransformAttribute = allowTransformAttribute;
  }

  /**
   * Returns an {@link Optional} field containing the new desired width of the canvas if set.
   * Gets called when editCanvas in {@link main.input.ProjectService} gets called.
   * @return Returns an {@link Optional} object containing the width in {@link Double} if set.
   */
  public Optional<Double> getWidth() {
    return width;
  }

  /**
   * Returns an {@link Optional} field containing the new desired height of the canvas if set.
   * Gets called when editCanvas in {@link main.input.ProjectService} gets called.
   * @return Returns an {@link Optional} object containing the height in {@link Double} if set.
   */
  public Optional<Double> getHeight() {
    return height;
  }

  /**
   * Returns an {@link Optional} field that, if set, contains the value <code>true</code> if the
   * HTML "transform" attribute should be allowed.
   * @return An {@link Optional} field that, if set, contains the value <code>true</code> if the
   * HTML "transform" attribute should be allowed, <code>false</code> otherwise.
   */
  public Optional<Boolean> doesAllowTransformAttribute() {
    return allowTransformAttribute;
  }

}
