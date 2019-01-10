package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import shapes.ShapeType;

/**
 * This class is used to store data of the request from the client if addShape in {@link main.input.RESTHandler} gets called and the user wants to add a {@link shapes.Shape}.
 * It is necessary to access the data of the JSON object/body which the server receives from the client.
 *
 * @see main.input.RESTHandler
 * @see main.input.ProjectService
 */
public class RequestAddShape {

  //TODO: Add a parameter for the new shape's position
  private final int layerIndex;
  private final ShapeType shapeType;

  /**
   * The constructor assigns the properties of the JSON object to the class attributes. The constructor gets called when the addShape method from the {@link main.input.RESTHandler} gets called.
   * @param layerIndex the index of the layer in {@link Integer} where the shape gets added
   * @param shapeType the type of shape which should get added
   */
  @JsonCreator
  RequestAddShape(
      @JsonProperty("layerIndex") int layerIndex,
      @JsonProperty("shapeType") ShapeType shapeType
  ) {
    this.layerIndex = layerIndex;
    this.shapeType = shapeType;
  }

  /**
   * Returns the index of the layer where the new shape should get added. Gets called when the addShape method in {@link main.input.ProjectService} gets called.
   * @return returns the layerIndex in {@link Integer}
   */
  public int getLayerIndex() {
    return layerIndex;
  }

  /**
   * Returns the desired shape type. Gets called when the addShape method in {@link main.input.ProjectService} gets called.
   * @return returns the shape type.
   */
  public ShapeType getShapeType() {
    return shapeType;
  }
}
