package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is used to store data of the request from the client if editLayer in {@link main.input.RESTHandler} gets called and the user wants to toggle the visibility.
 * It is necessary to access the data of the JSON object/body which the server receives from the client.
 *
 * @see main.input.RESTHandler
 * @see main.input.ProjectService
 */
public class RequestEditLayer {

  private final int layerIndex;
  private final boolean visible;

  /**
   * The constructor assigns the JSON Property to the class attributes layerIndex and visible, which indicates which layer should get set visible or invisible.
   * @param layerIndex index of the desired {@link canvas.Layer} in {@link Integer}
   * @param visible True or False in {@link Boolean}
   */
  @JsonCreator
  RequestEditLayer(
      @JsonProperty("layerIndex") int layerIndex,
      @JsonProperty("visible") boolean visible
  ) {
    this.layerIndex = layerIndex;
    this.visible = visible;
  }

  /**
   * Returns the index of the layer which should get visible or invisible. Gets called when the editLayer method in {@link main.input.ProjectService} gets called.
   * @return returns the layerIndex in {@link Integer}
   */
  public int getLayerIndex() {
    return layerIndex;
  }

  /**
   * Returns True or False depending if the user wants to set visible or invisible.
   * @return returns True or False in {@link Boolean}
   */
  public boolean isVisible() {
    return visible;
  }

}
