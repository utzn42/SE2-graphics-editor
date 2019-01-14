package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request parameter object for POST {projectID}/editElement.
 *
 * @see main.input.RESTHandler
 */
public class RequestEditElement {

  private final long elementID;
  private final boolean visible;

  /**
   * Creates a Request from the given parameters.
   *
   * @param elementID The ID of the element to edit.
   * @param visible <code>true</code> if the element should be set to visible, <code>false</code> if it should be set to invisible.
   */
  @JsonCreator
  RequestEditElement(
      @JsonProperty("elementID") long elementID,
      @JsonProperty("visible") boolean visible
  ) {
    this.elementID = elementID;
    this.visible = visible;
  }

  /**
   * Returns the ID of the element to edit.
   *
   * @return The ID of the element to edit.
   */
  public long getElementID() {
    return elementID;
  }

  /**
   * Returns <code>true</code> if the element should be set to visible.
   *
   * @return <code>true</code> if the element should be set to visible, <code>false</code> if it should be set to invisible.
   */
  public boolean isVisible() {
    return visible;
  }

}
