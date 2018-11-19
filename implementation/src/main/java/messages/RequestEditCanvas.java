package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is used to store data of the request from the client if editCanvas in {@link main.input.RESTHandler} gets called and the user wants to edit the {@link canvas.Canvas}.
 * It is necessary to access the data of the JSON object/body which the server receives from the client.
 *
 * @see main.input.RESTHandler
 * @see main.input.ProjectService
 */
public class RequestEditCanvas {

  private final double width;
  private final double height;

  /**
   * The constructor assigns the JSON Property to the class attributes width and height, which indicate the new desired values of the canvas.
   * @param width width in {@link Double}
   * @param height height in {@link Double}
   */
  @JsonCreator()
  public RequestEditCanvas(
      @JsonProperty("width") double width,
      @JsonProperty("height") double height
  ) {
    this.width = width;
    this.height = height;
  }

  /**
   * Returns the new desired width of the canvas. Gets called when editCanvas in {@link main.input.ProjectService} gets called.
   * @return returns the width in {@link Double}
   */
  public double getWidth() {
    return width;
  }

  /**
   * Returns the new desired height of the canvas. Gets called when editCanvas in {@link main.input.ProjectService} gets called.
   * @return returns the height in {@link Double}
   */
  public double getHeight() {
    return height;
  }

}
