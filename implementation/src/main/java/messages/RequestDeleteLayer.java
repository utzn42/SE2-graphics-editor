package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is used to store data of the request from the client if deleteLayer in {@link main.input.RESTHandler} gets called and the user wants to delete a {@link canvas.Layer}.
 * It is necessary to access the data of the JSON object/body which the server receives from the client.
 *
 * @see main.input.RESTHandler
 * @see main.input.ProjectService
 */
public class RequestDeleteLayer {

  private final int layerIndex;

  /**
   * The constructor assigns the JSON Property to the class attribute layerIndex, which indicates the layer which should get deleted.
   * @param layerIndex the index of the layer which should get deleted in {@link Integer}
   */
  @JsonCreator
  public RequestDeleteLayer(
      @JsonProperty("layerIndex") int layerIndex
  ) {
    this.layerIndex = layerIndex;
  }

  /**
   * Returns the index of the layer which should get deleted. Gets called when the deleteLayer method in {@link main.input.ProjectService} gets called.
   * @return returns the layerIndex in {@link Integer}
   */
  public int getLayerIndex() {
    return this.layerIndex;
  }

}
