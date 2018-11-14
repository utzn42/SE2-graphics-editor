package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import shapes.Shape;

public class RequestEditShape {

  private final int layerIndex;
  private final int shapeIndex;
  private Shape shape;

  @JsonCreator()
  public RequestEditShape(
      @JsonProperty("layerIndex") int layerIndex,
      @JsonProperty("shapeIndex") int shapeIndex,
      @JsonProperty("shape") Shape shape
  ) {
    this.layerIndex = layerIndex;
    this.shapeIndex = shapeIndex;
    this.shape = shape;
  }

  public void setShape(Shape shape) {
    this.shape = shape;
  }

  public int getLayerIndex() {
    return layerIndex;
  }

  public int getShapeIndex() {
    return shapeIndex;
  }

  public Shape getShape() {
    return shape;
  }
}
