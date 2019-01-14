package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;

/**
 * Request parameter object for POST {projectID}/addLayerGroup.
 *
 * @see main.input.RESTHandler
 */
public class RequestAddLayerGroup {

  private final Optional<Long> placeBeforeElementID;
  private final Optional<Long> placeIntoElementID;

  /**
   * Creates a Request from the given parameters.
   *
   * @param placeBeforeElementID (Optional) The ID of the element before which to place the new layer group.
   * @param placeIntoElementID (Optional) The ID of the element into which to place the new layer group. Takes precedence over placeBeforeElementID.
   */
  @JsonCreator()
  RequestAddLayerGroup(
      @JsonProperty("placeBeforeElementID") Optional<Long> placeBeforeElementID,
      @JsonProperty("placeIntoElementID") Optional<Long> placeIntoElementID)
  {
    this.placeBeforeElementID = placeBeforeElementID;
    this.placeIntoElementID = placeIntoElementID;
  }

  /**
   * (Optional) Returns the ID of the element before which to place the new layer group.
   *
   * @return (Optional) The ID of the element before which to place the new layer group.
   */
  public Optional<Long> getPlaceBeforeElementID() {
    return placeBeforeElementID;
  }

  /**
   * (Optional) Returns the ID of the element into which to place the new layer group.
   *
   * @return (Optional) The ID of the element into which to place the new layer group.
   */
  public Optional<Long> getPlaceIntoElementID() {
    return placeIntoElementID;
  }

}
