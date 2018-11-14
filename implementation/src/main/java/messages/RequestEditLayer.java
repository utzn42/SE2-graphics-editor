package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestEditLayer {

  private final int layerIndex;
  private final boolean visible;

  @JsonCreator
  RequestEditLayer(
      @JsonProperty("layerIndex") int layerIndex,
      @JsonProperty("visible") boolean visible
  ) {
    this.layerIndex = layerIndex;
    this.visible = visible;
  }

  public int getLayerIndex() {
    return layerIndex;
  }

  public boolean isVisible() {
    return visible;
  }

}
