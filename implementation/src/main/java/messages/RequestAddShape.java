package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestAddShape {

  private final int layerIndex;
  private final String shapeClass;

  @JsonCreator
  RequestAddShape(
      @JsonProperty("layerIndex") int layerIndex,
      @JsonProperty("shapeClass") String shapeClass
  ) {
    this.layerIndex = layerIndex;
    this.shapeClass = shapeClass;
  }

  public int getLayerIndex() {
    return layerIndex;
  }

  public String getShapeClass() {
    return shapeClass;
  }
}
