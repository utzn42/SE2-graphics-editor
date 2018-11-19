package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is used to store data of the request from the client if deleteShape in {@link main.input.RESTHandler} gets called and the user wants to delete a {@link shapes.Shape}.
 * It is necessary to access the data of the JSON object/body which the server receives from the client.
 *
 * @see main.input.RESTHandler
 * @see main.input.ProjectService
 */
public class RequestDeleteShape {

  private final int layerIndex;
  private final int shapeIndex;

  /**
   * The constructor assigns the JSON Property to the class attributes layerIndex and shapeIndex, which indicates the shape which should get deleted.
   * @param layerIndex the index of the layer where the shape is located in {@link Integer}
   * @param shapeIndex the index of the shape which should get deleted in {@link Integer}
   */
  @JsonCreator
  public RequestDeleteShape(
      @JsonProperty("layerIndex") int layerIndex,
      @JsonProperty("shapeIndex") int shapeIndex
  ) {
    this.layerIndex = layerIndex;
    this.shapeIndex = shapeIndex;
  }

  /**
   * Returns the index of the layer where the desired shape is located. Gets called when the deleteShape method in {@link main.input.ProjectService} gets called.
   * @return returns the layerIndex in {@link Integer}
   */
  public int getLayerIndex() {
    return this.layerIndex;
  }

  /**
   * Returns the index of the shape which should get deleted. Gets called when the deleteShape method in {@link main.input.ProjectService} gets called.
   * @return returns the shapeIndex in {@link Integer}
   */
  public int getShapeIndex() {
    return this.shapeIndex;
  }

}
