package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import messages.modifiers.shapes.ShapeModifier;

/**
 * Request parameter object for POST {projectID}/modifyShape.
 *
 * @see main.input.RESTHandler
 */
public class RequestModifyShape {

  private final long elementID;
  @JsonUnwrapped
  private final ShapeModifier shapeModifier;

  /**
   * Creates a Request from the given parameters.
   *
   * @param elementID The ID of the element to modify.
   * @param shapeModifier A {@link ShapeModifier} object containing the parameters to modify.
   */
  @JsonCreator()
  public RequestModifyShape(
      @JsonProperty("elementID") long elementID,
      @JsonProperty("shape") ShapeModifier shapeModifier
  ) {
    this.elementID = elementID;
    this.shapeModifier = shapeModifier;
  }

  /**
   * Returns the ID of the element to modify.
   *
   * @return The ID of the element to modify.
   */
  public long getElementID() {
    return elementID;
  }

  /**
   * Returns the ShapeModifier object containing the parameters to modify.
   *
   * @return The ShapeModifier object containing the parameters to modify.
   */
  public ShapeModifier getShapeModifier() {
    return shapeModifier;
  }

}
