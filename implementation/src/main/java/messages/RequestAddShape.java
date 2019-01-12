package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import shapes.ShapeType;

/**
 * This class is used to store data of the request from the client if addShape in {@link main.input.RESTHandler} gets called and the user wants to add a {@link shapes.Shape}.
 * It is necessary to access the data of the JSON object/body which the server receives from the client.
 *
 * @see main.input.RESTHandler
 * @see main.input.ProjectService
 */
public class RequestAddShape {

  private final ShapeType shapeType;
  private final Optional<Long> insertAfterID;

  /**
   * The constructor assigns the properties of the JSON object to the class attributes. The constructor gets called when the addShape method from the {@link main.input.RESTHandler} gets called.
   * @param insertAfterID ({@link Optional}) The ID of the element after which to insert the new Shape.
   * @param shapeType The type of shape which should get added.
   */
  @JsonCreator
  RequestAddShape(
      @JsonProperty("shapeType") ShapeType shapeType,
      @JsonProperty("insertAfterID") Optional<Long> insertAfterID
  ) {
    this.shapeType = shapeType;
    this.insertAfterID = insertAfterID;
  }

  /**
   * Returns the desired shape type. Gets called when the addShape method in {@link main.input.ProjectService} gets called.
   * @return returns the shape type.
   */
  public ShapeType getShapeType() {
    return shapeType;
  }

  /**
   * Returns an {@link Optional} containing the ID of the element after which to insert the new Shape, if present.
   * Gets called when the addShape method in {@link main.input.ProjectService} gets called.
   *
   * @return An {@link Optional} containing the ID of the element after which to insert the new Shape, if present.
   */
  public Optional<Long> getInsertAfterID() {
    return insertAfterID;
  }

}
