package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestDeleteShape {

  private final int layerIndex;
  private final int shapeIndex;

  @JsonCreator
  public RequestDeleteShape(
      @JsonProperty("layerIndex") int layerIndex,
      @JsonProperty("shapeIndex") int shapeIndex
  ) {
    this.layerIndex = layerIndex;
    this.shapeIndex = shapeIndex;
  }

  public int getLayerIndex() {
    return this.layerIndex;
  }

  public int getShapeIndex() {
    return this.shapeIndex;
  }

}
