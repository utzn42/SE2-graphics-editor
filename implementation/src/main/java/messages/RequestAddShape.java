package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import shapes.ShapeType;

/**
 * Request parameter object for POST {projectID}/addShape.
 *
 * @see main.input.RESTHandler
 */
public class RequestAddShape {

  private final ShapeType shapeType;
  private final Optional<Long> placeBeforeElementID;
  private final Optional<Long> placeIntoElementID;

  /**
   * Creates a Request from the given parameters.
   *
   * @param shapeType The type of shape to add.
   * @param placeBeforeElementID (Optional) The ID of the element before which to place the new shape.
   * @param placeIntoElementID (Optional) The ID of the element into which to place the new shape. Takes precedence over placeBeforeElementID.
   */
  @JsonCreator
  RequestAddShape(
      @JsonProperty("shapeType") ShapeType shapeType,
      @JsonProperty("placeBeforeElementID") Optional<Long> placeBeforeElementID,
      @JsonProperty("placeIntoElementID") Optional<Long> placeIntoElementID
  ) {
    this.shapeType = shapeType;
    this.placeBeforeElementID = placeBeforeElementID;
    this.placeIntoElementID = placeIntoElementID;
  }

  /**
   * Returns the type of shape to add.
   *
   * @return The type of shape to add.
   */
  public ShapeType getShapeType() {
    return shapeType;
  }

  /**
   * (Optional) Returns the ID of the element before which to place the new shape.
   *
   * @return (Optional) The ID of the element before which to place the new shape.
   */
  public Optional<Long> getPlaceBeforeElementID() {
    return placeBeforeElementID;
  }

  /**
   * (Optional) Returns the ID of the element into which to place the new shape.
   *
   * @return (Optional) The ID of the element into which to place the new shape.
   */
  public Optional<Long> getPlaceIntoElementID() {
    return placeIntoElementID;
  }

}
