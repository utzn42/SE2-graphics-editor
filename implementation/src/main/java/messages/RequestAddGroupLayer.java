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
  private final Optional<List<Long>> groupElementIDs;

  @JsonCreator()
  RequestAddGroupLayer(
      @JsonProperty("placeBeforeElementID") Optional<Long> placeBeforeElementID,
      @JsonProperty("groupElementIDs") Optional<List<Long>> groupElementIDs)
  {
    this.placeBeforeElementID = placeBeforeElementID;
    this.groupElementIDs = groupElementIDs;
  }

  public Optional<Long> getPlaceBeforeElementID() {
    return placeBeforeElementID;
  }

  public Optional<List<Long>> getGroupElementIDs() {
    return groupElementIDs;
  }

}
