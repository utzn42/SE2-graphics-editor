package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is used to store data of the request from the client if editElement in {@link main.input.RESTHandler} gets called and the user wants to toggle the visibility.
 * It is necessary to access the data of the JSON object/body which the server receives from the client.
 *
 * @see main.input.RESTHandler
 * @see main.input.ProjectService
 */
public class RequestEditElement {

  private final long elementID;
  private final boolean visible;

  /**
   * The constructor assigns the JSON Property to the class attributes elementID and visible, which indicates which element should get set visible or invisible.
   *
   * @param elementID The ID of the element to edit.
   * @param visible <code>true</code> if Element should be visible, <code>false</code> otherwise.
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
   * Returns <code>true</code> if Element should be visible.
   *
   * @return <code>true</code> if Element should be visible, <code>false</code> otherwise.
   */
  public boolean isVisible() {
    return visible;
  }

}
