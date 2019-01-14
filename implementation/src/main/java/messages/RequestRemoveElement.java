package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request parameter object for POST {projectID}/removeElement.
 *
 * @see main.input.RESTHandler
 */
public class RequestRemoveElement {

  private final long elementID;

  /**
   * Creates a Request from the given parameters.
   *
   * @param elementID The ID of the element to be removed.
   */
  @JsonCreator
  public RequestRemoveElement(
      @JsonProperty("elementID") int elementID
  ) {
    this.elementID = elementID;
  }

  /**
   * Returns the ID of the element to be removed.
   *
   * @return The ID of the element to be removed.
   */
  public long getElementID() {
    return this.elementID;
  }

}
