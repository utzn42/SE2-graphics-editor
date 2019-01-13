package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is used to store data of the request from the client if moveElement in {@link main.input.RESTHandler} gets called.
 * It is necessary to access the data of the JSON object/body which the server receives from the client.
 *
 * @see main.input.RESTHandler
 * @see main.input.ProjectService
 */
public class RequestMoveElement {

  private final long elementID;

  /**
   * The constructor assigns the JSON Property to the class attribute elementID, which indicates the element which should get moved.
   *
   * @param elementID The ID of the element to be moved.
   */
  @JsonCreator
  public RequestMoveElement(
      @JsonProperty("elementID") int elementID
  ) {
    this.elementID = elementID;
  }

  /**
   * Returns the ID of the element which should get deleted. Gets called when the moveElement method in {@link main.input.ProjectService} gets called.
   *
   * @return returns the element ID
   */
  public long getElementID() {
    return this.elementID;
  }

}
