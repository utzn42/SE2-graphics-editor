package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import shapes.Shape;

/**
 * This class is used to store data of the request from the client if editShape in {@link main.input.RESTHandler} gets called and the user wants to change the Shape.
 * It is necessary to access the data of the JSON object/body which the server receives from the client.
 *
 * @see main.input.RESTHandler
 * @see main.input.ProjectService
 */
public class RequestEditShape {

  private final int layerIndex;
  private final int shapeIndex;
  private Shape shape;

  /**
   * The constructor assigns the JSON Property to the class attributes layerIndex and shapeIndex and {@link Shape}, which indicates which {@link Shape} should get changed on which {@link canvas.Layer} and with which parameters.
   * @param layerIndex index of the desired {@link canvas.Layer} in {@link Integer}
   * @param shapeIndex index of the desired {@link Shape} in {@link Integer}
   * @param shape a {@link Shape} object with all the desired parameters
   */
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

  /**
   * Changes the shape to a new shape.
   * @param shape a {@link Shape} object to which the shape should get set
   */
  public void setShape(Shape shape) {
    this.shape = shape;
  }

  /**
   * Returns the index of the layer where the shape should get changed. Gets called when the editShape method in {@link main.input.ProjectService} gets called.
   * @return returns the layerIndex in {@link Integer}
   */
  public int getLayerIndex() {
    return layerIndex;
  }

  /**
   * Returns the index of the shape which should get changed. Gets called when the editShape method in {@link main.input.ProjectService} gets called.
   * @return returns the shapeIndex in {@link Integer}
   */
  public int getShapeIndex() {
    return shapeIndex;
  }

  /**
   * Returns the {@link Shape} with the new parameters.
   * @return returns a {@link Shape} object with the new parameters
   */
  public Shape getShape() {
    return shape;
  }
}
