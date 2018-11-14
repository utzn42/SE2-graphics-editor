package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestDeleteLayer {

  private final int layerIndex;

  @JsonCreator
  public RequestDeleteLayer(
      @JsonProperty("layerIndex") int layerIndex
  ) {
    this.layerIndex = layerIndex;
  }

  public int getLayerIndex() {
    return this.layerIndex;
  }

}
