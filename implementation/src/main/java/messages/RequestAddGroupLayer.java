package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;

/**
 * Request parameter object for POST {projectID}/addGroupLayer
 *
 * @see main.input.RESTHandler
 */
public class RequestAddGroupLayer {

  private final Optional<Long> placeBeforeElementID;
  private final Optional<Long> placeIntoElementID;

  @JsonCreator()
  RequestAddGroupLayer(
      @JsonProperty("placeBeforeElementID") Optional<Long> placeBeforeElementID,
      @JsonProperty("placeIntoElementID") Optional<Long> placeIntoElementID)
  {
    this.placeBeforeElementID = placeBeforeElementID;
    this.placeIntoElementID = placeIntoElementID;
  }

  public Optional<Long> getPlaceBeforeElementID() {
    return placeBeforeElementID;
  }

  public Optional<Long> getPlaceIntoElementID() {
    return placeIntoElementID;
  }

}
