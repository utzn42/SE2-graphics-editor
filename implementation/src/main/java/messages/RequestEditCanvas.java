package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestEditCanvas {

  private final double width;
  private final double height;

  @JsonCreator()
  public RequestEditCanvas(
      @JsonProperty("width") double width,
      @JsonProperty("height") double height
  ) {
    this.width = width;
    this.height = height;
  }

  public double getWidth() {
    return width;
  }

  public double getHeight() {
    return height;
  }

}
