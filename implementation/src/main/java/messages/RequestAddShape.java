package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is used to store data of the request from the client if addShape in {@link main.input.RESTHandler} gets called and the user wants to add a {@link shapes.Shape}.
 * It is necessary to access the data of the JSON object/body which the server receives from the client.
 *
 * @see main.input.RESTHandler
 * @see main.input.ProjectService
 */
public class RequestAddShape {

  private final int layerIndex;
  private final String shapeClass;

  /**
   * The constructor assigns the properties of the JSON object to the class attributes. The constructor gets called when the addShape method from the {@link main.input.RESTHandler} gets called.
   * @param layerIndex the index of the layer in {@link Integer} where the shape gets added
   * @param shapeClass the type of shape which should get added in {@link String}
   */
  @JsonCreator
  RequestAddShape(
      @JsonProperty("layerIndex") int layerIndex,
      @JsonProperty("shapeClass") String shapeClass
  ) {
    this.layerIndex = layerIndex;
    this.shapeClass = shapeClass;
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
   * @return returns the shape type in {@link String}
   */
  public String getShapeClass() {
    return shapeClass;
  }
}
